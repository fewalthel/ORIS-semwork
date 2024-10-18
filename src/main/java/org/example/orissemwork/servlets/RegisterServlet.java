package org.example.orissemwork.servlets;

import org.example.orissemwork.model.User;
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
        getServletContext().getRequestDispatcher("/views/register.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String email = req.getParameter("email");
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        String confirmPassword = req.getParameter("confirm_password");

        User account = new User(email, username, password);

        if (RegisterService.register(account, req, confirmPassword)) {
            //TODO: добавить всплывающее окошко, если вопрос успешно задан
            resp.sendRedirect(getServletContext().getContextPath() + "/signin");
        } else {
            req.getRequestDispatcher("/view/register.jsp").forward(req, resp);
        }
    }
}