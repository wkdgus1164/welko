package com.inhatc.welko;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

public class ViewActivity extends AppCompatActivity {

    private TextView intro;
    private TextView description;
    private TextView addr;
    private TextView trans;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view);

        intro = findViewById(R.id.introTextView);
        description = findViewById(R.id.descTextView);
        addr = findViewById(R.id.addrTextView);
        trans = findViewById(R.id.transTextView);

        intro.setText("Gyeongbokgung Palace was the first and largest of the royal palaces built during the Joseon Dynasty.");
        description.setText("Built in 1395, Gyeongbokgung Palace was located at the heart of the newly appointed capital of Seoul (then known as Hanyang) and represented the sovereignty of the Joseon Dynasty. \n" +
                "The largest of the Five Grand Palaces (the others being Gyeonghuigung Palace, Deoksugung Palace, Changgyeonggung Palace, Changdeokgung Palace), Gyeongbokgung served as the main palace of the Joseon Dynasty.");

        addr.setText("03045  161, Sajik-ro, Jongno-gu, Seoul");
        trans.setText("Subway Line 3, Gyeongbokgung Station, Exit 5 (5 mins on foot)\n" +
                "Subway Line 5, Gwanghwamun Station, Exit 2 (10 mins on foot)");
    }
}