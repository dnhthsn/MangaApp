package com.example.mangaapp.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.example.mangaapp.prevalent.Prevalent;
import com.example.mangaapp.R;
import com.example.mangaapp.fragment.FragmentInfo;
import com.example.mangaapp.util.Const;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;

public class UpdateUserActivity extends AppCompatActivity {
    private String uName, uPhone, uPass, uAddress, uGender;

    private EditText inputName, inputPassword, inputPhone, inputAddress;
    private RadioGroup genderGroup;
    private RadioButton genderRad;
    private Button clickUpdate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_user);

        inputName = findViewById(R.id.input_name);
        inputPassword = findViewById(R.id.input_password);
        inputPhone = findViewById(R.id.input_phone);
        inputAddress = findViewById(R.id.input_address);
        clickUpdate = findViewById(R.id.click_update);
        genderGroup = findViewById(R.id.gender_group);

        Intent intent = getIntent();
        uName = intent.getStringExtra(Const.Sender.name);
        uPhone = intent.getStringExtra(Const.Sender.phone);
        uPass = intent.getStringExtra(Const.Sender.password);
        uAddress = intent.getStringExtra(Const.Sender.address);

        inputName.setText(uName);
        inputPassword.setText(uPass);
        inputPhone.setText(uPhone);
        inputAddress.setText(uAddress);

        int genderGrID = genderGroup.getCheckedRadioButtonId();
        genderRad = findViewById(genderGrID);
        uGender = genderRad.getText().toString();

        clickUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DatabaseReference reference = FirebaseDatabase.getInstance().getReference()
                        .child(Const.Database.user);
                HashMap<String, Object> userMap = new HashMap<>();
                userMap.put("userAddress", inputAddress.getText());
                userMap.put("userGender", uGender);
                userMap.put("userName", inputName.getText());
                userMap.put("userPassword", inputPassword.getText());
                userMap.put("userPhone", inputPhone.getText());

                reference.child(Prevalent.currentOnlineUser.getUserName()).updateChildren(userMap);

                startActivity(new Intent(UpdateUserActivity.this, LoginActivity.class));
                Toast.makeText(UpdateUserActivity.this, Const.Success.update, Toast.LENGTH_SHORT).show();
                finish();
            }
        });
    }
}