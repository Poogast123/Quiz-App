package com.example.quiz_app;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.content.Intent;

import java.util.ArrayList;

public class TopScoresActivity extends AppCompatActivity {

    ListView scoresListView;
    Button backToMenuButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_top_scores);

        scoresListView = findViewById(R.id.scoresListView);
        backToMenuButton = findViewById(R.id.backToMenuButton);

        // Dummy top 10 scores (you can replace this with actual data)
        ArrayList<String> topScores = new ArrayList<>();
        for (int i = 1; i <= 10; i++) {
            topScores.add("Player " + i + ": " + (100 - i * 5) + " points");
        }

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, topScores);
        scoresListView.setAdapter(adapter);

        backToMenuButton.setOnClickListener(v -> {
            Intent intent = new Intent(TopScoresActivity.this, MenuActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
            finish();
        });
    }
}
