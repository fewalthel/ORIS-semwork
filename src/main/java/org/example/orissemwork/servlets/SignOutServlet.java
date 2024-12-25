package org.example.orissemwork.servlets;

import org.example.orissemwork.services.UserService;

import java.io.IOException;
import javax.servlet.ServletConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

@WebServlet("/signout")
public class SignOutServlet extends HttpServlet{

    private UserService userService;

    @Override
    public void init(ServletConfig config) {
        userService = (UserService) config.getServletContext().getAttribute("usersService");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        userService.signOut(req);
        resp.sendRedirect(getServletContext().getContextPath() + "/");
    }
}