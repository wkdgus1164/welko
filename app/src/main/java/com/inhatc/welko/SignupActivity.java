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

    private EditText etName, etEmail, etPassword, etPasswordConfirm;
    private Button btnSubmit;
    private TextView tvPasswordConfirm;

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
        etName.addTextChangedListener(this);
        etEmail.addTextChangedListener(this);
        etPassword.addTextChangedListener(this);
        etPasswordConfirm.addTextChangedListener(this);

        disableSubmitButton();
    }

    private void disableSubmitButton() {
        btnSubmit.setEnabled(false);
        btnSubmit.setBackgroundColor(Color.parseColor("#666666"));
    }

    private void enableSubmitButton() {
        btnSubmit.setEnabled(true);
        btnSubmit.setBackgroundColor(Color.parseColor("#2D51C9"));
    }

    private void requestSignUp() {
        String name = etName.getText().toString();
        String email = etEmail.getText().toString();
        String password = etPassword.getText().toString();

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        String url = "http://welko.ap-northeast-2.elasticbeanstalk.com/member";

        StringRequest stringRequest = new StringRequest(
                Request.Method.POST,
                url,
                response -> new MaterialAlertDialogBuilder(SignupActivity.this)
                        .setTitle("SignUp succeed")
                        .setMessage(response)
                        .setPositiveButton("Login", (dialogInterface, i) -> startActivity(
                                new Intent(SignupActivity.this, LoginActivity.class)
                        ))
                        .show(),
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

    @Override
    public void onClick(View view) {
        if (view == btnSubmit) {
            requestSignUp();
        }
    }

    @Override
    public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

    }

    @Override
    public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
        String name = etName.getText().toString();
        String email = etEmail.getText().toString();
        String password = etPassword.getText().toString();
        String passwordConfirm = etPasswordConfirm.getText().toString();

        boolean isInputValid =
                name.length() > 0
                        && email.length() > 0
                        && password.length() > 0
                        && passwordConfirm.length() > 0;

        boolean isPasswordSame = password.equals(passwordConfirm);

        if (isInputValid && isPasswordSame) {
            enableSubmitButton();
            tvPasswordConfirm.setText("");
        }

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