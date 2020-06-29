package com.example.APIHelpers;

import com.example.APIHelpers.Api.FacilityApi;
import com.example.APIHelpers.Api.RoomApi;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitClient {
    private static final String BASE_URL = "https://quanlytro.herokuapp.com/";
    private static RetrofitClient mInstance;
    private Retrofit retrofit;

    private RetrofitClient() {
        retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    public static synchronized RetrofitClient getInstance() {
        if (mInstance == null) {
            mInstance = new RetrofitClient();
        }

        return mInstance;
    }

    public FacilityApi getFacilityApi() {
        return retrofit.create(FacilityApi.class);
    }

    public RoomApi getRoomApi() {
        return retrofit.create(RoomApi.class);
    }
}
