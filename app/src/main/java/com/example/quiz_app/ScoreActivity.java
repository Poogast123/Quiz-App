package com.example.quiz_app;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.DatabaseReference;

public class ScoreActivity extends AppCompatActivity {

    private TextView scoreTextView;
    private ProgressBar circularProgressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_score);

        circularProgressBar = findViewById(R.id.circularProgressBar);
        scoreTextView = findViewById(R.id.scoreTextView);
        Button playAgainButton = findViewById(R.id.playAgainButton);
        Button returnToMenuButton = findViewById(R.id.returnToMenuButton);

        int score = getIntent().getIntExtra("score", 0);
        int totalQuestions = getIntent().getIntExtra("totalQuestions", 0);

        int progress = (int) ((float) score / totalQuestions * 100);
        circularProgressBar.setProgress(progress);
        scoreTextView.setText("Your Score: " + score + " / " + totalQuestions);

        // Save score to Firebase
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        if (user != null) {
            String userId = user.getUid();
            String userName = user.getDisplayName();
            if (userName == null || userName.isEmpty()) userName = "Anonymous";

            DatabaseReference scoresRef = FirebaseDatabase.getInstance().getReference("scores");

            // Create UserScore object (assuming this constructor exists)
            UserScore userScore = new UserScore(score, totalQuestions, System.currentTimeMillis(), userName);

            // Save score under userId
            scoresRef.child(userId).setValue(userScore)
                    .addOnSuccessListener(aVoid ->
                            Toast.makeText(ScoreActivity.this, "Score saved!", Toast.LENGTH_SHORT).show())
                    .addOnFailureListener(e ->
                            Toast.makeText(ScoreActivity.this, "Failed to save score", Toast.LENGTH_SHORT).show());
        }

        playAgainButton.setOnClickListener(v -> {
            startActivity(new Intent(this, QuizActivity.class));
            finish();
        });

        returnToMenuButton.setOnClickListener(v -> {
            Intent intent = new Intent(this, MenuActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
            finish();
        });
    }
}
