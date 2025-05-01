package com.example.quiz_app;


import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.*;
import android.content.Intent;

public class SignUpActivity extends AppCompatActivity {

    EditText nameEditText, emailEditText, passwordEditText;
    Button signupButton, loginButton; // optional login button too

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        nameEditText = findViewById(R.id.nameEditText);
        emailEditText = findViewById(R.id.emailEditText);
        passwordEditText = findViewById(R.id.passwordEditText);
        signupButton = findViewById(R.id.signupButton);
        loginButton = findViewById(R.id.loginButton);

        signupButton.setOnClickListener(v -> {
            String name = nameEditText.getText().toString().trim();
            String email = emailEditText.getText().toString().trim();
            String password = passwordEditText.getText().toString().trim();

            if (name.isEmpty() || email.isEmpty() || password.isEmpty()) {
                Toast.makeText(SignUpActivity.this, "Please fill all fields", Toast.LENGTH_SHORT).show();
            } else {
                // You can later save user data here

                Toast.makeText(SignUpActivity.this, "Signed Up Successfully!", Toast.LENGTH_SHORT).show();

                // After signup, maybe move to QuizActivity or MainActivity
                Intent intent = new Intent(SignUpActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        });

        loginButton.setOnClickListener(v -> {
            // Later you can move to login screen
            Toast.makeText(SignUpActivity.this, "Go to login screen (not implemented yet)", Toast.LENGTH_SHORT).show();
        });
    }
}
