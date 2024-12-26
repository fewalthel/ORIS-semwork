package org.example.orissemwork.servlets;

import org.example.orissemwork.db.*;
import org.example.orissemwork.model.*;
import org.example.orissemwork.services.*;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/settings")
public class SettingsServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        getServletContext().getRequestDispatcher("/views/profile/settings.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String new_username = req.getParameter("new_username");
        String old_password = req.getParameter("old_password");
        String new_password = req.getParameter("new_password");

        String current_password = req.getParameter("current_password");
        String current_username = req.getParameter("current_username");

        //случай когд пользователь пытается удалить аккаунт
        if (current_password != null && current_username != null) {
            User current_user = UserDAO.getByEmail((String) req.getSession().getAttribute("email"));

            if (SettingsService.accountDeleted(req, current_user, current_username,current_password)) {
                resp.sendRedirect(getServletContext().getContextPath() + "/");
            } else {
                req.getRequestDispatcher("/views/profile/settings.jsp").forward(req, resp);
            }

        } else {
            //случай когда пользоваетль пытается поменять пароль или юзернейм
            if (SettingsService.changesIsSaved(req, new_username, old_password, new_password)) {
                resp.sendRedirect(getServletContext().getContextPath() + "/settings");
            } else {
                req.getRequestDispatcher("/views/profile/settings.jsp").forward(req, resp);
            }
        }
    }
}