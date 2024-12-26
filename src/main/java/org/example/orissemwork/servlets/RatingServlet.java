package org.example.orissemwork.servlets;

import org.example.orissemwork.db.*;
import org.example.orissemwork.model.*;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;

@WebServlet("/rating")
public class RatingServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {

        Answer answer = AnswerDAO.getById(Integer.parseInt(req.getParameter("idOfAnswer")));
        User author = UserDAO.getByEmail((String) req.getSession().getAttribute("email"));
        String rating = (req.getParameter("rating"));

        //если переданнрое значение null, убираем поставленную пользователем оценку
        if (rating.equals("null")) {
            RatingDAO.deleteFromDB(author, answer);
        } else {
            //если изначальное значение в базе null, сохраяем новую оценку
            if (RatingDAO.getByIdOfUser(author, answer) == null) {
                RatingDAO.saveToDB(author, answer, Boolean.valueOf(rating));
            //если изначальное значение в базе НЕ null, обновляем оценку
            } else  {
                RatingDAO.updateRating(author, answer, Boolean.valueOf(rating));
            }
        }

        resp.sendRedirect(getServletContext().getContextPath() + "/question?id=" + answer.getQuestion().getId());
    }
}