package org.example.orissemwork.db;

import org.example.orissemwork.model.User;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class WorkWithDB {
    public static final String URL = "jdbc:postgresql://localhost:5432/postgres";
    public static final String USER = "postgres";
    public static final String PASSWORD = "postgres";

/*
    public static void main(String[] args) {
*/
/*        User user = new User("nagibator", "nagibator", "nagibator");
        saveUserToDB(user);*//*

        if (getUserByUsername("ronnicon") != null) {
            System.out.println("User already exists");
        } else {
            System.out.println("User don't exist");
        }
    }
*/


    public static User getUserByEmail(String email) {
        String selectSQL = "SELECT * FROM users WHERE email = ?";
        User user = null;

        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement preparedStatement = connection.prepareStatement(selectSQL)) {

            preparedStatement.setString(1, email);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                String emailResult = resultSet.getString("email");
                String username = resultSet.getString("username");
                String password = resultSet.getString("password");

                user = new User(emailResult, username, password);
            }

        } catch (SQLException e) {
            System.err.println("Ошибка при попытке выгрузить данные о почте: " + e.getMessage());
        }

        return user;
    }

    public static User getUserByUsername(String username) {
        String selectSQL = "SELECT * FROM users WHERE username = ?";
        User user = null;

        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement preparedStatement = connection.prepareStatement(selectSQL)) {

            preparedStatement.setString(1, username);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                String email = resultSet.getString("email");
                String usernameResult = resultSet.getString("username");
                String password = resultSet.getString("password");

                user = new User(email, usernameResult, password);
            }

        } catch (SQLException e) {
            System.err.println("Ошибка при попытке выгрузить данные о username: " + e.getMessage());
        }

        return user;
    }

    public static void saveUserToDB(User user) {
        String insertSQL = "INSERT INTO users (email, username, password) VALUES (?, ?, ?)";

        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement preparedStatement = connection.prepareStatement(insertSQL)) {

            preparedStatement.setString(1, user.getEmail());
            preparedStatement.setString(2, user.getUsername());
            preparedStatement.setString(3, user.getPassword());

            int rowsAffected = preparedStatement.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Пользователь успешно добавлен!");
            }

        } catch (SQLException e) {
            System.err.println("Ошибка при сохранении пользователя: " + e.getMessage());
        }
    }

}