package com.gads.project.leaderboard;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.content.Intent;
import android.widget.Toast;

public class SplashScreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        Thread thread = new Thread() {
            public void run() {
                try {
                    sleep(2 * 1000);
                    Intent intent = new Intent(SplashScreen.this, MainActivity.class);
                    startActivity(intent);
                }
                catch (Exception e){
                    Toast.makeText(SplashScreen.this, "Exception Error: " + e.getMessage(), Toast.LENGTH_SHORT).show();
                }
            }
        };
        thread.start();
    }
}