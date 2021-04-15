package ru.maximivanov.ege_helper;

import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import java.util.ArrayList;
import java.util.Objects;

/*
    id предметов:
        0 - Русский язык
        1 - Математика профиль
        2 - Информатика
        3 - Физика
        4 - Химия
        5 - Биология
        6 - Обществознание
        7 - Литература
        8 - География
        9 - История
        10 - Английский язык
*/

// На этой активности пользователь выбирает предметы для дальнейшего изучения
public class ChoseSubjectsActivity extends AppCompatActivity {
    CheckBox []checkBox;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (User.isInitialized) {
            // если пользователь уже заходил в приложение, он переходит на главный экран
            Intent toApp = new Intent(ChoseSubjectsActivity.this, MainPageActivity.class);
            startActivity(toApp);
            finish();
        }
        Objects.requireNonNull(getSupportActionBar()).hide();
        setContentView(R.layout.activity_choose_subjects);
        checkBox = new CheckBox[SubjectsList.subjectsAmount];
        Button button = findViewById(R.id.button_ready);
        checkBox[0] = findViewById(R.id.russian);
        checkBox[1] = findViewById(R.id.math_profile);
        checkBox[2] = findViewById(R.id.informatics);
        checkBox[3] = findViewById(R.id.physics);
        checkBox[4] = findViewById(R.id.chemistry);
        checkBox[5] = findViewById(R.id.biology);
        checkBox[6] = findViewById(R.id.social_studies);
        checkBox[7] = findViewById(R.id.literature);
        checkBox[8] = findViewById(R.id.geography);
        checkBox[9] = findViewById(R.id.history);
        checkBox[10] = findViewById(R.id.english);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ArrayList<Byte> chosenSubjects = new ArrayList<>();
                for (byte i = 0; i < SubjectsList.subjectsAmount; ++i) {
                    if (checkBox[i].isChecked()) {
                        chosenSubjects.add(i);
                    }
                }
                if (!chosenSubjects.isEmpty()) {
                    User.setUserSubjectsId(chosenSubjects);
                    User.isInitialized = true;
                    Intent toApp = new Intent(ChoseSubjectsActivity.this, MainPageActivity.class);
                    Files.writeInt(Files.keys[0], 1); // запись в хранилище телефона, что пользователь заходил в приложение
                    Files.insertSubjects(chosenSubjects);
                    startActivity(toApp);
                    finish();
                }
            }
        });
    }
}