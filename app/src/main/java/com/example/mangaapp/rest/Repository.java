package com.example.mangaapp.rest;

import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.example.mangaapp.model.Mangas;
import com.example.mangaapp.model.Users;
import com.example.mangaapp.util.Const;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;
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
        databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (!snapshot.child(databaseName).child(users.getName()).exists()) {
                    HashMap<String, Object> userdataMap = new HashMap<>();
                    userdataMap.put(Const.Database.name, users.getName());
                    userdataMap.put(Const.Database.password, users.getPassword());
                    userdataMap.put(Const.Database.phone, users.getPhone());
                    userdataMap.put(Const.Database.address, users.getAddress());
                    userdataMap.put(Const.Database.gender, users.getGender());

                    databaseReference.child(databaseName).child(users.getName()).updateChildren(userdataMap).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if(task.isSuccessful()){
                                Toast.makeText(context, Const.Success.created, Toast.LENGTH_SHORT).show();
                            }
                            else {
                                Toast.makeText(context, Const.Error.network, Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                } else {
                    Toast.makeText(context, Const.Error.existed, Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    public void getManga(String databaseName, Callback callback){
        ValueEventListener postListener = new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                List<Mangas> list = new ArrayList<>();
                Mangas mangas;
                for (DataSnapshot data : snapshot.getChildren()) {
                    mangas = data.getValue(Mangas.class);
                    list.add(mangas);
                }
                callback.getManga(list);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        };
        databaseReference.child(databaseName).addValueEventListener(postListener);
    }
}
