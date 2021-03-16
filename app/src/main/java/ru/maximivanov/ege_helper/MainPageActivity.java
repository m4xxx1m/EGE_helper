package ru.maximivanov.ege_helper;

import android.content.Intent;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

// главная активность приложения

public class MainPageActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_page);
        FooterFragment footerFragment = (FooterFragment) getSupportFragmentManager()
                .findFragmentById(R.id.footer);
        if (footerFragment != null) {
            footerFragment.changeImg((byte) 1);
        }
        TextView firstSubject= findViewById(R.id.first_subject);
        firstSubject.setText(User.getSubject((byte) 0).name);
        firstSubject.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                User.getSubject((byte) 0).makeCommonTest(MainPageActivity.this);
            }
        });
        LinearLayout varLayout = findViewById(R.id.var_layout);
        for (int i = 1; i < User.getSubjectsLen(); ++i) {
            View line = new View(this);
            LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(LinearLayout.
                    LayoutParams.MATCH_PARENT, dpToPx(3));
            layoutParams.setMargins(0, dpToPx(5), 0, dpToPx(5));
            line.setLayoutParams(layoutParams);
            line.setBackgroundColor(getColor(R.color.myRed));
            varLayout.addView(line);
            TextView subjectName = new TextView(this);
            subjectName.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.
                    LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT));
            subjectName.setClickable(true);
            subjectName.setFocusable(true);
            final byte thisSubjectId = (byte) i;
            subjectName.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    User.getSubject((byte) thisSubjectId).makeCommonTest(MainPageActivity.this);
                }
            });
            subjectName.setTextSize(18);
            subjectName.setText(User.getSubject((byte) i).name);
            varLayout.addView(subjectName);
        }
        Task badTask = User.userStatistic.getBadTask();
        if (badTask != null) {
            TextView textViewImprove = findViewById(R.id.first_improve);
            textViewImprove.setText(SubjectsList.getSubject(badTask.id) + " - задание №" + badTask.taskNum);
        }
    }
    
    public void onClickFooter(View v) {
        // функция вызывается, когда пользователь хочет перейти на другую активность, при нажатии кнопок с футера
        int id = v.getId();
        Intent toNextPage;
        switch (id) {
            case R.id.home_button:
                // текущая активаность
                break;
            case R.id.test_button:
                // на экран "Тесты"
                toNextPage = new Intent(MainPageActivity.this, TestsPageActivity.class);
                startActivity(toNextPage);
                break;
            case R.id.theory_button:
                // на экран "Теория"
                toNextPage = new Intent(MainPageActivity.this, TheoryPageActivity.class);
                startActivity(toNextPage);
                break;
            case R.id.statistic_button:
                // на экран "Статистика"
                toNextPage = new Intent(MainPageActivity.this, StatisticPageActivity.class);
                startActivity(toNextPage);
                break;
            case R.id.settings_button:
                // на экран "Настройки"
                toNextPage = new Intent(MainPageActivity.this, SettingsPageActivity.class);
                startActivity(toNextPage);
                break;
        }
    }

    public int dpToPx(int dp) {
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp,
                getResources().getDisplayMetrics());
    }
}
