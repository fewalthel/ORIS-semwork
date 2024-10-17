package org.example.orissemwork.db;

import org.example.orissemwork.model.User;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class WorkWithDB {
    private static final String URL = "jdbc:postgresql://localhost:5432/postgres";
    private static final String USER = "postgres";
    private static final String PASSWORD = "postgres";

    public static void main(String[] args) {

        if (getUserByField("email", "fewalthel@gmail.com") != null) {
            System.out.println("User already exists");
        } else {
            System.out.println("User don't exist");
        }
    }

    public static User getUserByField(String fieldName, String value) {
        String selectSQL = "SELECT * FROM users WHERE " + fieldName + " = ?";
        User user = null;

        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement preparedStatement = connection.prepareStatement(selectSQL)) {

            preparedStatement.setString(1, value);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                String emailResult = resultSet.getString("email");
                String username = resultSet.getString("username");
                String password = resultSet.getString("password");

                user = new User(emailResult, username, password);
            }

        } catch (SQLException e) {
            System.err.println("Ошибка при работе с базой данных: " + e.getMessage());
        }

        return user;
    }

    public static void saveUserToDB(User user) {
        String column1Value = user.getEmail();
        String column2Value = user.getUsername();
        String column3Value = user.getPassword();

        String insertSQL = "INSERT INTO users (email, username, password) VALUES (?, ?, ?)";

        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement preparedStatement = connection.prepareStatement(insertSQL)) {

            preparedStatement.setString(1, column1Value);
            preparedStatement.setString(2, column2Value);
            preparedStatement.setString(3, column3Value);

            int rowsAffected = preparedStatement.executeUpdate();
            System.out.println("Вставка выполнена успешно!");

        } catch (SQLException e) {
            System.err.println("Ошибка при работе с базой данных: " + e.getMessage());
        }
    }

}