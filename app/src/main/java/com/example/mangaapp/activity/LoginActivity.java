package com.example.mangaapp.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.mangaapp.Prevalent.Prevalent;
import com.example.mangaapp.R;
import com.example.mangaapp.model.Users;
import com.example.mangaapp.sharedpreferences.SharedPreference;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import io.paperdb.Paper;

public class LoginActivity extends AppCompatActivity {
    private final String databaseName = "Users";
    private String nameShared, passShared;

    private EditText inputName, inputPassword;
    private Button clickLogin;
    private TextView createAccount;
    private CheckBox rememberUser;

    private ProgressDialog loadingBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        inputName = findViewById(R.id.input_name);
        inputPassword = findViewById(R.id.input_password);
        clickLogin = findViewById(R.id.click_login);
        createAccount = findViewById(R.id.create_account);
        rememberUser = findViewById(R.id.remember_user);

        Paper.init(this);
        loadingBar = new ProgressDialog(this);

        Intent intent = getIntent();
        String nameIT = intent.getStringExtra("name");
        //String passIT = intent.getStringExtra("pass");

        inputName.setText(nameIT);
        //inputPassword.setText(passIT);

        SharedPreference.sharedPreferences = getSharedPreferences("dataLogin", MODE_PRIVATE);
        nameShared  = SharedPreference.sharedPreferences.getString("name", "");
        passShared = SharedPreference.sharedPreferences.getString("password", "");
        inputName.setText(nameShared);
        inputPassword.setText(passShared);

        clickLogin.setOnClickListener(view -> loginUser());

        createAccount.setOnClickListener(view -> {
            Intent intent1 = new Intent(LoginActivity.this, SignUpActivity.class);
            startActivity(intent1);
        });

        String userNameKey = Paper.book().read(Prevalent.userNameKey);
        String userPasswordKey = Paper.book().read(Prevalent.userPasswordKey);
        if(userNameKey != "" && userPasswordKey != ""){
            if(!TextUtils.isEmpty(userNameKey) && !TextUtils.isEmpty(userPasswordKey)){
                AllowAccess(userNameKey, userPasswordKey);

                loadingBar.setTitle("Already Logged in");
                loadingBar.setMessage("Please wait...");
                loadingBar.setCanceledOnTouchOutside(false);
                loadingBar.show();
            }
        }
    }

    private void AllowAccess(String name, String password) {
        final DatabaseReference rootFref;
        rootFref = FirebaseDatabase.getInstance().getReference();

        rootFref.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(snapshot.child(databaseName).child(name).exists()){
                    Users usersData = snapshot.child(databaseName).child(name).getValue(Users.class);
                    if(usersData.getUserName().equals(name)){
                        if(usersData.getUserPassword().equals(password)){
                            Toast.makeText(LoginActivity.this, "Logged in Successfully", Toast.LENGTH_SHORT).show();
                            loadingBar.dismiss();

                            Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                            Prevalent.currentOnlineUser = usersData;
                            startActivity(intent);
                        }
                        else {
                            loadingBar.dismiss();
                            Toast.makeText(LoginActivity.this, "Password is incorrect", Toast.LENGTH_SHORT).show();
                        }
                    }
                }else {
                    Toast.makeText(LoginActivity.this, "Account with this " + name + "number do not exist", Toast.LENGTH_SHORT).show();
                    loadingBar.dismiss();
                    Toast.makeText(LoginActivity.this, "You need to create a new account", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }


    private void loginUser() {
        String name = inputName.getText().toString();
        String password = inputPassword.getText().toString();

        if (TextUtils.isEmpty(name)) {
            Toast.makeText(this, "Please write your name...", Toast.LENGTH_SHORT).show();
        } else if (TextUtils.isEmpty(password)) {
            Toast.makeText(this, "Please write your password...", Toast.LENGTH_SHORT).show();
        } else {
            loadingBar.setTitle("Login Account");
            loadingBar.setMessage("Please wait, while we are checking the credentials");
            loadingBar.setCanceledOnTouchOutside(false);
            loadingBar.show();

            AllowAccesToAccount(name, password);
        }
    }

    private void AllowAccesToAccount(String name, String password) {
        if (rememberUser.isChecked()){
            SharedPreference.saveUser(name, password);
            Paper.book().write(Prevalent.userNameKey, name);
            Paper.book().write(Prevalent.userPasswordKey, password);
        }

        final DatabaseReference rootRef = FirebaseDatabase.getInstance().getReference();

        rootRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                if (snapshot.child(databaseName).child(name).exists()) {
                    Users usersData = snapshot.child(databaseName).child(name).getValue(Users.class);
                    if (usersData.getUserName().equals(name)) {
                        if (usersData.getUserPassword().equals(password)) {
                            Toast.makeText(LoginActivity.this, "Logged in Successfully", Toast.LENGTH_SHORT).show();
                            loadingBar.dismiss();

                            Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                            Prevalent.currentOnlineUser = usersData;
                            intent.putExtra("name", name);
                            startActivity(intent);
                        } else {
                            loadingBar.dismiss();
                            Toast.makeText(LoginActivity.this, "Password is incorrect", Toast.LENGTH_SHORT).show();
                        }
                    }
                } else {
                    Toast.makeText(LoginActivity.this, "Account do not exist", Toast.LENGTH_SHORT).show();
                    loadingBar.dismiss();
                    Toast.makeText(LoginActivity.this, "You need to create a new account", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
            }
        });
    }
}