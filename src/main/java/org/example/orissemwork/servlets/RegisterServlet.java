package org.example.orissemwork.servlets;

import org.example.orissemwork.model.User;
import org.example.orissemwork.services.ConfirmService;
import org.example.orissemwork.services.RegisterService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/register")
public class RegisterServlet extends HttpServlet{

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        getServletContext().getRequestDispatcher("/views/register/register.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String email = req.getParameter("email");
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        String confirmPassword = req.getParameter("confirm_password");

        User account = new User(null, email, username, password, "default");

        req.getSession().setAttribute("email", email);
        req.getSession().setAttribute("username", username);
        req.getSession().setAttribute("password", password);

        if (RegisterService.valuesIsValid(account, req, confirmPassword)) {
            ConfirmService confirmService = new ConfirmService();
            confirmService.confirm(req, resp, email);
            System.out.println("код подтверждения отправлен");
        } else {
            req.getRequestDispatcher("/views/register/register.jsp").forward(req, resp);
        }
    }
}