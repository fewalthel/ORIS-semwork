package org.example.orissemwork.db;

import org.example.orissemwork.model.*;

import java.sql.*;

public class RatingDAO implements DAO {
    private static final String SELECT_BY_ID_USER_QUERY = "SELECT * FROM rating WHERE id_user = ? AND id_answer = ?";
    private static final String UPDATE_RATING_QUERY = "UPDATE rating SET liked = ? WHERE id_user = ? AND id_answer = ?";
    private static final String INSERT_RATING_QUERY = "INSERT INTO rating(id_user, id_answer, liked) VALUES(?, ?, null)";

    public static Rating getByIdOfUser(User user, Answer answer) {
        Rating rating = null;

        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement statement = connection.prepareStatement(SELECT_BY_ID_USER_QUERY)) {

            statement.setInt(1, user.getId());
            statement.setInt(2, answer.getId());

            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                Boolean liked = resultSet.getBoolean("liked");

                rating = new Rating(answer, user, liked);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return rating;
    }

    public static void updateRating(User user, Answer answer, Boolean liked) {
        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_RATING_QUERY)) {

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

    public static void saveDefaultToDB(User user, Answer answer) {
        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement preparedStatement = connection.prepareStatement(INSERT_RATING_QUERY)) {

            preparedStatement.setInt(1, answer.getId());
            preparedStatement.setInt(2, user.getId());


            int rowsAffected = preparedStatement.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Рейтинг успешно добавлен!");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}