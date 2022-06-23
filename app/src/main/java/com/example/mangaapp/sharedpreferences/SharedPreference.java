package com.example.mangaapp.sharedpreferences;

import android.content.SharedPreferences;

public class SharedPreference {
    public static SharedPreferences sharedPreferences;
    public static String sharedName = "name";
    public static String sharedPass = "password";

    public static void saveUser(String name, String password) {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(sharedName, name);
        editor.putString(sharedPass, password);
        editor.commit();
    }

    public static void removeUser() {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.remove(sharedName);
        editor.remove(sharedPass);
        editor.commit();
    }
}
