package ru.maximivanov.ege_helper;

import android.content.Intent;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

// Экран "Статистика"
public class StatisticPageActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.statistic_page_activity);
        FooterFragment footerFragment = (FooterFragment) getSupportFragmentManager()
                .findFragmentById(R.id.footer);
        if (footerFragment != null) {
            footerFragment.changeImg((byte) 4);
        }
        int size = User.userStatistic.testResultsSize();
        if (size > 0) {
            TextView firstName = findViewById(R.id.first_statistic_name);
            TextView firstPercent = findViewById(R.id.first_percent);
            Test test = User.userStatistic.getLastTest();
            firstName.setText(SubjectsList.getSubject(test.id).name);
            int score = test.getTestScore();
            int amount = test.getTaskAmount();
            firstPercent.setText(100 * score / amount + "%");
        }
        LinearLayout placeHolder = findViewById(R.id.statistic_place_holder);

        LinearLayout.LayoutParams lineParams = new LinearLayout.LayoutParams(LinearLayout.
                LayoutParams.MATCH_PARENT, dpToPx(3));
        lineParams.setMargins(0, dpToPx(5), 0, dpToPx(5));
        LinearLayout.LayoutParams relativeParams = new LinearLayout.LayoutParams(LinearLayout
                .LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        RelativeLayout.LayoutParams nameParams = new RelativeLayout.LayoutParams(RelativeLayout
                .LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
        RelativeLayout.LayoutParams percentParams = new RelativeLayout.LayoutParams(RelativeLayout
                .LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
        percentParams.addRule(RelativeLayout.ALIGN_PARENT_END);
        for (int i = size - 2; i >= 0; --i) {
            View line = new View(this);
            line.setLayoutParams(lineParams);
            line.setBackgroundColor(getColor(R.color.myRed));
            placeHolder.addView(line);

            RelativeLayout relativeLayout = new RelativeLayout(this);
            relativeLayout.setLayoutParams(relativeParams);
            placeHolder.addView(relativeLayout);

            Test test = User.userStatistic.getTest(i);
            TextView subName = new TextView(this);
            subName.setLayoutParams(nameParams);
            subName.setTextSize(18);
            subName.setText(SubjectsList.getSubject(test.id).name);
            relativeLayout.addView(subName);

            TextView percent = new TextView(this);
            percent.setLayoutParams(percentParams);
            percent.setTextSize(18);
            int score = test.getTestScore();
            int amount = test.getTaskAmount();
            percent.setText(100 * score / amount + "%");
            relativeLayout.addView(percent);
        }
    }

    public void onClickFooter(View v) {
        int id = v.getId();
        Intent toNextPage;
        switch (id) {
            case R.id.home_button:
                toNextPage = new Intent(StatisticPageActivity.this, MainPageActivity.class);
                startActivity(toNextPage);
                break;
            case R.id.test_button:
                toNextPage = new Intent(StatisticPageActivity.this, TestsPageActivity.class);
                startActivity(toNextPage);
                break;
            case R.id.theory_button:
                toNextPage = new Intent(StatisticPageActivity.this, TheoryPageActivity.class);
                startActivity(toNextPage);
                break;
            case R.id.statistic_button:
                break;
            case R.id.settings_button:
                toNextPage = new Intent(StatisticPageActivity.this, SettingsPageActivity.class);
                startActivity(toNextPage);
                break;
        }
    }

    public int dpToPx(int dp) {
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp,
                getResources().getDisplayMetrics());
    }
}
