package com.boomarang.cbstop.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.EditText;

import com.boomarang.cbstop.R;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;


public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener, OnMapReadyCallback {

    @BindView(R.id.tb_main_bar)
    Toolbar tb_main_bar;
    @BindView(R.id.layout_main_drawer)
    DrawerLayout layout_main_drawer;
    @BindView(R.id.vw_main_navigation)
    NavigationView vw_main_navigation;
    @BindView(R.id.et_main_search)
    EditText et_main_search;

    private GoogleMap mGoogleMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        // setup google map
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        CombineNavigationWithToolbar();
        vw_main_navigation.setNavigationItemSelectedListener(this);
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mGoogleMap = googleMap;
    }

    private void CombineNavigationWithToolbar() {
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, layout_main_drawer, tb_main_bar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        layout_main_drawer.setDrawerListener(toggle);
        toggle.syncState();
    }


    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem paramItem) {

        switch (paramItem.getItemId()) {
            case R.id.nav_main_path:
                startActivity(new Intent(getApplicationContext(), FindPathActivity.class));
                break;
            case R.id.nav_main_badge:
                startActivity(new Intent(getApplicationContext(), BadgeActivity.class));
                break;
            case R.id.nav_main_event:
                startActivity(new Intent(getApplicationContext(), EventActivity.class));
                break;
            case R.id.nav_main_setting:
                startActivity(new Intent(getApplicationContext(), SettingActivity.class));
                break;
        }

        return true;
    }

    @OnClick(R.id.btn_main_search)
    public void OnSearchButtonClicked() {
        String searchValue = et_main_search.getText().toString();

        Intent intent = new Intent(getApplicationContext(), SearchActivity.class);
        intent.putExtra("searchValue", searchValue);
        startActivity(intent);
    }


}
