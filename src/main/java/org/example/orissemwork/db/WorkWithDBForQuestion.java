package org.example.orissemwork.db;

import org.example.orissemwork.model.Question;
import org.example.orissemwork.model.User;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class WorkWithDBForQuestion {
    private static final String URL = "jdbc:postgresql://localhost:5432/postgres";
    private static final String USER = "postgres";
    private static final String PASSWORD = "postgres";


    public static Question getQuestionByTitle(String title)  {
        Question question = null;
        String query = "SELECT * FROM questions WHERE title = ?";

        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setString(1, title);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                String titleResult = resultSet.getString("title");
                String description = resultSet.getString("description");
                String usernameOfAuthor = resultSet.getString("author");

                User author = new User(null, usernameOfAuthor, null);
                question = new Question(titleResult, description, author);
            }
        } catch (SQLException e) {
            System.err.println("Ошибка при попытке выгрузить данные о загаловке вопроса: " + e.getMessage());
        }
        return question;
    }

    public static Question getQuestionByDescription(String description)  {
        Question question = null;
        String query = "SELECT * FROM questions WHERE description = ?";

        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setString(1, description);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                String title = resultSet.getString("title");
                String descriptionResult = resultSet.getString("description");
                String usernameOfAuthor = resultSet.getString("author");

                User author = new User(null, usernameOfAuthor, null);
                question = new Question(title, descriptionResult, author);
            }
        } catch (SQLException e) {
            System.err.println("Ошибка при попытке выгрузить данные об описании вопроса: " + e.getMessage());
        }
        return question;
    }

    public static void saveQuestionToDB(Question question) {
        String query = "INSERT INTO questions (title, description, author) VALUES (?, ?, ?)";

        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setString(1, question.getTitle());
            preparedStatement.setString(2, question.getDescription());
            preparedStatement.setString(3, "example");
            /*preparedStatement.setString(3, question.getAuthor().getUsername());*/

            int rowsAffected = preparedStatement.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Вопрос успешно добавлен!");
            }

        } catch (SQLException e) {
            System.err.println("Ошибка при сохранении вопроса: " + e.getMessage());
        }
    }

    public static List<Question> getAllQuestions() {
        List<Question> questions = new ArrayList<>();
        String query = "SELECT * FROM questions";

        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement statement = connection.prepareStatement(query)) {


            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                String title = resultSet.getString("title");
                String description = resultSet.getString("description");
                String author = resultSet.getString("author");

                User authorUser = new User(null, author, null);
                Question question = new Question(title, description, authorUser);
                questions.add(question);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return questions;
    }
}