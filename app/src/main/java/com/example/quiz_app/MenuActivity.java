package com.example.quiz_app;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;

public class MenuActivity extends AppCompatActivity {

    private Button geographyQuizButton;
    private Button topScoresButton;
    private Button logoutButton; // New logout button

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        geographyQuizButton = findViewById(R.id.geographyQuizButton);
        topScoresButton = findViewById(R.id.topScoresButton);
        logoutButton = findViewById(R.id.logoutButton); // Reference new logout button

        geographyQuizButton.setOnClickListener(view -> {
            Intent intent = new Intent(MenuActivity.this, QuizActivity.class);
            startActivity(intent);
        });

        topScoresButton.setOnClickListener(view -> {
            Intent intent = new Intent(MenuActivity.this, TopScoresActivity.class);
            startActivity(intent);
        });

        logoutButton.setOnClickListener(view -> {
            Intent intent = new Intent(MenuActivity.this, MainActivity.class);
            // Optional: clear activity stack to prevent user from pressing back into menu
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
            finish();
        });
    }
}
