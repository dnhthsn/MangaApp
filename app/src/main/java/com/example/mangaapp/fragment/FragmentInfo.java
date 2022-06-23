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

import com.example.mangaapp.R;
import com.example.mangaapp.activity.UpdateUserActivity;

public class FragmentInfo extends Fragment {
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

        editInfomation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext(), UpdateUserActivity.class);
                startActivity(intent);
            }
        });
    }
}
