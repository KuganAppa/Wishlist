package com.kuganappa.wishlist.model;

import java.time.LocalDate;

public class User {
    private int userId;
    private String userName;
    private String email;
    private String password;
    private final LocalDate dateOfBirth;

    public User(String userName, String email, String password, LocalDate dateOfBirth) {
        this.userName = userName;
        this.email = email;
        this.password = password;
        this.dateOfBirth = dateOfBirth;
    }

    public void setUserId(int userId){
        this.userId = userId;
    }

    public int getUserId() {
        return userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setName(String name) {
        this.userName = name;
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

    @Override
    public String toString() {
        return "User{" +
                "userId=" + userId +
                ", userName='" + userName + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", dateOfBirth=" + dateOfBirth +
                '}';
    }
}
