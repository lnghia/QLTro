package com.example.APIHelpers.Api;

import com.example.Models.Report.Report;
import com.example.Models.Report.ReportCreateApiResponse;
import com.example.Models.Report.ReportGetApiResponse;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface ReportApi {
    @POST("reports/getAll")
    Call<ReportGetApiResponse> getAll(
            @Header("Authorization") String token,
            @Query("page") int page,
            @Query("limit") int limit
    );

    @POST("reports/getAll")
    Call<ReportGetApiResponse> getAllByStatus(
            @Header("Authorization") String token,
            @Query("page") int page,
            @Query("limit") int limit,
            @Body Report report
    );

    @PUT("reports/{id}")
    Call<ReportCreateApiResponse> confirm(
            @Header("Authorization") String token,
            @Path("id") String id,
            @Body Report report
    );

    @DELETE("reports/{id}")
    Call<ReportCreateApiResponse> delete(
            @Header("Authorization") String token,
            @Path("id") String id
    );
}
