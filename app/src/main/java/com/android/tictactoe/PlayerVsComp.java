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
import java.util.Random;

public class PlayerVsComp extends AppCompatActivity {

    // The image that show the turn of x or o
    ImageView x, o;

    //The box that let O play first if checked
    CheckBox let_o_first;

    // The boolean that indicate the turn of x
    // won indicate if any one (x or o) has won
    boolean turn = true, won = false;

    // The ARRAY that contain the position of x and o have played in it
    // in order not to play in it
    ArrayList<Integer> xArray = new ArrayList<>(),
            oArray = new ArrayList<>();

    // The Text that show the winner
    TextView txt_win;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.xomap);

        deceleration();
    }


    /**
     * Used to declare the objects that will be used in the program
     */
    void deceleration() {

        let_o_first = findViewById(R.id.let_o_first);

        x =  findViewById(R.id.x);
        o =  findViewById(R.id.o);

        txt_win = findViewById(R.id.txt_win);


        // The GridView that will make the x/o map and game will played in it
        GridView gridView = findViewById(R.id.Grid);

        // The array that contain the element of the map x/o if no one played in it
        // x or o if x or o played in it(click on it)
        final ArrayList<XO> xo = new ArrayList<>();

        //Make empty array or array that contain x/o images in it
        for (byte i = 0; i < 9; i++)
            xo.add(new XO(R.drawable.xo));

        final XOAdapter adapter = new XOAdapter(this, xo);

        gridView.setAdapter(adapter);


        if (let_o_first.isChecked())
            turn = false;

        let_o_first.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                oArray.clear();
                xArray.clear();
                won = false;
                txt_win.setText("");
                deceleration();

            }
        });


        // If we want x to play first let turn = true
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
            compTurn(adapter);
        }


        // Make the map interactive by making setOnItemClickListener
        // According to position x or o will be assigned in it
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                //Check if anyone has won
                if (won)
                    Toast.makeText(PlayerVsComp.this, "The game was ended",
                            Toast.LENGTH_SHORT).show();

                // Check if this position is x or o position
                else if (xArray.contains(position) || oArray.contains(position))
                    Toast.makeText(PlayerVsComp.this,
                            "Can't insert here, Choose another position",
                            Toast.LENGTH_SHORT).show();
                else {

                    // Check for turn and change the color and background color according to that
                    if (turn) {
                        xArray.add(position);

                        o.setBackgroundColor(Color.BLACK);
                        o.setColorFilter(Color.WHITE);
                        x.setColorFilter(Color.BLACK);
                        x.setBackgroundColor(Color.TRANSPARENT);

                        //update the map with new image that added
                        adapter.updateImage(position, R.drawable.x);

                        //Check if x has won
                        check(xArray, R.string.xwon);
                        turn = false;

                        //The computer turn after x played
                        compTurn(adapter);


                    } else compTurn(adapter);
                }
            }

        });
    }


    /**
     * The computer turn
     *
     * @param adapter the adapter that contain the map
     *                where we will update after computer turn
     */

    private void compTurn(XOAdapter adapter) {

        //if !turn mean computer turn  and no one has won
        if (!turn && !won) {

            //Try to win if can
            int position = getPosition(oArray, xArray);

            // Try to prevent x from wining if can
            if (position == -1)
                position = getPosition(xArray, oArray);

            // if it can't won or  x can't won will choose position from corner
            if (position == -1) {
                if (!(xArray.contains(0) || oArray.contains(0))) position = 0;
                else if (!(xArray.contains(8) || oArray.contains(8))) position = 8;
                else if (!(xArray.contains(2) || oArray.contains(2))) position = 2;
                else if (!(xArray.contains(6) || oArray.contains(6))) position = 6;

                // if all the position at corner was filled choose random position
                // where it should be empty
                else {
                    Random random = new Random();
                    position = random.nextInt(9);
                    while (xArray.contains(position) || oArray.contains(position))
                        position = random.nextInt(9);
                }
            }

            // Add the new position to the array of o
            //Change the color to indicate the turn has changed
            oArray.add(position);
            x.setBackgroundColor(Color.BLACK);
            x.setColorFilter(Color.WHITE);
            o.setBackgroundColor(Color.TRANSPARENT);
            o.setColorFilter(Color.BLACK);

            //update the map with new image that added
            adapter.updateImage(position, R.drawable.o);
            check(oArray, R.string.owon);
            turn = true;
        }
    }

    /**
     * Get position for comp to play
     * <p>
     * first we will see if o has played in two position and remaining the third to win
     * then check if x has played in two position and remaining the third to win and o not won yet
     *
     * @param arrayOne the array that played in two position and remain third to won
     * @param arrayTwo the array that not played in the third position in order to arrayone wil win
     */

    private int getPosition(ArrayList<Integer> arrayOne, ArrayList<Integer> arrayTwo) {

        //Check for horizontal one
        for (int i = 0; i < 7; i += 3)
            if (arrayOne.contains(i) && arrayOne.contains(i + 1) && !arrayTwo.contains(i + 2))
                return i + 2;
            else if (arrayOne.contains(i + 1) && arrayOne.contains(i + 2) && !arrayTwo.contains(i))
                return i;
            else if (arrayOne.contains(i) && arrayOne.contains(i + 2) && !arrayTwo.contains(i + 1))
                return i + 1;

        //Check for vertical one
        for (int i = 0; i < 3; i++)
            if (arrayOne.contains(i) && arrayOne.contains(i + 3) && !arrayTwo.contains(i + 6))
                return i + 6;
            else if (arrayOne.contains(i + 3) && arrayOne.contains(i + 6) && !arrayTwo.contains(i))
                return i;
            else if (arrayOne.contains(i) && arrayOne.contains(i + 6) && !arrayTwo.contains(i + 3))
                return i + 3;

        //Check for diagonal
        if (arrayOne.contains(0) && arrayOne.contains(4) && !arrayTwo.contains(8)) return 8;
        if (arrayOne.contains(4) && arrayOne.contains(8) && !arrayTwo.contains(0)) return 0;
        if (arrayOne.contains(0) && arrayOne.contains(8) && !arrayTwo.contains(4)) return 4;

        if (arrayOne.contains(2) && arrayOne.contains(4) && !arrayTwo.contains(6)) return 6;
        if (arrayOne.contains(4) && arrayOne.contains(6) && !arrayTwo.contains(2)) return 2;
        if (arrayOne.contains(2) && arrayOne.contains(6) && !arrayTwo.contains(4)) return 4;

        return -1;
    }

    /**
     * Check if x or o has won
     *
     * @param winArray xArray or oArray that if contain horizontal, vertical or  diagonal element
     *                 then it has won
     * @param win      indicate the turn if x turn then win = X, used to write the winner.
     */
    private void check(ArrayList<Integer> winArray, int win) {

        //Check for horizontal one, if there is a winner write the it and close the function
        for (int i = 0; i < 7; i += 3) {
            if (winArray.contains(i) && winArray.contains(i + 1) && winArray.contains(i + 2)) {
                won = true;
                txt_win.setText(win);
                return;
            }
        }


        //Check for vertical one, if there is a winner write the it and close the function
        for (int i = 0; i < 3; i++)
            if (winArray.contains(i) && winArray.contains(i + 3) && winArray.contains(i + 6)) {
                won = true;
                txt_win.setText(win);
                return;
            }

        // Check for diagonal, if there is a winner write the it and close the function
        if (winArray.contains(0) && winArray.contains(4) && winArray.contains(8)) {
            won = true;
            txt_win.setText(win);
            return;
        }

        if (winArray.contains(2) && winArray.contains(4) && winArray.contains(6)) {
            won = true;
            txt_win.setText(win);
            return;
        }

        //If the sum is 9 then the map will be completed and game was ended
        if (xArray.size() + oArray.size() == 9)
            won = true;

    }
}



