package com.example.quiz_app;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.*;
import java.util.Arrays;
import java.util.List;

public class QuizActivity extends AppCompatActivity {

    TextView questionTextView;
    RadioGroup optionsRadioGroup;
    Button submitButton, skipButton;

    ImageView questionImageView;


    List<Question> questions;
    int currentQuestionIndex = 0;
    int score = 0;
    boolean answered = false; // To prevent double submissions

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);
        questionImageView = findViewById(R.id.questionImageView);


        questionTextView = findViewById(R.id.questionTextView);
        optionsRadioGroup = findViewById(R.id.optionsRadioGroup);
        submitButton = findViewById(R.id.submitButton);
        skipButton = findViewById(R.id.skipButton);

        questions = Arrays.asList(
                new Question("What is the capital of France?",
                        Arrays.asList("Paris", "Rome", "Berlin", "Madrid"), 0, R.drawable.paris),

                new Question("2 + 2 equals?",
                        Arrays.asList("3", "4", "5", "6"), 1, R.drawable.math),

                new Question("The largest planet?",
                        Arrays.asList("Earth", "Mars", "Jupiter", "Venus"), 2, R.drawable.jupiter)
        );


        showQuestion();

        submitButton.setOnClickListener(v -> {
            if (!answered) {
                checkAnswer();
                answered = true;
                submitButton.setText("Next");
            } else {
                nextQuestion();
            }
        });

        skipButton.setOnClickListener(v -> {
            nextQuestion();
        });
    }

    private void showQuestion() {
        Question question = questions.get(currentQuestionIndex);
        questionTextView.setText(question.getText());
        questionImageView.setImageResource(question.getImageResId());

        for (int i = 0; i < optionsRadioGroup.getChildCount(); i++) {
            RadioButton radioButton = (RadioButton) optionsRadioGroup.getChildAt(i);
            radioButton.setText(question.getOptions().get(i));
        }

        optionsRadioGroup.clearCheck();
        answered = false;
        submitButton.setText("Submit");
    }


    private void checkAnswer() {
        int selectedOptionId = optionsRadioGroup.getCheckedRadioButtonId();
        if (selectedOptionId == -1) {
            Toast.makeText(this, "Please select an option!", Toast.LENGTH_SHORT).show();
            return;
        }
        int selectedIndex = optionsRadioGroup.indexOfChild(findViewById(selectedOptionId));

        if (selectedIndex == questions.get(currentQuestionIndex).getCorrectAnswerIndex()) {
            score++;
            Toast.makeText(this, "Correct!", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "Wrong!", Toast.LENGTH_SHORT).show();
        }
    }

    private void nextQuestion() {
        currentQuestionIndex++;
        if (currentQuestionIndex < questions.size()) {
            showQuestion();
        } else {
            Intent intent = new Intent(QuizActivity.this, ScoreActivity.class);
            intent.putExtra("score", score);
            intent.putExtra("totalQuestions", questions.size());
            startActivity(intent);
            finish();
        }
    }

}
