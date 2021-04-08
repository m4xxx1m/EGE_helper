package ru.maximivanov.ege_helper;

import android.content.Intent;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.View;
import android.widget.*;
import androidx.appcompat.app.AppCompatActivity;
import java.util.ArrayList;

// главная активность приложения

public class MainPageActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_page);
        
        LinearLayout.LayoutParams lineParams = new LinearLayout.LayoutParams(LinearLayout.
                LayoutParams.MATCH_PARENT, dpToPx(3));
        lineParams.setMargins(0, dpToPx(5), 0, dpToPx(5));
        
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
            line.setLayoutParams(lineParams);
            line.setBackgroundColor(getColor(R.color.myRed));
            varLayout.addView(line, i*2-1);
            TextView subjectName = new TextView(this);
            subjectName.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.
                    LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT));
            subjectName.setClickable(true);
            subjectName.setFocusable(true);
            final byte thisSubjectId = (byte) i;
            subjectName.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    User.getSubject(thisSubjectId).makeCommonTest(MainPageActivity.this);
                }
            });
            subjectName.setTextSize(18);
            subjectName.setText(User.getSubject((byte) i).name);
            varLayout.addView(subjectName, i*2);
        }

        updateLastResult();
        updateImproveTasks();

    }

    @Override
    protected void onResume() {
        super.onResume();
        updateLastResult();
        updateImproveTasks();
    }

    protected void updateLastResult() {
        if (User.userStatistic.testResultsSize() > 0) {
            TextView lastResultName = findViewById(R.id.last_result_name);
            TextView lastResultPercent = findViewById(R.id.last_result_percent);
            Test test = User.userStatistic.getLastTest();
            lastResultName.setText(SubjectsList.getSubject(test.id).name);
            int score = test.getTestScore();
            int amount = test.getTaskAmount();
            lastResultPercent.setText(100 * score / amount + "%");
        }
    }

    public void updateImproveTasks() {
        ArrayList<Task> badTasks = User.userStatistic.getBadTask();
        if (badTasks != null && badTasks.size() > 0) {
            LinearLayout improveLayout = findViewById(R.id.improve_layout);
            improveLayout.removeAllViewsInLayout();
            LinearLayout.LayoutParams lineParams = new LinearLayout.LayoutParams(LinearLayout.
                    LayoutParams.MATCH_PARENT, dpToPx(3));
            lineParams.setMargins(0, dpToPx(5), 0, dpToPx(5));
            for (int i = 0; i < badTasks.size(); ++i) {
                TextView improveText;
                if (i != 0) {
                    View line = new View(this);
                    line.setLayoutParams(lineParams);
                    line.setBackgroundColor(getColor(R.color.myRed));
                    improveLayout.addView(line);
                }
                improveText = new TextView(this);
                improveText.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.
                        LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT));
                improveText.setClickable(true);
                improveText.setFocusable(true);
                improveText.setTextSize(18);
                final byte finalI = (byte) i;
                improveText.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        byte sub = badTasks.get(finalI).id;
                        byte taskNum = badTasks.get(finalI).taskNum;
                        User.getSubject(sub).makeOneTaskTest(MainPageActivity.this, taskNum);
                    }
                });
                improveText.setText(badTasks.get(i).toString());
                improveLayout.addView(improveText);
            }
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
