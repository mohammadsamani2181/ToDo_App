package com.example.todo_app.database;
import com.example.todo_app.model.User;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class DBHandler extends Configs{
    private Connection dbConnection;

    public Connection getDbConnection() throws ClassNotFoundException, SQLException {
        String connectionString = "jdbc:mysql://" + dbHost +
                ":" + dbPort +
                "/" + dbName;

        Class.forName("com.mysql.jdbc.Driver");

        dbConnection = DriverManager.getConnection(connectionString , dbUser, dbPassword);

        return dbConnection;
    }

    public void createNewUser (User user) throws SQLException, ClassNotFoundException {
        String insert = "INSERT INTO users(firstname, lastname, address, gender, password, username)" +
                "VALUES(?, ?, ?, ?, ?, ?)";

        PreparedStatement preparedStatement = getDbConnection().prepareStatement(insert);

        preparedStatement.setString(1, user.getFirstname());
        preparedStatement.setString(2, user.getLastname());
        preparedStatement.setString(3, user.getAddress());
        preparedStatement.setString(4, user.getGender());
        preparedStatement.setString(5, user.getPassword());
        preparedStatement.setString(6, user.getUsername());

        preparedStatement.executeUpdate();
    }
}
