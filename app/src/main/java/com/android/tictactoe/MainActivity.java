/**
 * This Program is a simple Tic Tac Toe where you can play alone or with a friend
 *
 * author Haitham Essam
 *
 * version 1.0 beta
 */

package com.android.tictactoe;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //The TextView in which when click on it send you to the game with computer
        TextView playerVSComp = findViewById(R.id.PVSC);

        //The TextView in which when click on it send you to the game with your friend
        TextView playerVSPlayer = findViewById(R.id.PVSP);

        //To make the text(playerVSComp) clickable
        playerVSComp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //To send you to the game with computer at PlayerVsComp activity
                Intent open = new Intent(MainActivity.this, PlayerVsComp.class);
                startActivity(open);
            }
        });

        //To make the text(playerVSComp) clickable
        playerVSPlayer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //To send you to the game with player at PlayerVsPlayer activity
                Intent open = new Intent(MainActivity.this, PlayerVsPlayer.class);
                startActivity(open);
            }
        });

    }
}
