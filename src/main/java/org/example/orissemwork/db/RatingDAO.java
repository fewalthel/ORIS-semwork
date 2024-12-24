package org.example.orissemwork.db;

import org.example.orissemwork.model.*;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class RatingDAO implements DAO {

    private static DataSource dataSource;

    public RatingDAO(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    private static final String SELECT_BY_ID_USER_QUERY = "SELECT * FROM rating WHERE id_user = ? AND id_answer = ?";
    private static final String UPDATE_RATING_QUERY = "UPDATE rating SET is_liked = ? WHERE id_user = ? AND id_answer = ?";
    private static final String INSERT_RATING_QUERY = "INSERT INTO rating(id_user, id_answer, is_liked) VALUES(?, ?, ?)";
    private static final String DELETE_RATING_QUERY = "DELETE FROM rating WHERE id_user = ? AND id_answer = ?";
    private static final String SELECT_ALL_BY_ANSWER_QUERY = "SELECT * FROM rating WHERE id_answer = ? AND is_liked = ?";

    public static Rating getByIdOfUser(User user, Answer answer) throws SQLException {
        Rating rating = null;

        Connection connection = dataSource.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(SELECT_BY_ID_USER_QUERY);

        try {
            preparedStatement.setInt(1, user.getId());
            preparedStatement.setInt(2, answer.getId());

            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                Boolean liked = resultSet.getBoolean("is_liked");
                rating = new Rating(answer, user, liked);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return rating;
    }

    public static void updateRating(User user, Answer answer, Boolean liked) throws SQLException {

        Connection connection = dataSource.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_RATING_QUERY);

        try {
            preparedStatement.setBoolean(1, liked);
            preparedStatement.setInt(2, user.getId());
            preparedStatement.setInt(3, answer.getId());

            int rowsUpdated = preparedStatement.executeUpdate();

            if (rowsUpdated > 0) {
                System.out.println("Рейтинг успешно обновлен");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void saveToDB(User user, Answer answer, Boolean liked) throws SQLException {

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

    public static void deleteFromDB(User user, Answer answer) throws SQLException {

        Connection connection = dataSource.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(DELETE_RATING_QUERY);

        try {
            preparedStatement.setInt(1, user.getId());
            preparedStatement.setInt(2, answer.getId());

            int rowsAffected = preparedStatement.executeUpdate();

            if (rowsAffected > 0) {
                System.out.println("Запись успешно удалена.");
            } else {
                System.out.println("Запись с указанным id не найдена.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static List<Rating> getAllByAnswer(Answer answer, Boolean is_liked) throws SQLException {
        List<Rating> marks = new ArrayList<>();

        Connection connection = dataSource.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_BY_ANSWER_QUERY);

        try {
            preparedStatement.setInt(1, answer.getId());
            preparedStatement.setBoolean(2, is_liked);

            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Integer id_user = resultSet.getInt("id_user");
                Integer id_answer = resultSet.getInt("id_answer");

                Rating rating = new Rating(AnswerDAO.getById(id_answer), UserDAO.getById(id_user), is_liked);

                marks.add(rating);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return marks;
    }
}