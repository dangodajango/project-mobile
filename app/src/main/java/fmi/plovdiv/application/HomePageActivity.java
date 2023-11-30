package fmi.plovdiv.application;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import fmi.plovdiv.application.model.Festival;

public class HomePageActivity extends AppCompatActivity {

    private ListView festivalListView;
    private FestivalAdapter festivalAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);
        setupFestivalsListView();
        setupCreateButton();
    }

    private void setupCreateButton() {
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        FloatingActionButton fabCreateFestival = findViewById(R.id.fabCreateFestival);
        fabCreateFestival.setOnClickListener(view -> {
            try {
                Intent intent = new Intent(HomePageActivity.this, CreateFestivalActivity.class);
                intent.putExtra("userId", getIntent().getStringExtra("userId"));
                startActivity(intent);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    private void setupFestivalsListView() {
        festivalListView = findViewById(R.id.festivalListView);
        festivalAdapter = new FestivalAdapter(this, new ArrayList<>());
        festivalListView.setAdapter(festivalAdapter);
        populateFestivalListView();
        setupFestivalItemOnClick();
    }

    private void populateFestivalListView() {
        festivalAdapter.addFestivals(fetchFestivals());
    }

    private void setupFestivalItemOnClick() {
        festivalListView.setOnItemClickListener((adapterView, view, position, id) -> {
            Festival selectedFestival = festivalAdapter.getItem(position);
            if (selectedFestival != null) {
                Intent intent = new Intent(HomePageActivity.this, FestivalDetailActivity.class);
                intent.putExtra("id", selectedFestival.getId());
                intent.putExtra("festivalName", selectedFestival.getName());
                intent.putExtra("date", selectedFestival.getDate());
                intent.putExtra("location", selectedFestival.getLocation());
                intent.putExtra("userId", getIntent().getStringExtra("userId"));
                startActivity(intent);
            }
        });
    }

    private List<Festival> fetchFestivals() {
        try {
            List<Festival> festivals = new ArrayList<>();
            String response = RestClient.get("festivals", createQuery());
            JSONArray userFestivals = new JSONArray(response);
            for (int i = 0; i < userFestivals.length(); i++) {
                JSONObject festival = userFestivals.getJSONObject(i);
                String festivalId = festival.getString("festivalId");
                String festivalName = festival.getString("festivalName");
                String date = festival.getString("date");
                String location = festival.getString("location");
                festivals.add(new Festival(festivalId, festivalName, date, location));
            }
            return festivals;
        } catch (Exception exception) {
            exception.printStackTrace();
            return new ArrayList<>();
        }
    }

    private String createQuery() {
        String userId = getIntent().getStringExtra("userId");
        return String.format("userId=%s", userId);
    }
}
