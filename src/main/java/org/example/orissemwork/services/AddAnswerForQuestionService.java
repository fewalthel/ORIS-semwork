package org.example.orissemwork.services;

import org.example.orissemwork.db.AnswerDAO;
import org.example.orissemwork.db.RatingDAO;
import org.example.orissemwork.db.UserDAO;
import org.example.orissemwork.model.Answer;
import org.example.orissemwork.model.User;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;

public class AddAnswerForQuestionService {
    public static boolean answerIsGiven (Answer answer, String title_of_question, HttpServletRequest req) {
        if (answerForThisQuestionExists(answer, title_of_question)) {
            req.setAttribute("error", "This answer for this question already exists");
            return false;
        } else {
            saveAnswer(answer);
            return true;
        }
    }

    public static boolean answerForThisQuestionExists (Answer answer, String title_of_question) {
        return AnswerDAO.getForThisQuestion(answer, title_of_question) != null;
    }

    public static void saveAnswer (Answer answer) {
        AnswerDAO.saveToDB(answer);

        ArrayList<User> users = (ArrayList<User>) UserDAO.getAll();
        for (User user : users) {
            RatingDAO.saveDefaultToDB(user, answer);
        }
    }
}
