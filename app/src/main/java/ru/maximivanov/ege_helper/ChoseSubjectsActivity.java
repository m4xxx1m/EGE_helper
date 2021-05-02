package ru.maximivanov.ege_helper;

import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import java.util.ArrayList;
import java.util.Objects;

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
        checkBox = new CheckBox[SubjectsList.getSubjectsAmount()];
        Button button = findViewById(R.id.button_ready);

        int[] idArr = new int[] { R.id.russian, R.id.math_profile, R.id.informatics,
            R.id.physics, R.id.chemistry, R.id.biology, R.id.social_studies,
            R.id.literature, R.id.geography, R.id.history, R.id.english };
        for (int i = 0; i < SubjectsList.getSubjectsAmount(); ++i) {
            checkBox[i] = findViewById(idArr[i]);
        }
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ArrayList<Byte> chosenSubjects = new ArrayList<>();
                for (byte i = 0; i < SubjectsList.getSubjectsAmount(); ++i) {
                    if (checkBox[i].isChecked()) {
                        chosenSubjects.add(i);
                    }
                }
                if (!chosenSubjects.isEmpty()) {
                    User.setUserSubjectsId(chosenSubjects);
                    User.isInitialized = true;
                    Intent toApp = new Intent(ChoseSubjectsActivity.this, MainPageActivity.class);
                    Files.writeSharedPref(Files.keys[0], 1); // запись в хранилище телефона, что пользователь заходил в приложение
                    Files.insertSubjects(chosenSubjects);
                    startActivity(toApp);
                    finish();
                }
            }
        });
    }
}