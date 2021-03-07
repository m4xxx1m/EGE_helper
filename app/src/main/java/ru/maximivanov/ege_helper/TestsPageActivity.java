package ru.maximivanov.ege_helper;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import androidx.appcompat.app.AppCompatActivity;

public class TestsPageActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tests_page_activity);
        FooterFragment footerFragment = (FooterFragment) getSupportFragmentManager()
                .findFragmentById(R.id.footer);
        if (footerFragment != null) {
            footerFragment.changeImg((byte) 2);
        }
    }

    public void onClickFooter(View v) {
        int id = v.getId();
        Intent toNextPage;
        switch (id) {
            case R.id.home_button:
                toNextPage = new Intent(TestsPageActivity.this, MainPageActivity.class);
                startActivity(toNextPage);
                break;
            case R.id.test_button:
                break;
            case R.id.theory_button:
                toNextPage = new Intent(TestsPageActivity.this, TheoryPageActivity.class);
                startActivity(toNextPage);
                break;
            case R.id.statistic_button:
                toNextPage = new Intent(TestsPageActivity.this, StatisticPageActivity.class);
                startActivity(toNextPage);
                break;
            case R.id.settings_button:
                toNextPage = new Intent(TestsPageActivity.this, SettingsPageActivity.class);
                startActivity(toNextPage);
                break;
        }
    }
}
