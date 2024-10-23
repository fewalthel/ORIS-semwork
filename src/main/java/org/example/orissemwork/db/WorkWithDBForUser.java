package org.example.orissemwork.db;

import org.example.orissemwork.model.User;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class WorkWithDBForUser {
    private static final String URL = "jdbc:postgresql://localhost:5432/postgres";
    private static final String USER = "postgres";
    private static final String PASSWORD = "postgres";


    public static boolean findUserByEmailAndPassword(String email, String password) {
        String query = "SELECT email, password FROM users WHERE email = ? AND password = ?";
        User user = null;

        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setString(1, email);
            preparedStatement.setString(2, password);

            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                String foundEmail = resultSet.getString("email");
                String foundPassword = resultSet.getString("password");
                user = new User(foundEmail, null, foundPassword);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return user != null;
    }

    public static User getUserByEmail(String email) {
        String query = "SELECT * FROM users WHERE email = ?";
        User user = null;

        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

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
        String query = "SELECT * FROM users WHERE username = ?";
        User user = null;

        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

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
        String query = "INSERT INTO users (email, username, password) VALUES (?, ?, ?)";

        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

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

    public static void updatePassword(User user, String new_password) {
        String query = "UPDATE users SET password = ? WHERE email = ?";

        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setString(1, new_password);
            preparedStatement.setString(2, user.getEmail());

            int rowsUpdated = preparedStatement.executeUpdate();

            if (rowsUpdated > 0) {
                System.out.println("Пароль успешно обновлен");
            }

        } catch (SQLException e) {
            System.err.println("Ошибка при обновлении пароля: "+ e.getMessage());
        }
    }

    public static void updateUsername(User user, String new_username) {
        String query = "UPDATE users SET username = ? WHERE email = ?";

        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setString(1, new_username);
            preparedStatement.setString(2, user.getEmail());

            int rowsUpdated = preparedStatement.executeUpdate();

            if (rowsUpdated > 0) {
                System.out.println("Username успешно обновлен");
            }

        } catch (SQLException e) {
            System.err.println("Ошибка при обновлении username: "+ e.getMessage());
        }
    }

}