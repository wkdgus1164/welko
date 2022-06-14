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

public class FragShopping extends Fragment implements View.OnClickListener {

    private static final String TAG = "FragShopping";
    private HomeActivity mContext;

    // 탭 화면에 표시되는 여행지 정보 - 이미지, 이름, 지역
    private ImageView shopImg0;
    private ImageView shopImg1;
    private ImageView shopImg2;
    private ImageView shopImg3;
    private ImageView shopImg4;

    private TextView shopName1;
    private TextView shopName2;
    private TextView shopName3;
    private TextView shopName4;

    private TextView shopLoc1;
    private TextView shopLoc2;
    private TextView shopLoc3;
    private TextView shopLoc4;

    Travel travel = new Travel(); // 여행지 객체 생성

    // 여행지 정보
    private String type;
    private String name;
    private String location;
    private String thumbnail;

    private RequestQueue queue;

    public FragShopping(HomeActivity mContext) {
        this.mContext = mContext;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    // 참고자료 : https://trendy00develope.tistory.com/12
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.frag_shopping,container,false);

        shopImg0 = v.findViewById(R.id.shopImg0);

        shopName1 = v.findViewById(R.id.shopName1);
        shopLoc1 = v.findViewById(R.id.shopLoc1);
        shopImg1 = v.findViewById(R.id.shopImg1);

        shopName2 = v.findViewById(R.id.shopName2);
        shopLoc2 = v.findViewById(R.id.shopLoc2);
        shopImg2 = v.findViewById(R.id.shopImg2);

        shopName3 = v.findViewById(R.id.shopName3);
        shopLoc3 = v.findViewById(R.id.shopLoc3);
        shopImg3 = v.findViewById(R.id.shopImg3);

        shopName4 = v.findViewById(R.id.shopName4);
        shopLoc4 = v.findViewById(R.id.shopLoc4);
        shopImg4 = v.findViewById(R.id.shopImg4);

        // 여행지 이미지 클릭 이벤트
        shopImg1.setOnClickListener(this);
        shopImg2.setOnClickListener(this);
        shopImg3.setOnClickListener(this);
        shopImg4.setOnClickListener(this);

        queue = Volley.newRequestQueue(v.getContext());
        String url = "http://welko.ap-northeast-2.elasticbeanstalk.com/travel"; // 백엔드 서버 url

        // 백엔드에 GET 요청 -> ArrayList 형태로 여행지 JSON을 Parsing
        final JsonArrayRequest jsonRequest = new JsonArrayRequest(Request.Method.GET, url, null, new Response.Listener<JSONArray>() {

            @Override
            public void onResponse(JSONArray response) {
                int j = 0; // 여행지 출력 순서

                try {
                    // JSON 배열을 반복문으로 탐색
                    for (int i = 0; i < response.length(); i++) {
                        JSONObject jsonTravel = (JSONObject) response.get(i); // JSON 여행지 배열을 탐색하여, 여행지 하나씩 Object 형태로 받음

                        type = jsonTravel.getString("type"); // 받은 여행지 Object에서 "type" 값 추출

                        if (type.equals("SHOPPING")) { // type = "SHOPPING" 일 경우, 해당 여행지를 화면에 출력
                            j++;

                            // 여행지 객체에 JSON에서 Parsing 해온 정보 저장
                            travel.setType(type);
                            travel.setName(jsonTravel.getString("name"));
                            travel.setLocation(jsonTravel.getString("location"));
                            travel.setThumbnail(jsonTravel.getString("thumbnail"));

                            // 여행지 정보 출력
                            if (j==1) {
                                shopName1.setText(travel.getName());
                                shopLoc1.setText(travel.getLocation());

                                // 참고자료 : https://wonpaper.tistory.com/207
                                // Glide 라이브러리로 화면에 이미지 출력
                                Glide.with(v.getContext()).load(travel.getThumbnail()).into(shopImg0);
                                Glide.with(v.getContext()).load(travel.getThumbnail()).into(shopImg1);
                            }

                            if (j==2) {
                                shopName2.setText(travel.getName());
                                shopLoc2.setText(travel.getLocation());
                                Glide.with(v.getContext()).load(travel.getThumbnail()).into(shopImg2);
                            }

                            if (j==3) {
                                shopName3.setText(travel.getName());
                                shopLoc3.setText(travel.getLocation());
                                Glide.with(v.getContext()).load(travel.getThumbnail()).into(shopImg3);
                            }

                            if (j==4) {
                                shopName4.setText(travel.getName());
                                shopLoc4.setText(travel.getLocation());
                                Glide.with(v.getContext()).load(travel.getThumbnail()).into(shopImg4);
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

        return v; // fragment 화면 반환
    }

    @Override
    public void onStop() {
        super.onStop();
        if (queue != null) {
            queue.cancelAll(TAG);
        }
    }

    // 참고자료: https://onepinetwopine.tistory.com/166
    // 참고자료 : https://jeong9216.tistory.com/6
    // 여행지 이미지 클릭 이벤트 -> 여행지 상세정보 화면(viewActivity) 이동 및 해당 여행지 이름 전달
    @Override
    public void onClick(View view) {
        if (view == shopImg1) {
            Intent viewIntent = new Intent(getActivity(), ViewActivity.class);
            viewIntent.putExtra("name",shopName1.getText().toString()); // 여행지 이름 전달
            startActivity(viewIntent);
        }

        if (view == shopImg2) {
            Intent viewIntent = new Intent(getActivity(), ViewActivity.class);
            viewIntent.putExtra("name",shopName2.getText().toString());
            startActivity(viewIntent);
        }

        if (view == shopImg3) {
            Intent viewIntent = new Intent(getActivity(), ViewActivity.class);
            viewIntent.putExtra("name",shopName3.getText().toString());
            startActivity(viewIntent);
        }

        if (view == shopImg4) {
            Intent viewIntent = new Intent(getActivity(), ViewActivity.class);
            viewIntent.putExtra("name",shopName4.getText().toString());
            startActivity(viewIntent);
        }
    }
}

