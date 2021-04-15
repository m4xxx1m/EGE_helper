package ru.maximivanov.ege_helper;

import android.annotation.SuppressLint;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class TaskFragment extends Fragment {
    private TextView taskName;
    private TextView taskText;
    private EditText editText;
    private ImageView imageView;
    private String fullTaskText;
    int num;

    public TaskFragment() {

    }

    public String getUserAnswer() {
        return editText.getText().toString();
    }

    @SuppressLint("SetTextI18n")
    public void set(int num, int taskNum, String taskName, String taskText, Bitmap image) {
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
            if (image != null) {
                int inWidth = image.getWidth();
                int inHeight = image.getHeight();
                image = Bitmap.createScaledBitmap(image, 3*inWidth, 3*inHeight, false);
                imageView.setImageBitmap(image);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @SuppressLint("SetTextI18n")
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
        imageView = v.findViewById(R.id.task_image);
        taskText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showAllText();
            }
        });
        return v;
    }
}