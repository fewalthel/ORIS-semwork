package org.example.orissemwork.db;

import org.example.orissemwork.model.Answer;
import org.example.orissemwork.model.Question;
import org.example.orissemwork.model.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class WorkWithDBForAnswer {
    private static final String URL = "jdbc:postgresql://localhost:5432/postgres";
    private static final String USER = "postgres";
    private static final String PASSWORD = "postgres";

    public static Answer getAnswersForThisQuestion(Answer answer, String title_of_question) {
        Answer ans = null;
        String query = "SELECT * FROM answers WHERE question = ? AND content = ?";

        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setString(1, title_of_question);
            preparedStatement.setString(2, answer.getContent());

            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                Integer serialValue = resultSet.getInt("id");
                Question question = WorkWithDBForQuestion.getQuestionByTitle(resultSet.getString("question"));
                String content = resultSet.getString("content");
                User author = WorkWithDBForUser.getUserByEmail(resultSet.getString("author"));

                ans = new Answer(serialValue, question, content, author);
            }
        } catch (SQLException e) {
            System.err.println("Ошибка при попытке выгрузить данные о вопросе: " + e.getMessage());
        }
        return ans;
    }

    public static void saveAnswerToDB(Answer ans) {
        String query = "INSERT INTO answers (question, content, author) VALUES (?, ?, ?)";

        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setString(1, ans.getQuestion().getTitle());
            preparedStatement.setString(2, ans.getContent());
            preparedStatement.setString(3, ans.getAuthor().getEmail());

            int rowsAffected = preparedStatement.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Ответ на вопрос успешно добавлен!");
            }

        } catch (SQLException e) {
            System.err.println("Ошибка при сохранении вопроса: " + e.getMessage());
        }
    }

    public static List<Answer> getAllAnswersByQuestion(Question question) {
        List<Answer> answers = new ArrayList<>();
        String query = "SELECT * FROM answers WHERE question = ?";

        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement statement = connection.prepareStatement(query)) {

            statement.setString(1, question.getTitle());
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                int serialValue = resultSet.getInt("id");
                String questionRes = resultSet.getString("question");
                String content = resultSet.getString("content");
                String author = resultSet.getString("author");

                Answer answer = new Answer(serialValue, WorkWithDBForQuestion.getQuestionByTitle(questionRes), content, WorkWithDBForUser.getUserByEmail(author));
                answers.add(answer);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return answers;
    }

    public static List<Answer> getAnswersByAuthor(String author) {
        List<Answer> answers = new ArrayList<>();
        String query = "SELECT * FROM answers WHERE author = ?";

        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement statement = connection.prepareStatement(query)) {

            statement.setString(1, author);
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                int serialValue = resultSet.getInt("id");
                String questionRes = resultSet.getString("question");
                String content = resultSet.getString("content");

                Answer answer = new Answer(serialValue, WorkWithDBForQuestion.getQuestionByTitle(questionRes), content, WorkWithDBForUser.getUserByEmail(author));
                answers.add(answer);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return answers;
    }

}
