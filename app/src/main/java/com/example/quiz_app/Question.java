package com.example.quiz_app;

import java.util.List;

public class Question {
    private String text;
    private List<String> options;
    private int correctAnswerIndex;
    private int imageResId; // 👈 Add this field

    // 👇 Updated constructor to include image
    public Question(String text, List<String> options, int correctAnswerIndex, int imageResId) {
        this.text = text;
        this.options = options;
        this.correctAnswerIndex = correctAnswerIndex;
        this.imageResId = imageResId;
    }

    public String getText() {
        return text;
    }

    public List<String> getOptions() {
        return options;
    }

    public int getCorrectAnswerIndex() {
        return correctAnswerIndex;
    }

    public int getImageResId() {
        return imageResId;
    }
}
