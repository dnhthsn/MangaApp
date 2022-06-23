package com.example.mangaapp.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.example.mangaapp.R;
import com.example.mangaapp.database.Database;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;

public class SignUpActivity extends AppCompatActivity {
    private String gender, name, phone, password, address;
    private String databaseName = "Users";

    private EditText inputName, inputPassword, inputPhone, inputAddress;
    private RadioGroup genderGroup;
    private RadioButton genderRad;
    private Button clickSignup;

    private ProgressDialog loadingBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        inputName = findViewById(R.id.input_name);
        inputPassword = findViewById(R.id.input_password);
        inputPhone = findViewById(R.id.input_phone);
        inputAddress = findViewById(R.id.input_address);
        clickSignup = findViewById(R.id.click_sign_up);
        genderGroup = findViewById(R.id.gender_group);

        loadingBar = new ProgressDialog(this);

        clickSignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                CreateAccount();
            }
        });
    }

    private void CreateAccount() {
        name = inputName.getText().toString();
        phone = inputPhone.getText().toString();
        password = inputPassword.getText().toString();
        address = inputAddress.getText().toString();

        int genderGrID = genderGroup.getCheckedRadioButtonId();
        genderRad = findViewById(genderGrID);
        gender = genderRad.getText().toString();

        if (TextUtils.isEmpty(name)) {
            Toast.makeText(this, "Please write your name...", Toast.LENGTH_SHORT).show();
        } else if (TextUtils.isEmpty(phone)) {
            Toast.makeText(this, "Please write your phone...", Toast.LENGTH_SHORT).show();
        } else if (TextUtils.isEmpty(password)) {
            Toast.makeText(this, "Please write your password...", Toast.LENGTH_SHORT).show();
        } else if (TextUtils.isEmpty(address)) {
            Toast.makeText(this, "Please write your address...", Toast.LENGTH_SHORT).show();
        } else {
            loadingBar.setTitle("Create Account");
            loadingBar.setMessage("Please wait, while we are checking the credentials");
            loadingBar.setCanceledOnTouchOutside(false);
            loadingBar.show();

            Database.addUser(SignUpActivity.this, databaseName, name, phone, password, address, gender);
        }
    }
}