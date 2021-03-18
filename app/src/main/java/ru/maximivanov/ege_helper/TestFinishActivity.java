package ru.maximivanov.ege_helper;

import android.os.Bundle;
import android.widget.*;
import androidx.appcompat.app.AppCompatActivity;

public class TestFinishActivity extends AppCompatActivity {
    Test test;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_finish);
        test = User.userStatistic.getLastTest();
        for (int i = 0; i < test.getTaskAmount(); ++i) {
            TableRow tableRow = new TableRow(this);
            TextView taskNum = new TextView(this);
            taskNum.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT, 1));
        }
    }
}
