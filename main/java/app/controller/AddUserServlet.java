package app.controller;

import app.database.DataBaseInputOutput;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*;

public class AddUserServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        RequestDispatcher requestDispatcher = req.getRequestDispatcher("views/addUser.jsp");
        requestDispatcher.forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String name = req.getParameter("name");
        String password = req.getParameter("pass");
        DataBaseInputOutput dataBase = new DataBaseInputOutput();

        if (!dataBase.findSameLogin(name)) {
            dataBase.addNewUser(name, password);
            req.setAttribute("userName", "User: " + name + " added!");
        }
        else {
            req.setAttribute("userName", "Sorry, user with the same name already exists.");
        }

        doGet(req, resp);

    }

}
