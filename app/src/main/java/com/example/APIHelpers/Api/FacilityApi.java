package com.example.APIHelpers.Api;

import com.example.Models.Facility.Facility;
import com.example.Models.Facility.FacilityApiResponse;
import com.example.Models.Facility.GetFacilityApiResponse;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface FacilityApi {
    @GET("facilities")
    Call<GetFacilityApiResponse> getItemsInPage(
            @Header("Authorization") String token,
            @Query("page") int page,
            @Query("limit") int limit
    );

    @POST("facilities")
    Call<FacilityApiResponse> createNewFacility(
            @Header("Authorization") String token,
            @Body Facility facility
    );

    @PUT("facilities/{id}")
    Call<FacilityApiResponse> editFacility(
            @Header("Authorization") String token,
            @Path("id") String id,
            @Body Facility facility
    );

    @DELETE("facilities/{id}")
    Call<FacilityApiResponse> delete(
            @Header("Authorization") String token,
            @Path("id") String id
    );
}
