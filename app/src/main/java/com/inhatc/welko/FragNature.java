package com.inhatc.welko;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.bumptech.glide.Glide;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class FragNature extends Fragment implements View.OnClickListener {

    private static final String TAG = "FragNature";
    private HomeActivity mContext;

    private TextView natName1;
    private TextView natName2;
    private TextView natName3;
    private TextView natName4;

    private TextView natLoc1;
    private TextView natLoc2;
    private TextView natLoc3;
    private TextView natLoc4;

    private ImageView natImg0;
    private ImageView natImg1;
    private ImageView natImg2;
    private ImageView natImg3;
    private ImageView natImg4;

    private String type;
    private String name;
    private String location;
    private String intro;
    private String description;
    private String address;
    private String transportation;
    private String thumbnail;
    private String latlng;

    Travel travel = new Travel();

    private RequestQueue queue;

    public FragNature(HomeActivity mContext) {
        this.mContext = mContext;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    // https://trendy00develope.tistory.com/12
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.frag_nature,container,false);

        natImg0 = v.findViewById(R.id.natImg0);

        natName1 = v.findViewById(R.id.natName1);
        natLoc1 = v.findViewById(R.id.natLoc1);
        natImg1 = v.findViewById(R.id.natImg1);

        natName2 = v.findViewById(R.id.natName2);
        natLoc2 = v.findViewById(R.id.natLoc2);
        natImg2 = v.findViewById(R.id.natImg2);

        natName3 = v.findViewById(R.id.natName3);
        natLoc3 = v.findViewById(R.id.natLoc3);
        natImg3 = v.findViewById(R.id.natImg3);

        natName4 = v.findViewById(R.id.natName4);
        natLoc4 = v.findViewById(R.id.natLoc4);
        natImg4 = v.findViewById(R.id.natImg4);

        natImg1.setOnClickListener(this);
        natImg2.setOnClickListener(this);
        natImg3.setOnClickListener(this);
        natImg4.setOnClickListener(this);

        queue = Volley.newRequestQueue(v.getContext());
        String url = "http://welko.ap-northeast-2.elasticbeanstalk.com/travel";

        final JsonArrayRequest jsonRequest = new JsonArrayRequest(Request.Method.GET, url, null, new Response.Listener<JSONArray>() {

            @Override
            public void onResponse(JSONArray response) {
                int j=0;
                try {
                    for (int i = 0; i < response.length(); i++) {
                        JSONObject jsonTravel = (JSONObject) response.get(i);

                        type = jsonTravel.getString("type");

                        if (type.equals("NATURE")) {
                            j++;

                            travel.setType(type);
                            travel.setName(jsonTravel.getString("name"));
                            travel.setLocation(jsonTravel.getString("location"));
                            travel.setThumbnail(jsonTravel.getString("thumbnail"));

                            if (j==1) {
                                natName1.setText(travel.getName());
                                natLoc1.setText(travel.getLocation());

                                //https://wonpaper.tistory.com/207
                                Glide.with(v.getContext()).load(travel.getThumbnail()).into(natImg0);
                                Glide.with(v.getContext()).load(travel.getThumbnail()).into(natImg1);
                            }

                            if (j==2) {
                                natName2.setText(travel.getName());
                                natLoc2.setText(travel.getLocation());
                                Glide.with(v.getContext()).load(travel.getThumbnail()).into(natImg2);
                            }

                            if (j==3) {
                                natName3.setText(travel.getName());
                                natLoc3.setText(travel.getLocation());
                                Glide.with(v.getContext()).load(travel.getThumbnail()).into(natImg3);
                            }

                            if (j==4) {
                                natName4.setText(travel.getName());
                                natLoc4.setText(travel.getLocation());
                                Glide.with(v.getContext()).load(travel.getThumbnail()).into(natImg4);
                            }
                        }
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });

        jsonRequest.setTag(TAG);
        queue.add(jsonRequest);

        return v;
    }

    @Override
    public void onStop() {
        super.onStop();
        if (queue != null) {
            queue.cancelAll(TAG);
        }
    }

    //https://onepinetwopine.tistory.com/166
    //https://jeong9216.tistory.com/6
    @Override
    public void onClick(View view) {
        if (view == natImg1) {
            Intent viewIntent = new Intent(getActivity(), ViewActivity.class);
            viewIntent.putExtra("name",natName1.getText().toString());
            startActivity(viewIntent);
        }

        if (view == natImg2) {
            Intent viewIntent = new Intent(getActivity(), ViewActivity.class);
            viewIntent.putExtra("name",natName2.getText().toString());
            startActivity(viewIntent);
        }

        if (view == natImg3) {
            Intent viewIntent = new Intent(getActivity(), ViewActivity.class);
            viewIntent.putExtra("name",natName3.getText().toString());
            startActivity(viewIntent);
        }

        if (view == natImg4) {
            Intent viewIntent = new Intent(getActivity(), ViewActivity.class);
            viewIntent.putExtra("name",natName4.getText().toString());
            startActivity(viewIntent);
        }
    }
}

