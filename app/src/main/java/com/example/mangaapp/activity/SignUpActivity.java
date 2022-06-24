package com.example.mangaapp.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
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
import com.example.mangaapp.util.Const;

public class SignUpActivity extends AppCompatActivity {
    private String gender, name, phone, password, address;

    private EditText inputName, inputPassword, inputPhone, inputAddress;
    private RadioGroup genderGroup;
    private RadioButton genderRad;
    private Button clickSignup;

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
            Toast.makeText(this, Const.Error.name, Toast.LENGTH_SHORT).show();
        } else if (TextUtils.isEmpty(phone)) {
            Toast.makeText(this, Const.Error.phone, Toast.LENGTH_SHORT).show();
        } else if (TextUtils.isEmpty(password)) {
            Toast.makeText(this, Const.Error.password, Toast.LENGTH_SHORT).show();
        } else if (TextUtils.isEmpty(address)) {
            Toast.makeText(this, Const.Error.address, Toast.LENGTH_SHORT).show();
        } else {
            Database.addUser(SignUpActivity.this, Const.Database.user, name, phone, password, address, gender);
        }
    }
}