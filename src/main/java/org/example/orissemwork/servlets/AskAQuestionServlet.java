package org.example.orissemwork.servlets;

import org.example.orissemwork.db.*;
import org.example.orissemwork.model.*;
import org.example.orissemwork.services.*;

import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;

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
        String email_of_author = (String) req.getSession().getAttribute("email");

        User author = UserDAO.getByEmail(email_of_author);

        Question question = new Question(null, title, description, author, CategoryDAO.getByName(category));

        if (AskAQuestionService.questionIsAsked(question, author, req)) {
            resp.sendRedirect(getServletContext().getContextPath() + "/my_questions");
        } else {
            req.getRequestDispatcher("/views/profile/my_questions.jsp").forward(req, resp);
        }
    }
}