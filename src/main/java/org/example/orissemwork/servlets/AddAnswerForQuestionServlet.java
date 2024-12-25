package org.example.orissemwork.servlets;

import lombok.SneakyThrows;
import org.example.orissemwork.db.*;
import org.example.orissemwork.model.*;
import org.example.orissemwork.services.*;

import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;

@WebServlet("/question")
public class AddAnswerForQuestionServlet extends HttpServlet {

    private AnswerService answerService;
    private UserService userService;
    private QuestionService questionService;

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        answerService = (AnswerService) getServletContext().getAttribute("answerService");
        userService = (UserService) getServletContext().getAttribute("userService");
        questionService = (QuestionService) getServletContext().getAttribute("questionService");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        getServletContext().getRequestDispatcher("/views/profile/question.jsp").forward(req, resp);
    }

    @SneakyThrows
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) {
        String content = req.getParameter("answer");
        Integer id_of_question = Integer.valueOf(req.getParameter("id"));

        UserDAO userDAO = userService.userDAO;
        QuestionDAO questionDAO = questionService.questionDAO;
        
        User author = userDAO.getByEmail((String) req.getSession().getAttribute("email"));

        Answer ans = new Answer(null, questionDAO.getById(id_of_question), content, author);
        String title_of_question = questionDAO.getById(id_of_question).getTitle();

        if (answerService.answerIsGiven(ans, title_of_question, req)) {
            resp.sendRedirect(getServletContext().getContextPath() +"/question"+"?id="+id_of_question);
        } else {
            req.getRequestDispatcher("/views/profile/question.jsp").forward(req, resp);
        }
    }
}