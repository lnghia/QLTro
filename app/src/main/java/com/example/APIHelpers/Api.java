package com.example.APIHelpers;

import com.example.Models.Facility;
import com.example.Models.FacilityApiResponse;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface Api {
    @GET("facilities")
    Call<ArrayList<Facility>> getItemsInPage(
            @Header("Authorization") String token,
            @Query("page") int page,
            @Query("limit") int limit
    );

    @POST("facilities")
    Call<FacilityApiResponse> createNewFacility(
            @Header("Authorization") String token,
            @Body Facility facility
    );
}
