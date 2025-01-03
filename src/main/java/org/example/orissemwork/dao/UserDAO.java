package org.example.orissemwork.dao;

import org.example.orissemwork.model.User;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDAO {

    public DataSource dataSource;

    public UserDAO(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    private static final String SELECT_BY_EMAIL_QUERY = "SELECT * FROM users WHERE email = ?";
    private static final String SELECT_BY_USERNAME_QUERY = "SELECT * FROM users WHERE username = ?";
    private static final String SELECT_BY_ID_QUERY = "SELECT * FROM users WHERE id = ?";
    private static final String SELECT_ALL_QUERY = "SELECT * FROM users";
    private static final String INSERT_USER_QUERY = "INSERT INTO users (email, username, password, role) VALUES (?, ?, ?, ?)";
    private static final String DELETE_USER_QUERY = " DELETE FROM users WHERE id = ?";
    private static final String UPDATE_PASSWORD_QUERY = "UPDATE users SET password = ? WHERE email = ?";
    private static final String UPDATE_USERNAME_QUERY = "UPDATE users SET username = ? WHERE email = ?";
    private static final String UPDATE_ROLE_QUERY = "UPDATE users SET role = ? WHERE email = ?";

    public User getByEmail(String email) throws SQLException {
        User user = null;

        Connection connection = dataSource.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(SELECT_BY_EMAIL_QUERY);

        preparedStatement.setString(1, email);
        ResultSet resultSet = preparedStatement.executeQuery();

        if (resultSet.next()) {
            Integer id = resultSet.getInt("id");
            String emailResult = resultSet.getString("email");
            String username = resultSet.getString("username");
            String password = resultSet.getString("password");
            String role = resultSet.getString("role");

            user = new User(id, emailResult, username, password, role);
        }
        return user;
    }

    public User getByUsername(String username) throws SQLException {
        User user = null;

        Connection connection = dataSource.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(SELECT_BY_USERNAME_QUERY);

        preparedStatement.setString(1, username);
        ResultSet resultSet = preparedStatement.executeQuery();

        if (resultSet.next()) {
            Integer id = resultSet.getInt("id");
            String email = resultSet.getString("email");
            String usernameResult = resultSet.getString("username");
            String password = resultSet.getString("password");
            String role = resultSet.getString("role");

            user = new User(id, email, usernameResult, password, role);
        }
        return user;
    }

    public User getById(Integer id) throws SQLException {
        User user = null;

        Connection connection = dataSource.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(SELECT_BY_ID_QUERY);

        preparedStatement.setInt(1, id);
        ResultSet resultSet = preparedStatement.executeQuery();

        if (resultSet.next()) {
            String email = resultSet.getString("email");
            String username = resultSet.getString("username");
            String password = resultSet.getString("password");
            String role = resultSet.getString("role");

            System.out.println("email: " + email);
            System.out.println("username: " + username);
            System.out.println("password: " + password);
            System.out.println("role: " + role);

            user = new User(id, email, username, password, role);
        }
        return user;
    }

    public List<User> getAll() throws SQLException {
        List<User> users = new ArrayList<>();

        Connection connection = dataSource.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_QUERY);

        ResultSet resultSet = preparedStatement.executeQuery();
        while (resultSet.next()) {
            Integer id = resultSet.getInt("id");
            String email = resultSet.getString("email");
            String username = resultSet.getString("username");
            String password = resultSet.getString("password");
            String role = resultSet.getString("role");

            User user = new User(id, email, username, password, role);
            users.add(user);
        }
        return users;
    }

    public void saveToDB(User user) throws SQLException {
        Connection connection = dataSource.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(INSERT_USER_QUERY);

        preparedStatement.setString(1, user.getEmail());
        preparedStatement.setString(2, user.getUsername());
        preparedStatement.setString(3, user.getPassword());
        preparedStatement.setString(4, user.getRole());

        preparedStatement.executeUpdate();
    }

    public void deleteFromDB(User user) throws SQLException {
        Connection connection = dataSource.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(DELETE_USER_QUERY);

        preparedStatement.setInt(1, user.getId());
        preparedStatement.executeUpdate();
    }

    public void upgradeRole(User user) throws SQLException {
        Connection connection = dataSource.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_ROLE_QUERY);


        preparedStatement.setString(1, "admin");
        preparedStatement.setString(2, user.getEmail());

        preparedStatement.executeUpdate();
    }

    public void updatePassword(User user, String new_password) throws SQLException {
        Connection connection = dataSource.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_PASSWORD_QUERY);

        preparedStatement.setString(1, new_password);
        preparedStatement.setString(2, user.getEmail());

        preparedStatement.executeUpdate();
    }

    public void updateUsername(User user, String new_username) throws SQLException {
        Connection connection = dataSource.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_USERNAME_QUERY);

        preparedStatement.setString(1, new_username);
        preparedStatement.setString(2, user.getEmail());

        preparedStatement.executeUpdate();
    }
}