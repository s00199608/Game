package edu.qquaile.game;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class GameOver extends AppCompatActivity {
    int score;
    TextView textScore;
    Button btn;
    EditText edt;
    DatabaseHandler db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_over);


        Intent mIntent = getIntent();
        score = mIntent.getIntExtra("score", 0);
        score = score * 2;

        btn = findViewById(R.id.Submit);
        edt = findViewById(R.id.Name);
        textScore = findViewById(R.id.score);
        textScore.setText("Your score was : " + score);

        int dbScore;
        db = new DatabaseHandler(this);
        List<HiScore> top5HiScores = db.getTopFiveScores();
        for (HiScore hs : top5HiScores) {
            dbScore = hs.getScore();
            if(score > dbScore){
                btn.setVisibility(View.VISIBLE);
                edt.setVisibility((View.VISIBLE));
            }
        }

    }

    public void TopFive(View view) {
        Intent myIntent = new Intent(GameOver.this, TopFive.class);
        startActivity(myIntent);
    }

    public void PlayAgain(View view) {
        int dbCheck = 1;
        Intent myIntent = new Intent(GameOver.this, MainActivity.class);
        myIntent.putExtra("dbCheck", dbCheck);
        startActivity(myIntent);
    }

    public void submit(View view) {
        String Name = edt.getText().toString();

        Date c = Calendar.getInstance().getTime();
        SimpleDateFormat df = new SimpleDateFormat("dd-MMM-yyyy", Locale.getDefault());
        String formattedDate = df.format(c);

        db.addHiScore(new HiScore(formattedDate, Name, score));
    }
}