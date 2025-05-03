package com.example.quiz_app;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.content.Intent;
import com.google.firebase.database.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class TopScoresActivity extends AppCompatActivity {

    ListView scoresListView;
    Button backToMenuButton;
    DatabaseReference scoresRef;
    ArrayAdapter<String> adapter;
    ArrayList<String> topScoresList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_top_scores);

        scoresListView = findViewById(R.id.scoresListView);
        backToMenuButton = findViewById(R.id.backToMenuButton);
        topScoresList = new ArrayList<>();
        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, topScoresList);
        scoresListView.setAdapter(adapter);

        // Firebase DB reference
        scoresRef = FirebaseDatabase.getInstance().getReference("scores");

        // Fetch top 10 scores ordered by score DESC
        scoresRef.orderByChild("score").limitToLast(10).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                topScoresList.clear();
                ArrayList<UserScore> scoreObjects = new ArrayList<>();

                for (DataSnapshot child : snapshot.getChildren()) {
                    UserScore score = child.getValue(UserScore.class);
                    if (score != null) {
                        scoreObjects.add(score);
                    }
                }

                // Reverse to make it descending
                Collections.sort(scoreObjects, new Comparator<UserScore>() {
                    @Override
                    public int compare(UserScore a, UserScore b) {
                        return Integer.compare(b.score, a.score);
                    }
                });

                for (int i = 0; i < scoreObjects.size(); i++) {
                    UserScore s = scoreObjects.get(i);
                    topScoresList.add((i + 1) + ". " + s.userName + ": " + s.score + " pts");
                }

                adapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                topScoresList.clear();
                topScoresList.add("Error loading scores.");
                adapter.notifyDataSetChanged();
            }
        });

        backToMenuButton.setOnClickListener(v -> {
            Intent intent = new Intent(TopScoresActivity.this, MenuActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
            finish();
        });
    }
}
