package org.example.orissemwork.services;

import org.example.orissemwork.db.*;
import org.example.orissemwork.model.*;

import javax.servlet.http.HttpServletRequest;

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
    }
}
