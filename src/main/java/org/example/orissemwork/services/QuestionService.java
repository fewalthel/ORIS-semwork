package org.example.orissemwork.services;

import org.example.orissemwork.dao.UserDAO;
import org.example.orissemwork.model.Question;
import org.example.orissemwork.dao.QuestionDAO;
import org.example.orissemwork.model.User;

import javax.servlet.http.HttpServletRequest;
import java.sql.SQLException;


public class QuestionService {

    public QuestionDAO questionDAO;

    public QuestionService(QuestionDAO questionDAO) {
        this.questionDAO = questionDAO;
    }

    public boolean questionIsAsked(Question question, HttpServletRequest req) throws SQLException {
        if (titleExists(question)) {
            req.setAttribute("error", "A question with this title has already been asked");
            return false;
        } else if (descriptionExists(question)) {
            req.setAttribute("error", "A question with this description has already been asked");
            return false;
        } else {
            UserDAO userDAO = new UserDAO(questionDAO.dataSource);
            User author = userDAO.getByEmail((String) req.getSession().getAttribute("email"));
            save(question, author);
            return true;
        }
    }

    public boolean titleExists(Question question) throws SQLException {
        return questionDAO.getByTitle(question.getTitle()) != null;
    }

    public boolean descriptionExists(Question question) throws SQLException {
        return questionDAO.getByDescription(question.getDescription()) != null;
    }

    public void save(Question question, User user) throws SQLException {
        questionDAO.saveToDB(question, user);
    }
}
