package com.example.dechproduct.s23dquizapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

public class SecondActivity extends AppCompatActivity {

    private RecyclerView taskRecyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        getSupportActionBar().hide();

        taskRecyclerView = findViewById(R.id.taskRecyclerview);
        taskRecyclerView.setLayoutManager(new LinearLayoutManager(this));

    }
}