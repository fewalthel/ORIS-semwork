package org.example.orissemwork.db;

import org.example.orissemwork.model.*;

import java.sql.*;

public class CategoryDAO implements DAO {
    private static final String SELECT_BY_NAME_QUERY = "SELECT * FROM categories WHERE name = ?";
    private static final String SELECT_BY_ID_QUERY = "SELECT * FROM categories WHERE id = ?";

    public static Category getByName(String name) {
        Category category = null;

        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_BY_NAME_QUERY)) {

            preparedStatement.setString(1, name);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                Integer serialValue = resultSet.getInt("id");
                category = new Category(serialValue, name);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return category;
    }


    public static Category getById(Integer id) {
        Category category = null;

        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_BY_ID_QUERY)) {

            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                String name = resultSet.getString("name");
                category = new Category(id, name);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return category;
    }

}