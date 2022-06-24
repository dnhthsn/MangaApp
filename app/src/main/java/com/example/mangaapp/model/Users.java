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

    public String getName() {
        return name;
    }

    public String getPhone() {
        return phone;
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
