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

    // 지역으로 검색
    @GET("place.php?")
    Call<PlaceList> getPlaces(@Query("region")String region);

    // 지역과 카테고리로 검색
    @GET("place.php?")
    Call<PlaceList> getPlaces(@Query("region")String region, @Query("category") String category);

    // 이름으로 검색
    @GET("place.php?")
    Call<PlaceList> getPlacesWithName(@Query("name")String name);

    // 이름으로 검색
    @GET("place.php?")
    Call<PlaceList> getPlacesWithAddr(@Query("address")String address);
}
