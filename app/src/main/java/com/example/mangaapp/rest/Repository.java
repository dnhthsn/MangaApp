package com.example.mangaapp.rest;

import android.content.Context;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.example.mangaapp.model.Users;
import com.example.mangaapp.util.Const;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class Repository {
    private FirebaseDatabase db;
    private DatabaseReference databaseReference;

    public Repository() {
        this.db = FirebaseDatabase.getInstance();
        this.databaseReference = db.getReference();
    }

    public void getUser(String databaseName, Callback callback) {
        ValueEventListener postListener = new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                List<Users> list = new ArrayList<>();
                Users users;
                for (DataSnapshot data : snapshot.getChildren()) {
                    users = data.getValue(Users.class);
                    list.add(users);
                }
                callback.getUser(list);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        };
        databaseReference.child(databaseName).addValueEventListener(postListener);
    }

    public void addUser(String databaseName, Users users, Context context) {
        ValueEventListener postListener = new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (!snapshot.child(databaseName).child(users.getName()).exists()) {
                    databaseReference.child(databaseName).child(users.getName()).setValue(users);
                } else {
                    Toast.makeText(context, Const.Error.existed, Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        };
        databaseReference.child(databaseName).child(users.getName()).addValueEventListener(postListener);
    }
}
