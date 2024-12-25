package org.example.orissemwork.db;

import org.example.orissemwork.model.*;

import javax.sql.DataSource;
import java.sql.*;

public class CategoryDAO {

    private static DataSource dataSource;

    public CategoryDAO(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    private static final String SELECT_BY_NAME_QUERY = "SELECT * FROM categories WHERE name = ?";
    private static final String SELECT_BY_ID_QUERY = "SELECT * FROM categories WHERE id = ?";

    public static Category getByName(String name) throws SQLException {

        Category category = null;

        Connection connection = dataSource.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(SELECT_BY_NAME_QUERY);

        try {
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


    public Category getById(Integer id) throws SQLException {
        Category category = null;

        Connection connection = dataSource.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(SELECT_BY_ID_QUERY);

        try {
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