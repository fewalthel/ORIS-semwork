package org.example.orissemwork.services;

import org.example.orissemwork.db.*;
import org.example.orissemwork.model.*;

import javax.servlet.http.HttpServletRequest;
import java.sql.SQLException;


public class AnswerService {

    private AnswerDAO answerDAO;

    public AnswerService(AnswerDAO answerDAO) {
        this.answerDAO = answerDAO;
    }

    public boolean answerIsGiven(Answer answer, String title_of_question, HttpServletRequest req) throws SQLException {
        if (answerForThisQuestionExists(answer, title_of_question)) {
            req.setAttribute("error", "This answer for this question already exists");
            return false;
        } else {
            saveAnswer(answer);
            return true;
        }
    }

    public boolean answerForThisQuestionExists(Answer answer, String title_of_question) throws SQLException {
        return answerDAO.getForThisQuestion(answer, title_of_question) != null;
    }

    public void saveAnswer(Answer answer) throws SQLException {
        answerDAO.saveToDB(answer);
    }
}
