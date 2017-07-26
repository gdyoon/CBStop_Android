package com.boomarang.cbstop.Activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.boomarang.cbstop.CustomView.Place;
import com.boomarang.cbstop.R;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SearchDetailActivity extends AppCompatActivity implements OnMapReadyCallback {

    private Place mPlace;
    private GoogleMap mGoogleMap;
    @BindView(R.id.tv_detail_place_name)    TextView tv_detail_place_name;
    @BindView(R.id.tv_detail_place_address) TextView tv_detail_place_address;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_detail);
        ButterKnife.bind(this);

        Intent intent = getIntent();
        mPlace = (Place)intent.getSerializableExtra("place");

        SupportMapFragment mapFragment
                = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map_search_detail);
        mapFragment.getMapAsync(this);

        tv_detail_place_name.setText(mPlace.getName());
        tv_detail_place_address.setText(mPlace.getAddress());
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mGoogleMap = googleMap;

        double lat = Double.parseDouble(mPlace.getLatitude());
        double lng = Double.parseDouble(mPlace.getLongitude());
        Marker marker = mGoogleMap.addMarker(
                new MarkerOptions()
                .position(new LatLng(lat, lng))
                .title(mPlace.getName()));
        marker.showInfoWindow();

        mGoogleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(lat, lng), 15.0f));
    }
}
