package com.example.mangaapp.model;

import java.io.Serializable;

public class Users {
    private String userName, userPhone, userPassword, userAddress, userGender;

    public Users() {
    }

    public Users(String userName, String userPhone, String userPassword, String userAddress, String userGender) {
        this.userName = userName;
        this.userPhone = userPhone;
        this.userPassword = userPassword;
        this.userAddress = userAddress;
        this.userGender = userGender;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserPhone() {
        return userPhone;
    }

    public void setUserPhone(String userPhone) {
        this.userPhone = userPhone;
    }

    public String getUserPassword() {
        return userPassword;
    }

    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }

    public String getUserAddress() {
        return userAddress;
    }

    public void setUserAddress(String userAddress) {
        this.userAddress = userAddress;
    }

    public String getUserGender() {
        return userGender;
    }

    public void setUserGender(String userGender) {
        this.userGender = userGender;
    }
}
