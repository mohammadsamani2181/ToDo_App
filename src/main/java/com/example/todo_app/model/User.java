package com.example.todo_app.model;

public class User {
    private String firstname;
    private String lastname;
    private String username;
    private String password;
    private String address;
    private String gender;

    public User() {
    }

    public User(String firstname, String lastname, String username, String password, String address, String gender) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.username = username;
        this.password = password;
        this.address = address;
        this.gender = gender;
    }

    public String getFirstname() {
        return firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getAddress() {
        return address;
    }

    public String getGender() {
        return gender;
    }
}
