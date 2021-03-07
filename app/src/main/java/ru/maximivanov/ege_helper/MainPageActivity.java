package ru.maximivanov.ege_helper;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import androidx.appcompat.app.AppCompatActivity;

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
