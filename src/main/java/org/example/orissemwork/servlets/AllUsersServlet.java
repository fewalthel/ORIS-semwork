package org.example.orissemwork.servlets;

import lombok.SneakyThrows;
import org.example.orissemwork.dao.UserDAO;

import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;

@WebServlet("/all_users")
public class AllUsersServlet extends HttpServlet {

    private UserDAO userDAO;


    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        userDAO = (UserDAO) getServletContext().getAttribute("userDAO");
    }

    @SneakyThrows
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("all_users", userDAO.getAll());
        getServletContext().getRequestDispatcher("/views/profile/all_users.jsp").forward(req, resp);
    }

}
