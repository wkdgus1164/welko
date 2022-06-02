package com.inhatc.welko;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class LoginActivity extends AppCompatActivity {

    private EditText email;
    private EditText pw;
    private Button loginBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        email = findViewById(R.id.loginEmail);
        pw = findViewById(R.id.loginPw);
        loginBtn = findViewById(R.id.loginBtn);

//        if (email.getText().toString().length() == 0
//        || pw.getText().toString().length() == 0) {
//            loginBtn.setTextColor("#000000");
//            loginBtn.setBackground();
//
//        }
    }
}