package ru.maximivanov.ege_helper;

import android.os.Bundle;
import android.util.TypedValue;
import android.view.View;
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
        test = User.userStatistic.getLastTest();
        tasks = test.getTasks();
        findViewById(R.id.finish).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();

            }
        });
        TextView result = findViewById(R.id.result);
        result.setText(getString(R.string.your_result) + test.getTestScore()
                + getString(R.string.out_of) + test.getTaskAmount());
        LinearLayout numLayout = findViewById(R.id.taskNum);
        LinearLayout userAnswerLayout = findViewById(R.id.your_answer);
        LinearLayout correctAnswerLayout = findViewById(R.id.correct_answer);
        for (int i = 0; i < test.getTaskAmount(); ++i) {
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
            params.setMargins(dpToPx(5), dpToPx(5), dpToPx(5), dpToPx(5));
            TextView numText = new TextView(this);
            TextView userAnswerText = new TextView(this);
            TextView correctAnswerText = new TextView(this);
            numText.setLayoutParams(params);
            userAnswerText.setLayoutParams(params);
            correctAnswerText.setLayoutParams(params);
            numText.setSingleLine();
            userAnswerText.setSingleLine();
            correctAnswerText.setSingleLine();
            numText.setTextSize(18);
            userAnswerText.setTextSize(18);
            correctAnswerText.setTextSize(18);
            numText.setText(String.valueOf(i+1));
            userAnswerText.setText(tasks.get(i).userAnswer);
            correctAnswerText.setText(tasks.get(i).getAnswer());
            numLayout.addView(numText);
            userAnswerLayout.addView(userAnswerText);
            correctAnswerLayout.addView(correctAnswerText);
        }
    }

    public int dpToPx(int dp) {
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp,
                getResources().getDisplayMetrics());
    }
}
