package org.example.orissemwork.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import org.json.JSONArray;
import org.json.JSONObject;

public class DatabaseToJson {
    public static void main(String[] args) {
        String url = "jdbc:postgresql://localhost:5432/postgres"; // Замените на ваш URL
        String user = "postgres"; // Замените на ваше имя пользователя
        String password = "postgres"; // Замените на ваш пароль
        String tableName = "users"; // Замените на имя вашей таблицы

        try {
            // Подключение к базе данных
            Connection connection = DriverManager.getConnection(url, user, password);
            Statement statement = connection.createStatement();

            // Выполнение SQL-запроса
            String query = "SELECT * FROM " + tableName;
            ResultSet resultSet = statement.executeQuery(query);

            // Создание JSON массива
            JSONArray jsonArray = new JSONArray();

            // Обработка результата запроса
            while (resultSet.next()) {
                JSONObject jsonObject = new JSONObject();
                int columnCount = resultSet.getMetaData().getColumnCount();

                for (int i = 1; i <= columnCount; i++) {
                    String columnName = resultSet.getMetaData().getColumnName(i);
                    Object columnValue = resultSet.getObject(i);
                    jsonObject.put(columnName, columnValue);
                }
                jsonArray.put(jsonObject);
            }

            // Вывод JSON
            System.out.println(jsonArray.toString(4)); // Форматированный вывод

            // Закрытие ресурсов
            resultSet.close();
            statement.close();
            connection.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
