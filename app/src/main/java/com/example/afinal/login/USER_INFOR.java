package com.example.afinal.login;

public class USER_INFOR {

    public String userName, userPhone, userEmail, userPassword;

    public USER_INFOR(){}

    public USER_INFOR(String userName, String userEmail, String userPhone, String userPassword){
        this.userName = userName;
        this.userEmail = userEmail;
        this.userPhone = userPhone;
        this.userPassword = userPassword;
    }

    public String getUserName() {
        return userName;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public String getUserPhone() {
        return userPhone;
    }

    public String getUserPassword() {
        return userPassword;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public void setUserPhone(String userPhone) {
        this.userPhone = userPhone;
    }

    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }
}
