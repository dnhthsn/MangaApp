package com.example.mangaapp.activity;

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

import com.example.mangaapp.prevalent.Prevalent;
import com.example.mangaapp.R;
import com.example.mangaapp.database.Database;
import com.example.mangaapp.sharedpreferences.SharedPreference;

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
        String nameIT = intent.getStringExtra(Database.putName);

        inputName.setText(nameIT);

        SharedPreference.sharedPreferences = getSharedPreferences("dataLogin", MODE_PRIVATE);
        nameShared = SharedPreference.sharedPreferences.getString(SharedPreference.sharedName, "");
        passShared = SharedPreference.sharedPreferences.getString(SharedPreference.sharedPass, "");
        inputName.setText(nameShared);
        inputPassword.setText(passShared);

        clickLogin.setOnClickListener(view -> loginUser());

        createAccount.setOnClickListener(view -> {
            Intent intent1 = new Intent(LoginActivity.this, SignUpActivity.class);
            startActivity(intent1);
        });

        String userNameKey = Paper.book().read(Prevalent.userNameKey);
        String userPasswordKey = Paper.book().read(Prevalent.userPasswordKey);
        if (userNameKey != "" && userPasswordKey != "") {
            if (!TextUtils.isEmpty(userNameKey) && !TextUtils.isEmpty(userPasswordKey)) {
                Database.getUser(LoginActivity.this, databaseName, userNameKey, userPasswordKey);

                loadingBar.setTitle("Already Logged in");
                loadingBar.setMessage("Please wait...");
                loadingBar.setCanceledOnTouchOutside(false);
                loadingBar.show();
            }
        }
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

            if (rememberUser.isChecked()) {
                SharedPreference.saveUser(name, password);
                Paper.book().write(Prevalent.userNameKey, name);
                Paper.book().write(Prevalent.userPasswordKey, password);
            } else {
                SharedPreference.removeUser();
            }

            Database.getUser(LoginActivity.this, databaseName, name, password);
        }
    }
}