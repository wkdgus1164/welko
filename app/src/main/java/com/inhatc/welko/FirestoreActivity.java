package com.inhatc.welko;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.inhatc.welko.functions.FirestoreFunction;

public class FirestoreActivity extends AppCompatActivity implements View.OnClickListener {

    private Button btnGetData, btnSendData;
    private EditText etFirst, etSecond, etThird;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_firestore);

        btnGetData = findViewById(R.id.btnGetData);
        btnSendData = findViewById(R.id.btnSendData);

        etFirst = findViewById(R.id.edtFirst);
        etSecond = findViewById(R.id.edtSecond);
        etThird = findViewById(R.id.edtThird);

        btnGetData.setOnClickListener(this);
        btnSendData.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        FirestoreFunction firestoreFunction = new FirestoreFunction();

        if (view == btnGetData) {
            firestoreFunction.getFirestoreUserData();
            Toast.makeText(this, "데이터 가져오기", Toast.LENGTH_SHORT).show();
        } else if (view == btnSendData) {
            String first, second, third;

            first = etFirst.getText().toString();
            second = etSecond.getText().toString();
            third = etThird.getText().toString();

            try {
                firestoreFunction.sendFirestoreUserData(first, second, third);
                Toast.makeText(this, first + second + third + "데이터 저장 완료", Toast.LENGTH_SHORT).show();
            } catch (Exception e) {
                Toast.makeText(this, "데이터 전송에 오류가 발생했습니다.", Toast.LENGTH_SHORT).show();
                Log.e("Error on sending data", e.getMessage());
            }
        }
    }
}