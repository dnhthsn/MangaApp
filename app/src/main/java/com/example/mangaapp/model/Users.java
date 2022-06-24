package com.example.mangaapp.model;

import java.io.Serializable;

public class Users implements Serializable {
    private String name, phone, password, address, gender;

    public Users() {
    }

    public Users(String name, String phone, String password, String address, String gender) {
        this.name = name;
        this.phone = phone;
        this.password = password;
        this.address = address;
        this.gender = gender;
    }

    public String getUserName() {
        return name;
    }

    public String getUserPhone() {
        return phone;
    }

    public String getUserPassword() {
        return password;
    }

    public String getUserAddress() {
        return address;
    }

    public String getUserGender() {
        return gender;
    }
}
