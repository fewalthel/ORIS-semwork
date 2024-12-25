package org.example.orissemwork.servlets;

import lombok.SneakyThrows;
import org.example.orissemwork.services.UserService;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

import java.io.IOException;

@WebServlet("/signin")
public class SignInServlet extends HttpServlet{

    private UserService userService;

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        userService = (UserService) getServletContext().getAttribute("userService");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        getServletContext().getRequestDispatcher("/views/signin/signin.jsp").forward(req, resp);
    }

    @SneakyThrows
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) {
        String email = req.getParameter("email").toLowerCase();
        String password = req.getParameter("password");

        if(userService.signIn(req, email, password)){
            resp.sendRedirect(getServletContext().getContextPath() + "/profile");
        } else {
            req.getRequestDispatcher("/views/signin/signin.jsp").forward(req, resp);
        }
    }

}