package fmi.plovdiv.application;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import org.json.JSONObject;

public class CreateFestivalActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_festival);
        Button buttonSave = findViewById(R.id.buttonSave);
        buttonSave.setOnClickListener(view -> {
            saveFestival();
            redirectToHomePage();
        });
    }

    private void saveFestival() {
        try {
            EditText editTextName = findViewById(R.id.editTextName);
            EditText editTextDate = findViewById(R.id.editTextDate);
            EditText editTextLocation = findViewById(R.id.editTextLocation);
            JSONObject requestBody = new JSONObject();
            requestBody.put("festivalName", editTextName.getText().toString());
            requestBody.put("date", editTextDate.getText().toString());
            requestBody.put("location", editTextLocation.getText().toString());
            requestBody.put("userId", getIntent().getStringExtra("userId"));
            RestClient.post("festival", requestBody);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private void redirectToHomePage() {
        Intent intent = new Intent(CreateFestivalActivity.this, HomePageActivity.class);
        intent.putExtra("userId", getIntent().getStringExtra("userId"));
        startActivity(intent);
    }
}