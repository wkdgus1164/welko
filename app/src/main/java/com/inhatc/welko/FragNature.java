package com.inhatc.welko;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;

public class FragNature extends Fragment {

    private static final String TAG = "FragNature";
    private HomeActivity mContext;

    public FragNature(HomeActivity mContext) {
        this.mContext = mContext;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.frag_nature,container,false);
    }
}
