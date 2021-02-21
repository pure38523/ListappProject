package com.example.dechproduct.s23dquizapplication.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.dechproduct.s23dquizapplication.MainActivity;
import com.example.dechproduct.s23dquizapplication.Model.ModelMock;
import com.example.dechproduct.s23dquizapplication.R;

import java.util.List;

public class S23DAdapter extends RecyclerView.Adapter<S23DAdapter.ViewHolder> {

    private List<ModelMock> taskList;
    private MainActivity activity;

    public S23DAdapter(MainActivity activity){
        this.activity = activity;
}

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.task_layout,parent,false);

        return new ViewHolder(itemView);

    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
     ModelMock item = taskList.get(position);
     holder.task.setText(item.getTask());
     holder.task.setChecked(toBoolean(item.getStatus()));
    }

    private  Boolean toBoolean(int n){
        return n!=0;
    }

    @Override
    public int getItemCount() {
        return taskList.size();
    }

    public void setTasks(List<ModelMock> modelMockList){
        this.taskList = modelMockList;
        notifyDataSetChanged();

    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        CheckBox task;

         ViewHolder(View view) {
             super(view);
             task = view.findViewById(R.id.firstCheckBox);
        }
    }
}
