package com.example.dechproduct.s23dquizapplication;

import android.app.Activity;
import android.content.DialogInterface;
import android.graphics.Color;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;

import com.example.dechproduct.s23dquizapplication.DBUtils.DatabaseHandler;
import com.example.dechproduct.s23dquizapplication.Model.MenuNote;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;

import java.util.Objects;

public class AddNewList extends BottomSheetDialogFragment {

 public static final String TAG = "ActionBottomDialog";
    private EditText newTableText;
    private EditText newTaskText;
    private Button newTaskSaveButton;

    private DatabaseHandler db;

    public static AddNewList newInstance(){
        return new AddNewList();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setStyle(STYLE_NORMAL, R.style.DialogStyle);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.new_task, container, false);
        getDialog().getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE);

        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        newTableText = Objects.requireNonNull(getView()).findViewById(R.id.newTableText);
        newTaskText = Objects.requireNonNull(getView()).findViewById(R.id.newTaskText);
        newTaskSaveButton = getView().findViewById(R.id.newTaskButton);

        boolean isUpdate = false;

        final Bundle bundle = getArguments();
        if(bundle != null){
            isUpdate = true;
            String table_naja = bundle.getString("table_naja");
            newTableText.setText(table_naja);
            String task = bundle.getString("task");
            newTaskText.setText(task);
            assert ((task != null) & (table_naja != null));
            if((task.length()>0) & (table_naja.length()>0))
                newTaskSaveButton.setTextColor(ContextCompat.getColor(Objects.requireNonNull(getContext()), R.color.colorPrimaryOrange));
        }

        db = new DatabaseHandler(getActivity());
        db.openDatabase();

        newTaskText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) { }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if(s.toString().equals("")){
                    newTaskSaveButton.setEnabled(false);
                    newTaskSaveButton.setTextColor(Color.GRAY);
                }
                else{
                    newTaskSaveButton.setEnabled(true);
                    newTaskSaveButton.setTextColor(ContextCompat.getColor(Objects.requireNonNull(getContext()), R.color.colorPrimaryOrange));
                }
            }

            @Override
            public void afterTextChanged(Editable s) { }
        });

        final boolean finalIsUpdate = isUpdate;
        newTaskSaveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String table_naja = newTableText.getText().toString();
                String text = newTaskText.getText().toString();
                if(finalIsUpdate){
                    db.updateTable(bundle.getInt("id"), table_naja);
                    db.updateTask(bundle.getInt("id"), text);
                }
                else {
                    MenuNote task = new MenuNote();
                    task.setTable(table_naja);
                    task.setTask(text);
                    task.setStatus(0);
                    db.insertTask(task);
                }
                dismiss();
            }
        });
    }

    @Override
    public void onDismiss(@NonNull DialogInterface dialog){
        Activity activity = getActivity();
        if(activity instanceof DialogCloseListener)
            ((DialogCloseListener)activity).handleDialogClose(dialog);
    }
}


