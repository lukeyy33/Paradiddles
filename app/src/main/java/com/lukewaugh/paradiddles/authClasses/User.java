package com.lukewaugh.paradiddles.authClasses;


public class User {
    String username, email, password;

    //For creating user by all details
    public User(String username, String password, String email) {
        this.email = email;
        this.username = username;
        this.password = password;
    }
    //For creating with just username and password
    public User (String username, String password) {
        this.username = username;
        this.password = password;
    }
}
