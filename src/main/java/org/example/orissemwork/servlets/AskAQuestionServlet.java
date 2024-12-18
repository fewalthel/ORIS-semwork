package org.example.orissemwork.servlets;

import org.example.orissemwork.db.CategoryDAO;
import org.example.orissemwork.db.UserDAO;
import org.example.orissemwork.model.*;
import org.example.orissemwork.services.AskAQuestionService;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/my_questions")
public class AskAQuestionServlet extends HttpServlet{

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        getServletContext().getRequestDispatcher("/views/profile/my_questions.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String title = req.getParameter("title");
        String description = req.getParameter("description");
        String category = req.getParameter("category");

        Question question = new Question(null, title, description, null, CategoryDAO.getByName(category));

        if (AskAQuestionService.questionIsAsked(question, req)) {
            resp.sendRedirect(getServletContext().getContextPath() + "/my_questions");
        } else {
            req.getRequestDispatcher("/views/profile/my_questions.jsp").forward(req, resp);
        }
    }
}