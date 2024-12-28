package org.example.orissemwork.db;

import org.example.orissemwork.model.Category;
import org.example.orissemwork.model.FileInfo;
import org.example.orissemwork.model.Question;
import org.example.orissemwork.model.User;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class FileInfoDAO {

    private DataSource dataSource;

    private static final String INSERT_FILEINFO_QUERY = "INSERT INTO avatars (storage_file_name, original_file_name, type, size, id_user) VALUES (?, ?, ?, ?, ?)";
    private static final String SELECT_BY_ID_USER_QUERY = " SELECT * FROM avatars WHERE id_user = ?";
    private static final String DELETE_AVATAR_QUERY = "DELETE FROM avatars WHERE id_user = ?";

    public FileInfoDAO(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public void save(FileInfo file, User user) throws SQLException {
        Connection connection = dataSource.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(INSERT_FILEINFO_QUERY);

        preparedStatement.setString(1, file.getStorageFileName());
        preparedStatement.setString(2, file.getOriginalFileName());
        preparedStatement.setString(3, file.getType());
        preparedStatement.setLong(4, file.getSize());
        preparedStatement.setInt(5, user.getId());

        int rowsAffected = preparedStatement.executeUpdate();
        if (rowsAffected > 0) {
            System.out.println("Информация о файле успешно сохранена!");
        }

    }

    public FileInfo findByIdUser(Integer id_user) throws SQLException {
        FileInfo fileInfo = null;

        Connection connection = dataSource.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(SELECT_BY_ID_USER_QUERY);


        preparedStatement.setInt(1, id_user);
        ResultSet resultSet = preparedStatement.executeQuery();

        if (resultSet.next()) {
            Integer id = resultSet.getInt("id");
            String storageFileName = resultSet.getString("storage_file_name");
            String originalFileName = resultSet.getString("original_file_name");
            String type = resultSet.getString("type");
            long size = resultSet.getLong("size");

            fileInfo = new FileInfo(id, originalFileName, storageFileName, size, type);
        }
        return fileInfo;
    }


    public void deleteFromDB(User user) throws SQLException {
        Connection connection = dataSource.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(DELETE_AVATAR_QUERY);

        try {
            preparedStatement.setInt(1, user.getId());
            int rowsAffected = preparedStatement.executeUpdate();

            if (rowsAffected > 0) {
                System.out.println("Запись успешно удалена.");
            } else {
                System.out.println("Запись с указанным id не найдена.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
