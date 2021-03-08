package ru.maximivanov.ege_helper;

import android.content.Intent;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;

public class StartPageActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Files.initialize(getApplicationContext());
        Files.read();
        Intent intent = new Intent(StartPageActivity.this, MainActivity.class);
        startActivity(intent);
        finish();
    }
}