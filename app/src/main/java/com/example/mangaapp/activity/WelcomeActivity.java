package com.example.mangaapp.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.LinearLayout;

import com.example.mangaapp.R;

import io.paperdb.Paper;

public class WelcomeActivity extends AppCompatActivity {
    private static final int MESSAGE_COUNT_DOWN = 100;

    private LinearLayout ln1, ln2;

    private Handler handler;
    private Animation uptoDown, downtoUp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);

        ln1 = findViewById(R.id.ln1);
        ln2 = findViewById(R.id.ln2);

        uptoDown = AnimationUtils.loadAnimation(this, R.anim.uptodown);
        downtoUp = AnimationUtils.loadAnimation(this, R.anim.downtoup);

        ln1.setAnimation(uptoDown);
        ln2.setAnimation(downtoUp);

        Paper.init(this);

        handler = new Handler() {
            @Override
            public void handleMessage(@NonNull Message msg) {
                switch (msg.what) {
                    case MESSAGE_COUNT_DOWN:
                        break;
                    case 234:
                        //Toast.makeText(MainActivity.this, "finish", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(WelcomeActivity.this, LoginActivity.class);
                        startActivity(intent);
                }
            }
        };
    }

    @Override
    protected void onStart() {
        super.onStart();
        new CountDown().start();
    }

    class CountDown extends Thread {
        @Override
        public void run() {
            int count = 5;
            while (count > 0) {
                count--;
                Message message = new Message();
                message.what = MESSAGE_COUNT_DOWN;
                message.arg1 = count;
                handler.sendMessage(message);
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    Log.d("loi:", e.getMessage());
                }
            }

            handler.sendEmptyMessage(234);
        }
    }
}