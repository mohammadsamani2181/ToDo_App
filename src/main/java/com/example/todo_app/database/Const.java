package com.example.todo_app.database;

public class Const {
    //Tables
    final static String USERS_TABLE = "users";
    final static String TASKS_TABLE = "tasks";

    //Users Table Column Names
    final static String USER_ID = "idusers";
    final static String USER_FIRSTNAME = "firstname";
    final static String USER_LASTNAME = "lastname";
    final static String USER_ADDRESS = "address";
    final static String USER_GENDER = "gender";
    final static String USER_USERNAME = "username";
    final static String USER_PASSWORD = "password";

    //Tasks Table Column Names
    final static String TASK_ID = "idtasks";
    final static String TASK_IDUSER = "idusers";
    final static String TASK_TASK = "task";
    final static String TASK_DESCRIPTION = "description";
    final static String TASK_DATECREATED = "dateCreated";
}
