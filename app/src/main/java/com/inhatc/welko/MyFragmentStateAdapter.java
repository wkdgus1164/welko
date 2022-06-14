package com.inhatc.welko;

import androidx.annotation.NonNull;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;

import androidx.viewpager2.adapter.FragmentStateAdapter;

import java.util.ArrayList;
import java.util.List;

// 참고자료 : https://velog.io/@ruinak_4127/08-05-TabLayout
// TabLayout과 각 탭의 화면인 Fragment를 연결하기 위한 클래스
public class MyFragmentStateAdapter extends FragmentStateAdapter {
    private List<Fragment> mFragmentList = new ArrayList<>();

    public MyFragmentStateAdapter(@NonNull FragmentActivity fragmentActivity) {
        super(fragmentActivity);
    }

    public void addFragment(Fragment fragment) {
        this.mFragmentList.add(fragment);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        return mFragmentList.get(position);
    }

    @Override
    public int getItemCount() {
        return mFragmentList.size();
    }
}
