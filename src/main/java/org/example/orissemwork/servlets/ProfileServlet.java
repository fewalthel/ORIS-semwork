package org.example.orissemwork.servlets;

import lombok.SneakyThrows;
import org.example.orissemwork.dao.AnswerDAO;
import org.example.orissemwork.dao.QuestionDAO;
import org.example.orissemwork.dao.UserDAO;
import org.example.orissemwork.model.User;
import org.example.orissemwork.services.FileService;

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
    private FileService fileService;

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        questionDAO = (QuestionDAO) getServletContext().getAttribute("questionDAO");
        answerDAO = (AnswerDAO) getServletContext().getAttribute("answerDAO");
        userDAO = (UserDAO) getServletContext().getAttribute("userDAO");
        fileService = (FileService) getServletContext().getAttribute("fileService");
    }

    @SneakyThrows
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        User user = userDAO.getByEmail((String) req.getSession().getAttribute("email"));
        req.setAttribute("total_questions", questionDAO.getAllByAuthor(user).size());
        req.setAttribute("total_answers", answerDAO.getAllByAuthor(user).size());

        req.setAttribute("path_to_avatar", fileService.getPathToAvatarForUser(user));

        getServletContext().getRequestDispatcher("/views/profile/profile.jsp").forward(req, resp);
    }

}