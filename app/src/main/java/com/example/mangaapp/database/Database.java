package com.example.mangaapp.database;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.example.mangaapp.prevalent.Prevalent;
import com.example.mangaapp.activity.LoginActivity;
import com.example.mangaapp.activity.MainActivity;
import com.example.mangaapp.model.Users;
import com.example.mangaapp.util.Const;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;

public class Database {
    public static void addUser(Context context, String databaseName, String name, String phone, String password, String address, String gender) {
        final DatabaseReference rootFref;
        rootFref = FirebaseDatabase.getInstance().getReference();
        rootFref.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (!(snapshot.child(databaseName).child(name).exists())) {
                    HashMap<String, Object> userdataMap = new HashMap<>();
                    userdataMap.put(Const.Database.name, name);
                    userdataMap.put(Const.Sender.phone, phone);
                    userdataMap.put(Const.Sender.password, password);
                    userdataMap.put(Const.Sender.address, address);
                    userdataMap.put(Const.Sender.gender, gender);

                    rootFref.child(databaseName).child(name).updateChildren(userdataMap).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if (task.isSuccessful()) {
                                Toast.makeText(context, Const.Success.created, Toast.LENGTH_SHORT).show();

                                Intent intent = new Intent(context, LoginActivity.class);
                                intent.putExtra(Const.Sender.name, name);
                                context.startActivity(intent);
                            } else {
                                Toast.makeText(context, Const.Error.network, Toast.LENGTH_SHORT).show();
                            }
                        }
                    });

                } else {
                    Toast.makeText(context, "This " + name + Const.Error.existed, Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    public static void getUser(Context context, String databaseName, String name, String password) {
        DatabaseReference rootFref;
        rootFref = FirebaseDatabase.getInstance().getReference();

        rootFref.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.child(databaseName).child(name).exists()) {
                    Users usersData = snapshot.child(databaseName).child(name).getValue(Users.class);
                    if (usersData.getUserName().equals(name)) {
                        if (usersData.getUserPassword().equals(password)) {
                            Toast.makeText(context, Const.Success.login, Toast.LENGTH_SHORT).show();

                            Intent intent = new Intent(context, MainActivity.class);
                            Prevalent.currentOnlineUser = usersData;
                            context.startActivity(intent);
                        } else {
                            Toast.makeText(context, Const.Error.passIncorrect, Toast.LENGTH_SHORT).show();
                        }
                    }
                } else {
                    Toast.makeText(context, Const.Error.nameIncorrect, Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}
