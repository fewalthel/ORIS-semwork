package org.example.orissemwork.dao;

import org.example.orissemwork.model.*;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class RatingDAO {

    private DataSource dataSource;
    private AnswerDAO answerDAO;

    public RatingDAO(DataSource dataSource) {
        this.dataSource = dataSource;
        this.answerDAO = new AnswerDAO(dataSource);
    }

    private static final String SELECT_BY_USER_QUERY = "SELECT * FROM rating WHERE id_user = ?";
    private static final String SELECT_BY_USER_AND_ANSWER_QUERY = "SELECT * FROM rating WHERE id_user = ? AND id_answer = ?";
    private static final String UPDATE_RATING_QUERY = "UPDATE rating SET is_liked = ? WHERE id_user = ? AND id_answer = ?";
    private static final String INSERT_RATING_QUERY = "INSERT INTO rating(id_user, id_answer, is_liked) VALUES(?, ?, ?)";
    private static final String DELETE_RATING_QUERY = "DELETE FROM rating WHERE id_user = ? AND id_answer = ?";
    private static final String SELECT_ALL_BY_ANSWER_QUERY = "SELECT * FROM rating WHERE id_answer = ? AND is_liked = ?";

    public List<Rating> getAllByUser(User user) throws SQLException {
        List<Rating> ratings = null;

        Connection connection = dataSource.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(SELECT_BY_USER_QUERY);

        preparedStatement.setInt(1, user.getId());

        ResultSet resultSet = preparedStatement.executeQuery();

        if (resultSet.next()) {
            Boolean liked = resultSet.getBoolean("is_liked");
            Answer answer = answerDAO.getById(resultSet.getInt("id_answer"));
            ratings.add(new Rating(answer, user, liked));
        }
        return ratings;
    }

    public Rating getByUserAndAnswer(User user, Answer answer) throws SQLException {

        Rating rating = null;

        Connection connection = dataSource.getConnection();
        PreparedStatement statement = connection.prepareStatement(SELECT_BY_USER_AND_ANSWER_QUERY);

        statement.setInt(1, user.getId());
        statement.setInt(2, answer.getId());

        ResultSet resultSet = statement.executeQuery();

        if (resultSet.next()) {
            Boolean liked = resultSet.getBoolean("is_liked");

            rating = new Rating(answer, user, liked);
        }

        return rating;
    }

    public void updateRating(User user, Answer answer, Boolean liked) throws SQLException {

        Connection connection = dataSource.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_RATING_QUERY);

        preparedStatement.setBoolean(1, liked);
        preparedStatement.setInt(2, user.getId());
        preparedStatement.setInt(3, answer.getId());

        int rowsUpdated = preparedStatement.executeUpdate();

        if (rowsUpdated > 0) {
            System.out.println("Рейтинг успешно обновлен");
        }
    }

    public void saveToDB(User user, Answer answer, Boolean liked) throws SQLException {

        Connection connection = dataSource.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(INSERT_RATING_QUERY);
        try {
            preparedStatement.setInt(1, user.getId());
            preparedStatement.setInt(2, answer.getId());
            preparedStatement.setBoolean(3, liked);

            int rowsAffected = preparedStatement.executeUpdate();

            if (rowsAffected > 0) {
                System.out.println("Лайк или дизлайк успешно сохранен в бд!");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteFromDB(User user, Answer answer) throws SQLException {

        Connection connection = dataSource.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(DELETE_RATING_QUERY);

        preparedStatement.setInt(1, user.getId());
        preparedStatement.setInt(2, answer.getId());

        int rowsAffected = preparedStatement.executeUpdate();

        if (rowsAffected > 0) {
            System.out.println("Запись успешно удалена.");
        } else {
            System.out.println("Запись с указанным id не найдена.");
        }

    }

    public List<Rating> getAllByAnswer(Answer answer, Boolean is_liked) throws SQLException {
        List<Rating> marks = new ArrayList<>();

        Connection connection = dataSource.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_BY_ANSWER_QUERY);


        preparedStatement.setInt(1, answer.getId());
        preparedStatement.setBoolean(2, is_liked);

        ResultSet resultSet = preparedStatement.executeQuery();
        while (resultSet.next()) {
            Integer id_user = resultSet.getInt("id_user");
            Integer id_answer = resultSet.getInt("id_answer");

            AnswerDAO answerDAO = new AnswerDAO(dataSource);
            UserDAO userDAO = new UserDAO(dataSource);

            Rating rating = new Rating(answerDAO.getById(id_answer), userDAO.getById(id_user), is_liked);

            marks.add(rating);
        }
        return marks;
    }
}