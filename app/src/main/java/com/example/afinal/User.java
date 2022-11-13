package com.example.afinal;

public class User {
    public String userName, userPhone, userEmail, userPassword;

    public User(){

    }

    public User(String userName, String userEmail, String userPhone, String userPassword){
        this.userName = userName;
        this.userEmail = userEmail;
        this.userPhone = userPhone;
        this.userPassword = userPassword;
    }
}
