package org.example.orissemwork.db;

import org.example.orissemwork.model.Category;
import org.example.orissemwork.model.Question;
import org.example.orissemwork.model.User;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class QuestionDAO {

    public DataSource dataSource;

    public QuestionDAO(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    private static final String SELECT_BY_TITLE_QUERY = "SELECT * FROM questions WHERE title = ?";
    private static final String SELECT_BY_DESCRIPTION_QUERY = "SELECT * FROM questions WHERE description = ?";
    private static final String INSERT_QUESTION_QUERY = "INSERT INTO questions (title, description, id_user, id_category) VALUES (?, ?, ?, ?)";
    private static final String SELECT_ALL_QUERY = "SELECT * FROM questions";
    private static final String SELECT_ALL_BY_AUTHOR_QUERY = "SELECT * FROM questions WHERE id_user = ?";
    private static final String SELECT_BY_ID_QUERY = "SELECT * FROM questions WHERE id = ?";
    private static final String SELECT_ALL_BY_CATEGORY_QUERY = "SELECT * FROM questions WHERE id_category = ?";
    private static final String DELETE_QUESTIONS_BY_USER_QUERY = " DELETE FROM questions WHERE id_user = ?";

    public Question getByTitle(String title) throws SQLException {
        Question question = null;

        Connection connection = dataSource.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(SELECT_BY_TITLE_QUERY);


        preparedStatement.setString(1, title);
        ResultSet resultSet = preparedStatement.executeQuery();

        if (resultSet.next()) {
            Integer serialValue = resultSet.getInt("id");
            String titleResult = resultSet.getString("title");
            String description = resultSet.getString("description");

            CategoryDAO categoryDAO = new CategoryDAO(dataSource);
            UserDAO userDAO = new UserDAO(dataSource);

            Category category = categoryDAO.getById(resultSet.getInt("id_category"));
            User author = userDAO.getById(resultSet.getInt("id_user"));
            question = new Question(serialValue, titleResult, description, author, category);
        }

        return question;
    }

    public Question getByDescription(String description) throws SQLException {
        Question question = null;

        Connection connection = dataSource.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(SELECT_BY_DESCRIPTION_QUERY);


        preparedStatement.setString(1, description);
        ResultSet resultSet = preparedStatement.executeQuery();


        if (resultSet.next()) {
            Integer serialValue = resultSet.getInt("id");
            String title = resultSet.getString("title");
            String descriptionResult = resultSet.getString("description");

            CategoryDAO categoryDAO = new CategoryDAO(dataSource);
            UserDAO userDAO = new UserDAO(dataSource);

            Category category = categoryDAO.getById(resultSet.getInt("id_category"));
            User author = userDAO.getById(resultSet.getInt("id_user"));
            question = new Question(serialValue, title, descriptionResult, author, category);
        }

        return question;
    }

    public void saveToDB(Question question, User user) throws SQLException {
        Connection connection = dataSource.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(INSERT_QUESTION_QUERY);


        preparedStatement.setString(1, question.getTitle());
        preparedStatement.setString(2, question.getDescription());
        preparedStatement.setInt(3, user.getId());
        preparedStatement.setInt(4, question.getCategory().getId());

        int rowsAffected = preparedStatement.executeUpdate();
        if (rowsAffected > 0) {
            System.out.println("Вопрос успешно добавлен!");
        }

    }

    public List<Question> getAll() throws SQLException {
        List<Question> questions = new ArrayList<>();

        Connection connection = dataSource.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_QUERY);

        ResultSet resultSet = preparedStatement.executeQuery();

        while (resultSet.next()) {
            Integer serialValue = resultSet.getInt("id");
            String title = resultSet.getString("title");
            String description = resultSet.getString("description");

            CategoryDAO categoryDAO = new CategoryDAO(dataSource);
            UserDAO userDAO = new UserDAO(dataSource);

            Category category = categoryDAO.getById(resultSet.getInt("id_category"));
            User author = userDAO.getById(resultSet.getInt("id_user"));

            Question question = new Question(serialValue, title, description, author, category);
            questions.add(question);
        }

        return questions;
    }

    public List<Question> getAllByAuthor(User author) throws SQLException {
        List<Question> questions = new ArrayList<>();
        Connection connection = dataSource.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_BY_AUTHOR_QUERY);

        preparedStatement.setInt(1, author.getId());
        ResultSet resultSet = preparedStatement.executeQuery();

        while (resultSet.next()) {
            int serialValue = resultSet.getInt("id");
            String title = resultSet.getString("title");
            String description = resultSet.getString("description");

            CategoryDAO categoryDAO = new CategoryDAO(dataSource);

            Category category = categoryDAO.getById(resultSet.getInt("id_category"));
            Question question = new Question(serialValue, title, description, author, category);
            questions.add(question);
        }

        return questions;
    }

    public List<Question> getAllByCategory(Category category) throws SQLException {
        List<Question> questions = new ArrayList<>();

        Connection connection = dataSource.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_BY_CATEGORY_QUERY);

        preparedStatement.setInt(1, category.getId());
        ResultSet resultSet = preparedStatement.executeQuery();

        UserDAO userDAO = new UserDAO(dataSource);

        while (resultSet.next()) {
            int serialValue = resultSet.getInt("id");
            String title = resultSet.getString("title");
            String description = resultSet.getString("description");
            User author = userDAO.getById(resultSet.getInt("id_user"));

            Question question = new Question(serialValue, title, description, author, category);
            questions.add(question);
        }


        return questions;
    }

    public Question getById(Integer id) throws SQLException {
        Question question = null;

        Connection connection = dataSource.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(SELECT_BY_ID_QUERY);

        preparedStatement.setInt(1, id);
        ResultSet resultSet = preparedStatement.executeQuery();

        if (resultSet.next()) {
            Integer serialValue = resultSet.getInt("id");
            String title = resultSet.getString("title");
            String description = resultSet.getString("description");

            CategoryDAO categoryDAO = new CategoryDAO(dataSource);
            UserDAO userDAO = new UserDAO(dataSource);

            Category category = categoryDAO.getById(resultSet.getInt("id_category"));
            User author = userDAO.getById(resultSet.getInt("id_user"));

            question = new Question(serialValue, title, description, author, category);
        }

        return question;
    }

    public void deleteFromDBByAuthor(User user) throws SQLException {
        Connection connection = dataSource.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(DELETE_QUESTIONS_BY_USER_QUERY);

        preparedStatement.setInt(1, user.getId());
        int rowsAffected = preparedStatement.executeUpdate();

        if (rowsAffected > 0) {
            System.out.println("Все вопросы под авторством пользователя удалены.");
        } else {
            System.out.println("Записи с указанным id не найдены.");
        }

    }
}