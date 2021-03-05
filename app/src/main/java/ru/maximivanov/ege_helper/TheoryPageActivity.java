package ru.maximivanov.ege_helper;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import androidx.appcompat.app.AppCompatActivity;

public class TheoryPageActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.theory_page_activity);
    }

    public void onClickFooter(View v) {
        int id = v.getId();
        Intent toNextPage;
        switch (id) {
            case R.id.home_button:
                toNextPage = new Intent(TheoryPageActivity.this, MainPageActivity.class);
                startActivity(toNextPage);
                break;
            case R.id.test_button:
                toNextPage = new Intent(TheoryPageActivity.this, TestsPageActivity.class);
                startActivity(toNextPage);
                break;
            case R.id.theory_button:
                break;
            case R.id.statistic_button:
                toNextPage = new Intent(TheoryPageActivity.this, StatisticPageActivity.class);
                startActivity(toNextPage);
                break;
            case R.id.settings_button:
                toNextPage = new Intent(TheoryPageActivity.this, SettingsPageActivity.class);
                startActivity(toNextPage);
                break;
        }
    }
}
