package fmi.plovdiv.application;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import org.json.JSONObject;

import fmi.plovdiv.application.model.CreateUserData;

public class SignUpActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        setupButtons();
    }

    private void setupButtons() {
        setupGoBackToSignInButton();
        setupSubmitSignUpButton();
    }

    private void setupGoBackToSignInButton() {
        Button goToSignInButton = findViewById(R.id.btnGoToSignIn);
        goToSignInButton.setOnClickListener(view -> {
            Intent intent = new Intent(SignUpActivity.this, SignInActivity.class);
            startActivity(intent);
            finish();
        });
    }

    private void setupSubmitSignUpButton() {
        Button submitSignUpButton = findViewById(R.id.btnSubmitSignUp);
        submitSignUpButton.setOnClickListener(view -> {
            RestClient.post("user", createRequestBody());
            Button goToSignInButton = findViewById(R.id.btnGoToSignIn);
            goToSignInButton.performClick();
        });
    }

    private JSONObject createRequestBody() {
        try {
            CreateUserData createUserData = parseActivityCreateUserData();
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("firstName", createUserData.getFirstName());
            jsonObject.put("lastName", createUserData.getLastName());
            jsonObject.put("dateOfBirth", createUserData.getDateOfBirth());
            jsonObject.put("email", createUserData.getEmail());
            jsonObject.put("password", createUserData.getPassword());
            return jsonObject;
        } catch (Exception exception) {
            exception.printStackTrace();
            return null;
        }
    }

    private CreateUserData parseActivityCreateUserData() {
        EditText editTextFirstName = findViewById(R.id.editTextFirstName);
        EditText editTextLastName = findViewById(R.id.editTextLastName);
        EditText editTextDateOfBirth = findViewById(R.id.editTextDateOfBirth);
        EditText editTextEmailSignUp = findViewById(R.id.editTextEmailSignUp);
        EditText editTextPasswordSignUp = findViewById(R.id.editTextPasswordSignUp);
        return new CreateUserData(
                editTextFirstName.getText().toString(),
                editTextLastName.getText().toString(),
                editTextDateOfBirth.getText().toString(),
                editTextEmailSignUp.getText().toString(),
                editTextPasswordSignUp.getText().toString());
    }
}
