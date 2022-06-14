package com.inhatc.welko;

import android.content.Context;
import android.content.Intent;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.ScrollView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.bumptech.glide.Glide;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.List;

public class ViewActivity extends AppCompatActivity {

    private static final String TAG = "ViewActivity";

    // 상세정보 화면에 출력되는 정보
    private ImageView viewImg;
    private TextView viewName1;
    private TextView viewLoc;
    private TextView viewIntro;
    private TextView viewDesc;
    private TextView viewAddr;
    private TextView viewTrans;

    private GoogleMap mMap;
    private ScrollView mScrollView;

    private RequestQueue queue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view);

        viewImg = findViewById(R.id.viewImg);
        viewName1 = findViewById(R.id.viewName1);
        viewLoc = findViewById(R.id.viewLoc);
        viewIntro = findViewById(R.id.viewIntro);
        viewDesc = findViewById(R.id.viewDesc);
        viewAddr = findViewById(R.id.viewAddr);
        viewTrans = findViewById(R.id.viewTrans);

        // 홈 화면에서 전달한 여행지 이름 받기
        Intent viewIntent = getIntent();
        String name = viewIntent.getStringExtra("name");

        queue = Volley.newRequestQueue(this); // Volley: 안드로이드에서 외부 API 를 요청
        String url = "http://welko.ap-northeast-2.elasticbeanstalk.com/travel"; // 백엔드 서버 url

        // 백엔드에 GET 요청 -> ArrayList 형태로 여행지 JSON을 Parsing
        final JsonArrayRequest jsonRequest = new JsonArrayRequest(Request.Method.GET, url, null, new Response.Listener<JSONArray>() {

            @Override
            public void onResponse(JSONArray response) {
                try {
                    // JSON 배열을 반복문으로 탐색
                    for (int i = 0; i < response.length(); i++) {
                        JSONObject jsonTravel = (JSONObject) response.get(i); // JSON 여행지 배열을 탐색하여, 여행지 하나씩 Object 형태로 받음

                        String jsonName = jsonTravel.getString("name"); // 받은 여행지 Object에서 "name" 값 추출

                        // 홈 화면에서 전달 받은 여행지 이름 - JSON에서 받은 여행지의 이름이 같으면
                        if (jsonName.equals(name)) {

                            // 여행지 정보 출력
                            // Glide 라이브러리로 화면에 이미지 출력
                            Glide.with(getApplicationContext()).load(jsonTravel.getString("thumbnail")).into(viewImg);

                            viewName1.setText(jsonTravel.getString("name"));
                            viewLoc.setText(jsonTravel.getString("location"));
                            viewIntro.setText(jsonTravel.getString("intro"));
                            viewDesc.setText(jsonTravel.getString("description"));
                            viewAddr.setText(jsonTravel.getString("address"));
                            viewTrans.setText(jsonTravel.getString("transportation"));
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

        // 참고자료 : https://milkissboy.tistory.com/10
        // 구글 맵 출력
        WorkaroundMapFragment mapFrag = (WorkaroundMapFragment) getSupportFragmentManager().findFragmentById(R.id.fragment_map);
        mapFrag.getMapAsync(new OnMapReadyCallback() {
            @Override
            public void onMapReady(GoogleMap googleMap) {
                mMap = googleMap;

                // 위도, 경도 구하는 메소드 호출
                Location latlng = addrToPoint(getApplicationContext());
                final LatLng nameLatlng = new LatLng(latlng.getLatitude(), latlng.getLongitude());

                // 구글 맵에 위치 마크 표시
                Marker marker = mMap.addMarker(new MarkerOptions()
                        .position(nameLatlng)
                        .title(name));
                marker.showInfoWindow();

                // 구글 맵 카메라 이동 및 줌
                mMap.moveCamera(CameraUpdateFactory.newLatLng(nameLatlng));
                mMap.animateCamera(CameraUpdateFactory.zoomTo(20));

                googleMap.setOnMapLoadedCallback(new GoogleMap.OnMapLoadedCallback(){
                    @Override
                    public void onMapLoaded() {

                    }
                });
            }

            // 참고자료 : https://1d1cblog.tistory.com/116
            // 여행지 이름으로 위도, 경도 구하는 메소드
            public Location addrToPoint(Context context) {
                Location location = new Location("");
                Geocoder geocoder = new Geocoder(context); // Geocoder API
                List<Address> addresses = null;

                try {
                    addresses = geocoder.getFromLocationName(name,3);
                } catch (IOException e) {
                    e.printStackTrace();
                }

                if (addresses != null) {
                    for (int i=0; i<addresses.size(); i++) {
                        Address addr = addresses.get(i);
                        location.setLatitude(addr.getLatitude());
                        location.setLongitude(addr.getLongitude());
                    }
                }

                return location;
            }
        });

        mScrollView = (ScrollView) findViewById(R.id.sv_container);

        // 스크롤 뷰 안의 구글 맵 제어
        mapFrag.setListener(new WorkaroundMapFragment.OnTouchListener() {
            @Override
            public void onTouch() {
                mScrollView.requestDisallowInterceptTouchEvent(true);
            }
        });
    }
}