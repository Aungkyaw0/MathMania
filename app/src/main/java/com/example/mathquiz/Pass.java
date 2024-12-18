package com.example.mathquiz;

import static com.example.mathquiz.MainActivity.mp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class Pass extends AppCompatActivity {

    Button playAgain, quit;
    TextView totalScore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pass);

        playAgain = findViewById(R.id.playagain_btn);
        quit = findViewById(R.id.homeBtn);

        Intent i = getIntent();
        int tScore = i.getIntExtra("score", 0);
        totalScore = findViewById(R.id.passScoreResult);
        totalScore.setText(tScore + "/10 Score");


        playAgain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Pass.this, LevelOption.class);
                startActivity(i);
                finish();
            }
        });

        quit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Pass.this, MainActivity.class);
                startActivity(i);
                finish();
            }
        });
    }
}
