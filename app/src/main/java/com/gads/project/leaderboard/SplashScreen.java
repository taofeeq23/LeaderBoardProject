package com.gads.project.leaderboard;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.content.Intent;
import android.os.Handler;
import android.widget.Toast;

public class SplashScreen extends AppCompatActivity {
    Handler handler;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(SplashScreen.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        }, 3000);

    }

    //        Thread thread = new Thread() {
//            public void run() {
//                try {
//                    sleep(2 * 1000);
//                    Intent intent = new Intent(SplashScreen.this, MainActivity.class);
//                    startActivity(intent);
//                }
//                catch (Exception e){
//                    Toast.makeText(SplashScreen.this, "Exception Error: " + e.getMessage(), Toast.LENGTH_SHORT).show();
//                }
//            }
//        };
//        thread.start();

}