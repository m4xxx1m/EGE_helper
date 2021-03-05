package ru.maximivanov.ege_helper;

import android.content.Intent;
import android.os.Bundle;
import android.provider.Settings;
import android.view.View;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class MainPageActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (!User.isInitialised) {
            Intent choose_subjects = new Intent(MainPageActivity.this, MainActivity.class);
            startActivity(choose_subjects);
        }
        setContentView(R.layout.activity_main_page);
    }
    
    public void onClickFooter(View v) {
        int id = v.getId();
        Intent toNextPage;
        switch (id) {
            case R.id.home_button:
                break;
            case R.id.test_button:
                toNextPage = new Intent(MainPageActivity.this, TestsPageActivity.class);
                startActivity(toNextPage);
                break;
            case R.id.theory_button:
                toNextPage = new Intent(MainPageActivity.this, TheoryPageActivity.class);
                startActivity(toNextPage);
                break;
            case R.id.statistic_button:
                toNextPage = new Intent(MainPageActivity.this, StatisticPageActivity.class);
                startActivity(toNextPage);
                break;
            case R.id.settings_button:
                toNextPage = new Intent(MainPageActivity.this, SettingsPageActivity.class);
                startActivity(toNextPage);
                break;
        }
    }
}
