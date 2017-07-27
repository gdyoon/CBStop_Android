package com.boomarang.cbstop.Api;

import com.boomarang.cbstop.CustomView.Place;

import java.util.List;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by YOONGOO on 2017-07-26.
 */

public interface PlaceService {

    @GET("place.php?")
    Call<PlaceList> getPlaces(@Query("region")String region);

    @GET("place.php?")
    Call<PlaceList> getPlaces(@Query("region")String region, @Query("category") String category);
}
