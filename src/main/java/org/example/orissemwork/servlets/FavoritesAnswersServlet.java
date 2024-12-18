package org.example.orissemwork.servlets;

import org.example.orissemwork.db.*;
import org.example.orissemwork.model.*;

import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;

@WebServlet("/favorites_answers")
public class FavoritesAnswersServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        getServletContext().getRequestDispatcher("/views/profile/favorites_answers.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String emailOfUser = req.getSession().getAttribute("email").toString();
        Integer idOfAnswer = Integer.parseInt(req.getParameter("idOfAnswer"));

        User user = UserDAO.getByEmail(emailOfUser);
        Answer ans = AnswerDAO.getById(idOfAnswer);

        if (AnswerDAO.ansInFavForUser(ans, user)) {
            AnswerDAO.removeFromFavorites(ans, user);
        } else {
            AnswerDAO.saveToFavorites(ans, user);
        }

        resp.sendRedirect(getServletContext().getContextPath() + "/favorites_answers");
    }
}
