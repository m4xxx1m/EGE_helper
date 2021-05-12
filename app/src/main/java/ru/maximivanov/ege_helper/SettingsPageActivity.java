package ru.maximivanov.ege_helper;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import androidx.appcompat.app.AppCompatActivity;

// Экран настроек
public class SettingsPageActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.settings_page_activity);
        FooterFragment footerFragment = (FooterFragment) getSupportFragmentManager()
                .findFragmentById(R.id.footer);
        if (footerFragment != null) {
            footerFragment.changeImg((byte) 5);
        }
        findViewById(R.id.sign_up_button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SettingsPageActivity.this, SignUpActivity.class);
                startActivity(intent);
            }
        });
        findViewById(R.id.wipe_data_button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentDialog dialog = new FragmentDialog(FragmentDialog.WIPE_DATA, getApplicationContext());
                dialog.show(getSupportFragmentManager(), getString(R.string.dialog_tag_wipe_data));

            }
        });
        findViewById(R.id.about_button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentDialog dialog = new FragmentDialog(FragmentDialog.ABOUT, getApplicationContext());
                dialog.show(getSupportFragmentManager(), getString(R.string.dialog_tag_about));
            }
        });
    }

    public void onClickFooter(View v) {
        int id = v.getId();
        Intent toNextPage;
        switch (id) {
            case R.id.home_button:
                toNextPage = new Intent(SettingsPageActivity.this, MainPageActivity.class);
                startActivity(toNextPage);
                break;
            case R.id.test_button:
                toNextPage = new Intent(SettingsPageActivity.this, TestsPageActivity.class);
                startActivity(toNextPage);
                break;
            case R.id.theory_button:
                toNextPage = new Intent(SettingsPageActivity.this, TheoryPageActivity.class);
                startActivity(toNextPage);
                break;
            case R.id.statistic_button:
                toNextPage = new Intent(SettingsPageActivity.this, StatisticPageActivity.class);
                startActivity(toNextPage);
                break;
            case R.id.settings_button:
                break;
        }
    }
}
