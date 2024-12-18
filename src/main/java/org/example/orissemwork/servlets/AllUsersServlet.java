package org.example.orissemwork.servlets;

import org.example.orissemwork.db.UserDAO;
import org.example.orissemwork.model.User;
import org.example.orissemwork.services.SettingsService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/all_users")
public class AllUsersServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        getServletContext().getRequestDispatcher("/views/profile/all_users.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        if (req.getParameter("deleted_username") != null) {
            User user = UserDAO.getByUsername(req.getParameter("deleted_username") );
            SettingsService.deleteAccount(user);
        }
        if (req.getParameter("upgraded_username") != null) {
            User user = UserDAO.getByUsername(req.getParameter("upgraded_username"));
            UserDAO.upgradeRole(user);
        }

        resp.sendRedirect(getServletContext().getContextPath() + "/all_users");
    }

}
