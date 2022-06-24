package com.example.mangaapp.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.mangaapp.activity.LoginActivity;
import com.example.mangaapp.prevalent.Prevalent;
import com.example.mangaapp.R;
import com.example.mangaapp.activity.UpdateUserActivity;
import com.example.mangaapp.model.Users;
import com.example.mangaapp.util.Const;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class FragmentInfo extends Fragment {
    private TextView nameInfo, passInfo, phoneInfo, addressInfo, genderInfo;
    private Button editInfomation, logOut;

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
        logOut = view.findViewById(R.id.log_out);

        String name = Prevalent.currentOnlineUser.getUserName();

        final DatabaseReference rootFref;
        rootFref = FirebaseDatabase.getInstance().getReference();

        rootFref.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                Users usersData = snapshot.child(Const.Database.user).child(name).getValue(Users.class);
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
                intent.putExtra(Const.Sender.name, nameInfo.getText());
                intent.putExtra(Const.Sender.phone, phoneInfo.getText());
                intent.putExtra(Const.Sender.password, passInfo.getText());
                intent.putExtra(Const.Sender.address, addressInfo.getText());
                intent.putExtra(Const.Sender.gender, genderInfo.getText());
                startActivity(intent);
            }
        });

        logOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext(), LoginActivity.class);
                startActivity(intent);
            }
        });
    }
}
