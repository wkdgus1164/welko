package com.inhatc.welko;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;

public class IntroActivity extends AppCompatActivity implements View.OnClickListener{

    // 1초 동안 intro 화면 노출 후, login 화면으로 이동
    // https://anywaydevlog.tistory.com/24 (참고 자료)

    private Button signup;
    private Button login;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intro);

        signup = findViewById(R.id.introSignupBtn);
        signup.setOnClickListener(this);

        login = findViewById(R.id.introLoginBtn);
        login.setOnClickListener(this);

//        moveMain(1);
    }

    private void moveMain(int sec) {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(getApplicationContext(),MainActivity.class);
                startActivity(intent);
                finish();
            }
        },1000*sec);
    }

    @Override
    public void onClick(View v) {

        // signupIntent: 회원가입 화면 이동
        if (v == signup) {
            Intent signupIntent = new Intent(this, SignupActivity.class);
            startActivity(signupIntent);
        }

        // loginIntent: 로그인 화면 이동
        if (v == login) {
            Intent loginIntent = new Intent(this, LoginActivity.class);
            startActivity(loginIntent);
        }
    }
}