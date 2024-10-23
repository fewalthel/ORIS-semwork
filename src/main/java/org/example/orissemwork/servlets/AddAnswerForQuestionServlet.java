package org.example.orissemwork.servlets;


import org.example.orissemwork.db.WorkWithDBForQuestion;
import org.example.orissemwork.db.WorkWithDBForUser;
import org.example.orissemwork.model.Answer;
import org.example.orissemwork.model.User;
import org.example.orissemwork.services.AddAnswerForQuestionService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/question")
public class AddAnswerForQuestionServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        getServletContext().getRequestDispatcher("/views/profile/question.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String content = req.getParameter("answer");
        Integer id = Integer.valueOf(req.getParameter("id"));

        User author = WorkWithDBForUser.getUserByEmail((String) req.getSession().getAttribute("email"));

        Answer ans = new Answer(null, WorkWithDBForQuestion.getQuestionById(id), content, author);
        String title_of_question = WorkWithDBForQuestion.getQuestionById(id).getTitle();
        if (AddAnswerForQuestionService.answerIsGiven(ans, title_of_question, req)) {
            resp.sendRedirect(getServletContext().getContextPath() +"/question?id="+id);
        } else {
            req.getRequestDispatcher("/views/profile/question.jsp").forward(req, resp);
        }
    }
}