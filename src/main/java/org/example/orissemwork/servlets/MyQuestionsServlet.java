package org.example.orissemwork.servlets;

import org.example.orissemwork.model.Question;
import org.example.orissemwork.model.User;
import org.example.orissemwork.services.AskAQuestionService;
import org.example.orissemwork.services.RegisterService;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/my_questions")
public class MyQuestionsServlet extends HttpServlet{

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        getServletContext().getRequestDispatcher("/views/my_questions.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String title = req.getParameter("title");
        String description = req.getParameter("description");

        Question question = new Question(title, description, null);

        if (AskAQuestionService.questionIsAsked(question, req)) {
            resp.sendRedirect(getServletContext().getContextPath() + "/my_questions");
        } else {
            req.getRequestDispatcher("/view/my_questions.jsp").forward(req, resp);
        }
    }
}