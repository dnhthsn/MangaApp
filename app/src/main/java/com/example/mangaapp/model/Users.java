package com.example.mangaapp.model;

import java.io.Serializable;

public class Users implements Serializable {
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

    public String getUserPhone() {
        return userPhone;
    }

    public String getUserPassword() {
        return userPassword;
    }

    public String getUserAddress() {
        return userAddress;
    }

    public String getUserGender() {
        return userGender;
    }
}
