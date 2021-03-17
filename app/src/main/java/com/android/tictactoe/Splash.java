package com.android.tictactoe;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;

public class Splash extends AppCompatActivity {

    ImageView xo;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        Thread background = new Thread() {
            public void run() {
                try {

                    sleep(2000);

                    Intent open =new Intent(getBaseContext(), MainActivity.class);
                    startActivity(open);

                    finish();
                } catch (Exception e) {
                }
            }
        };
        // start thread
        background.start();

    }
}
