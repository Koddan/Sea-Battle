package app.controller;

import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;


public class MainMenuServlet extends HttpServlet {

    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        RequestDispatcher requestDispatcher;
        HttpSession session = req.getSession(false);

        if (session.getAttribute("condition") == "online") {
            requestDispatcher = req.getRequestDispatcher("views/mainMenu.jsp");
        }
        else {
            requestDispatcher = req.getRequestDispatcher("/LoginServlet");
        }

        requestDispatcher.include(req, resp);

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        HttpSession session = req.getSession(false);
        String name = (String)session.getAttribute("name");
        /*DataBaseInputOutput dataBase = new DataBaseInputOutput();
        dataBase.battleInitiate(name);

        List<List<Integer>> zeros = new ArrayList<>();

        for (int i = 0; i < 10; i++) {
            zeros.add(new ArrayList<>());
            for (int j = 0; j < 10; j++) {
                zeros.get(i).add(j);
            }
        }
        dataBase.update(name, zeros, zeros, zeros, zeros);

        List<List<Integer>> example = dataBase.getUserUser(name);
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                System.out.print(example.get(i).get(j) + " ");
            }
            System.out.println();
        }

        dataBase.closeBattle(name);*/

        if (session.getAttribute("condition") == "online") {
            System.out.println("inMenu");
            name = (String)session.getAttribute("name");
            req.setAttribute("userName", "name");
            req.setAttribute("flag", "true");
        }
        else {
            req.setAttribute("flag", "false");
        }

        doGet(req, resp);

    }

}