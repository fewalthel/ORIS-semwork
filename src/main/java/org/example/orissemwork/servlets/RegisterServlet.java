package org.example.orissemwork.servlets;

import lombok.SneakyThrows;
import org.example.orissemwork.model.*;
import org.example.orissemwork.services.*;

import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;

@WebServlet("/register")
public class RegisterServlet extends HttpServlet{

    private RegisterService registerService;

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        registerService = (RegisterService) getServletContext().getAttribute("registerService");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        getServletContext().getRequestDispatcher("/views/register/register.jsp").forward(req, resp);
    }

    @SneakyThrows
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) {
        String email = req.getParameter("email").toLowerCase();
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        String confirmPassword = req.getParameter("confirm_password");

        User account = new User(null, email, username, password, "default");

        req.getSession().setAttribute("email", email);
        req.getSession().setAttribute("username", username);
        req.getSession().setAttribute("password", password);

        if (registerService.valuesIsValid(account, req, confirmPassword)) {
            registerService.confirm(req, resp, email);
            System.out.println("код подтверждения отправлен");
        } else {
            req.getRequestDispatcher("/views/register/register.jsp").forward(req, resp);
        }
    }
}