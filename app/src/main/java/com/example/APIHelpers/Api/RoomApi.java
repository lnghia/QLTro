package com.example.APIHelpers.Api;

import com.example.Models.Room.Room;
import com.example.Models.Room.RoomApiResponse;
import com.example.Models.Room.RoomCreateApiResponse;
import com.example.Models.Room.RoomDeleteApiResponse;
import com.example.Models.Room.RoomTypeBody;
import com.example.Models.Room.RoomVehicleNumBody;

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
    @POST("rooms/getAll")
    Call<RoomApiResponse> getRooms(
            @Header("Authorization") String token,
            @Query("page") int page,
            @Query("limit") int limit,
            @Body RoomTypeBody room
    );

    @POST("rooms/getAll")
    Call<RoomApiResponse> getAllRooms(
            @Header("Authorization") String token,
            @Query("page") int page,
            @Query("limit") int limit
    );

    @POST("rooms")
    Call<RoomCreateApiResponse> createRoom(
            @Header("Authorization") String token,
            @Body Room room
    );

    @DELETE("rooms/{id}")
    Call<RoomDeleteApiResponse> deleteRoom(
            @Header("Authorization") String token,
            @Path("id") String id
    );

    @PUT("rooms/{id}")
    Call<RoomDeleteApiResponse> editRoom(
            @Header("Authorization") String token,
            @Path("id") String id,
            @Body Room room
    );

    @PUT("rooms/{id}")
    Call<RoomDeleteApiResponse> editVehicleNum(
            @Header("Authorization") String token,
            @Path("id") String id,
            @Body RoomVehicleNumBody body
    );

    @POST("rooms/getAll")
    Call<RoomApiResponse> getAllRooms(String token);
}
