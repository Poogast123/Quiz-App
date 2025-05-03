package com.example.quiz_app;

public class UserScore {
    public int score;
    public int totalQuestions;
    public long timestamp;
    public String userName;

    public UserScore() {} // Needed for Firebase

    public UserScore(int score, int totalQuestions, long timestamp, String userName) {
        this.score = score;
        this.totalQuestions = totalQuestions;
        this.timestamp = timestamp;
        this.userName = userName;
    }
}

