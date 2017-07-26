package com.boomarang.cbstop.Api;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


/**
 * Created by YOONGOO on 2017-07-26.
 */


public class RetrofitClient {

    private static Retrofit retrofit = null;
    private static Gson gson = null;


    public static Retrofit getClient(String baseUrl) {

        if(gson == null){
            gson = new GsonBuilder()
                    .setLenient()
                    .create();
        }

        if (retrofit==null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl(baseUrl)
                    .addConverterFactory(GsonConverterFactory.create(gson))
                    .build();
        }
        return retrofit;
    }
}