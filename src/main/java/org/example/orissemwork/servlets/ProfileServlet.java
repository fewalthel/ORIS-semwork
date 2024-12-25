package org.example.orissemwork.servlets;

import lombok.SneakyThrows;
import org.example.orissemwork.db.AnswerDAO;
import org.example.orissemwork.db.QuestionDAO;
import org.example.orissemwork.db.UserDAO;
import org.example.orissemwork.model.User;

import java.io.IOException;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

@WebServlet("/profile")
public class ProfileServlet extends HttpServlet{

    private QuestionDAO questionDAO;
    private AnswerDAO answerDAO;
    private UserDAO userDAO;

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        questionDAO = (QuestionDAO) getServletContext().getAttribute("questionDAO");
        answerDAO = (AnswerDAO) getServletContext().getAttribute("answerDAO");
        userDAO = (UserDAO) getServletContext().getAttribute("userDAO");
    }

    @SneakyThrows
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        User user = userDAO.getByEmail((String) req.getSession().getAttribute("email"));
        req.setAttribute("total_questions", questionDAO.getAllByAuthor(user).size());
        req.setAttribute("total_answers", answerDAO.getAllByAuthor(user).size());

        getServletContext().getRequestDispatcher("/views/profile/profile.jsp").forward(req, resp);
    }

}