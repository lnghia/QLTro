package com.example.APIHelpers.Api;

import com.example.Models.Customer.AddCustomerApiResponse;
import com.example.Models.Customer.CreateCustomerApiBody;
import com.example.Models.Customer.Customer;
import com.example.Models.Customer.CustomerApiResponse;
import com.example.Models.Customer.CustomerGetDetailApiResponse;
import com.example.Models.Customer.DeleteCustomerApiResponse;
import com.example.Models.Room.ApiRoomFilter;
import com.example.Models.Room.Room;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.HEAD;
import retrofit2.http.HTTP;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface CustomerApi {
//    @GET("customers")
//    @HTTP(method = "GET",path = "/customers",hasBody = true)
    @POST("customers/getAll")
    Call<CustomerApiResponse> getCustomer(
            @Header("Authorization") String token,
            @Query("page") int page,
            @Query("limit") int limit,
            @Body ApiRoomFilter roomId
    );

    @GET("customers/{id}")
    Call<CustomerGetDetailApiResponse> getCustomerDetail(
            @Header("Authorization") String token,
            @Path("id") String id
    );

    @POST("customers")
    Call<AddCustomerApiResponse> createNewCustomer(
            @Header("Authorization") String token,
            @Body CreateCustomerApiBody customer
    );

    @PUT("customers/{id}")
    Call<AddCustomerApiResponse> editCustomer(
            @Header("Authorization") String token,
            @Path("id") String id,
            @Body Customer customer
    );

    @DELETE("customers/{id}")
    Call<DeleteCustomerApiResponse> deleteCustomer(
            @Header("Authorization") String token,
            @Path("id") String id
    );
}
