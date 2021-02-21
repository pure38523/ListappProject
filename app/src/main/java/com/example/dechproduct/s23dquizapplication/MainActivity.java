package com.example.dechproduct.s23dquizapplication;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.dechproduct.s23dquizapplication.Adapter.S23DAdapter;
import com.example.dechproduct.s23dquizapplication.DBUtils.DatabaseHandler;
import com.example.dechproduct.s23dquizapplication.Model.MenuNote;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class MainActivity extends AppCompatActivity implements DialogCloseListener {

    private DatabaseHandler db;

    private RecyclerView taskRecyclerView;
    private S23DAdapter taskAdapter;
    private List<MenuNote> taskList;
    private FloatingActionButton floatingActionButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
//        Objects.requireNonNull(getSupportActionBar()).hide();

        db = new DatabaseHandler(this);
        db.openDatabase();
        taskList = new ArrayList<>();

        taskRecyclerView = findViewById(R.id.taskRecyclerview);
        taskRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        taskAdapter = new S23DAdapter(db,this);
        taskRecyclerView.setAdapter(taskAdapter);

        floatingActionButton = findViewById(R.id.floatingButton);

        //slide left right
        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(new IconEditDeleteRecyclerView(taskAdapter));
        itemTouchHelper.attachToRecyclerView(taskRecyclerView);

        taskList = db.getAllTasks();
        Collections.reverse(taskList);
        taskAdapter.setTasks(taskList);

        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AddNewList.newInstance().show(getSupportFragmentManager(),AddNewList.TAG);

            }
        });




//Mock
//        MenuNote task = new MenuNote();
//        task.setTask("Test zaza Task Test");
//        task.setStatus(0);
//        task.setId(1);
//
//        taskList.add(task);
//        taskList.add(task);
//        taskList.add(task);
//        taskList.add(task);
//        taskList.add(task);
//
//        taskAdapter.setTasks(taskList);


    }

    @Override
    public void handleDialogClose(DialogInterface dialog) {
        taskList = db.getAllTasks();
        Collections.reverse(taskList);
        taskAdapter.setTasks(taskList);
        taskAdapter.notifyDataSetChanged();

    }
}