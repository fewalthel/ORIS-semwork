package org.example.orissemwork.servlets;

import lombok.SneakyThrows;
import org.example.orissemwork.db.*;
import org.example.orissemwork.model.*;
import org.example.orissemwork.services.*;

import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;

@WebServlet("/my_questions")
public class AskAQuestionServlet extends HttpServlet {

    private QuestionService questionService;

    @Override
    public void init(ServletConfig config) {
        questionService = (QuestionService) config.getServletContext().getAttribute("questionService");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        getServletContext().getRequestDispatcher("/views/profile/my_questions.jsp").forward(req, resp);
    }

    @SneakyThrows
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) {
        String title = req.getParameter("title");
        String description = req.getParameter("description");
        String category = req.getParameter("category");

        Question question = new Question(null, title, description, null, CategoryDAO.getByName(category));

        if (questionService.questionIsAsked(question, req)) {
            resp.sendRedirect(getServletContext().getContextPath() + "/my_questions");
        } else {
            req.getRequestDispatcher("/views/profile/my_questions.jsp").forward(req, resp);
        }
    }
}