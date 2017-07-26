package com.boomarang.cbstop.Api;

/**
 * Created by YOONGOO on 2017-07-26.
 */

public class ApiUtils {

    public static final String BASE_URL = "https://sammaru.cbnu.ac.kr/cbstop/";

    public static PlaceService getPlaceService() {
        return RetrofitClient.getClient(BASE_URL).create(PlaceService.class);
    }
}