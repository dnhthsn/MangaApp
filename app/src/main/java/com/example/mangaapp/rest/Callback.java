package com.example.mangaapp.rest;

import com.example.mangaapp.model.Mangas;
import com.example.mangaapp.model.Users;

import java.util.List;

public abstract class Callback {
    public void getUser(List<Users> list){}
    public void getManga(List<Mangas> list){}
}
