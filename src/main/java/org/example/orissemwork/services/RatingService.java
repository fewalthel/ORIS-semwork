package org.example.orissemwork.services;

import org.example.orissemwork.db.*;
import org.example.orissemwork.model.*;

import java.sql.SQLException;

public class RatingService {

    private RatingDAO ratingDAO;

    public RatingService(RatingDAO ratingDAO) {
        this.ratingDAO = ratingDAO;
    }

    public void updateRatingOnDB(String rating, User author, Answer answer) throws SQLException {
        //если переданнрое значение null, убираем поставленную пользователем оценку
        if (rating.equals("null")) {
            ratingDAO.deleteFromDB(author, answer);
        } else {
            //если изначальное значение в базе null, сохраяем новую оценку
            if (ratingDAO.getByIdOfUser(author, answer) == null) {
                ratingDAO.saveToDB(author, answer, Boolean.valueOf(rating));
                //если изначальное значение в базе НЕ null, обновляем оценку
            } else  {
                ratingDAO.updateRating(author, answer, Boolean.valueOf(rating));
            }
        }
    }
}
