package com.example.mathquiz;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.graphics.Color;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Level1 extends AppCompatActivity implements View.OnClickListener {

    TextView totalQuestionTV;
    TextView questionTV, timerTV;
    Button ansA, ansB, ansC, ansD;
    Button selectBtn;
    Button submitBtn, nextBtn;
    CountDownTimer countDownTimer;
    int score = 0;
    int totalQuestion = QuestionAnswer.question.length;
    int currentQuestionIndex = 0;
    String selectedAnswer = "";
    MediaPlayer correctMusic;
    MediaPlayer wrongMusic;
    int shuffledQuestionIndex;
    List<Integer> questionIndices = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_level1); // Correct the layout reference to activity_level2

        totalQuestionTV = findViewById(R.id.total_question);
        questionTV = findViewById(R.id.questionTxt);
        ansA = findViewById(R.id.ans_A);
        ansB = findViewById(R.id.ans_B);
        ansC = findViewById(R.id.ans_C);
        ansD = findViewById(R.id.ans_D);
        submitBtn = findViewById(R.id.submit_btn);
        nextBtn = findViewById(R.id.next_btn);
        timerTV = findViewById(R.id.timerLvl2TV);

        ansA.setOnClickListener(this);
        ansB.setOnClickListener(this);
        ansC.setOnClickListener(this);
        ansD.setOnClickListener(this);
        submitBtn.setOnClickListener(this);
        nextBtn.setOnClickListener(this);


        // Shuffle the questions to randomize their order
        for (int i = 0; i < totalQuestion; i++) {
            questionIndices.add(i);
        }
        Collections.shuffle(questionIndices);

        loadNewQuestion();
    }

    public void timer() {
        countDownTimer = new CountDownTimer(20000, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                long secondsLeft = millisUntilFinished / 1000;
                timerTV.setText(String.format("00:%02d", secondsLeft));
            }

            @Override
            public void onFinish() {
                nextBtn.performClick();
            }
        }.start();
    }

    public void loadNewQuestion() {
        if (currentQuestionIndex == totalQuestion) {
            finishQuiz();
            return;
        }
        timer();
        totalQuestionTV.setText("Question " + (currentQuestionIndex + 1) + "/10");
        shuffledQuestionIndex = questionIndices.get(currentQuestionIndex);
        questionTV.setText(QuestionAnswer.question[shuffledQuestionIndex]);
        ansA.setText(QuestionAnswer.choices[shuffledQuestionIndex][0]);
        ansB.setText(QuestionAnswer.choices[shuffledQuestionIndex][1]);
        ansC.setText(QuestionAnswer.choices[shuffledQuestionIndex][2]);
        ansD.setText(QuestionAnswer.choices[shuffledQuestionIndex][3]);

        enableAnswerButtons();
    }

    void finishQuiz() {
        Intent i;
        if (score > (totalQuestion - 4)) {
            i = new Intent(Level1.this, Pass.class);
        } else {
            i = new Intent(Level1.this, Fail.class);
        }
        i.putExtra("score", score);
        startActivity(i);
        finish();
    }



    @Override
    public void onClick(View v) {
        Button clickButton = (Button) v;
        ansA.setBackgroundColor(Color.parseColor("#5918db"));
        ansB.setBackgroundColor(Color.parseColor("#5918db"));
        ansC.setBackgroundColor(Color.parseColor("#5918db"));
        ansD.setBackgroundColor(Color.parseColor("#5918db"));
        nextBtn.setEnabled(false);

        if (clickButton.getId() == R.id.submit_btn) {
            countDownTimer.cancel();
            disableAnswerButtons();
            submitBtn.setEnabled(false);
            nextBtn.setEnabled(true);
            if (selectedAnswer.equals(QuestionAnswer.correctAnswer[shuffledQuestionIndex])) {
                score++;
                selectBtn.setBackgroundColor(Color.GREEN);
                correctMusic = MediaPlayer.create(this, R.raw.success);
                correctMusic.start();
            } else {
                selectBtn.setBackgroundColor(Color.RED);
                wrongMusic = MediaPlayer.create(this, R.raw.wrong);
                wrongMusic.start();
                if (ansA.getText().toString().equals(QuestionAnswer.correctAnswer[shuffledQuestionIndex])){
                    ansA.setBackgroundColor(Color.GREEN);
                }else if (ansB.getText().toString().equals(QuestionAnswer.correctAnswer[shuffledQuestionIndex])){
                    ansB.setBackgroundColor(Color.GREEN);
                }else if (ansC.getText().toString().equals(QuestionAnswer.correctAnswer[shuffledQuestionIndex])){
                    ansC.setBackgroundColor(Color.GREEN);
                }else if (ansD.getText().toString().equals(QuestionAnswer.correctAnswer[shuffledQuestionIndex])){
                    ansD.setBackgroundColor(Color.GREEN);
                }else{
                    submitBtn.setBackgroundColor(Color.BLACK);
                }
            }
        } else if (clickButton.getId() == R.id.next_btn) {
            if(correctMusic != null && correctMusic.isPlaying()){
                correctMusic.stop();
            }
            submitBtn.setEnabled(true);
            currentQuestionIndex++;
            loadNewQuestion();
        } else {
            selectedAnswer = clickButton.getText().toString();
            selectBtn = clickButton;
            clickButton.setBackgroundColor(Color.CYAN);
        }
    }

    private void disableAnswerButtons() {
        ansA.setEnabled(false);
        ansB.setEnabled(false);
        ansC.setEnabled(false);
        ansD.setEnabled(false);
    }

    private void enableAnswerButtons() {
        ansA.setEnabled(true);
        ansB.setEnabled(true);
        ansC.setEnabled(true);
        ansD.setEnabled(true);
    }
}
