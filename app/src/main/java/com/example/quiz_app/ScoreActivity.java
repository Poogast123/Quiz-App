package com.example.quiz_app;


import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Button;
import android.content.Intent;

public class ScoreActivity extends AppCompatActivity {

    TextView scoreTextView;
    Button playAgainButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_score);

        scoreTextView = findViewById(R.id.scoreTextView);
        playAgainButton = findViewById(R.id.playAgainButton);

        int score = getIntent().getIntExtra("score", 0);
        int totalQuestions = getIntent().getIntExtra("totalQuestions", 0);

        scoreTextView.setText("Your Score: " + score + " / " + totalQuestions);

        playAgainButton.setOnClickListener(v -> {
            Intent intent = new Intent(ScoreActivity.this, QuizActivity.class);
            startActivity(intent);
            finish();
        });
    }
}
