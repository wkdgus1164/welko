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

public class FragAttractions extends Fragment implements View.OnClickListener {

    private static final String TAG = "FragAttractions";
    private HomeActivity mContext;

    // 탭 화면에 표시되는 여행지 정보 - 이미지, 이름, 지역
    private ImageView attrImg0;
    private ImageView attrImg1;
    private ImageView attrImg2;
    private ImageView attrImg3;
    private ImageView attrImg4;
    private TextView attrName1;
    private TextView attrName2;
    private TextView attrName3;
    private TextView attrName4;
    private TextView attrLoc1;
    private TextView attrLoc2;
    private TextView attrLoc3;
    private TextView attrLoc4;

    Travel travel = new Travel(); // 여행지 객체 생성

    // 여행지 정보
    private String type;
    private String name;
    private String location;
    private String thumbnail;

    private RequestQueue queue;

    public FragAttractions(HomeActivity mContext) {
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

        View v = inflater.inflate(R.layout.frag_attractions,container,false);

        attrImg0 = v.findViewById(R.id.attrImg0);

        attrName1 = v.findViewById(R.id.attrName1);
        attrLoc1 = v.findViewById(R.id.attrLoc1);
        attrImg1 = v.findViewById(R.id.attrImg1);

        attrName2 = v.findViewById(R.id.attrName2);
        attrLoc2 = v.findViewById(R.id.attrLoc2);
        attrImg2 = v.findViewById(R.id.attrImg2);

        attrName3 = v.findViewById(R.id.attrName3);
        attrLoc3 = v.findViewById(R.id.attrLoc3);
        attrImg3 = v.findViewById(R.id.attrImg3);

        attrName4 = v.findViewById(R.id.attrName4);
        attrLoc4 = v.findViewById(R.id.attrLoc4);
        attrImg4 = v.findViewById(R.id.attrImg4);

        // 여행지 이미지 클릭 이벤트
        attrImg1.setOnClickListener(this);
        attrImg2.setOnClickListener(this);
        attrImg3.setOnClickListener(this);
        attrImg4.setOnClickListener(this);

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

                        if (type.equals("ATTRACTIONS")) { // type = "ATTRACTIONS" 일 경우, 해당 여행지를 화면에 출력
                            j++;

                            // 여행지 객체에 JSON에서 Parsing 해온 정보 저장
                            travel.setType(type);
                            travel.setName(jsonTravel.getString("name"));
                            travel.setLocation(jsonTravel.getString("location"));
                            travel.setThumbnail(jsonTravel.getString("thumbnail"));

                            // 여행지 정보 출력
                            if (j==1) {
                                attrName1.setText(travel.getName());
                                attrLoc1.setText(travel.getLocation());

                                // 참고자료 : https://wonpaper.tistory.com/207
                                // Glide 라이브러리로 화면에 이미지 출력
                                Glide.with(v.getContext()).load(travel.getThumbnail()).into(attrImg0);
                                Glide.with(v.getContext()).load(travel.getThumbnail()).into(attrImg1);
                            }

                            if (j==2) {
                                attrName2.setText(travel.getName());
                                attrLoc2.setText(travel.getLocation());
                                Glide.with(v.getContext()).load(travel.getThumbnail()).into(attrImg2);
                            }

                            if (j==3) {
                                attrName3.setText(travel.getName());
                                attrLoc3.setText(travel.getLocation());
                                Glide.with(v.getContext()).load(travel.getThumbnail()).into(attrImg3);
                            }

                            if (j==4) {
                                attrName4.setText(travel.getName());
                                attrLoc4.setText(travel.getLocation());
                                Glide.with(v.getContext()).load(travel.getThumbnail()).into(attrImg4);
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
        if (view == attrImg1) {
            Intent viewIntent = new Intent(getActivity(), ViewActivity.class);
            viewIntent.putExtra("name",attrName1.getText().toString()); // 여행지 이름 전달
            startActivity(viewIntent);
        }

        if (view == attrImg2) {
            Intent viewIntent = new Intent(getActivity(), ViewActivity.class);
            viewIntent.putExtra("name",attrName2.getText().toString());
            startActivity(viewIntent);
        }

        if (view == attrImg3) {
            Intent viewIntent = new Intent(getActivity(), ViewActivity.class);
            viewIntent.putExtra("name",attrName3.getText().toString());
            startActivity(viewIntent);
        }

        if (view == attrImg4) {
            Intent viewIntent = new Intent(getActivity(), ViewActivity.class);
            viewIntent.putExtra("name",attrName4.getText().toString());
            startActivity(viewIntent);
        }
    }
}

