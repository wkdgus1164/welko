package com.inhatc.welko;

import android.content.Intent;
import android.os.Bundle;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;


import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

public class FragAttractions extends Fragment implements View.OnClickListener {

//    private ArrayList<Travel> arrayList;
//    private Travel travel = new Travel();
//    private final FirebaseFirestore firebaseFirestore = FirebaseFirestore.getInstance();

    private static final String TAG = "FragAttractions";
    private HomeActivity mContext;

    private ImageView img;

    public FragAttractions(HomeActivity mContext) {
        this.mContext = mContext;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.frag_attractions,container,false);

        img = v.findViewById(R.id.imgView2);
        img.setOnClickListener(this);

        return v;
    }

    //https://onepinetwopine.tistory.com/166
    @Override
    public void onClick(View view) {
        if (view == img) {
            Intent viewIntent = new Intent(getActivity(), ViewActivity.class);
            startActivity(viewIntent);
        }
    }
}
