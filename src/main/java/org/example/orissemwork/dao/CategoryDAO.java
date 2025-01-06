package org.example.orissemwork.dao;

import org.example.orissemwork.model.*;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CategoryDAO {

    private static DataSource dataSource;

    public CategoryDAO(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    private static final String SELECT_BY_NAME_QUERY = "SELECT * FROM categories WHERE name = ?";
    private static final String SELECT_BY_ID_QUERY = "SELECT * FROM categories WHERE id = ?";
    private static final String SELECT_ALL_QUERY = "SELECT * FROM categories";
    private static final String INSERT_CATEGORY_QUERY = "INSERT INTO categories (id, name) VALUES (?, ?)";

    public static Category getByName(String name) throws SQLException {
        Category category = null;

        Connection connection = dataSource.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(SELECT_BY_NAME_QUERY);

        preparedStatement.setString(1, name);
        ResultSet resultSet = preparedStatement.executeQuery();

        if (resultSet.next()) {
            Integer id = resultSet.getInt("id");
            category = Category.builder().id(id).name(name).build();
        }
        return category;
    }


    public Category getById(Integer id) throws SQLException {
        Category category = null;

        Connection connection = dataSource.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(SELECT_BY_ID_QUERY);

        preparedStatement.setInt(1, id);
        ResultSet resultSet = preparedStatement.executeQuery();

        if (resultSet.next()) {
            String name = resultSet.getString("name");
            category = Category.builder().id(id).name(name).build();
        }
        return category;
    }

    public List<Category> getAll() throws SQLException {

        List<Category> categories = new ArrayList<>();

        Connection connection = dataSource.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_QUERY);

        ResultSet resultSet = preparedStatement.executeQuery();

        while (resultSet.next()) {
            Integer id = resultSet.getInt("id");
            String name = resultSet.getString("name");

            Category category = Category.builder().id(id).name(name).build();
            categories.add(category);
        }
        return categories;
    }

    public void saveToDB(Category category) throws SQLException {
        Connection connection = dataSource.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(INSERT_CATEGORY_QUERY);

        preparedStatement.setInt(1, category.getId());
        preparedStatement.setString(2, category.getName());

        preparedStatement.executeUpdate();
    }
}