package com.example.todo_app.model;

import java.sql.Timestamp;

public class Task {
    private int idUser;
    private String task;
    private String description;
    private Timestamp dateCreated;


    public Task() {
    }

    public Task(int idUser, String task, String description, Timestamp dateCreated) {
        this.idUser = idUser;
        this.task = task;
        this.description = description;
        this.dateCreated = dateCreated;
    }

    public int getIdUser() {
        return idUser;
    }

    public void setIdUser(int idUser) {
        this.idUser = idUser;
    }

    public String getTask() {
        return task;
    }

    public void setTask(String task) {
        this.task = task;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Timestamp getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(Timestamp dateCreated) {
        this.dateCreated = dateCreated;
    }

}
