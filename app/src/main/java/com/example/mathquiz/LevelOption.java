package com.example.mathquiz;



import static com.example.mathquiz.MainActivity.switchButton;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class LevelOption extends AppCompatActivity {
    Button startLvl1, startLvl2, startLvl3;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_level_option);

        startLvl1 = findViewById(R.id.lvl0Btn);
        startLvl2 = findViewById(R.id.lvl1Btn);
        startLvl3 = findViewById(R.id.lvl2Btn);

        startLvl1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(LevelOption.this, Level0.class);
                startActivity(i);
                switchButton.setChecked(false);
            }
        });

        startLvl2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(LevelOption.this, Level1.class);
                startActivity(i);
                switchButton.setChecked(false);

            }
        });

        startLvl3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(LevelOption.this, Level2.class);
                startActivity(i);
                switchButton.setChecked(false);
            }
        });



    }
}