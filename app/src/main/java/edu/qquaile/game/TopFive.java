package edu.qquaile.game;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

public class TopFive extends AppCompatActivity {
    TextView player1,player2,player3,player4,player5;
    TextView[] textViews = new TextView[5];
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_top_five);
        player1 = findViewById(R.id.player1);
        player2= findViewById(R.id.player2);
        player3= findViewById(R.id.player3);
        player4= findViewById(R.id.player4);
        player5= findViewById(R.id.player5);
        textViews[0] = player1;
        textViews[1] = player2;
        textViews[2] = player3;
        textViews[3] = player4;
        textViews[4] = player5;
        String log = "";
        int counter = 0;

        DatabaseHandler db = new DatabaseHandler(this);
        List<HiScore> top5HiScores = db.getTopFiveScores();

        for (HiScore hs : top5HiScores) {
            log =
                    "Id: " + hs.getScore_id() +
                            ", Date: " + hs.getGame_date() +
                            " , Player: " + hs.getPlayer_name() +
                            " , Score: " + hs.getScore();

            textViews[counter].setText(log);
            counter++;
        }
    }

    public void playAgain(View view) {
        int dbCheck = 1;

        Intent myIntent = new Intent(TopFive.this, MainActivity.class);
        myIntent.putExtra("dbCheck", dbCheck);
        startActivity(myIntent);
    }
}