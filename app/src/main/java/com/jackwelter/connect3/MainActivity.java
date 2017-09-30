package com.jackwelter.connect3;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    int activePlayer = 0; // 0 = yellow, 1 = red
    int[] gameState = {2,2,2,2,2,2,2,2,2}; //2 means unplayed
    int turn =0;
    int winner = 2;
    public TextView endMsg;
    public Button rstButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        endMsg = (TextView) findViewById(R.id.endMessage);
        rstButton = (Button) findViewById(R.id.resetButton);
        rstButton.setClickable(false);
    }

    public void dropIn(View view) {
        int pos;

        ImageView counter = (ImageView) view;

        pos  = Integer.parseInt(counter.getTag().toString());

        //Check board position clicked
        if (gameState[pos] == 2 && !checkWinner(gameState)) {
            counter.setTranslationY(-1000f);
            gameState[pos] = activePlayer;
        }else {
            return;     //Do nothing if counter already in position
        }

        //Check who's playing and set counter color
        if (activePlayer == 1) {
            counter.setImageResource(R.drawable.red);
            activePlayer = 0;
        } else {
            counter.setImageResource(R.drawable.yellow);
            activePlayer = 1;
        }
        counter.animate().translationYBy(1000f).rotation(360).setDuration(300);

        turn++;

        if (!checkWinner(gameState) && turn == 9){
            endMsg.setText("Cats Game!");
            endMsg.setAlpha(1);
            rstButton.setClickable(true);
            rstButton.setAlpha(1);
        }
    }

    public boolean checkWinner(int[] gameState){
        int[][] winPos = {{0,1,2},{3,4,5},{6,7,8},{0,3,6},{1,4,7},{2,5,8},{0,4,8},{2,4,6}};
        String color = "Yellow";

        for (int[] win:winPos) {
            if(gameState[win[0]] == gameState[win[1]] && gameState[win[0]] == gameState[win[2]] && gameState[win[0]] != 2){
                winner = gameState[win[0]];
                if (gameState[win[0]] == 1){
                    color = "Red";
                }
                endMsg.setText(color +  " Player has won the game!");
                endMsg.setAlpha(1);
                rstButton.setClickable(true);
                rstButton.setAlpha(1);
                return true;
            }
        }
        return false;
    }

    public void gameReset(View view){
        endMsg.setAlpha(0);
        rstButton.setAlpha(0);
        rstButton.setClickable(false);

        for(int i = 0 ; i < gameState.length ; i++){
            gameState[i] = 2;
        }

        turn = 0;

        GridLayout gridLayout = (GridLayout) findViewById(R.id.gridLayout);
        for (int i = 0; i < gridLayout.getChildCount(); i++){

            ((ImageView) gridLayout.getChildAt(i)).setImageResource(0);
        }

    }
}
