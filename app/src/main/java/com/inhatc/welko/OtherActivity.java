package com.inhatc.welko;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class OtherActivity extends AppCompatActivity {
    private ListView list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_other);

//        list = findViewById(R.id.otherListView);
//        List<String> data = new ArrayList<>();
//
//        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1,data);
//        list.setAdapter(adapter);
//
//        data.add("Account");
//        adapter.notifyDataSetChanged();
    }
}