package com.example.mangaapp.activity;

import androidx.appcompat.app.AppCompatActivity;

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
import com.example.mangaapp.util.Const;

public class LoginActivity extends AppCompatActivity {
    private String name, pass;

    private EditText inputName, inputPassword;
    private Button clickLogin;
    private TextView createAccount;
    private CheckBox rememberUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        inputName = findViewById(R.id.input_name);
        inputPassword = findViewById(R.id.input_password);
        clickLogin = findViewById(R.id.click_login);
        createAccount = findViewById(R.id.create_account);
        rememberUser = findViewById(R.id.remember_user);

        SharedPreference.sharedPreferences = getSharedPreferences("dataLogin", MODE_PRIVATE);
        name = SharedPreference.sharedPreferences.getString(Const.Sender.name, "");
        pass = SharedPreference.sharedPreferences.getString(Const.Sender.password, "");

        Intent intent = getIntent();
        String nameIT = intent.getStringExtra(Const.Sender.name);

        inputName.setText(!TextUtils.isEmpty(name) ? name:nameIT);
        inputPassword.setText(pass);

        clickLogin.setOnClickListener(view -> loginUser());

        createAccount.setOnClickListener(view -> {
            Intent intent1 = new Intent(LoginActivity.this, SignUpActivity.class);
            startActivity(intent1);
        });
    }

    private void loginUser() {
        String name = inputName.getText().toString();
        String password = inputPassword.getText().toString();

        if (TextUtils.isEmpty(name)) {
            Toast.makeText(this, Const.Error.name, Toast.LENGTH_SHORT).show();
        } else if (TextUtils.isEmpty(password)) {
            Toast.makeText(this, Const.Error.password, Toast.LENGTH_SHORT).show();
        } else {
            if (rememberUser.isChecked()) {
                SharedPreference.saveUser(name, password);
            } else {
                SharedPreference.removeUser();
            }
            Database.getUser(LoginActivity.this, Const.Database.user, name, password);
        }
    }
}