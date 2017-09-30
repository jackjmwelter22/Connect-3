package com.jackwelter.connect3;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {

    int activePlayer = 0; // 0 = yellow, 1 = red
    int[] gameState = {2,2,2,2,2,2,2,2,2}; //2 means unplayed

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void dropIn(View view) {

        int pos;

        ImageView counter = (ImageView) view;

        pos  = Integer.parseInt(counter.getTag().toString());

        if (gameState[pos] != 2) {
            counter.setTranslationY(-1000f);
            gameState[pos] = activePlayer;
        }else {
            return;
        }


        if (activePlayer == 1) {
            counter.setImageResource(R.drawable.red);
            activePlayer = 0;
        } else {
            counter.setImageResource(R.drawable.yellow);
            activePlayer = 1;
        }
        counter.animate().translationYBy(1000f).rotation(360).setDuration(300);

    }
}
