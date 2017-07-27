package com.boomarang.cbstop.Utils;

import android.location.Location;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.LatLng;

/**
 * Created by olion on 2017-07-28.
 */

public class MapAnimator {
    public static void moveTo(GoogleMap googleMap, LatLng latLng) {
        CameraUpdate cameraUpdate = CameraUpdateFactory.newLatLngZoom(latLng, 16);
        googleMap.animateCamera(cameraUpdate);
    }

    public static void moveTo(GoogleMap googleMap, Location location) {
        moveTo(googleMap, new LatLng(location.getLatitude(), location.getLongitude()));
    }
}
