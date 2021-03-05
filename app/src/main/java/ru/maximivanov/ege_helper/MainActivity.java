package ru.maximivanov.ege_helper;

import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

import java.util.ArrayList;
import java.util.Objects;

public class MainActivity extends AppCompatActivity {
    private final byte checkboxNum = 11;
    CheckBox []checkBox;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Objects.requireNonNull(getSupportActionBar()).hide();
        setContentView(R.layout.activity_main);
        checkBox = new CheckBox[checkboxNum];
        Button button = findViewById(R.id.button_ready);
        checkBox[0] = findViewById(R.id.russian);
        checkBox[1] = findViewById(R.id.math_base);
        checkBox[2] = findViewById(R.id.math_profile);
        checkBox[3] = findViewById(R.id.informatics);
        checkBox[4] = findViewById(R.id.physics);
        checkBox[5] = findViewById(R.id.chemistry);
        checkBox[6] = findViewById(R.id.biology);
        checkBox[7] = findViewById(R.id.social_studies);
        checkBox[8] = findViewById(R.id.literature);
        checkBox[9] = findViewById(R.id.geography);
        checkBox[10] = findViewById(R.id.history);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ArrayList<Byte> chosenSubjects = new ArrayList<>();
                for (byte i = 0; i < checkboxNum; ++i) {
                    if (checkBox[i].isChecked()) {
                        chosenSubjects.add(i);
                    }
                }
                User.setUserSubjectsId(chosenSubjects);
                finish();
            }
        });
    }
}