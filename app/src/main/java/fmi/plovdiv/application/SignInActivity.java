package fmi.plovdiv.application;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import org.json.JSONException;
import org.json.JSONObject;

public class SignInActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);
        setupButtons();
    }

    private void setupButtons() {
        setupGoToSignUpPageButton();
        setupSubmitSignInButton();
    }

    private void setupGoToSignUpPageButton() {
        Button goToSignUpButton = findViewById(R.id.btnSubmit);
        goToSignUpButton.setOnClickListener(view -> {
            Intent intent = new Intent(SignInActivity.this, SignUpActivity.class);
            startActivity(intent);
            finish();
        });
    }

    private void setupSubmitSignInButton() {
        Button submitSignInButton = findViewById(R.id.btnSubmit);
        submitSignInButton.setOnClickListener(view -> {
            String response = RestClient.get("user", createHttpQuery());
            if (response.isEmpty()) {
                showErrorPopup();
            } else {
                redirectToHomePage(response);
            }
        });
    }

    private void redirectToHomePage(String response) {
        Intent intent = new Intent(SignInActivity.this, HomePageActivity.class);
        try {
            JSONObject jsonObject = new JSONObject(response);
            intent.putExtra("userId", jsonObject.getString("id"));
            startActivity(intent);
            finish();
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    private void showErrorPopup() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Error")
                .setMessage("Incorrect password. Please try again.")
                .setPositiveButton("OK", (dialog, which) -> dialog.dismiss())
                .show();
    }

    private String createHttpQuery() {
        EditText editTextEmail = findViewById(R.id.editTextEmail);
        EditText editTextPassword = findViewById(R.id.editTextPassword);
        return String.format("email=%s&password=%s",
                editTextEmail.getText(), editTextPassword.getText());
    }
}
