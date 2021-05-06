package ru.maximivanov.ege_helper;

import android.content.Intent;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;

public class StartPageActivity extends AppCompatActivity {
    // Стартовая активность приложения, отображающая экран загрузки и подгружающая информацию из хранилища телефона
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        SubjectsList.setSubjects(getApplicationContext());
        Files.initialize(getApplicationContext());
        Files.selectSubjects(); // считывание из базы данных
        Intent intent = new Intent(StartPageActivity.this, ChoseSubjectsActivity.class);
        startActivity(intent);
        finish();
    }
}