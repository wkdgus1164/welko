package com.inhatc.welko;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private TextView signup; // sign up (회원가입 화면 이동)
    private Button signin; // sign in (로그인 기능 수행 -> home 화면 이동)

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        signup = findViewById(R.id.signupTextView);
        signup.setOnClickListener(this);

        signin = findViewById(R.id.signinBtn);
        signin.setOnClickListener(this);

        // App UI Test
        // setContentView(R.layout.activity_intro);
//         setContentView(R.layout.activity_login);
//         setContentView(R.layout.activity_signup);
        // setContentView(R.layout.activity_home);
//         setContentView(R.layout.activity_signuptest);
    }

    @Override
    public void onClick(View v) {

        // signupIntent: 회원가입 화면 이동
        if (v == signup) {
            Intent signupIntent = new Intent(this, SignupActivity.class);
            startActivity(signupIntent);
        }

        // homeIntent: 홈 화면 이동
        if (v == signin) {
            Intent homeIntent = new Intent(this, HomeActivity.class);
            startActivity(homeIntent);
        }
    }
}