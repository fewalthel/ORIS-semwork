package org.example.orissemwork.servlets;

import lombok.SneakyThrows;
import org.example.orissemwork.services.*;

import java.io.IOException;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/settings")
public class SettingsServlet extends HttpServlet {

    private UserService userService;

    @Override
    public void init(ServletConfig config) {
        userService = (UserService) getServletContext().getAttribute("userService");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        getServletContext().getRequestDispatcher("/views/profile/settings.jsp").forward(req, resp);
    }

    @SneakyThrows
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) {
        String new_username = req.getParameter("new_username");
        String old_password = req.getParameter("old_password");
        String new_password = req.getParameter("new_password");

        String current_password = req.getParameter("current_password");
        String current_username = req.getParameter("current_username");

        //случай когда пользователь пытается удалить аккаунт
        if (current_password != null || current_username != null) {
            userService.deleteAccount(req, resp, current_username, current_password);
        } else {
            //случай когда пользоваетль пытается поменять пароль или юзернейм
            userService.changePasswordOrUsername(req, resp, new_username, old_password, new_password);
        }
    }
}