package com.example.mangaapp.Prevalent;

import com.example.mangaapp.model.Mangas;
import com.example.mangaapp.model.Users;

import java.util.ArrayList;
import java.util.List;

public class Prevalent {
    public static Users currentOnlineUser;
    public static List<Mangas> favouriteMangas = new ArrayList<>();
    public static final String userNameKey = "UserName";
    public static final String userPasswordKey = "UserPassword";
}
