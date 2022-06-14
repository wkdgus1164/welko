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

    // 로그인 입력 정보
    private EditText etEmail;
    private EditText etPassword;

    // 로그인 버튼
    private Button btnLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        etEmail = findViewById(R.id.etEmail);
        etPassword = findViewById(R.id.etPassword);
        btnLogin = findViewById(R.id.btnLogin);

        btnLogin.setOnClickListener(this);

        // Edit Text 입력 변화 실시간 감시 (watcher)
        etEmail.addTextChangedListener(this);
        etPassword.addTextChangedListener(this);

        disableLoginButton(); //로그인 버튼 비활성화
    }

    // 로그인 버튼 비활성화 메소드
    private void disableLoginButton() {
        btnLogin.setEnabled(false);
        btnLogin.setBackgroundColor(Color.parseColor("#666666"));
    }

    // 로그인 버튼 활성화 메소드
    private void enableLoginButton() {
        btnLogin.setEnabled(true);
        btnLogin.setBackgroundColor(Color.parseColor("#2D51C9"));
    }

    // 백엔드 요청 메소드 (로그인 정보 검증) -> GET 요청을 통해 입력한 로그인 정보가 DB에 유효한 정보인지 검증
    private void requestMemberList() {
        String email = etEmail.getText().toString();
        String password = etPassword.getText().toString();

        RequestQueue requestQueue = Volley.newRequestQueue(this); // Volley: 안드로이드에서 외부 API 를 요청

        // 백엔드 서버 url
        String url = "http://welko.ap-northeast-2.elasticbeanstalk.com/member/login?email=" + email + "&password=" + password;

        StringRequest stringRequest = new StringRequest(
                Request.Method.GET,
                url,
                // 요청에 성공 응답 (로그인 성공)
                response -> new MaterialAlertDialogBuilder(LoginActivity.this)
                        .setTitle("Login succeed")
                        .setMessage(response)
                        .setPositiveButton("Go home", (dialogInterface, i) -> startActivity(
                                new Intent(LoginActivity.this, HomeActivity.class)
                        ))
                        .show(),
                // 실패 응답 (로그인 실패) -> 유효하지 않은 로그인 정보
                error -> new MaterialAlertDialogBuilder(LoginActivity.this)
                        .setTitle("Login failed")
                        .setMessage("Please check your account information.")
                        .setPositiveButton("Close", (dialogInterface, i) -> {
                        })
                        .show()
        );

        requestQueue.add(stringRequest);
    }

    // 로그인 버튼 이벤트 -> 백엔드 요청 메소드 호출
    @Override
    public void onClick(View view) {
        if (view == btnLogin) requestMemberList();
    }

    @Override
    public void beforeTextChanged(CharSequence charSequence, int start, int count, int after) {

    }

    // Edit Text 입력 변화 이벤트 처리 메소드
    @Override
    public void onTextChanged(CharSequence charSequence, int start, int before, int count) {
        String email = etEmail.getText().toString();
        String password = etPassword.getText().toString();

        boolean isInputValid = email.length() > 0 && password.length() > 0; // 로그인 버튼 활성화 조건 (이메일 & 비밀번호 길이 0 보다 크면)

        if (isInputValid) enableLoginButton(); // 이메일 & 비밀번호 길이 > 0이면 로그인 버튼 활성화
        else disableLoginButton(); // 아니면, 로그인 버튼 비활성화
    }

    @Override
    public void afterTextChanged(Editable editable) {

    }
}