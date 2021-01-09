package ru.maximivanov.ege_helper;

import android.content.Intent;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;

public class MainPageActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_page);

        /*Intent choose_subjects = new Intent(MainPageActivity.this, MainActivity.class);
        startActivityForResult(choose_subjects, 1);*/

    }
}
