package edu.qquaile.game;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;

import java.util.List;


import java.util.Random;

public class MainActivity extends AppCompatActivity {
    View Selection1, Selection2, Selection3, Selection4;
    String Answer = "";
    int count, score = 0, dbCheck = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Intent mIntent = getIntent();
        score = mIntent.getIntExtra("score", 0);
        dbCheck = mIntent.getIntExtra("dbCheck", 0);


        Selection1 = findViewById((R.id.SelectOrange));
        Selection2 = findViewById(R.id.SelectGreen);
        Selection3 = findViewById(R.id.SelectRed);
        Selection4 = findViewById(R.id.SelectPurple);

        count = 4;
        int play = 2 * score;
        count = count + play;



        DatabaseHandler db = new DatabaseHandler(this);

        if(dbCheck == 0){
            db.emptyHiScores();     // empty table if required

            db.addHiScore(new HiScore("20 OCT 2020", "Frodo", 2));
            db.addHiScore(new HiScore("28 OCT 2020", "Dobby", 2));
            db.addHiScore(new HiScore("20 NOV 2020", "DarthV", 2));
            db.addHiScore(new HiScore("20 NOV 2020", "Bob", 2));
            db.addHiScore(new HiScore("22 NOV 2020", "Gemma", 22));
            db.addHiScore(new HiScore("30 NOV 2020", "Joe", 30));
            db.addHiScore(new HiScore("01 DEC 2020", "DarthV", 22));
            db.addHiScore(new HiScore("02 DEC 2020", "Gandalf", 132));

        }
    }

    public void check(){
        if( Selection1.getVisibility() == View.VISIBLE ||Selection2.getVisibility() == View.VISIBLE ||Selection3.getVisibility() == View.VISIBLE ||Selection4.getVisibility() == View.VISIBLE){
            Selection1.setVisibility(View.INVISIBLE);
            Selection2.setVisibility(View.INVISIBLE);
            Selection3.setVisibility(View.INVISIBLE);
            Selection4.setVisibility(View.INVISIBLE);
        }
    }
    public void selection(View selection, int random){
        selection.setVisibility(View.VISIBLE);
        Answer = Answer + random;
    }

    public void Play(View view) {

        if(count == 0){
            Intent myIntent = new Intent(MainActivity.this, GameScreen.class);
            myIntent.putExtra("score", score);
            myIntent.putExtra("answer", Answer);
            startActivity(myIntent);
        }

    }

    public void Highlight(View view) {

        if(count != 0 ){
            check();
            int random = new Random().nextInt(4) + 1;
            View selectionColor = null;
            if (random == 1) {
                selectionColor = Selection1;
            }
            else if (random == 2) {
                selectionColor = Selection2;
            }
            else if (random == 3) {
                selectionColor = Selection3;
            }
            else if (random == 4) {
                selectionColor = Selection4;
            }
            selection(selectionColor,random);

            count--;
        }
    }
}