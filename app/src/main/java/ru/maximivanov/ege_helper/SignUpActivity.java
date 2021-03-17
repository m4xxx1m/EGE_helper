package ru.maximivanov.ege_helper;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import androidx.appcompat.app.AppCompatActivity;

// пока что класс никак не задействован
public class SignUpActivity extends AppCompatActivity {

    private EditText login;
    private EditText password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        login = findViewById(R.id.Login);
        password = findViewById(R.id.Password);
        login.setText(getIntent().getStringExtra("login"));
        password.setText(getIntent().getStringExtra("password"));
    }

    public void clickSignUpButton(View view) {
        finish();
    }
}
