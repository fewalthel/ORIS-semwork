package org.example.orissemwork.servlets;

import lombok.SneakyThrows;
import org.example.orissemwork.db.QuestionDAO;

import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;

@WebServlet("/all_questions")
public class AllQuestionsServlet extends HttpServlet {

    private QuestionDAO questionDAO;

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        questionDAO = (QuestionDAO) getServletContext().getAttribute("questionDAO");
    }

    @SneakyThrows
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("all_questions", questionDAO.getAll());
        getServletContext().getRequestDispatcher("/views/profile/all_questions.jsp").forward(req, resp);
    }

}