package com.gauravlha.androparticles;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

public class HomeActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        RecyclerView recyclerView = findViewById(R.id.homeRecyclerView);
        HomeAdapter homeAdapter = new HomeAdapter();
        recyclerView.setAdapter(homeAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(HomeActivity.this));
        recyclerView.setNestedScrollingEnabled(false);

    }
}
