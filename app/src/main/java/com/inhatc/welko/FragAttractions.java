package com.inhatc.welko;

import android.content.Intent;
import android.os.Bundle;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;


import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.inhatc.welko.functions.FirestoreFunction;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class FragAttractions extends Fragment implements View.OnClickListener {

//    private ArrayList<Travel> arrayList;
//    private Travel travel = new Travel();
//    private final FirebaseFirestore firebaseFirestore = FirebaseFirestore.getInstance();

    private static final String TAG = "FragAttractions";
    private HomeActivity mContext;

    private ImageView img;

    private TextView attrName1;

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

        FirestoreFunction firestoreFunction = new FirestoreFunction();
        Travel travel = new Travel();

        img = v.findViewById(R.id.imgView2);
        img.setOnClickListener(this);

        Map<String, String> data = firestoreFunction.getFirestoreTravelList("Bukhansan National Park");
//        Log.d("Success", data.get("name"));
        attrName1 = v.findViewById(R.id.attrName1);

//        attrName1.setText(data.get("name"));
//        ArrayList<Travel> travelList = firestoreFunction.getFirestoreTravelList("Attractions");
//
//
//        attrName1.setText(travelList.get(0).getName().toString());
//        Log.d("Success", travelList.get(new Integer(0)).getName());
        return v;
    }

    //https://onepinetwopine.tistory.com/166
    @Override
    public void onClick(View view) {
        if (view == img) {
            Intent viewIntent = new Intent(getActivity(), ViewActivity.class);
            startActivity(viewIntent);
//            Intent viewIntent = new Intent(getActivity(), FirestoreActivity.class);
//            startActivity(viewIntent);
        }
    }
}
