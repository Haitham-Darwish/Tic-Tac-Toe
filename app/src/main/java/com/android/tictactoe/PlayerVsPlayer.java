/**
 * The Activity that contain the game between you and your friends
 */

package com.android.tictactoe;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.CheckBox;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class PlayerVsPlayer extends AppCompatActivity {

//    private final String EXTRA_XARRAY = "xArray";
//    private final String EXTRA_OARRAY = "oArray";
//    private final String EXTRA_WON = "won";
//    private final String EXTRA_TURN = "turn";
//    private final String EXTRA_TXT_WIN = "txt_win";


    //The image that show the turn of x or o
    ImageView x, o;

    //The box that let O play first if checked
    CheckBox let_o_first;

    //The boolean that indicate the turn of x
    //won indicate if any one (x or o) has won
    boolean turn, isWon = false;

    //The ARRAY that contain the position of x and o have played in it
    //in order not to play in it
    ArrayList<Integer> xArray = new ArrayList<Integer>(),
            oArray = new ArrayList<Integer>();

    //The Text that show the winner
    TextView txt_win;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.xomap);


        deceleration();

//        if (savedInstanceState != null) {
//            xArray = savedInstanceState.getIntegerArrayList(EXTRA_XARRAY);
//            oArray = savedInstanceState.getIntegerArrayList(EXTRA_OARRAY);
//            isWon = savedInstanceState.getBoolean(EXTRA_WON);
//            turn = savedInstanceState.getBoolean(EXTRA_TURN);
////           txt_win = savedInstanceState.getA(EXTRA_TXT_WIN);
//
//        }

    }


//    @Override
//    protected void onSaveInstanceState(@NonNull Bundle outState) {
//        outState.putIntegerArrayList(EXTRA_XARRAY, xArray);
//        outState.putIntegerArrayList(EXTRA_OARRAY, oArray);
//        outState.putBoolean(EXTRA_WON, isWon);
//        outState.putBoolean(EXTRA_TURN, turn);
////        outState.putString(EXTRA_TXT_WIN, txt_win.toString());
//
//        super.onSaveInstanceState(outState);
//    }

    /**
     * Used to declare the objects that will be used in the program
     */
    void deceleration() {

        let_o_first = (CheckBox) findViewById(R.id.let_o_first);


        x = (ImageView) findViewById(R.id.x);
        o = (ImageView) findViewById(R.id.o);

        txt_win = (TextView) findViewById(R.id.txt_win);

        if (let_o_first.isChecked())
            turn = false;
        else
            turn = true;

        let_o_first.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                oArray.clear();
                xArray.clear();
                isWon = false;
                txt_win.setText("");
                deceleration();

            }
        });


        //If we want x to play first let turn = true
        if (turn) {
            x.setBackgroundColor(Color.BLACK);
            x.setColorFilter(Color.WHITE);
            o.setBackgroundColor(Color.TRANSPARENT);
            o.setColorFilter(Color.BLACK);
        } else {
            o.setBackgroundColor(Color.BLACK);
            o.setColorFilter(Color.WHITE);
            x.setColorFilter(Color.BLACK);
            x.setBackgroundColor(Color.TRANSPARENT);
        }

        // The GridView that will make the x/o map and game will played in it
        GridView gridView = (GridView) findViewById(R.id.Grid);

        // The array that contain the element of the map x/o if no one played in it
        // x or o if x or o played in it(click on it)
        final ArrayList<XO> xo = new ArrayList<XO>();

        //Make empty array or array that contain x/o images in it
        for (byte i = 0; i < 9; i++)
            xo.add(new XO(R.drawable.xo));

        final XOAdapter adapter = new XOAdapter(this, xo);

        gridView.setAdapter(adapter);

        // Make the map interactive by making setOnItemClickListener
        // According to position x or o will be assigned in it
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //Check if anyone has won
                if (isWon)
                    Toast.makeText(PlayerVsPlayer.this, "The game was ended", Toast.LENGTH_SHORT).show();
                    // Check if this position is x or o position
                else if (xArray.contains(position) || oArray.contains(position))
                    Toast.makeText(PlayerVsPlayer.this, "Can't insert here, Choose another position", Toast.LENGTH_SHORT).show();
                else {
                    //Log.v("Main activity", "The postion is " + position);
                    // Check for turn and change the color and background color according to that
                    if (turn) {
                        xArray.add(position);

                        o.setBackgroundColor(Color.BLACK);
                        o.setColorFilter(Color.WHITE);
                        x.setColorFilter(Color.BLACK);
                        x.setBackgroundColor(Color.TRANSPARENT);

                        //update the map with new image that added
                        adapter.updateImage(position, R.drawable.x);
                        turn = false;
                    } else {
                        oArray.add(position);

                        x.setBackgroundColor(Color.BLACK);
                        x.setColorFilter(Color.WHITE);
                        o.setBackgroundColor(Color.TRANSPARENT);
                        o.setColorFilter(Color.BLACK);

                        //update the map with new image that added
                        adapter.updateImage(position, R.drawable.o);
                        turn = true;
                    }

                    //Check if x has won
                    if (xArray.contains(0) && xArray.contains(1) && xArray.contains(2)) {
                        isWon = true;
                        txt_win.setText(R.string.xwon);
                    }
                    if (xArray.contains(0) && xArray.contains(4) && xArray.contains(8)) {
                        isWon = true;
                        txt_win.setText(R.string.xwon);
                    }
                    if (xArray.contains(0) && xArray.contains(3) && xArray.contains(6)) {
                        isWon = true;
                        txt_win.setText(R.string.xwon);
                    }
                    if (xArray.contains(1) && xArray.contains(4) && xArray.contains(7)) {
                        isWon = true;
                        txt_win.setText(R.string.xwon);
                    }
                    if (xArray.contains(2) && xArray.contains(5) && xArray.contains(8)) {
                        isWon = true;
                        txt_win.setText(R.string.xwon);
                    }
                    if (xArray.contains(2) && xArray.contains(4) && xArray.contains(6)) {
                        isWon = true;
                        txt_win.setText(R.string.xwon);
                    }
                    if (xArray.contains(3) && xArray.contains(4) && xArray.contains(5)) {
                        isWon = true;
                        txt_win.setText(R.string.xwon);
                    }
                    if (xArray.contains(6) && xArray.contains(7) && xArray.contains(8)) {
                        isWon = true;
                        txt_win.setText(R.string.xwon);
                    }
                    //Check if o has won
                    if (oArray.contains(0) && oArray.contains(1) && oArray.contains(2)) {
                        isWon = true;
                        txt_win.setText(R.string.owon);
                    }
                    if (oArray.contains(0) && oArray.contains(4) && oArray.contains(8)) {
                        isWon = true;
                        txt_win.setText(R.string.owon);
                    }
                    if (oArray.contains(0) && oArray.contains(3) && oArray.contains(6)) {
                        isWon = true;
                        txt_win.setText(R.string.owon);
                    }
                    if (oArray.contains(1) && oArray.contains(4) && oArray.contains(7)) {
                        isWon = true;
                        txt_win.setText(R.string.owon);
                    }
                    if (oArray.contains(2) && oArray.contains(5) && oArray.contains(8)) {
                        isWon = true;
                        txt_win.setText(R.string.owon);
                    }
                    if (oArray.contains(2) && oArray.contains(4) && oArray.contains(6)) {
                        isWon = true;
                        txt_win.setText(R.string.owon);
                    }
                    if (oArray.contains(3) && oArray.contains(4) && oArray.contains(5)) {
                        isWon = true;
                        txt_win.setText(R.string.owon);
                    }
                    if (oArray.contains(6) && oArray.contains(7) && oArray.contains(8)) {
                        isWon = true;
                        txt_win.setText(R.string.owon);
                    }
                }
            }
        });

    }
}
