package com.inhatc.welko;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.material.dialog.MaterialAlertDialogBuilder;

@SuppressLint({"ResourceAsColor", "SetTextI18n"})
public class LoginActivity extends Activity implements View.OnClickListener, TextWatcher {

    private EditText etEmail;
    private EditText etPassword;
    private Button btnLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        etEmail = findViewById(R.id.etEmail);
        etPassword = findViewById(R.id.etPassword);
        btnLogin = findViewById(R.id.btnLogin);

        btnLogin.setOnClickListener(this);
        etEmail.addTextChangedListener(this);
        etPassword.addTextChangedListener(this);

        disableLoginButton();
    }

    private void disableLoginButton() {
        btnLogin.setEnabled(false);
        btnLogin.setBackgroundColor(Color.parseColor("#666666"));
    }

    private void enableLoginButton() {
        btnLogin.setEnabled(true);
        btnLogin.setBackgroundColor(Color.parseColor("#2D51C9"));
    }

    // Volley: 안드로이드에서 외부 API 를 요청할 때
    private void requestMemberList() {
        String email = etEmail.getText().toString();
        String password = etPassword.getText().toString();

        RequestQueue requestQueue = Volley.newRequestQueue(this);

        // 백엔드 서버 url (aws 도메인)
        String url = "http://welko.ap-northeast-2.elasticbeanstalk.com/member/login?email=" + email + "&password=" + password;

        StringRequest stringRequest = new StringRequest(
                Request.Method.GET,
                url,
                // 성공했을 때, 응답
                response -> new MaterialAlertDialogBuilder(LoginActivity.this)
                        .setTitle("Login succeed")
                        .setMessage(response)
                        .setPositiveButton("Go home", (dialogInterface, i) -> startActivity(
                                new Intent(LoginActivity.this, HomeActivity.class)
                        ))
                        .show(),
                // 실패 응답
                error -> new MaterialAlertDialogBuilder(LoginActivity.this)
                        .setTitle("Login failed")
                        .setMessage("Please check your account information.")
                        .setPositiveButton("Close", (dialogInterface, i) -> {
                        })
                        .show()
        );

        requestQueue.add(stringRequest);
    }

    @Override
    public void onClick(View view) {
        if (view == btnLogin) requestMemberList();
    }

    @Override
    public void beforeTextChanged(CharSequence charSequence, int start, int count, int after) {

    }

    @Override
    public void onTextChanged(CharSequence charSequence, int start, int before, int count) {
        // 로그인 폼 입력 조건 검사

        String email = etEmail.getText().toString();
        String password = etPassword.getText().toString();

        boolean isInputValid = email.length() > 0 && password.length() > 0;

        if (isInputValid) enableLoginButton();
        else disableLoginButton();
    }

    @Override
    public void afterTextChanged(Editable editable) {

    }
}