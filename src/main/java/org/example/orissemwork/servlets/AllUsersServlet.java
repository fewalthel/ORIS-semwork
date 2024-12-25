package org.example.orissemwork.servlets;

import lombok.SneakyThrows;
import org.example.orissemwork.db.*;
import org.example.orissemwork.model.*;
import org.example.orissemwork.services.*;

import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;

@WebServlet("/all_users")
public class AllUsersServlet extends HttpServlet {

    private UserService userService;

    @Override
    public void init(ServletConfig config) {
        userService= (UserService) config.getServletContext().getAttribute("userService");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        getServletContext().getRequestDispatcher("/views/profile/all_users.jsp").forward(req, resp);
    }

    @SneakyThrows
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String deleted_username = req.getParameter("deleted_username");
        String updated_username = req.getParameter("updated_username");

        userService.adminSettings(req, resp, deleted_username, updated_username);
    }

}
