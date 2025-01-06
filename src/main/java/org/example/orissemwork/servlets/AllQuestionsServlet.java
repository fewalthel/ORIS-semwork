package org.example.orissemwork.servlets;

import lombok.SneakyThrows;
import org.example.orissemwork.dao.*;
import org.example.orissemwork.model.Category;

import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;

@WebServlet("/all_questions")
public class AllQuestionsServlet extends HttpServlet {

    private QuestionDAO questionDAO;
    private CategoryDAO categoryDAO;

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        questionDAO = (QuestionDAO) getServletContext().getAttribute("questionDAO");
        categoryDAO = (CategoryDAO) getServletContext().getAttribute("categoryDAO");
    }

    @SneakyThrows
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Category category = CategoryDAO.getByName(req.getParameter("category"));
        if (category != null) {
            req.setAttribute("questions_by_category", questionDAO.getAllByCategory(category));
        } else {
            req.setAttribute("all_questions", questionDAO.getAll());
        }
        req.setAttribute("all_categories", categoryDAO.getAll());
        getServletContext().getRequestDispatcher("/views/profile/all_questions.jsp").forward(req, resp);
    }
}