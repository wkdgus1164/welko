package com.inhatc.welko;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class IntroActivity extends AppCompatActivity implements View.OnClickListener {

    private Button signup;
    private Button login;
    private TextView signupLater;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intro);

        signup = findViewById(R.id.introSignupBtn);
        signup.setOnClickListener(this);

        login = findViewById(R.id.introLoginBtn);
        login.setOnClickListener(this);

        signupLater = findViewById(R.id.signupLaterTextView);
        signupLater.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {

        // 회원가입 화면 이동
        if (v == signup) {
            Intent signupIntent = new Intent(this, SignupActivity.class);
            startActivity(signupIntent);
        }

        // 로그인 화면 이동
        if (v == login) {
            Intent loginIntent = new Intent(this, LoginActivity.class);
            startActivity(loginIntent);
        }

        // 홈 화면 이동 (로그인 없이 이용하기)
        if (v == signupLater) {
            Intent homeIntent = new Intent(this, HomeActivity.class);
            startActivity(homeIntent);
        }
    }
}