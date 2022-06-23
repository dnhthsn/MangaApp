package com.example.mangaapp.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;

import com.example.mangaapp.Prevalent.Prevalent;
import com.example.mangaapp.R;
import com.example.mangaapp.model.Mangas;
import com.example.mangaapp.model.Users;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class ChapterListActivity extends AppCompatActivity {
    private String databaseName = "Mangas";

    private WebView chapterWeb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chapter_list);

        chapterWeb = findViewById(R.id.chapter_web);

        Intent intent = getIntent();
        String nameManga = intent.getStringExtra("mangaName");

        final DatabaseReference rootFref;
        rootFref = FirebaseDatabase.getInstance().getReference();

        rootFref.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                Mangas mangasData = snapshot.child(databaseName).child(nameManga).getValue(Mangas.class);
                chapterWeb.setWebViewClient(new WebViewClient());
                chapterWeb.loadUrl(mangasData.getChapters());

                WebSettings webSettings = chapterWeb.getSettings();
                webSettings.setJavaScriptEnabled(true);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

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