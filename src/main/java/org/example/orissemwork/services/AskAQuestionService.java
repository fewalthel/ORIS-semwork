package org.example.orissemwork.services;

import org.example.orissemwork.model.Question;
import org.example.orissemwork.db.QuestionDAO;
import org.example.orissemwork.model.User;

import javax.servlet.http.HttpServletRequest;

public class AskAQuestionService {
    public static boolean questionIsAsked(Question question, User author, HttpServletRequest req) {
        if (titleExists(question)) {
            req.setAttribute("error", "A question with this title has already been asked");
            return false;
        } else if (descriptionExists(question)) {
            req.setAttribute("error", "A question with this description has already been asked");
            return false;
        } else {
            save(question, author);
            return true;
        }
    }

    public static boolean titleExists(Question question) {
        return QuestionDAO.getByTitle(question.getTitle()) != null;
    }

    public static boolean descriptionExists(Question question) {
        return QuestionDAO.getByDescription(question.getDescription()) != null;
    }

    public static void save(Question question, User user) {
        QuestionDAO.saveToDB(question, user);
    }
}
