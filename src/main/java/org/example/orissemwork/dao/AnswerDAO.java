package org.example.orissemwork.dao;

import org.example.orissemwork.model.*;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AnswerDAO {

    private DataSource dataSource;

    public AnswerDAO(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    private static final String SELECT_BY_TITLE_OF_QUESTION_QUERY = "SELECT answers.* FROM questions INNER JOIN answers ON answers.id_question = questions.id WHERE questions.title = ? AND answers.content = ?";
    private static final String INSERT_ANSWER_QUERY = "INSERT INTO answers (id_question, content, id_user) VALUES (?, ?, ?)";
    private static final String SELECT_ALL_ANSWERS_BY_QUESTION_QUERY = "SELECT * FROM answers WHERE id_question = ?";
    private static final String SELECT_ALL_ANSWERS_BY_AUTHOR_QUERY = "SELECT * FROM answers WHERE id_user = ?";
    private static final String SELECT_FAVOURITES_ANSWERS_QUERY = "SELECT * FROM favourites_answers WHERE id_user = ?";
    private static final String SELECT_BY_ID_QUERY = "SELECT * FROM answers WHERE id = ?";
    private static final String INSERT_INTO_FAVOURITES_QUERY = "INSERT INTO favourites_answers (id_user, id_answer) VALUES (?, ?)";
    private static final String DELETE_FAVOURITES_QUERY = "DELETE FROM favourites_answers WHERE id_user = ? AND id_answer = ?";
    private static final String SELECT_FROM_FAVS_BY_USER_AND_ANSWER_QUERY = "SELECT * FROM favourites_answers WHERE id_user = ? AND id_answer = ?";
    private static final String DELETE_FROM_DB_QUERY = " DELETE FROM answers WHERE id = ?";

    public Answer getForThisQuestion(Answer answer, String title_of_question) throws SQLException {
        Answer ans = null;

        Connection connection = dataSource.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(SELECT_BY_TITLE_OF_QUESTION_QUERY);

        preparedStatement.setString(1, title_of_question);
        preparedStatement.setString(2, answer.getContent());

        ResultSet resultSet = preparedStatement.executeQuery();

        QuestionDAO questionDAO = new QuestionDAO(dataSource);
        UserDAO userDAO = new UserDAO(dataSource);

        if (resultSet.next()) {
            Integer serialValue = resultSet.getInt("id");
            Question question = questionDAO.getByTitle(resultSet.getString("question"));
            String content = resultSet.getString("content");
            User author = userDAO.getByEmail(resultSet.getString("author"));

            ans = new Answer(serialValue, question, content, author);
        }
        return ans;
    }

    public void saveToDB(Answer ans) throws SQLException {
        Connection connection = dataSource.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(INSERT_ANSWER_QUERY);

        preparedStatement.setInt(1, ans.getQuestion().getId());
        preparedStatement.setString(2, ans.getContent());
        preparedStatement.setInt(3, ans.getAuthor().getId());

        preparedStatement.executeUpdate();
    }

    public List<Answer> getAllByQuestion(Question question) throws SQLException {
        List<Answer> answers = new ArrayList<>();

        Connection connection = dataSource.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_ANSWERS_BY_QUESTION_QUERY);

        preparedStatement.setInt(1, question.getId());
        ResultSet resultSet = preparedStatement.executeQuery();

        QuestionDAO questionDAO = new QuestionDAO(dataSource);
        UserDAO userDAO = new UserDAO(dataSource);

        while (resultSet.next()) {
            Integer serialValue = resultSet.getInt("id");
            Integer questionId = resultSet.getInt("id_question");
            String content = resultSet.getString("content");
            Integer authorId = resultSet.getInt("id_user");

            Answer answer = new Answer(serialValue, questionDAO.getById(questionId), content, userDAO.getById(authorId));
            answers.add(answer);
        }
        return answers;
    }

    public List<Answer> getAllByAuthor(User author) throws SQLException {
        List<Answer> answers = new ArrayList<>();

        Connection connection = dataSource.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_ANSWERS_BY_AUTHOR_QUERY);

        preparedStatement.setInt(1, author.getId());
        ResultSet resultSet = preparedStatement.executeQuery();

        QuestionDAO questionDAO = new QuestionDAO(dataSource);

        while (resultSet.next()) {
            Integer serialValue = resultSet.getInt("id");
            Integer id_question = resultSet.getInt("id_question");
            String content = resultSet.getString("content");

            Answer answer = new Answer(serialValue, questionDAO.getById(id_question), content, author);
            answers.add(answer);
        }
        return answers;
    }

    public List<Answer> getFavoriteAnswers(User user) throws SQLException {
        List<Answer> answers = new ArrayList<>();

        Connection connection = dataSource.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(SELECT_FAVOURITES_ANSWERS_QUERY);

        preparedStatement.setInt(1, user.getId());
        ResultSet resultSet = preparedStatement.executeQuery();

        while (resultSet.next()) {
            Integer idAnswer = resultSet.getInt("id_answer");

            Answer answer = getById(idAnswer);
            answers.add(answer);
        }
        return answers;
    }

    public Answer getById(Integer id) throws SQLException {
        Answer ans = null;

        Connection connection = dataSource.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(SELECT_BY_ID_QUERY);

        preparedStatement.setInt(1, id);

        ResultSet resultSet = preparedStatement.executeQuery();

        QuestionDAO questionDAO = new QuestionDAO(dataSource);
        UserDAO userDAO = new UserDAO(dataSource);

        if (resultSet.next()) {
            Question question = questionDAO.getById(resultSet.getInt("id_question"));
            String content = resultSet.getString("content");
            User author = userDAO.getById(resultSet.getInt("id_user"));

            ans = new Answer(id, question, content, author);
        }
        return ans;
    }

    public void saveToFavorites(Answer ans, User user) throws SQLException {
        Connection connection = dataSource.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(INSERT_INTO_FAVOURITES_QUERY);

        preparedStatement.setInt(1, user.getId());
        preparedStatement.setInt(2, ans.getId());

        preparedStatement.executeUpdate();
    }

    public void removeFromFavorites(Answer ans, User user) throws SQLException {
        Connection connection = dataSource.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(DELETE_FAVOURITES_QUERY);

        preparedStatement.setInt(1, user.getId());
        preparedStatement.setInt(2, ans.getId());

        preparedStatement.executeUpdate();
    }

    public boolean ansInFavForUser(Answer ans, User user) throws SQLException {
        String result = null;

        Connection connection = dataSource.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(SELECT_FROM_FAVS_BY_USER_AND_ANSWER_QUERY);

        preparedStatement.setInt(1, user.getId());
        preparedStatement.setInt(2, ans.getId());
        ResultSet resultSet = preparedStatement.executeQuery();

        if (resultSet.next()) {
            String foundEmail = resultSet.getString("id_user");
            String foundPassword = resultSet.getString("id_answer");

            result = foundEmail + foundPassword;
        }
        return (result != null && !result.equals(""));
    }

    public void deleteFromDBB(Answer ans) throws SQLException {
        Connection connection = dataSource.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(DELETE_FROM_DB_QUERY);

        preparedStatement.setInt(1, ans.getId());
        preparedStatement.executeUpdate();

    }
}