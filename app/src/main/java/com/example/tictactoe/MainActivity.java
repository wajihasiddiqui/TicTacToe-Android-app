package com.example.tictactoe;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    boolean gameActive = true;
    //  Player Representation
    //  0 -> x
    //  1 -> o

    int avtiveplayer = 0;
    int[] gamestate = {2, 2, 2, 2, 2, 2, 2, 2, 2};

    //  State
    //  0 -> 1
    //  1 -> x
    //  2 -> Null

    //  Winning Positions

    int [] [] winningPosition = {{0, 1, 2},{3, 4, 5},{6, 7, 8},{0, 3, 6},{1, 4, 7},{2, 5, 8},{0, 4, 8},{2, 4, 6}};

    public void taptap(View view){

        ImageView img = (ImageView) view;
        int tappedimage = Integer.parseInt(img.getTag().toString());
        if(!gameActive){
            gameReset(view);
        }
        if (gamestate[tappedimage] == 2){
            gamestate[tappedimage] = avtiveplayer;
            img.setTranslationY(-1000f);
            if (avtiveplayer == 0){
                img.setImageResource(R.drawable.x);
                TextView status = findViewById(R.id.status);
                status.setText("O,s Turn Tap To Play");
                avtiveplayer = 1;
            }
            else {
                img.setImageResource(R.drawable.ok);
                TextView status = findViewById(R.id.status);
                status.setText("X,s Turn Tap To Play");
                avtiveplayer = 0;
            }

            img.animate().translationYBy(1000f).setDuration(300);

        }

        // Check If any player has won

        for(int[] winPosition: winningPosition){
            if(gamestate[winPosition[0]] == gamestate[winPosition[1]] &&
                    gamestate[winPosition[1]] == gamestate[winPosition[2]] &&
                    gamestate[winPosition[0]]!=2){
                // Somebody has won! - Find out who!
                String winnerStr;
                gameActive = false;
                if(gamestate[winPosition[0]] == 0){
                    winnerStr = "X has won";
                }
                else{
                    winnerStr = "O has won";
                }
                // Update the status bar for winner announcement
                TextView status = findViewById(R.id.status);
                status.setText(winnerStr);

            }



        }

    }

    public void gameReset(View view) {
        gameActive = true;
        avtiveplayer = 0;
        for(int i=0; i<gamestate.length; i++){
            gamestate[i] = 2;
        }
        ((ImageView)findViewById(R.id.imageView0)).setImageResource(0);
        ((ImageView)findViewById(R.id.imageView1)).setImageResource(0);
        ((ImageView)findViewById(R.id.imageView2)).setImageResource(0);
        ((ImageView)findViewById(R.id.imageView3)).setImageResource(0);
        ((ImageView)findViewById(R.id.imageView4)).setImageResource(0);
        ((ImageView)findViewById(R.id.imageView5)).setImageResource(0);
        ((ImageView)findViewById(R.id.imageView6)).setImageResource(0);
        ((ImageView)findViewById(R.id.imageView7)).setImageResource(0);
        ((ImageView)findViewById(R.id.imageView8)).setImageResource(0);

        TextView status = findViewById(R.id.status);
        status.setText("X's Turn - Tap to play");
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}