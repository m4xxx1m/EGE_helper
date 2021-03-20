package ru.maximivanov.ege_helper;

import android.graphics.Color;
import android.os.Bundle;
import android.widget.*;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class TestFinishActivity extends AppCompatActivity {
    Test test;
    ArrayList<Task> tasks;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_finish);
        TableLayout tableLayout = findViewById(R.id.table);
        test = User.userStatistic.getLastTest();
        tasks = test.getTasks();
        for (int i = 0; i < test.getTaskAmount(); ++i) {
            TableRow tableRow = new TableRow(this);
            tableRow.setLayoutParams(new TableLayout.LayoutParams(TableRow.LayoutParams.MATCH_PARENT,
                    TableRow.LayoutParams.WRAP_CONTENT));
//            tableLayout.addView(tableRow);
            TextView taskNum = new TextView(this);
            taskNum.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT, 1));

            tableRow.addView(taskNum);
            taskNum.setText(String.valueOf(i+1));

            TextView userAnswer = new TextView(this);
            userAnswer.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT, 1));
            userAnswer.setText(tasks.get(i).userAnswer);
            tableRow.addView(userAnswer);
            TextView correctAnswer = new TextView(this);
            correctAnswer.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT, 1));
            userAnswer.setText(tasks.get(i).getAnswer());
            tableRow.addView(correctAnswer);
            if (userAnswer.equals(correctAnswer)) {
                tableRow.setBackgroundColor(Color.GREEN);
            }
            else {
                tableRow.setBackgroundColor(Color.RED);
            }
            tableLayout.addView(tableRow, i + 2);
        }
    }
}
