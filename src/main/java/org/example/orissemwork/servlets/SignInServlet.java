package org.example.orissemwork.servlets;

import org.example.orissemwork.db.WorkWithDBForUser;
import org.example.orissemwork.services.SecurityService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/signin")
public class SignInServlet extends HttpServlet{

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        getServletContext().getRequestDispatcher("/views/signin/signin.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String email = req.getParameter("email");
        String password = req.getParameter("password");

        if(email != null && password != null){
            if(SecurityService.signIn(req, email, password)){
                resp.sendRedirect(getServletContext().getContextPath() + "/profile");
                return;
            }
        }
        req.setAttribute("username", WorkWithDBForUser.getUserByEmail(email).getUsername());
        getServletContext().getRequestDispatcher("/views/signin/signin.jsp").forward(req, resp);
    }
}