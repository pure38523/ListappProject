package com.example.dechproduct.s23dquizapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.dechproduct.s23dquizapplication.Adapter.S23DAdapter;
import com.example.dechproduct.s23dquizapplication.Model.ModelMock;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private RecyclerView taskRecyclerView;
    private S23DAdapter taskAdapter;
    private List<ModelMock> taskList;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
//        getSupportActionBar().hide();

        taskList = new ArrayList<>();

        taskRecyclerView = findViewById(R.id.taskRecyclerview);
        taskRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        taskAdapter = new S23DAdapter(this);
        taskRecyclerView.setAdapter(taskAdapter);

        ModelMock task = new ModelMock();
        task.setTask("Test zaza Task Test");
        task.setStatus(0);
        task.setId(1);

        taskList.add(task);
        taskList.add(task);
        taskList.add(task);
        taskList.add(task);
        taskList.add(task);

        taskAdapter.setTasks(taskList);


    }
}