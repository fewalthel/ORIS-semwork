package org.example.orissemwork.servlets;

import org.example.orissemwork.services.*;

import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;

@WebServlet("/all_users")
public class AllUsersServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        getServletContext().getRequestDispatcher("/views/profile/all_users.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        SettingsService.adminSettings(req);
        resp.sendRedirect(getServletContext().getContextPath() + "/all_users");
    }

}
