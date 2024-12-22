package org.example.orissemwork.servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

@WebServlet("/main")
public class MainServlet extends HttpServlet{

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("email", req.getSession().getAttribute("email"));
        getServletContext().getRequestDispatcher("/views/main/main.jsp").forward(req, resp);
    }

}