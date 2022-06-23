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

        logOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Paper.book().destroy();
                Intent intent = new Intent(getContext(), LoginActivity.class);
                startActivity(intent);
            }
        });
    }
}
