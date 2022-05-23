package com.inhatc.welko;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Button btnFirestore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnFirestore = findViewById(R.id.btnFirestore);
        btnFirestore.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        if (view == btnFirestore) {
            startActivity(new Intent(this, FirestoreActivity.class));
        }
    }
}