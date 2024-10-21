package org.example.orissemwork.servlets;

import org.example.orissemwork.services.SettingsService;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/settings")
public class SettingsServlet extends HttpServlet{

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        getServletContext().getRequestDispatcher("/views/profile/settings.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String new_username = req.getParameter("new_username");
        String old_password = req.getParameter("old_password");
        String new_password = req.getParameter("new_password");

        if (SettingsService.changesIsSaved(req, new_username, old_password, new_password)) {
            resp.sendRedirect(getServletContext().getContextPath() + "/settings");
        } else {
            req.getRequestDispatcher("/views/profile/settings.jsp").forward(req, resp);
        }
    }

}