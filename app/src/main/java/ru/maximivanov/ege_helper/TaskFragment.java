package ru.maximivanov.ege_helper;

import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class TaskFragment extends Fragment {
//    private String answer;
//    private TextView taskNum;
    private TextView taskName;
    private TextView taskText;
    private EditText editText;
    String fullTaskText;
    int num;
    public TaskFragment() {

    }

    public String getUserAnswer() {
        return editText.getText().toString();
    }

    public void set(int num, int taskNum, String taskName, String taskText) {
        this.num = num;
        try {
            this.taskName.setText("№" + taskNum + " - " + taskName);
            fullTaskText = taskText;
            if (taskText.length() > 230) {
                this.taskText.setText(taskText.substring(0, 200) + "...\n\nПоказать ещё");
            }
            else {
                this.taskText.setText(taskText);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void showAllText() {
        String text = taskText.getText().toString();
        if (text.endsWith("Показать ещё")) {
            taskText.setText(fullTaskText);
        }
        else {
            if (fullTaskText.length() > 230) {
                taskText.setText(fullTaskText.substring(0, 200) + "...\n\nПоказать ещё");
            }
            else {
                taskText.setText(fullTaskText);
            }
        }
    }

    public static TaskFragment newInstance() {
        return new TaskFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_task, container, false);
        taskName = v.findViewById(R.id.taskName);
        taskText = v.findViewById(R.id.taskText);
        editText = v.findViewById(R.id.edit_text);
        taskText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showAllText();
            }
        });
        return v;
    }
}