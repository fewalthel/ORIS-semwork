package org.example.orissemwork.servlets;

import lombok.SneakyThrows;
import org.example.orissemwork.dao.*;
import org.example.orissemwork.model.*;
import org.example.orissemwork.services.*;

import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.util.List;

@WebServlet("/question")
public class AddAnswerForQuestionServlet extends HttpServlet {

    private AnswerService answerService;
    private AnswerDAO answerDAO;
    private QuestionDAO questionDAO;
    private UserService userService;
    private RatingDAO ratingDAO;

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        answerDAO = (AnswerDAO) getServletContext().getAttribute("answerDAO");
        questionDAO = (QuestionDAO) getServletContext().getAttribute("questionDAO");
        userService = (UserService) getServletContext().getAttribute("userService");
        answerService = (AnswerService) getServletContext().getAttribute("answerService");
        ratingDAO = (RatingDAO) getServletContext().getAttribute("ratingDAO");
    }

    @SneakyThrows
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Integer id_of_question = Integer.valueOf(req.getParameter("id"));
        Question question = questionDAO.getById(id_of_question);
        User user = userService.getUser(req);

        req.setAttribute("ratings_for_user", ratingDAO.getAllByUser(user, question));
        req.setAttribute("all_answers_for_this_question", answerDAO.getAllByQuestion(question));
        req.setAttribute("question", question);
        req.setAttribute("favorites_answers_for_user", answerDAO.getFavoriteAnswers(user));

        getServletContext().getRequestDispatcher("/views/profile/question.jsp").forward(req, resp);
    }

    @SneakyThrows
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) {
        String content = req.getParameter("answer");
        Integer id_of_question = Integer.valueOf(req.getParameter("id"));
        
        User author = userService.getUser(req);

        Answer ans = new Answer(null, questionDAO.getById(id_of_question), content, author);
        String title_of_question = questionDAO.getById(id_of_question).getTitle();

        if (answerService.answerIsGiven(ans, title_of_question, req)) {
            resp.sendRedirect(getServletContext().getContextPath() +"/question"+"?id="+id_of_question);
        } else {
            req.getRequestDispatcher("/views/profile/question.jsp").forward(req, resp);
        }
    }
}