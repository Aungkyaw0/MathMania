package com.example.mathquiz;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class Fail extends AppCompatActivity {
    Button playAgain, quit;
    TextView totalScore;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fail);

        playAgain = findViewById(R.id.tryAgain_btn);
        quit = findViewById(R.id.homeBtn);

        Intent i = getIntent();
        int tScore = i.getIntExtra("score", 0);

        totalScore = findViewById(R.id.failscoreResult);
        totalScore.setText(tScore + "/10 Score");


        playAgain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Fail.this, LevelOption.class);
                startActivity(i);
                finish();
            }
        });

        quit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Fail.this, MainActivity.class);
                startActivity(i);
                finish();
            }
        });
    }
}