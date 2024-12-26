package org.example.orissemwork.servlets;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.orissemwork.db.*;
import org.example.orissemwork.model.*;
import org.example.orissemwork.services.RatingService;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;

@WebServlet("/rating")
public class RatingServlet extends HttpServlet {

    private static int cnt = 1;
    private ObjectMapper objectMapper = new ObjectMapper();
    private RatingService ratingService;

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        ratingService = (RatingService) getServletContext().getAttribute("ratingService");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        /*Answer answer = AnswerDAO.getById(Integer.parseInt(req.getParameter("id_of_answer")));
        User author = UserDAO.getByEmail((String) req.getSession().getAttribute("email"));
        String rating = (req.getParameter("rating"));
*/
        System.out.println("hello"+cnt);
        cnt++;

       /* // приняли JSON на вход, с помоьщью ObjectMapper-а превратили в User-объект
        User user = objectMapper.readValue(req.getReader(), User.class);
        // сохранили нового пользователя в бд
        usersService.addUser(user);
        // получили всех пользователей из бд
        List<User> users = usersService.getAll();
        // сформировали JSON-строку-ответ
        String usersAsJson = objectMapper.writeValueAsString(users);
        resp.setContentType("application/json");
        // отправили ответ
        resp.getWriter().println(usersAsJson);*/



        /*resp.sendRedirect(getServletContext().getContextPath() + "/question?id=" + answer.getQuestion().getId());*/
    }

}