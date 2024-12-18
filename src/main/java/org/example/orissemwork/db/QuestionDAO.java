package org.example.orissemwork.db;

import org.example.orissemwork.model.Category;
import org.example.orissemwork.model.Question;
import org.example.orissemwork.model.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class QuestionDAO implements DAO {
    private static final String SELECT_BY_TITLE_QUERY = "SELECT * FROM questions WHERE title = ?";
    private static final String SELECT_BY_DESCRIPTION_QUERY = "SELECT * FROM questions WHERE description = ?";
    private static final String INSERT_QUESTION_QUERY = "INSERT INTO questions (title, description, id_user, id_category) VALUES (?, ?, ?, ?)";
    private static final String SELECT_ALL_QUERY = "SELECT * FROM questions";
    private static final String SELECT_ALL_BY_AUTHOR_QUERY = "SELECT * FROM questions WHERE id_user = ?";
    private static final String SELECT_BY_ID_QUERY = "SELECT * FROM questions WHERE id = ?";
    private static final String SELECT_ALL_BY_CATEGORY_QUERY = "SELECT * FROM questions WHERE id_category = ?";
    private static final String DELETE_QUESTIONS_BY_USER_QUERY = " DELETE FROM questions WHERE id_user = ?";

    public static Question getByTitle(String title) {
        Question question = null;

        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_BY_TITLE_QUERY)) {

            preparedStatement.setString(1, title);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                Integer serialValue = resultSet.getInt("id");
                String titleResult = resultSet.getString("title");
                String description = resultSet.getString("description");

                Category category = CategoryDAO.getById(resultSet.getInt("id_category"));
                User author = UserDAO.getById(resultSet.getInt("id_user"));
                question = new Question(serialValue, titleResult, description, author, category);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return question;
    }

    public static Question getByDescription(String description) {
        Question question = null;

        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_BY_DESCRIPTION_QUERY)) {

            preparedStatement.setString(1, description);
            ResultSet resultSet = preparedStatement.executeQuery();


            if (resultSet.next()) {
                Integer serialValue = resultSet.getInt("id");
                String title = resultSet.getString("title");
                String descriptionResult = resultSet.getString("description");

                Category category = CategoryDAO.getById(resultSet.getInt("id_category"));
                User author = UserDAO.getById(resultSet.getInt("id_user"));
                question = new Question(serialValue, title, descriptionResult, author, category);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return question;
    }

    public static void saveToDB(Question question, User user) {
        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement preparedStatement = connection.prepareStatement(INSERT_QUESTION_QUERY)) {

            preparedStatement.setString(1, question.getTitle());
            preparedStatement.setString(2, question.getDescription());
            preparedStatement.setInt(3, user.getId());
            preparedStatement.setInt(4, question.getCategory().getId());

            int rowsAffected = preparedStatement.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Вопрос успешно добавлен!");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static List<Question> getAll() {
        List<Question> questions = new ArrayList<>();

        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement statement = connection.prepareStatement(SELECT_ALL_QUERY)) {

            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                Integer serialValue = resultSet.getInt("id");
                String title = resultSet.getString("title");
                String description = resultSet.getString("description");

                Category category = CategoryDAO.getById(resultSet.getInt("id_category"));
                User author = UserDAO.getById(resultSet.getInt("id_user"));

                Question question = new Question(serialValue, title, description, author, category);
                questions.add(question);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return questions;
    }

    public static List<Question> getAllByAuthor(User author) {
        List<Question> questions = new ArrayList<>();

        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement statement = connection.prepareStatement(SELECT_ALL_BY_AUTHOR_QUERY)) {

            statement.setInt(1, author.getId());
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                int serialValue = resultSet.getInt("id");
                String title = resultSet.getString("title");
                String description = resultSet.getString("description");

                Category category = CategoryDAO.getById(resultSet.getInt("id_category"));
                Question question = new Question(serialValue, title, description, author, category);
                questions.add(question);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return questions;
    }

    public static List<Question> getAllByCategory(Category category) {
        List<Question> questions = new ArrayList<>();

        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement statement = connection.prepareStatement(SELECT_ALL_BY_CATEGORY_QUERY)) {

            statement.setInt(1, category.getId());
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                int serialValue = resultSet.getInt("id");
                String title = resultSet.getString("title");
                String description = resultSet.getString("description");
                User author = UserDAO.getById(resultSet.getInt("id_user"));

                Question question = new Question(serialValue, title, description, author, category);
                questions.add(question);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return questions;
    }

    public static Question getById(Integer id) {
        Question question = null;

        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement statement = connection.prepareStatement(SELECT_BY_ID_QUERY)) {

            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                Integer serialValue = resultSet.getInt("id");
                String title = resultSet.getString("title");
                String description = resultSet.getString("description");

                Category category = CategoryDAO.getById(resultSet.getInt("id_category"));
                User author = UserDAO.getById(resultSet.getInt("id_user"));

                question = new Question(serialValue, title, description, author, category);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return question;
    }

    public static void deleteFromDBByAuthor(User user) {
        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement preparedStatement = connection.prepareStatement(DELETE_QUESTIONS_BY_USER_QUERY)) {

            preparedStatement.setInt(1, user.getId());
            int rowsAffected = preparedStatement.executeUpdate();

            if (rowsAffected > 0) {
                System.out.println("Все вопросы под авторством пользователя удалены.");
            } else {
                System.out.println("Записи с указанным id не найдены.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}