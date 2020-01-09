package app.controller;

import app.database.DataBaseInputOutput;
import app.model.SeaBattleBot;

import java.io.*;
import java.util.*;
import javax.servlet.*;
import javax.servlet.http.*;


public class DoBattleServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        System.out.println("in DoBattleServlet");
        RequestDispatcher requestDispatcher;
        DataBaseInputOutput dataBase = new DataBaseInputOutput();
        HttpSession session = req.getSession(false);
        String name = (String)session.getAttribute("name");

        if (session.getAttribute("initialise") != null) {

            if (session.getAttribute("create") == null) {

                String str = (String) session.getAttribute("userTable");
                List<List<Integer>> userTable = new ArrayList<>();
                int counter = 0;

                for (int i = 0; i < 10; i++) {
                    userTable.add(new ArrayList<>());
                    for (int j = 0; j < 10; j++) {
                        userTable.get(i).add(Integer.parseInt(str.substring(counter, counter + 1)));
                        counter++;
                    }
                }
                System.out.println(userTable);

                dataBase.battleInitiate(name, userTable);
                session.setAttribute("create", "yes");
            }
            //dataBase.closeBattle(name);
            List<List<Integer>> myTable = dataBase.getUserUser(name);
            List<List<Integer>> enemyTable = dataBase.getUserBot(name);

            req.setAttribute("myTable", myTable);
            req.setAttribute("enemyTable", enemyTable);

            SeaBattleBot bot = new SeaBattleBot(name);
            int winner = bot.isWin();
            System.out.println("ships in doGet = " + winner);
            if (winner == 5) {
                String end = "5";
                dataBase.closeBattle(name);
                session.removeAttribute("create");
                session.removeAttribute("initialise");
                req.setAttribute("endGame", end);
            } else if (winner == -5) {
                String end = "-5";
                req.setAttribute("endGame", end);
                dataBase.closeBattle(name);
                session.removeAttribute("create");
                session.removeAttribute("initialise");
            }

            if (session.getAttribute("condition") == "online") {
                requestDispatcher = req.getRequestDispatcher("views/doBattle.jsp");
            } else {
                requestDispatcher = req.getRequestDispatcher("/LoginServlet");
            }

        }
        else {
            requestDispatcher = req.getRequestDispatcher("views/createTable.jsp");
            session.setAttribute("initialise", "initialized");
        }
        requestDispatcher.include(req, resp);

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        HttpSession session = req.getSession(false);
        String name = (String) session.getAttribute("name");
        System.out.println("in DoBattleServlet");
        RequestDispatcher requestDispatcher;
        DataBaseInputOutput dataBase = new DataBaseInputOutput();

        if (req.getParameter("User_matrix") != null) {

            String str = req.getParameter("User_matrix");
            session.setAttribute("userTable", str);
            doGet(req, resp);
            requestDispatcher = req.getRequestDispatcher("views/doBattle.jsp");

        } else {

            if (req.getParameter("over") != null) {

                dataBase.closeBattle(name);
                requestDispatcher = req.getRequestDispatcher("/MainMenuServlet");
                session.removeAttribute("create");
                session.removeAttribute("initialise");

            } else if (session.getAttribute("condition") == "online") {

                boolean flag = true;
                SeaBattleBot bot = new SeaBattleBot(name);
                String coords = req.getParameter("fire1");
                System.out.println("fire1");
                int x = Integer.parseInt(coords.split(" ")[0]);
                int y = Integer.parseInt(coords.split(" ")[1]);
                int answer = bot.botAnswer(x, y);
                System.out.println("ships in doPost = " + answer);
                if (answer == 5) {
                    String end = "5";
                    req.setAttribute("endGame", end);
                    flag = false;

                    //requestDispatcher = req.getRequestDispatcher("/MainMenuServlet");
                } else if (answer == -5) {
                    String end = "-5";
                    req.setAttribute("endGame", end);
                    flag = false;
                    //requestDispatcher = req.getRequestDispatcher("/MainMenuServlet");
                }

                if (flag) {
                    List<List<Integer>> myTable = dataBase.getUserUser(name);
                    List<List<Integer>> enemyTable = dataBase.getUserBot(name);
                    req.setAttribute("myTable", myTable);
                    req.setAttribute("enemyTable", enemyTable);
                }

                requestDispatcher = req.getRequestDispatcher("views/doBattle.jsp");

            } else {
                requestDispatcher = req.getRequestDispatcher("/LoginServlet");
            }
        }
        requestDispatcher.include(req, resp);

    }

}