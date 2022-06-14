package com.inhatc.welko;


import android.os.Bundle;


import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager2.widget.ViewPager2;

import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

public class HomeActivity extends AppCompatActivity {

    private HomeActivity mContext = HomeActivity.this;

    private MyFragmentStateAdapter myFragmentStateAdapter; // 탭 레이아웃 - 각 탭의 화면인 fragment를 연결
    private ViewPager2 viewPager2; // 각 탭 화면인 fragment를 출력
    private TabLayout tabLayout; // 홈 화면의 탭 레이아웃

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        init();
        initAdapter();
        intiTab();
    }

    // 탭 레이아웃 생성 메소드
    private void intiTab() {
        new TabLayoutMediator(tabLayout, viewPager2, (tab, position) -> {}).attach();

        tabLayout.getTabAt(0).setText("Attractions");
        tabLayout.getTabAt(1).setText("Nature");
        tabLayout.getTabAt(2).setText("Shopping");
    }

    // 탭 레이아웃 - 각 탭의 화면 fragment 연결 메소드
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