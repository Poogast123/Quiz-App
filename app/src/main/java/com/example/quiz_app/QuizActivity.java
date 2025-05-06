package com.example.quiz_app;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.*;
import java.util.Arrays;
import java.util.List;

public class QuizActivity extends AppCompatActivity {

    TextView questionTextView;
    TextView questionNumberTextView; // New: For "Question n/m"
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

        // Bind views
        questionTextView = findViewById(R.id.questionTextView);
        questionNumberTextView = findViewById(R.id.textView); // New: Question counter
        optionsRadioGroup = findViewById(R.id.optionsRadioGroup);
        submitButton = findViewById(R.id.submitButton);
        skipButton = findViewById(R.id.skipButton);
        questionImageView = findViewById(R.id.questionImageView);

        // Questions list
        questions = Arrays.asList(
                new Question("Which country is shown in the image?",
                        Arrays.asList("Italy", "Greece", "Albania", "Montenegro"), 2, R.drawable.albenia),

                new Question("Which country is shown in the image?",
                        Arrays.asList("Cyprus", "Turkey", "Syria", "Lebanon"), 0, R.drawable.cyprus),

                new Question("Which country is shown in the image?",
                        Arrays.asList("Ethiopia", "Italy", "Sudan", "Djibouti"), 1, R.drawable.italy),

                new Question("Which country is shown in the image?",
                        Arrays.asList("Sweden", "Finland", "Norway", "Russia"), 1, R.drawable.finland),

                new Question("Which country is shown in the image?",
                        Arrays.asList("Bangladesh", "Pakistan", "Nepal", "China"), 3, R.drawable.china),

                new Question("Which country is shown in the image?",
                        Arrays.asList("New Zealand", "Australia", "Fiji", "Indonesia"), 0, R.drawable.new_zealand),

                new Question("Which country is shown in the image?",
                        Arrays.asList("Benin", "Ghana", "Cameroon", "Senegal"), 3, R.drawable.senegal),

                new Question("Which country is shown in the image?",
                        Arrays.asList("Sweden", "Norway", "Finland", "Denmark"), 1, R.drawable.norway),

                new Question("Which country is shown in the image?",
                        Arrays.asList("Iraq", "Yemen", "Australia", "Iran"), 2, R.drawable.australia),

                new Question("Which country is shown in the image?",
                        Arrays.asList("Mexico", "Senegal", "Guinea-Bissau", "Sierra Leone"), 0, R.drawable.mozambique)
        );


        showQuestion();

        // Submit button logic
        submitButton.setOnClickListener(v -> {
            if (!answered) {
                checkAnswer();
                answered = true;
                submitButton.setText("Next");
            } else {
                nextQuestion();
            }
        });

        // Skip button logic
        skipButton.setOnClickListener(v -> nextQuestion());
    }

    private void showQuestion() {
        Question question = questions.get(currentQuestionIndex);

        // New: Set question number (e.g., "Question 1/4")
        questionNumberTextView.setText("Question " + (currentQuestionIndex + 1) + "/" + questions.size());

        // Set question and image
        questionTextView.setText(question.getText());
        questionImageView.setImageResource(question.getImageResId());

        // Set options
        for (int i = 0; i < optionsRadioGroup.getChildCount(); i++) {
            RadioButton radioButton = (RadioButton) optionsRadioGroup.getChildAt(i);
            radioButton.setText(question.getOptions().get(i));
        }

        // Reset selection
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
            // End of quiz
            Intent intent = new Intent(QuizActivity.this, ScoreActivity.class);
            intent.putExtra("score", score);
            intent.putExtra("totalQuestions", questions.size());
            startActivity(intent);
            finish();
        }
    }
}
