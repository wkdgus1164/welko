package com.inhatc.welko;


import androidx.appcompat.app.AppCompatActivity;

import androidx.viewpager2.widget.ViewPager2;

import android.content.Intent;
import android.graphics.drawable.GradientDrawable;
import android.os.Bundle;

import android.view.View;
import android.widget.ImageView;
import android.widget.SearchView;
import android.widget.TextView;


import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;
import com.inhatc.welko.functions.FirestoreFunction;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class HomeActivity extends AppCompatActivity {

    private HomeActivity mContext = HomeActivity.this;
    private MyFragmentStateAdapter myFragmentStateAdapter;
    private ViewPager2 viewPager2;
    private TabLayout tabLayout;
//    private TextView attrName1;

    private SearchView searchView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        searchView = findViewById(R.id.searchView);
        searchView.setQueryHint("Search for tourist destinations");

//        attrName1 = findViewById(R.id.attrName1);

//        FirestoreFunction firestoreFunction = new FirestoreFunction();
//        ArrayList<Travel> travelList = new ArrayList<Travel>();
//        Travel travel = new Travel();
//        travelList = firestoreFunction.getFirestoreTravelList("Attractions");
//        travel = travelList.get(0);


        init();
        initAdapter();
        intiTab();

//        attrName1.setText(travel.getName().toString());
    }

    private void intiTab() {
        new TabLayoutMediator(tabLayout, viewPager2, (tab, position) -> {}).attach();

        tabLayout.getTabAt(0).setText("Attractions");
        tabLayout.getTabAt(1).setText("Nature");
        tabLayout.getTabAt(2).setText("Shopping");
    }

    private void initAdapter() {
        myFragmentStateAdapter = new MyFragmentStateAdapter(this);
        myFragmentStateAdapter.addFragment(new FragAttractions(mContext));
        myFragmentStateAdapter.addFragment(new FragNature(mContext));
        myFragmentStateAdapter.addFragment(new FragShopping(mContext));

        viewPager2.setAdapter(myFragmentStateAdapter);
    }

    private void init() {
        viewPager2 = findViewById(R.id.vpContainer);
        tabLayout = findViewById(R.id.tabLayout);
    }
}