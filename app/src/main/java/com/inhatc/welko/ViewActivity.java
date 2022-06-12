package com.inhatc.welko;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.Fragment;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.ScrollView;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.bumptech.glide.Glide;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
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

    private ImageView viewImg;
    private TextView viewName1;
    private TextView viewName2;
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
        viewName2 = findViewById(R.id.viewName2);
        viewLoc = findViewById(R.id.viewLoc);
        viewIntro = findViewById(R.id.viewIntro);
        viewDesc = findViewById(R.id.viewDesc);
        viewAddr = findViewById(R.id.viewAddr);
        viewTrans = findViewById(R.id.viewTrans);

        Intent viewIntent = getIntent();
        String name = viewIntent.getStringExtra("name");

        queue = Volley.newRequestQueue(this);
        String url = "http://welko.ap-northeast-2.elasticbeanstalk.com/travel";

        final JsonArrayRequest jsonRequest = new JsonArrayRequest(Request.Method.GET, url, null, new Response.Listener<JSONArray>() {

            @Override
            public void onResponse(JSONArray response) {
                try {
                    for (int i = 0; i < response.length(); i++) {
                        JSONObject jsonTravel = (JSONObject) response.get(i);

                        String jsonName = jsonTravel.getString("name");

                        if (jsonName.equals(name)) {
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

        //https://milkissboy.tistory.com/10
        WorkaroundMapFragment mapFrag = (WorkaroundMapFragment) getSupportFragmentManager().findFragmentById(R.id.fragment_map);
        mapFrag.getMapAsync(new OnMapReadyCallback() {
            @Override
            public void onMapReady(GoogleMap googleMap) {
                mMap = googleMap;

                // Add a marker in Sydney and move the camera
//                LatLng sydney = new LatLng(-34, 151);
//                mMap.addMarker(new MarkerOptions().position(sydney).title("Marker in Sydney"));
//                mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));

                Location latlng = addrToPoint(getApplicationContext());
                final LatLng nameLatlng = new LatLng(latlng.getLatitude(), latlng.getLongitude());
//                MarkerOptions markerOptions = new MarkerOptions();
//                markerOptions.position(nameLatlng);
//                markerOptions.title(name);
//                mMap.addMarker(markerOptions);

                Marker marker = mMap.addMarker(new MarkerOptions()
                        .position(nameLatlng)
                        .title(name));
                marker.showInfoWindow();

                googleMap.setOnMapLoadedCallback(new GoogleMap.OnMapLoadedCallback(){

                    @Override
                    public void onMapLoaded() {
                        mMap.moveCamera(CameraUpdateFactory.newLatLng(nameLatlng));
                        mMap.animateCamera(CameraUpdateFactory.zoomTo(20));
                    }
                });
            }

            //https://1d1cblog.tistory.com/116
            public Location addrToPoint(Context context) {
                Location location = new Location("");
                Geocoder geocoder = new Geocoder(context);
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
        mapFrag.setListener(new WorkaroundMapFragment.OnTouchListener() {
            @Override
            public void onTouch() {
                mScrollView.requestDisallowInterceptTouchEvent(true);
            }
        });
    }
}