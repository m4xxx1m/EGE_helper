package ru.maximivanov.ege_helper;

import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class TaskFragment extends Fragment {
    String answer;
    TextView taskNum;
    TextView taskName;
    TextView taskText;
    EditText editText;
    public TaskFragment() {

    }

    public void set(int num, int taskNum, String taskName, String taskText) {
//        this.taskNum = getActivity().findViewById(R.id.taskNum);
        try {
            this.taskNum.setText(String.valueOf(num));
            this.taskName.setText("№" + taskNum + " - " + taskName);
            this.taskText.setText(taskText);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public static TaskFragment newInstance() {
        TaskFragment fragment = new TaskFragment();
        return fragment;
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
        taskNum = v.findViewById(R.id.taskNum);
        taskText = v.findViewById(R.id.taskText);
        editText = v.findViewById(R.id.edit_text);

        return v;
    }
}