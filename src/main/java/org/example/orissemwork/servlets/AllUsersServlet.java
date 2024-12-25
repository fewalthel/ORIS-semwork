package org.example.orissemwork.servlets;

import lombok.SneakyThrows;
import org.example.orissemwork.db.UserDAO;
import org.example.orissemwork.services.*;

import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;

@WebServlet("/all_users")
public class AllUsersServlet extends HttpServlet {

    private UserService userService;
    private UserDAO userDAO;


    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        userService = (UserService) getServletContext().getAttribute("userService");
        userDAO = (UserDAO) getServletContext().getAttribute("userDAO");
    }

    @SneakyThrows
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("all_users", userDAO.getAll());
        getServletContext().getRequestDispatcher("/views/profile/all_users.jsp").forward(req, resp);
    }

    @SneakyThrows
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) {
        String deleted_username = req.getParameter("deleted_username");
        String updated_username = req.getParameter("updated_username");

        userService.adminSettings(req, resp, deleted_username, updated_username);
    }

}
