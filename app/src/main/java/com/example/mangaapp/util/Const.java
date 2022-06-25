package com.example.mangaapp.util;

public interface Const {
    interface Database {
        String manga = "Mangas";
        String user = "Users";
        String name = "name";
        String password = "password";
        String phone = "phone";
        String address = "address";
        String gender = "gender";
    }

    interface Sender {
        String name = "name";
        String password = "password";
        String phone = "phone";
        String gender = "gender";
        String address = "address";
        String mangaName = "mangaName";
    }

    interface Error {
        String name = "Please write your name...";
        String password = "Please write your password...";
        String phone = "Please write your phone...";
        String address = "Please write your address...";
        String information = "Wrong information";
        String network = "Network Error: Please try again...";
        String existed = "already exists";
    }

    interface Success {
        String created = "Congratulation, your account has been created";
        String update = "Profile info update successfully";
        String login = "Logged in Successfully";
    }
}
