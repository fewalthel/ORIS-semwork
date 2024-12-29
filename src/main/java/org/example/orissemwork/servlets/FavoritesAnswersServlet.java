package org.example.orissemwork.servlets;

import lombok.SneakyThrows;
import org.example.orissemwork.dao.*;
import org.example.orissemwork.model.*;
import org.example.orissemwork.services.AnswerService;
import org.example.orissemwork.services.UserService;

import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;

@WebServlet("/favorites_answers")
public class FavoritesAnswersServlet extends HttpServlet {

    private AnswerService answerService;
    private UserDAO userDAO;
    private AnswerDAO answerDAO;
    private UserService userService;

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        answerService = (AnswerService) getServletContext().getAttribute("answerService");
        userDAO = (UserDAO) getServletContext().getAttribute("userDAO");
        answerDAO = (AnswerDAO) getServletContext().getAttribute("answerDAO");
        userService = (UserService) getServletContext().getAttribute("userService");
    }

    @SneakyThrows
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        User user = userService.getUser(req);
        req.setAttribute("favorites_answers", answerDAO.getFavoriteAnswers(user));
        getServletContext().getRequestDispatcher("/views/profile/favorites_answers.jsp").forward(req, resp);
    }

    @SneakyThrows
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) {
        String emailOfUser = req.getSession().getAttribute("email").toString();
        Integer id_of_answer = Integer.parseInt(req.getParameter("id_of_answer"));

        User user = userDAO.getByEmail(emailOfUser);
        Answer ans = answerDAO.getById(id_of_answer);

        answerService.favoritesSettings(ans, user);

        resp.sendRedirect(getServletContext().getContextPath() + "/favorites_answers");
    }
}
