package ru.maximivanov.ege_helper;

import android.content.Intent;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

// экран "Теория"
public class TheoryPageActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.theory_page_activity);
        FooterFragment footerFragment = (FooterFragment) getSupportFragmentManager()
                .findFragmentById(R.id.footer);
        if (footerFragment != null) {
            footerFragment.changeImg((byte) 3);
        }

        LinearLayout tasksLayout = findViewById(R.id.theory_place_holder);
        for (byte i = 0; i < User.getSubjectsLen(); ++i) {
            TextView subjectName = new TextView(this);
            subjectName.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.
                    LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT));
            subjectName.setTextSize(24);
            subjectName.setText(User.getSubject(i).name);
            tasksLayout.addView(subjectName);
            LinearLayout roundedRectangle = new LinearLayout(this);
            LinearLayout.LayoutParams linLayParams = new LinearLayout.LayoutParams(LinearLayout
                    .LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
            linLayParams.setMargins(0, dpToPx(15), 0, dpToPx(15));
            roundedRectangle.setLayoutParams(linLayParams);
            roundedRectangle.setOrientation(LinearLayout.VERTICAL);
            roundedRectangle.setBackground(getDrawable(R.drawable.rounded_rectangle));
            roundedRectangle.setPadding(dpToPx(12), dpToPx(12), dpToPx(12), dpToPx(12));
            tasksLayout.addView(roundedRectangle);
            for (byte j = 1; j <= User.getSubject(i).taskAmount; j++) {
                TextView theoryButton = new TextView(this);
                LinearLayout.LayoutParams theoryButParams = new LinearLayout.LayoutParams(LinearLayout
                        .LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
                theoryButParams.setMargins(0, dpToPx(4), 0, dpToPx(4));
                theoryButton.setLayoutParams(theoryButParams);
                try {
                    theoryButton.setText(j + ") " + User.getSubject(i).tasksNames[j-1]);
                }
                catch (NullPointerException e) {
                    e.printStackTrace();
                    theoryButton.setText(j + ") " + User.getSubject(i).name);
                }
                theoryButton.setClickable(true);
                theoryButton.setFocusable(true);
                theoryButton.setTextSize(18);
                final byte finalI = i;
                final byte finalJ = j;
                theoryButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        // change!
                    }
                });
                roundedRectangle.addView(theoryButton);
            }
        }
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

    public int dpToPx(int dp) {
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp,
                getResources().getDisplayMetrics());
    }
}
