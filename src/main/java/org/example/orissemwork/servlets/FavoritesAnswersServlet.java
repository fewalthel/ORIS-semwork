package org.example.orissemwork.servlets;

import lombok.SneakyThrows;
import org.example.orissemwork.db.*;
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
    private UserService userService;

    @Override
    public void init(ServletConfig config) {
        answerService = (AnswerService) config.getServletContext().getAttribute("answerService");
        userService = (UserService) config.getServletContext().getAttribute("userService");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        getServletContext().getRequestDispatcher("/views/profile/favorites_answers.jsp").forward(req, resp);
    }

    @SneakyThrows
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) {
        String emailOfUser = req.getSession().getAttribute("email").toString();
        Integer id_of_answer = Integer.parseInt(req.getParameter("id_of_answer"));

        UserDAO userDAO = userService.userDAO;
        User user = userDAO.getByEmail(emailOfUser);

        AnswerDAO answerDAO = answerService.answerDAO;
        Answer ans = answerDAO.getById(id_of_answer);

        answerService.favoritesSettings(ans, user);

        resp.sendRedirect(getServletContext().getContextPath() + "/favorites_answers");
    }
}
