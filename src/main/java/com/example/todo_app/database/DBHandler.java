package com.example.todo_app.database;
import com.example.todo_app.model.Task;
import com.example.todo_app.model.User;

import java.sql.*;

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
        String insert = "INSERT INTO " + Const.USERS_TABLE +
                "(" + Const.USER_FIRSTNAME + ", " + Const.USER_LASTNAME
                +", " + Const.USER_ADDRESS +", "+ Const.USER_GENDER +", "
                + Const.USER_PASSWORD + ", " + Const.USER_USERNAME + ")"
                + "VALUES(?, ?, ?, ?, ?, ?)";


        PreparedStatement preparedStatement = getDbConnection().prepareStatement(insert);

        preparedStatement.setString(1, user.getFirstname());
        preparedStatement.setString(2, user.getLastname());
        preparedStatement.setString(3, user.getAddress());
        preparedStatement.setString(4, user.getGender());
        preparedStatement.setString(5, user.getPassword());
        preparedStatement.setString(6, user.getUsername());

        preparedStatement.executeUpdate();
    }

    public ResultSet findUser (User user) throws SQLException, ClassNotFoundException {
        String query = "SELECT * FROM " + Const.USERS_TABLE
                + " WHERE "
                + Const.USER_USERNAME + "=?"
                + " AND "
                + Const.USER_PASSWORD + "=?";

        PreparedStatement preparedStatement = getDbConnection().prepareStatement(query);
        preparedStatement.setString(1, user.getUsername());
        preparedStatement.setString(2, user.getPassword());

        ResultSet resultSet = preparedStatement.executeQuery();

        return resultSet;
    }

    public int addNewTask (Task task) throws SQLException, ClassNotFoundException {
        String insert = "INSERT INTO " + Const.TASKS_TABLE
                + "(" + Const.TASK_IDUSER + ", " + Const.TASK_TASK
                + ", " + Const.TASK_DESCRIPTION + ", " + Const.TASK_DATECREATED + ")"
                + "VALUES(?, ?, ?, ?)";

        PreparedStatement preparedStatement = getDbConnection().prepareStatement(insert);

        preparedStatement.setInt(1, task.getIdUser());
        preparedStatement.setString(2, task.getTask());
        preparedStatement.setString(3, task.getDescription());
        preparedStatement.setTimestamp(4, task.getDateCreated());

        preparedStatement.executeUpdate();

        return getIdTask(task);
    }

    private int getIdTask (Task task) throws SQLException, ClassNotFoundException {
        String query = "SELECT idtasks FROM " + Const.TASKS_TABLE +
                " WHERE " + Const.TASK_IDUSER + "=?" +
                " AND " + Const.TASK_DATECREATED + "=?";

        PreparedStatement preparedStatement = getDbConnection().prepareStatement(query);

        preparedStatement.setInt(1, task.getIdUser());
        preparedStatement.setTimestamp(2, task.getDateCreated());

        ResultSet resultSet = preparedStatement.executeQuery();

        int idTask = 0;

        while (resultSet.next()) {
            idTask = resultSet.getInt("idtasks");
        }

        return idTask;
    }

    public ResultSet getTasksByUserId (User user) throws SQLException, ClassNotFoundException {
        String query = "SELECT * FROM " + Const.TASKS_TABLE +
                " WHERE " + Const.TASK_IDUSER + "=?";

        PreparedStatement preparedStatement = getDbConnection().prepareStatement(query);
        preparedStatement.setInt(1, user.getId());

        ResultSet resultSet = preparedStatement.executeQuery();

        return  resultSet;
    }

    public void deleteTask (Task task) throws SQLException, ClassNotFoundException {
        String query = "DELETE FROM " + Const.TASKS_TABLE +
                " WHERE " + Const.TASK_ID + "=?";

        PreparedStatement preparedStatement = getDbConnection().prepareStatement(query);

        preparedStatement.setInt(1, task.getIdTask());
        preparedStatement.execute();
        preparedStatement.close();
    }

    public void updateTask (Task oldTask, Task newTask) throws SQLException, ClassNotFoundException {
        String query = "UPDATE " + Const.TASKS_TABLE +
                " SET " + Const.TASK_TASK + "=?" +
                " , " + Const.TASK_DESCRIPTION + "=?" +
                " , " + Const.TASK_DATECREATED + "=?" +
                " WHERE " + Const.TASK_ID + "=?";

        PreparedStatement preparedStatement = getDbConnection().prepareStatement(query);

        newTask.setIdTask(oldTask.getIdTask());
        newTask.setIdUser(oldTask.getIdUser());

        preparedStatement.setString(1, newTask.getTask());
        preparedStatement.setString(2, newTask.getDescription());
        preparedStatement.setTimestamp(3, newTask.getDateCreated());
        preparedStatement.setInt(4, oldTask.getIdTask());

        preparedStatement.executeUpdate();
        preparedStatement.close();
    }
}
