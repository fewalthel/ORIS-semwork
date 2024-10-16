package org.example.orissemwork.services;

import org.example.orissemwork.model.Question;
import org.example.orissemwork.db.WorkWithDBForQuestion;

import javax.servlet.http.HttpServletRequest;

public class AskAQuestionService {
    //TODO: прописать методы для проверки title и description отправляемого пользоваетелем вопроса

    public static boolean questionIsAsked(Question question, HttpServletRequest req) {
        if (titleExists(question)) {
            req.setAttribute("error", "Вопрос с таким заголовком уже задан");
            System.out.println("Вопрос с таким заголовком уже задан");
            return false;
        } else if (descriptionExists(question)) {
            req.setAttribute("error", "Вопрос с таким описание уже задан");
            System.out.println("Вопрос с таким описанием уже задан");
            return false;
        } else {
            save(question);
            return true;
        }
    }

    public static boolean titleExists(Question question) {
        return WorkWithDBForQuestion.getQuestionByTitle(question.getTitle()) != null;
    }

    public static boolean descriptionExists(Question question) {
        return WorkWithDBForQuestion.getQuestionByDescription(question.getDescription()) != null;
    }

    public static void save(Question question) {
        WorkWithDBForQuestion.saveQuestionToDB(question);
    }
}
