package com.example.APIHelpers.Api;

import com.example.Models.Room.Room;
import com.example.Models.Room.RoomApiResponse;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;
import retrofit2.http.Query;


public interface RoomApi {
    @GET("rooms")
    Call<RoomApiResponse> getRooms(
            @Header("Authorization") String token,
            @Query("page") int page,
            @Query("limit") int limit
    );

    @POST("rooms")
    Call<RoomApiResponse> createRoom(
            @Header("Authorization") String token,
            @Body Room room
    );
}
