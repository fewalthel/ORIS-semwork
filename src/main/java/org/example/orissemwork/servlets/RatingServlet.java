package org.example.orissemwork.servlets;

import lombok.SneakyThrows;
import org.example.orissemwork.dao.*;
import org.example.orissemwork.model.*;
import org.example.orissemwork.services.RatingService;
import org.example.orissemwork.services.UserService;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;

@WebServlet("/rating")
public class RatingServlet extends HttpServlet {

    private RatingService ratingService;
    private AnswerDAO answerDAO;
    private UserService userService;


    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        ratingService = (RatingService) getServletContext().getAttribute("ratingService");
        answerDAO = (AnswerDAO) getServletContext().getAttribute("answerDAO");
        userService = (UserService) getServletContext().getAttribute("userService");
    }

    @SneakyThrows
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        Answer answer = answerDAO.getById(Integer.parseInt(req.getParameter("id_of_answer")));
        User author = userService.getUser(req);
        String rating = (req.getParameter("rating"));

        ratingService.updateRatingOnDB(rating, author, answer);

        resp.sendRedirect(getServletContext().getContextPath() + "/question?id=" + answer.getQuestion().getId());
    }

}