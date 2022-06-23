package com.example.mangaapp.sharedpreferences;

import android.content.SharedPreferences;

public class SharedPreference {
    public static SharedPreferences sharedPreferences;
    public static void saveUser(String name, String password){
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("name", name);
        editor.putString("password", password);
        editor.commit();
    }

    public static void removeUser(){
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.remove("name");
        editor.remove("password");
        editor.commit();
    }
}
