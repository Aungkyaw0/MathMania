package com.example.mathquiz;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import android.graphics.Color;
import android.view.View;
public class Level0 extends AppCompatActivity implements View.OnClickListener{


    TextView totalQuestionTV;
    TextView questionTV;
    Button ansA, ansB, ansC, ansD;
    Button selectBtn;
    Button submitBtn, nextBtn;
    int score = 0;
    int totalQuestion = QuestionAnswer.question.length;
    int currentQuestionIndex = 0;
    String selectedAnswer = "";

    MediaPlayer correctMusic;
    MediaPlayer wrongMusic;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_level0);

        totalQuestionTV = findViewById(R.id.total_question);
        questionTV = findViewById(R.id.questionTxt);
        ansA = findViewById(R.id.ans_A);
        ansB = findViewById(R.id.ans_B);
        ansC = findViewById(R.id.ans_C);
        ansD = findViewById(R.id.ans_D);
        submitBtn = findViewById(R.id.submit_btn);
        nextBtn = findViewById(R.id.next_btn);
        ansA.setOnClickListener(this);
        ansB.setOnClickListener(this);
        ansC.setOnClickListener(this);
        ansD.setOnClickListener(this);
        submitBtn.setOnClickListener(this);
        nextBtn.setOnClickListener(this);
        loadNewQuestion();

    }

    public void loadNewQuestion(){

        if(currentQuestionIndex == totalQuestion){
            finishQuiz();
            return;
        }
        totalQuestionTV.setText("Question "+ (currentQuestionIndex + 1) +"/10");

        questionTV.setText(QuestionAnswer.question[currentQuestionIndex]);
        ansA.setText(QuestionAnswer.choices[currentQuestionIndex][0]);
        ansB.setText(QuestionAnswer.choices[currentQuestionIndex][1]);
        ansC.setText(QuestionAnswer.choices[currentQuestionIndex][2]);
        ansD.setText(QuestionAnswer.choices[currentQuestionIndex][3]);

    }

    void finishQuiz(){
        Intent i;
        if(score > (totalQuestion - 4)){
            i = new Intent(Level0.this, Pass.class);
            //Need to pass Total Score
        }else{
            i = new Intent(Level0.this, Fail.class);
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
            // User has clicked the submit button
            ansA.setEnabled(false); // Disable answer buttons
            ansB.setEnabled(false);
            ansC.setEnabled(false);
            ansD.setEnabled(false);
            submitBtn.setEnabled(false); // Disable submit button
            nextBtn.setEnabled(true);
            if (selectedAnswer.equals(QuestionAnswer.correctAnswer[currentQuestionIndex])) {
                score++;
                selectBtn.setBackgroundColor(Color.GREEN);
                correctMusic = MediaPlayer.create(this, R.raw.success);
                correctMusic.start();
            } else {
                if (ansA.getText().toString().equals(QuestionAnswer.correctAnswer[currentQuestionIndex])){
                    ansA.setBackgroundColor(Color.GREEN);
                }else if (ansB.getText().toString().equals(QuestionAnswer.correctAnswer[currentQuestionIndex])){
                    ansB.setBackgroundColor(Color.GREEN);
                }else if (ansC.getText().toString().equals(QuestionAnswer.correctAnswer[currentQuestionIndex])){
                    ansC.setBackgroundColor(Color.GREEN);
                }else if (ansD.getText().toString().equals(QuestionAnswer.correctAnswer[currentQuestionIndex])){
                    ansD.setBackgroundColor(Color.GREEN);
                }
                selectBtn.setBackgroundColor(Color.RED);
                wrongMusic = MediaPlayer.create(this, R.raw.wrong);
                wrongMusic.start();
            }
        } else if (clickButton.getId() == R.id.next_btn) {
            // User is moving to the next question
                // Load the next question
                if(correctMusic != null && correctMusic.isPlaying()){
                    correctMusic.stop();
                }
                currentQuestionIndex++;
                loadNewQuestion();
                ansA.setEnabled(true); // Re-enable answer buttons
                ansB.setEnabled(true);
                ansC.setEnabled(true);
                ansD.setEnabled(true);
                submitBtn.setEnabled(true); // Re-enable submit button

        } else {
            // Choices button
            selectedAnswer = clickButton.getText().toString();
            selectBtn = clickButton;
            clickButton.setBackgroundColor(Color.CYAN);
        }
    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {
        super.onPointerCaptureChanged(hasCapture);
    }

}