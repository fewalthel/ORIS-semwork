package org.example.orissemwork.servlets;

import org.example.orissemwork.db.*;
import org.example.orissemwork.model.Answer;
import org.example.orissemwork.model.User;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;

@WebServlet("/rating")
public class RatingServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {

        Answer answer = AnswerDAO.getById(Integer.parseInt(req.getParameter("idOfAnswer")));
        User author = UserDAO.getByEmail((String) req.getSession().getAttribute("email"));
        Boolean rating = Boolean.valueOf(req.getParameter("rating"));

        System.out.println(rating);
        RatingDAO.updateRating(author, answer, rating);

        resp.sendRedirect(getServletContext().getContextPath() + "/question?id="+ Integer.toString(answer.getQuestion().getId()));
    }
}