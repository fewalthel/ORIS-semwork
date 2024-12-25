package org.example.orissemwork.servlets;

import org.example.orissemwork.services.QuestionService;

import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;

@WebServlet("/all_questions")
public class AllQuestionsServlet extends HttpServlet {

    private QuestionService questionService;

    @Override
    public void init(ServletConfig config) {
        questionService = (QuestionService) config.getServletContext().getAttribute("questionService");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        getServletContext().getRequestDispatcher("/views/profile/all_questions.jsp").forward(req, resp);
    }

}