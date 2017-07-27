package com.boomarang.cbstop.Utils;

import android.Manifest;
import android.content.Context;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by olion on 2017-07-28.
 */

public class MapDrawer {
    private Context context;
    private GoogleMap googleMap;

    private List<Marker> markerList;
    private MarkerOptions markerOption;

    public MapDrawer(Context context, GoogleMap googleMap) {
        this.context = context;
        this.googleMap = googleMap;

        markerList = new ArrayList<>();
        markerOption = new MarkerOptions()
                .draggable(false)
                .flat(true);    // TODO : insert marker image
    }

    public boolean setUiSetting() {
        if (PermissionUtil.hasPermission(context, Manifest.permission.ACCESS_FINE_LOCATION)) {
            googleMap.setMyLocationEnabled(true);
            googleMap.getUiSettings().setMyLocationButtonEnabled(true);
            return true;
        }
        return false;
    }

    private void addMarker(String title, LatLng point) {
        Marker marker = googleMap.addMarker(markerOption.position(point).title(title));
        markerList.add(marker);
    }

    private void clearMarker() {
        for (Marker marker : markerList) {
            marker.remove();
        }
    }
}
