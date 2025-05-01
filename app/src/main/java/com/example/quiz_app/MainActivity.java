package com.example.quiz_app;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    EditText usernameEditText, passwordEditText;
    Button loginButton, signUpButton;
    ImageView appLogoImageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Initialize Views
        usernameEditText = findViewById(R.id.usernameEditText);
        passwordEditText = findViewById(R.id.passwordEditText);
        loginButton = findViewById(R.id.loginButton);
        signUpButton = findViewById(R.id.signUpButton);
        appLogoImageView = findViewById(R.id.appLogoImageView);

        // Handle Login Button Click
        loginButton.setOnClickListener(v -> {
            String username = usernameEditText.getText().toString().trim();
            String password = passwordEditText.getText().toString().trim();

            if (username.equals("admin") && password.equals("1234")) {
                Intent intent = new Intent(MainActivity.this, QuizActivity.class);
                startActivity(intent);
                finish();
            } else {
                Toast.makeText(MainActivity.this, "Incorrect credentials", Toast.LENGTH_SHORT).show();
            }
        });

        // Handle Sign Up Button Click
        signUpButton.setOnClickListener(v -> {
            Toast.makeText(MainActivity.this, "Sign Up button clicked", Toast.LENGTH_SHORT).show();
            // You can replace this with opening a SignUpActivity
            // Example:
            Intent intent = new Intent(MainActivity.this, SignUpActivity.class);
            startActivity(intent);
        });
    }
}
