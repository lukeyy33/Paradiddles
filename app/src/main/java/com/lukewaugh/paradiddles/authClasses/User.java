package com.lukewaugh.paradiddles.authClasses;


public class User {
    String username, email, password;
    int age;

    //For creating user by all details
    public User(String username, String email, String password, int age) {
        this.email = email;
        this.username = username;
        this.age = age;
        this.password = password;
    }
    //For creating with just username and password
    public User (String username, String password) {
        this.username = username;
        this.password = password;
    }
}
