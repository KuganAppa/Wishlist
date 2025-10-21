package com.kuganappa.wishlist.model;

import java.time.LocalDate;

public class User {
    private final int userId;
    private String name;
    private String email;
    private String password;
    private final LocalDate dateOfBirth;

    public User(int userId, String name, String email, String password, LocalDate dateOfBirth) {
        this.userId = userId;
        this.name = name;
        this.email = email;
        this.password = password;
        this.dateOfBirth = dateOfBirth;
    }

    public int getUserId() {
        return userId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }
}
