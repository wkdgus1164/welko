package com.inhatc.welko;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.material.dialog.MaterialAlertDialogBuilder;

import java.util.HashMap;
import java.util.Map;

@SuppressLint("SetTextI18n")
public class SignupActivity extends AppCompatActivity implements View.OnClickListener, TextWatcher {

    private EditText etName, etEmail, etPassword, etPasswordConfirm; // 회원가입 입력 정보
    private Button btnSubmit; // 회원가입 버튼
    private TextView tvPasswordConfirm; // 비밀번호 - 비밀번호 확인 입력 불일치 메시지

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        etName = findViewById(R.id.etName);
        etEmail = findViewById(R.id.etEmail);
        etPassword = findViewById(R.id.etPassword);
        etPasswordConfirm = findViewById(R.id.etPasswordConfirm);
        tvPasswordConfirm = findViewById(R.id.tvPasswordConfirm);
        btnSubmit = findViewById(R.id.btnSubmit);

        btnSubmit.setOnClickListener(this);

        // Edit Text 입력 변화 실시간 감시 (watcher)
        etName.addTextChangedListener(this);
        etEmail.addTextChangedListener(this);
        etPassword.addTextChangedListener(this);
        etPasswordConfirm.addTextChangedListener(this);

        disableSubmitButton(); // 회원가입 버튼 비활성화
    }

    // 회원가입 버튼 비활성화 메소드
    private void disableSubmitButton() {
        btnSubmit.setEnabled(false);
        btnSubmit.setBackgroundColor(Color.parseColor("#666666"));
    }

    // 회원가입 버튼 활성화 메소드
    private void enableSubmitButton() {
        btnSubmit.setEnabled(true);
        btnSubmit.setBackgroundColor(Color.parseColor("#2D51C9"));
    }

    // 백엔드 요청 메소드 (회원가입 이메일 중복 검증) -> POST 요청을 통해 입력한 회원가입 이메일 정보가 DB에 중복인지 검증
    private void requestSignUp() {

        // 입력한 회원가입 정보
        String name = etName.getText().toString();
        String email = etEmail.getText().toString();
        String password = etPassword.getText().toString();

        RequestQueue requestQueue = Volley.newRequestQueue(this); // Volley: 안드로이드에서 외부 API 를 요청
        String url = "http://welko.ap-northeast-2.elasticbeanstalk.com/member"; // 백엔드 서버 url

        StringRequest stringRequest = new StringRequest(
                Request.Method.POST,
                url,
                // 요청에 성공 응답 (회원가입 성공)
                response -> new MaterialAlertDialogBuilder(SignupActivity.this)
                        .setTitle("SignUp succeed")
                        .setMessage(response)
                        .setPositiveButton("Login", (dialogInterface, i) -> startActivity(
                                new Intent(SignupActivity.this, LoginActivity.class)
                        ))
                        .show(),
                // 실패 응답 (회원가입 실패) -> 이메일 중복됨
                error -> new MaterialAlertDialogBuilder(SignupActivity.this)
                        .setTitle("Request failed")
                        .setMessage("Already existing email.")
                        .setPositiveButton("Close", (dialogInterface, i) -> {
                        })
                        .show()
        ) {
            @NonNull
            @Override
            protected Map<String, String> getParams() {
                // Map 형태로 회원가입 정보를 서버에 전달
                Map<String, String> params = new HashMap<>();
                params.put("name", name);
                params.put("email", email);
                params.put("password", password);

                return params;
            }
        };

        stringRequest.setShouldCache(false);
        requestQueue.add(stringRequest);
    }

    // 회원가입 버튼 이벤트 -> 백엔드 요청 메소드 호출
    @Override
    public void onClick(View view) {
        if (view == btnSubmit) {
            requestSignUp();
        }
    }

    @Override
    public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

    }

    // Edit Text 입력 변화 이벤트 처리 메소드
    @Override
    public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
        String name = etName.getText().toString();
        String email = etEmail.getText().toString();
        String password = etPassword.getText().toString();
        String passwordConfirm = etPasswordConfirm.getText().toString();

        // 회원가입 버튼 활성화 조건 1 (이름 & 이메일 & 비밀번호 & 비밀번호 확인 길이 0 보다 크면)
        boolean isInputValid =
                name.length() > 0
                        && email.length() > 0
                        && password.length() > 0
                        && passwordConfirm.length() > 0;

        // 회원가입 버튼 활성화 조건 2 (비밀번호 - 비밀번호 확인 입력이 같으면)
        boolean isPasswordSame = password.equals(passwordConfirm);

        // 조건 1, 2 만족 시, 회원가입 버튼 활성화
        if (isInputValid && isPasswordSame) {
            enableSubmitButton();
            tvPasswordConfirm.setText("");
        }

        // 비밀번호 - 비밀번호 입력이 다르면 -> 불일치 메시지 출력 & 회원가입 버튼 비활성화
        if (password.length() > 0 && passwordConfirm.length() > 0 && !isPasswordSame) {
            disableSubmitButton();
            tvPasswordConfirm.setText("Password do not match.");
        }

        if (password.length() <= 0 || passwordConfirm.length() <= 0) {
            disableSubmitButton();
            tvPasswordConfirm.setText("");
        }
    }

    @Override
    public void afterTextChanged(Editable editable) {

    }
}