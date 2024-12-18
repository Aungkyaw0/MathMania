package com.example.mathquiz;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SwitchCompat;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;

public class MainActivity extends AppCompatActivity {
    Button playNowBtn, quitGame;
    public static SwitchCompat switchButton;
    public static MediaPlayer mp;
    boolean isMusicPlaying = false; // Track the music state
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        playNowBtn = findViewById(R.id.startBtn);
        quitGame = findViewById(R.id.exitBtn);

        playNowBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, LevelOption.class);
                startActivity(intent);

            }
        });
        quitGame.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                if (mp != null && mp.isPlaying()) {
                    mp.stop();
                    mp.release(); // Release the MediaPlayer
                }
                moveTaskToBack(true);
            }
        });
        switchButton = findViewById(R.id.switchCompat);
        switchButton.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    if (!isMusicPlaying) { // Start music only if it's not already playing
                        mp = MediaPlayer.create(MainActivity.this, R.raw.theme_song);
                        mp.setLooping(true);
                        mp.start();
                        isMusicPlaying = true;
                    }
                } else {
                    if (mp != null && mp.isPlaying()) {
                        mp.stop();
                        mp.release(); // Release the MediaPlayer
                        isMusicPlaying = false;
                    }
                }
            }
        });
    }
}
