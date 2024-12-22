package org.example.orissemwork.servlets;

import org.example.orissemwork.db.*;
import org.example.orissemwork.model.*;
import org.example.orissemwork.services.*;

import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
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

        User author = UserDAO.getByEmail((String) req.getSession().getAttribute("email"));

        Answer ans = new Answer(null, QuestionDAO.getById(id), content, author);
        String title_of_question = QuestionDAO.getById(id).getTitle();

        if (AddAnswerForQuestionService.answerIsGiven(ans, title_of_question, req)) {
            resp.sendRedirect(getServletContext().getContextPath() +"/question"+"?id="+id);
        } else {
            req.getRequestDispatcher("/views/profile/question.jsp").forward(req, resp);
        }
    }
}