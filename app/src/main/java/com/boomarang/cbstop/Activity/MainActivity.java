package com.boomarang.cbstop.Activity;

import android.content.Intent;
import android.location.Location;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.Toast;

import com.boomarang.cbstop.R;
import com.boomarang.cbstop.Utils.MapAnimator;
import com.boomarang.cbstop.Utils.MapDrawer;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;


public class MainActivity extends LocationActivity implements NavigationView.OnNavigationItemSelectedListener, OnMapReadyCallback {

    @BindView(R.id.tb_main_bar)         Toolbar tb_main_bar;
    @BindView(R.id.layout_main_drawer)  DrawerLayout layout_main_drawer;
    @BindView(R.id.vw_main_navigation)  NavigationView vw_main_navigation;
    @BindView(R.id.et_main_search)      EditText et_main_search;

    private GoogleMap mGoogleMap;
    private MapDrawer mapDrawer;

    private EventBus bus;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        bus = EventBus.getDefault();

        // setup google map
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        CombineNavigationWithToolbar();
        SetOnMenuItemClickListener();
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mGoogleMap = googleMap;
        mapDrawer = new MapDrawer(this, mGoogleMap);
    }

    @Override
    protected void onLocationPermissionGrunted() {
        mapDrawer.setUiSetting();
    }

    @Subscribe
    public void onLocationChanged(Location location) {
        MapAnimator.moveTo(mGoogleMap, location);
        Toast.makeText(this, "location : " + location, Toast.LENGTH_SHORT).show();
    }

    private void CombineNavigationWithToolbar() {
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, layout_main_drawer, tb_main_bar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        layout_main_drawer.setDrawerListener(toggle);
        toggle.syncState();
        vw_main_navigation.setNavigationItemSelectedListener(this);
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

    private void SetOnMenuItemClickListener()
    {
        tb_main_bar.inflateMenu(R.menu.menu_main_search);
        tb_main_bar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                if(item.getItemId() == R.id.menu_main_search){
                    String searchValue = et_main_search.getText().toString();

                    Intent intent = new Intent(getApplicationContext(), SearchActivity.class);
                    intent.putExtra("searchValue", searchValue);
                    startActivity(intent);
                }
                return false;
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (!bus.isRegistered(this))
            bus.register(this);
    }

    @Override
    protected void onPause() {
        super.onPause();
        if (bus.isRegistered(this))
            bus.unregister(this);
    }
}
