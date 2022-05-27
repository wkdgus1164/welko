package com.inhatc.welko;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class SignupActivity extends AppCompatActivity implements View.OnClickListener {

    private Button createAcc; // create Account (회원가입 기능 수행 -> home 화면 이동)

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        createAcc = findViewById(R.id.createAccBtn);
        createAcc.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {

        // homeIntent: 홈 화면 이동
        if (v == createAcc) {
            Intent homeIntent = new Intent(this, HomeActivity.class);
            startActivity(homeIntent);
        }
    }
}