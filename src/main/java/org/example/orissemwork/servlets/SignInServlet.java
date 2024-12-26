package org.example.orissemwork.servlets;

import org.example.orissemwork.db.UserDAO;
import org.example.orissemwork.services.SecurityService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

import java.io.IOException;

@WebServlet("/signin")
public class SignInServlet extends HttpServlet{

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        getServletContext().getRequestDispatcher("/views/signin/signin.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String email = req.getParameter("email").toLowerCase();
        String password = req.getParameter("password");

        if(SecurityService.signIn(req, email, password)){
            req.setAttribute("username", UserDAO.getByEmail(email).getUsername());
            resp.sendRedirect(getServletContext().getContextPath() + "/profile");
        } else {
            req.getRequestDispatcher("/views/signin/signin.jsp").forward(req, resp);
        }
    }

}