package com.example.APIHelpers.Api;

import com.example.Models.Invoice.CreateInvoiceApiResponse;
import com.example.Models.Invoice.GetInvoiceApiResponse;
import com.example.Models.Invoice.Invoice;
import com.example.Models.Invoice.InvoiceGetAllRoom;
import com.example.Models.Invoice.InvoiceGetByRoomId;
import com.example.Models.Invoice.InvoiceGetByStatus;
import com.example.Models.Invoice.InvoiceStatus;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface InvoiceApi {
    @POST("invoices")
    Call<CreateInvoiceApiResponse> createInvoice(
            @Header("Authorization") String token,
            @Body Invoice body
    );

    @POST("invoices/getAll")
    Call<GetInvoiceApiResponse> getAll(
            @Header("Authorization") String token,
            @Query("page") int page,
            @Query("limit") int limit
    );

    @POST("invoices/getAll")
    Call<GetInvoiceApiResponse> getAllByRoomId(
            @Header("Authorization") String token,
            @Body InvoiceGetByRoomId body
    );

    @POST("invoices/getAll")
    Call<GetInvoiceApiResponse> getAll(
            @Header("Authorization") String token,
            @Body InvoiceGetAllRoom body
    );

    @DELETE("invoices/{id}")
    Call<CreateInvoiceApiResponse> deleteInvoice(
            @Header("Authorization") String token,
            @Path("id") String id
    );

    @GET("invoices/{id}")
    Call<CreateInvoiceApiResponse> getDetail(
            @Header("Authorization") String token,
            @Path("id") String id
    );

    @POST("invoices/getAll")
    Call<GetInvoiceApiResponse> getAllByStatus(
            @Header("Authorization") String token,
            @Body InvoiceGetByStatus body
    );

    @PUT("invoices/{id}")
    Call<CreateInvoiceApiResponse> confirm(
            @Header("Authorization") String token,
            @Path("id") String id,
            @Body InvoiceStatus body
    );
}
