package ru.maximivanov.ege_helper;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

// пока что класс никак не задействован

public class LogInActivity extends AppCompatActivity {

    // ВРЕМЕННО
    private final String correctLogin = "admin";
    private final String correctPassword = "12345678";
    //
    private EditText login;
    private EditText password;
    private TextView wrong_login_or_password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_in);
        login = findViewById(R.id.Login);
        password = findViewById(R.id.Password);
        wrong_login_or_password = findViewById(R.id.wrong_password);
    }

    public void clickSignInButton(View view) {
        String thisLogin, thisPassword;
        thisLogin = login.getText().toString();
        thisPassword = password.getText().toString();

        if (thisLogin.equals(correctLogin) && thisPassword.equals(correctPassword)) {
            finish();
        }
        else {
            wrong_login_or_password.setVisibility(View.VISIBLE);
        }
    }

    public void clickSignUpButton(View view) {
        Intent intent = new Intent(LogInActivity.this, SignUpActivity.class);
        String thisLogin, thisPassword;
        thisLogin = login.getText().toString();
        thisPassword = password.getText().toString();
        intent.putExtra("login", thisLogin);
        intent.putExtra("password", thisPassword);
        startActivity(intent);
    }
}
