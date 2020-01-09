package app.servlets;

import app.controller.DataBaseInputOutput;
import app.entities.GameSession;

import java.io.*;
import java.sql.*;
import java.util.*;
import javax.faces.view.EditableValueHolderAttachedObjectTarget;
import javax.servlet.*;
import javax.servlet.http.*;

import static java.lang.Thread.sleep;


public class BattleServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA");
        DataBaseInputOutput dataBase = new DataBaseInputOutput();
        resp.setContentType("text/html");
        HttpSession session = req.getSession(false);
        RequestDispatcher requestDispatcher;

        if (session.getAttribute("worked") == null) {
            if (session.getAttribute("condition") == "online") {
                System.out.println("inBattle");
                req.setAttribute("userNames", dataBase.getUsersNames());
                req.setAttribute("flag", "true");
            } else {
                req.setAttribute("flag", "false");
            }

            if (req.getAttribute("flag") == "true") {
                requestDispatcher = req.getRequestDispatcher("views/initBattle.jsp");
            } else {
                requestDispatcher = req.getRequestDispatcher("/LoginServlet");
            }
            requestDispatcher.include(req, resp);
        }
        else {
            if (session.getAttribute("gameRole").equals("calling")) {

                List<List<Integer>> myTable = dataBase.getCallingSelf((String) session.getAttribute("callingName"), (String) session.getAttribute("replyingName"));
                List<List<Integer>> enemyTable = dataBase.getCallingEnemy((String) session.getAttribute("callingName"), (String) session.getAttribute("replyingName"));

                req.setAttribute("myTable", myTable);
                req.setAttribute("enemyTable", enemyTable);
            }
            else {
                List<List<Integer>> myTable = dataBase.getReplyingSelf((String) session.getAttribute("callingName"), (String) session.getAttribute("replyingName"));
                List<List<Integer>> enemyTable = dataBase.getReplyingEnemy((String) session.getAttribute("callingName"), (String) session.getAttribute("replyingName"));

                req.setAttribute("myTable", myTable);
                req.setAttribute("enemyTable", enemyTable);
            }

            if (session.getAttribute("gameRole").equals("calling")) {
                req.setAttribute("yourName", session.getAttribute("callingName"));
                req.setAttribute("enemyName", session.getAttribute("replyingName"));
                System.out.println((String) session.getAttribute("callingName") + session.getAttribute("replyingName"));

                if (dataBase.checkFlag((String) session.getAttribute("callingName"), (String) session.getAttribute("replyingName"), (String) session.getAttribute("gameRole")) == 5) {
                    req.setAttribute("canFire", "yes");
                }
                else {
                    req.setAttribute("canFire", "no");
                }
            }
            else {
                req.setAttribute("yourName", session.getAttribute("replyingName"));
                req.setAttribute("enemyName", session.getAttribute("callingName"));
                System.out.println((String) session.getAttribute("replyingName") + session.getAttribute("callingName"));

                if (dataBase.checkFlag((String) session.getAttribute("callingName"), (String) session.getAttribute("replyingName"), (String) session.getAttribute("gameRole")) == -5) {
                    req.setAttribute("canFire", "yes");
                }
                else {
                    req.setAttribute("canFire", "no");
                }
            }

            requestDispatcher = req.getRequestDispatcher("views/usersBattle.jsp");
            requestDispatcher.forward(req, resp);
        }

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        RequestDispatcher requestDispatcher;
        HttpSession session = req.getSession(false);
        resp.setContentType("text/html");
        DataBaseInputOutput dataBase = new DataBaseInputOutput();

        if (req.getParameter("menu") != null) {
            requestDispatcher = req.getRequestDispatcher("/MainMenuServlet");
            requestDispatcher.forward(req, resp);
            return;
        }

        if (session.getAttribute("endGame") == null) {
            if (req.getParameter("over") != null) {

                dataBase.changeFlag((String) session.getAttribute("callingName"), (String) session.getAttribute("replyingName"), (String) session.getAttribute("gameRole"), 9);
                requestDispatcher = req.getRequestDispatcher("/MainMenuServlet");
                session.removeAttribute("create1");
                //session.removeAttribute("initialise");
                session.removeAttribute("worked");
                //session.removeAttribute("callingName");
                //session.removeAttribute("replyingName");
                //session.removeAttribute("gameRole");
                //session.setAttribute("endGame", "yes");
                if (session.getAttribute("cantFire") != null) {
                    session.setAttribute("loop", "no");
                }

                String lose = "You lost in battle";
                req.setAttribute("lose", lose);
                requestDispatcher.forward(req, resp);

            } else {
                if (session.getAttribute("worked") == null) {
                    if (req.getParameter("User_matrix") == null) {

                        if (req.getParameter("replyingName") != null) {
                            //System.out.println("AFTER WAGABANGA");

                            session.setAttribute("gameRole", "calling");
                            String callingName = (String) session.getAttribute("name");
                            String replyingName = req.getParameter("replyingName");

                            System.out.println("names in calling = " + callingName + replyingName);

                            session.setAttribute("callingName", callingName);
                            session.setAttribute("replyingName", replyingName);
                            GameSession gameSession = new GameSession(callingName, replyingName);
                            gameSession.callAUser();

                            requestDispatcher = req.getRequestDispatcher("views/usersCreateTable.jsp");
                            requestDispatcher.include(req, resp);

                        }//для запрашивающего пользователя

                        if (req.getParameter("isGame") != null) {

                            session.setAttribute("gameRole", "replying");
                            String name = (String) session.getAttribute("name");
                            String dbName = "";
                            while (true) {
                                try {
                                    sleep(100);
                                } catch (InterruptedException e) {
                                    e.printStackTrace();
                                }
                                System.out.println("loop in replying");
                                dbName = dataBase.findUsersBattle(name);
                                if (!dbName.equals("")) {
                                    dataBase.replyingAnswerBattle(dbName);
                                    break;
                                }
                            }
                            session.setAttribute("callingName", dbName.split("_")[0]);
                            session.setAttribute("replyingName", dbName.split("_")[1]);

                            requestDispatcher = req.getRequestDispatcher("views/usersCreateTable.jsp");
                            requestDispatcher.forward(req, resp);
                        }//для принимающего пользователя

                    } else {
                        session.setAttribute("worked", "yes");
                        if (session.getAttribute("gameRole").equals("calling")) {

                            String str = req.getParameter("User_matrix");
                            List<List<Integer>> userTable = new ArrayList<>();
                            int counter = 0;
                            for (int i = 0; i < 10; i++) {
                                userTable.add(new ArrayList<>());
                                for (int j = 0; j < 10; j++) {
                                    userTable.get(i).add(Integer.parseInt(str.substring(counter, counter + 1)));
                                    counter++;
                                }
                            }

                            //System.out.println(userTable);

                            dataBase.createCallingTables((String) session.getAttribute("callingName"), (String) session.getAttribute("replyingName"), userTable);
                            session.setAttribute("create1", "yes");

                            while (true) {
                                try {
                                    sleep(100);
                                } catch (InterruptedException e) {
                                    e.printStackTrace();
                                }
                                if (dataBase.sizeOfUsersTables((String) session.getAttribute("callingName"), (String) session.getAttribute("replyingName")) == 41) {
                                    break;
                                }
                            }
                            //System.out.println("WAGABANGA FRON CALLING");
                            requestDispatcher = req.getRequestDispatcher("/startUserBattleServlet");
                            requestDispatcher.forward(req, resp);

                            //dataBase.closeUsersBattle((String) session.getAttribute("callingName"), (String) session.getAttribute("replyingName"));

                        } else {

                            String str = req.getParameter("User_matrix");
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
                            dataBase.createReplyingTables((String) session.getAttribute("callingName"), (String) session.getAttribute("replyingName"), userTable);
                            session.setAttribute("create1", "yes");

                            while (true) {
                                try {
                                    sleep(100);
                                } catch (InterruptedException e) {
                                    e.printStackTrace();
                                }
                                System.out.println(dataBase.sizeOfUsersTables((String) session.getAttribute("callingName"), (String) session.getAttribute("replyingName")));
                                if (dataBase.sizeOfUsersTables((String) session.getAttribute("callingName"), (String) session.getAttribute("replyingName")) == 41) {
                                    break;
                                }
                            }

                            //System.out.println("WAGABANGA FRON REPLYING");

                            requestDispatcher = req.getRequestDispatcher("/startUserBattleServlet");
                            requestDispatcher.forward(req, resp);

                        }

                    }
                } else {

                    System.out.println(session.getAttribute("endGame"));
                    if (req.getParameter("cantFire") != null) {
                        session.setAttribute("cantFire", "yes");
                        while (true) {
                            if (session.getAttribute("loop") == null) {
                                int flag = dataBase.checkFlag((String) session.getAttribute("callingName"), (String) session.getAttribute("replyingName"), (String) session.getAttribute("gameRole"));
                                try {
                                    sleep(100);
                                } catch (InterruptedException e) {
                                    e.printStackTrace();
                                }
                                if (session.getAttribute("gameRole").equals("calling")) {
                                    if (flag == 5) {
                                        session.removeAttribute("cantFire");
                                        break;
                                    } else if (flag == 9) {
                                        dataBase.closeUsersBattle((String) session.getAttribute("callingName"), (String) session.getAttribute("replyingName"));
                                        requestDispatcher = req.getRequestDispatcher("/MainMenuServlet");
                                        session.removeAttribute("create1");
                                        //session.removeAttribute("initialise");
                                        session.removeAttribute("worked");
                                        //session.removeAttribute("callingName");
                                        //session.removeAttribute("replyingName");
                                        //session.removeAttribute("gameRole");
                                        session.setAttribute("endGame", "yes");

                                        String enemyName = "";
                                        if (((String) session.getAttribute("gameRole")).equals("calling")) {
                                            enemyName = (String) session.getAttribute("replyingName");
                                        }
                                        else {
                                            enemyName = (String) session.getAttribute("callingName");
                                        }
                                        String lose = "You lose in battle with " + enemyName;
                                        req.setAttribute("lose", lose);

                                        requestDispatcher.forward(req, resp);
                                        session.removeAttribute("cantFire");

                                        return;
                                    }
                                } else {
                                    if (flag == -5) {
                                        session.removeAttribute("cantFire");
                                        break;
                                    } else if (flag == 9) {
                                        dataBase.closeUsersBattle((String) session.getAttribute("callingName"), (String) session.getAttribute("replyingName"));
                                        requestDispatcher = req.getRequestDispatcher("/MainMenuServlet");
                                        session.removeAttribute("create1");
                                        //session.removeAttribute("initialise");
                                        session.removeAttribute("worked");
                                        //session.removeAttribute("callingName");
                                        //session.removeAttribute("replyingName");
                                        //session.removeAttribute("gameRole");
                                        session.setAttribute("endGame", "yes");

                                        String enemyName = "";
                                        if (((String) session.getAttribute("gameRole")).equals("calling")) {
                                            enemyName = (String) session.getAttribute("replyingName");
                                        }
                                        else {
                                            enemyName = (String) session.getAttribute("callingName");
                                        }
                                        String lose = "You lose in battle with " + enemyName;
                                        req.setAttribute("lose", lose);

                                        requestDispatcher.forward(req, resp);
                                        session.removeAttribute("cantFire");

                                        return;
                                    }
                                }
                            }
                            else {
                                System.out.println("TRADER");
                                dataBase.closeUsersBattle((String) session.getAttribute("callingName"), (String) session.getAttribute("replyingName"));
                                session.removeAttribute("loop");
                                return;
                            }
                        }
                    }

                    if (req.getParameter("fire1") != null) {

                        int flag = dataBase.checkFlag((String) session.getAttribute("callingName"), (String) session.getAttribute("replyingName"), (String) session.getAttribute("gameRole"));

                        if (flag == 9) {
                            System.out.println("FLAG 9 IN FIRING");
                            //dataBase.closeUsersBattle((String) session.getAttribute("callingName"), (String) session.getAttribute("replyingName"));
                            requestDispatcher = req.getRequestDispatcher("/MainMenuServlet");
                            session.removeAttribute("create1");
                            //session.removeAttribute("initialise");
                            session.removeAttribute("worked");
                            //session.removeAttribute("callingName");
                            //session.removeAttribute("replyingName");
                            session.removeAttribute("loop");
                            //session.removeAttribute("gameRole");
                            session.setAttribute("endGame", "yes");
                            requestDispatcher.forward(req, resp);

                            return;
                        }

                        String coords = req.getParameter("fire1");
                        System.out.println(coords);
                        int x = Integer.parseInt(coords.split(" ")[0]);
                        int y = Integer.parseInt(coords.split(" ")[1]);
                        GameSession gameSession = new GameSession((String) session.getAttribute("callingName"), (String) session.getAttribute("replyingName"));
                        gameSession.stepDraw(x, y, (String) session.getAttribute("gameRole"));

                        if (flag == 9) {
                            System.out.println("FLAG 9 IN FIRING");
                            //dataBase.closeUsersBattle((String) session.getAttribute("callingName"), (String) session.getAttribute("replyingName"));
                            requestDispatcher = req.getRequestDispatcher("/MainMenuServlet");
                            session.removeAttribute("create1");
                            //session.removeAttribute("initialise");
                            session.removeAttribute("worked");
                            //session.removeAttribute("callingName");
                            //session.removeAttribute("replyingName");
                            session.removeAttribute("loop");
                            //session.removeAttribute("gameRole");
                            session.setAttribute("endGame", "yes");
                            requestDispatcher.forward(req, resp);

                            return;
                        }

                    }

                    List<List<Integer>> myTable;
                    List<List<Integer>> enemyTable;

                    if (session.getAttribute("gameRole").equals("calling")) {

                        req.setAttribute("yourName", session.getAttribute("callingName"));
                        req.setAttribute("enemyName", session.getAttribute("replyingName"));

                        int flag = dataBase.checkFlag((String) session.getAttribute("callingName"), (String) session.getAttribute("replyingName"), (String) session.getAttribute("gameRole"));

                        if (flag == 5) {
                            req.setAttribute("canFire", "yes");
                        } else if (flag == -5) {
                            req.setAttribute("canFire", "no");
                        } else if (flag == 9) {
                            //dataBase.closeUsersBattle((String) session.getAttribute("callingName"), (String) session.getAttribute("replyingName"));
                            requestDispatcher = req.getRequestDispatcher("/MainMenuServlet");
                            session.removeAttribute("create1");
                            //session.removeAttribute("initialise");
                            session.removeAttribute("worked");
                            //session.removeAttribute("callingName");
                            //session.removeAttribute("replyingName");
                            session.removeAttribute("loop");
                            //session.removeAttribute("gameRole");
                            session.setAttribute("endGame", "yes");
                            requestDispatcher.forward(req, resp);

                            return;
                        }

                        myTable = dataBase.getCallingSelf((String) session.getAttribute("callingName"), (String) session.getAttribute("replyingName"));
                        enemyTable = dataBase.getCallingEnemy((String) session.getAttribute("callingName"), (String) session.getAttribute("replyingName"));

                    } else {

                        req.setAttribute("yourName", session.getAttribute("replyingName"));
                        req.setAttribute("enemyName", session.getAttribute("callingName"));
                        int flag = dataBase.checkFlag((String) session.getAttribute("callingName"), (String) session.getAttribute("replyingName"), (String) session.getAttribute("gameRole"));

                        if (flag == -5) {
                            req.setAttribute("canFire", "yes");
                        } else if (flag == 5) {
                            req.setAttribute("canFire", "no");
                        } else if (flag == 9) {
                            //dataBase.closeUsersBattle((String) session.getAttribute("callingName"), (String) session.getAttribute("replyingName"));
                            requestDispatcher = req.getRequestDispatcher("/MainMenuServlet");
                            session.removeAttribute("create1");
                            //session.removeAttribute("initialise");
                            session.removeAttribute("worked");
                            //session.removeAttribute("callingName");
                            //session.removeAttribute("replyingName");
                            session.removeAttribute("loop");
                            //session.removeAttribute("gameRole");
                            session.setAttribute("endGame", "yes");
                            requestDispatcher.forward(req, resp);

                            return;
                        }

                        myTable = dataBase.getReplyingSelf((String) session.getAttribute("callingName"), (String) session.getAttribute("replyingName"));
                        enemyTable = dataBase.getReplyingEnemy((String) session.getAttribute("callingName"), (String) session.getAttribute("replyingName"));

                    }

                    req.setAttribute("myTable", myTable);
                    req.setAttribute("enemyTable", enemyTable);

                    requestDispatcher = req.getRequestDispatcher("views/usersBattle.jsp");
                    requestDispatcher.forward(req, resp);

                }
            }
        }
        else {

            String lose = "You lose in battle";
            int flag = dataBase.getWinner((String) session.getAttribute("callingName"), (String) session.getAttribute("replyingName"));

            if ((flag == 5) && ((String) session.getAttribute("gameRole")).equals("calling")) {
                lose = "You won in battle";
            }
            if ((flag == -5) && ((String) session.getAttribute("gameRole")).equals("replying")) {
                lose = "You won in battle";
            }

            req.setAttribute("lose", lose);

            System.out.println("ENDGAME");
            session.removeAttribute("endGame");
            requestDispatcher = req.getRequestDispatcher("/MainMenuServlet");
            requestDispatcher.forward(req, resp);
        }
    }

}