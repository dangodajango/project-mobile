package fmi.plovdiv.application;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class FestivalDetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_festival_detail);
        setupFestivalData();
        setupDeleteButton();
    }

    private void setupFestivalData() {
        String festivalName = getIntent().getStringExtra("festivalName");
        String date = getIntent().getStringExtra("date");
        String location = getIntent().getStringExtra("location");
        TextView festivalNameTextView = findViewById(R.id.festivalNameTextView);
        festivalNameTextView.setText(String.format("%s - %s - %s", festivalName, date, location));
    }

    private void setupDeleteButton() {
        Button btnDelete = findViewById(R.id.btnDelete);
        btnDelete.setOnClickListener(view -> {
            RestClient.delete("festival", String.format("festivalId=%s", getIntent().getStringExtra("id")));
            Intent intent = new Intent(FestivalDetailActivity.this, HomePageActivity.class);
            intent.putExtra("userId", getIntent().getStringExtra("userId"));
            startActivity(intent);
            finish();
        });
    }
}