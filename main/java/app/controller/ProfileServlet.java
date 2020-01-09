package app.controller;

import app.database.DataBaseInputOutput;

import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;


public class ProfileServlet extends HttpServlet {

    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        resp.setContentType("text/html");
        HttpSession session = req.getSession(false);
        RequestDispatcher requestDispatcher;

        if (session.getAttribute("condition") == "online") {
            requestDispatcher = req.getRequestDispatcher("views/profile.jsp");
        }
        else {
            requestDispatcher = req.getRequestDispatcher("/LoginServlet");
        }
        requestDispatcher.forward(req, resp);

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        resp.setContentType("text/html");
        HttpSession session = req.getSession(false);
        RequestDispatcher requestDispatcher;
        DataBaseInputOutput dataBase = new DataBaseInputOutput();

        String name = (String) session.getAttribute("name");
        String password = req.getParameter("password");
        if (req.getParameter("newfullname") != null) {
            String newName = req.getParameter("newfullname");
            dataBase.changeUserName(name, password, newName);
        }
        if (req.getParameter("newpassword") != null) {
            String newPassword = req.getParameter("newpassword");
            dataBase.changeUserPassword(name, password, newPassword);
        }

        requestDispatcher = req.getRequestDispatcher("views/profile.jsp");
        requestDispatcher.forward(req, resp);

    }

}