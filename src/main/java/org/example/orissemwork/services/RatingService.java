package org.example.orissemwork.services;

import org.example.orissemwork.db.*;
import org.example.orissemwork.model.*;

import java.sql.SQLException;

public class RatingService {

    private RatingDAO ratingDAO;

    public RatingService(RatingDAO ratingDAO) {
        this.ratingDAO = ratingDAO;
    }


    public static void updateRatingOnDB(String rating, User author, Answer answer) throws SQLException {
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
    }
}
