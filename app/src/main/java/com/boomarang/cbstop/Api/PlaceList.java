package com.boomarang.cbstop.Api;

import com.boomarang.cbstop.CustomView.Place;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by YOONGOO on 2017-07-26.
 */

public class PlaceList {
    @SerializedName("result")
    private List<Place> places;

    public List<Place> getPlaces() {
        return places;
    }

    public void setPlaces(List<Place> places) {
        this.places = places;
    }
}
