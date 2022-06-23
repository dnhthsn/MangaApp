package com.example.mangaapp.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.mangaapp.R;
import com.example.mangaapp.activity.LoginActivity;

import io.paperdb.Paper;

public class FragmentSetting extends Fragment {
    private Button logOut, logOut1, logOut2, logOut3, logOut4, logOut5, logOut6, logOut7;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_setting, container, false);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        logOut = view.findViewById(R.id.log_out);
        logOut1 = view.findViewById(R.id.log_out1);
        logOut2 = view.findViewById(R.id.log_out2);
        logOut3 = view.findViewById(R.id.log_out3);
        logOut4 = view.findViewById(R.id.log_out4);
        logOut5 = view.findViewById(R.id.log_out5);
        logOut6 = view.findViewById(R.id.log_out6);
        logOut7 = view.findViewById(R.id.log_out7);

        logOutButtons();
    }

    public void logOutButtons() {
        logOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Paper.book().destroy();
                Intent intent = new Intent(getContext(), LoginActivity.class);
                startActivity(intent);
            }
        });

        logOut1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                logOut2.setVisibility(View.VISIBLE);
                logOut1.setVisibility(View.INVISIBLE);
            }
        });

        logOut2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                logOut7.setVisibility(View.VISIBLE);
                logOut2.setVisibility(View.INVISIBLE);
            }
        });

        logOut7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                logOut6.setVisibility(View.VISIBLE);
                logOut7.setVisibility(View.INVISIBLE);
            }
        });

        logOut6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                logOut3.setVisibility(View.VISIBLE);
                logOut6.setVisibility(View.INVISIBLE);
            }
        });

        logOut3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                logOut5.setVisibility(View.VISIBLE);
                logOut3.setVisibility(View.INVISIBLE);
            }
        });

        logOut5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                logOut4.setVisibility(View.VISIBLE);
                logOut5.setVisibility(View.INVISIBLE);
            }
        });

        logOut4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                logOut.setVisibility(View.VISIBLE);
                logOut4.setVisibility(View.INVISIBLE);
            }
        });
    }
}
