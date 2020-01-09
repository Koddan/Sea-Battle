package app.servlets;

import app.controller.DataBaseInputOutput;

import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;

public class LoginServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        RequestDispatcher requestDispatcher;
        HttpSession session = req.getSession(false);
        requestDispatcher = req.getRequestDispatcher("views/logIn.jsp");
        if (req.getAttribute("userName") == "Username or password is wrong.") {
            requestDispatcher = req.getRequestDispatcher("views/logIn.jsp");
        }
        else if ((req.getAttribute("userName") == "1") && (session.getAttribute("condition") == "online")){
            requestDispatcher = req.getRequestDispatcher("/MainMenuServlet");
        }
        requestDispatcher.include(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        if (req.getParameter("name") != null) {
            resp.setContentType("text/html");
            String name = req.getParameter("name");
            String password = req.getParameter("password");
            DataBaseInputOutput dataBase = new DataBaseInputOutput();
            HttpSession session = req.getSession();
            if (dataBase.logIn(name, password) && (session.getAttribute("time")) != "notFirst") {
                session.setAttribute("name", name);
                session.setAttribute("condition", "online");
                session.setAttribute("time", "First");
                req.setAttribute("userName", "1");
                System.out.println("login");
            } else {
                req.setAttribute("userName", "Username or password is wrong.");
            }
        }

        doGet(req, resp);

    }

}