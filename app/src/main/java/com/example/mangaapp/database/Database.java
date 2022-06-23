package com.example.mangaapp.database;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.example.mangaapp.Prevalent.Prevalent;
import com.example.mangaapp.activity.LoginActivity;
import com.example.mangaapp.activity.MainActivity;
import com.example.mangaapp.activity.SignUpActivity;
import com.example.mangaapp.model.Users;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;

public class Database {
    private static ProgressDialog loadingBar;
    public static void getAllMangas(){

    }

    public static void addUser(Context context, String databaseName, String name, String phone, String password, String address, String gender){
        loadingBar = new ProgressDialog(context);
        final DatabaseReference rootFref;
        rootFref = FirebaseDatabase.getInstance().getReference();
        rootFref.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (!(snapshot.child(databaseName).child(name).exists())) {
                    HashMap<String, Object> userdataMap = new HashMap<>();
                    userdataMap.put("userName", name);
                    userdataMap.put("userPhone", phone);
                    userdataMap.put("userPassword", password);
                    userdataMap.put("userAddress", address);
                    userdataMap.put("userGender", gender);

                    rootFref.child(databaseName).child(name).updateChildren(userdataMap).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if (task.isSuccessful()) {
                                Toast.makeText(context, "Congratulation, your account has been created", Toast.LENGTH_SHORT).show();
                                loadingBar.dismiss();

                                Intent intent = new Intent(context, LoginActivity.class);
                                intent.putExtra("name", name);
                                intent.putExtra("pass", password);
                                context.startActivity(intent);
                            } else {
                                loadingBar.dismiss();
                                Toast.makeText(context, "Network Error: Please try again...", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });

                } else {
                    Toast.makeText(context, "This " + name + "already exists", Toast.LENGTH_SHORT).show();
                    loadingBar.dismiss();
                    Toast.makeText(context, "Please try using another phone number", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    public static void getUser(Context context, String databaseName, String name, String password){
        loadingBar = new ProgressDialog(context);
        final DatabaseReference rootFref;
        rootFref = FirebaseDatabase.getInstance().getReference();

        rootFref.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(snapshot.child(databaseName).child(name).exists()){
                    Users usersData = snapshot.child(databaseName).child(name).getValue(Users.class);
                    if(usersData.getUserName().equals(name)){
                        if(usersData.getUserPassword().equals(password)){
                            Toast.makeText(context, "Logged in Successfully", Toast.LENGTH_SHORT).show();
                            loadingBar.dismiss();

                            Intent intent = new Intent(context, MainActivity.class);
                            Prevalent.currentOnlineUser = usersData;
                            context.startActivity(intent);
                        }
                        else {
                            loadingBar.dismiss();
                            Toast.makeText(context, "Password is incorrect", Toast.LENGTH_SHORT).show();
                        }
                    }
                }else {
                    Toast.makeText(context, "Account with this " + name + "number do not exist", Toast.LENGTH_SHORT).show();
                    loadingBar.dismiss();
                    Toast.makeText(context, "You need to create a new account", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}
