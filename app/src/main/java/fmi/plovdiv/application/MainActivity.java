package fmi.plovdiv.application;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setupButtons();
    }

    private void setupButtons() {
        setupSignInButton();
        setupSignUpButton();
    }

    private void setupSignInButton() {
        Button signInButton = findViewById(R.id.btnSignIn);
        signInButton.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, SignInActivity.class);
            startActivity(intent);
        });
    }

    private void setupSignUpButton() {
        Button signUpButton = findViewById(R.id.btnSignUp);
        signUpButton.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, SignUpActivity.class);
            startActivity(intent);
        });
    }
}