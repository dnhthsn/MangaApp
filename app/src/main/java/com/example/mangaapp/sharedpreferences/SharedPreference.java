package com.example.mangaapp.sharedpreferences;

import android.content.SharedPreferences;

import com.example.mangaapp.util.Const;

public class SharedPreference {
    public static SharedPreferences sharedPreferences;

    public static void saveUser(String name, String password) {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(Const.Sender.name, name);
        editor.putString(Const.Sender.password, password);
        editor.commit();
    }

    public static void removeUser() {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.remove(Const.Sender.name);
        editor.remove(Const.Sender.password);
        editor.commit();
    }
}
