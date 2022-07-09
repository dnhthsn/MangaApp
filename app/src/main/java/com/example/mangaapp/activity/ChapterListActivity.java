package com.example.mangaapp.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;

import com.example.mangaapp.R;
import com.example.mangaapp.fragment.FragmentHome;
import com.example.mangaapp.model.Mangas;
import com.example.mangaapp.util.Const;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class ChapterListActivity extends AppCompatActivity {
    private WebView chapterWeb;
    private ImageView back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chapter_list);

        chapterWeb = findViewById(R.id.chapter_web);
        back = findViewById(R.id.back);

        Intent intent = getIntent();
        String nameManga = intent.getStringExtra(Const.Sender.mangaName);

        final DatabaseReference rootFref;
        rootFref = FirebaseDatabase.getInstance().getReference();

        rootFref.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                Mangas mangasData = snapshot.child(Const.Database.manga).child(nameManga).getValue(Mangas.class);
                chapterWeb.setWebViewClient(new WebViewClient());
                chapterWeb.loadUrl("https://devminigame.ewings.vn/landing/");

                WebSettings webSettings = chapterWeb.getSettings();
                webSettings.setJavaScriptEnabled(true);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (chapterWeb.canGoBack()) {
                    chapterWeb.goBack();
                } else {
                    finish();
                }
            }
        });
    }

    @Override
    public void onBackPressed() {
        if (chapterWeb.canGoBack()) {
            chapterWeb.goBack();
        } else {
            super.onBackPressed();
        }
    }
}