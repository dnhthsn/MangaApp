package com.example.mangaapp.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.mangaapp.Prevalent.Prevalent;
import com.example.mangaapp.R;
import com.example.mangaapp.activity.UpdateUserActivity;
import com.example.mangaapp.model.Users;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class FragmentInfo extends Fragment {
    private String databaseName = "Users";
    public static String nameEdit = "name";
    public static String phoneEdit = "phone";
    public static String passEdit = "password";
    public static String addressEdit = "address";
    public static String genderEdit = "gender";

    private TextView nameInfo, passInfo, phoneInfo, addressInfo, genderInfo;
    private Button editInfomation;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_info, container, false);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        nameInfo = view.findViewById(R.id.name_info);
        passInfo = view.findViewById(R.id.password_info);
        phoneInfo = view.findViewById(R.id.phone_info);
        addressInfo = view.findViewById(R.id.address_info);
        genderInfo = view.findViewById(R.id.gender_info);
        editInfomation = view.findViewById(R.id.edit_information);

        String name = Prevalent.currentOnlineUser.getUserName();

        final DatabaseReference rootFref;
        rootFref = FirebaseDatabase.getInstance().getReference();

        rootFref.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                Users usersData = snapshot.child(databaseName).child(name).getValue(Users.class);
                nameInfo.setText(usersData.getUserName());
                phoneInfo.setText(usersData.getUserPhone());
                passInfo.setText(usersData.getUserPassword());
                addressInfo.setText(usersData.getUserAddress());
                genderInfo.setText(usersData.getUserGender());
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        editInfomation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext(), UpdateUserActivity.class);
                intent.putExtra(nameEdit, nameInfo.getText());
                intent.putExtra(phoneEdit, phoneInfo.getText());
                intent.putExtra(passEdit, passInfo.getText());
                intent.putExtra(addressEdit, addressInfo.getText());
                intent.putExtra(genderEdit, genderInfo.getText());
                startActivity(intent);
            }
        });
    }
}
