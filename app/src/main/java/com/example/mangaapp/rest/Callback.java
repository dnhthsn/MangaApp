package com.example.mangaapp.rest;

import com.example.mangaapp.model.Users;

import java.util.List;

public interface Callback {
    void getUser(List<Users> list);
}
