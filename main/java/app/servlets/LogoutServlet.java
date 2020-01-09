package app.servlets;

import app.controller.DataBaseInputOutput;

import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;

public class LogoutServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        DataBaseInputOutput dataBase = new DataBaseInputOutput();
        response.setContentType("text/html");
        HttpSession session = request.getSession();
        session.removeAttribute("name");
        session.setAttribute("condition", "offline");
        session.setAttribute("time", "notFirst");

        /*session.removeAttribute("create1");
        //session.removeAttribute("initialise");
        session.removeAttribute("worked");
        //session.removeAttribute("callingName");
        //session.removeAttribute("replyingName");
        //session.removeAttribute("gameRole");
        //session.setAttribute("endGame", "yes");
        if (session.getAttribute("cantFire") != null) {
            session.setAttribute("loop", "no");
        }*/
        session.invalidate();

        System.out.println("logout");
        request.getRequestDispatcher("index.jsp").include(request, response);


    }

}