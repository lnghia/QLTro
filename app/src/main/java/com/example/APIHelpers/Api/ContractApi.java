package com.example.APIHelpers.Api;

import com.example.Models.Contract.Contract;
import com.example.Models.Contract.ContractExtensionBody;
import com.example.Models.Contract.CreateContractApiResponse;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface ContractApi {
    @POST("contracts")
    Call<CreateContractApiResponse> createContract(
            @Header("Authorization") String token,
            @Body Contract contract
    );

    @DELETE("contracts/{id}")
    Call<CreateContractApiResponse> deleteContract(
            @Header("Authorization") String token,
            @Path("id") String id
    );

    @PUT("contracts/{id}")
    Call<CreateContractApiResponse> extendContract(
            @Header("Authorization") String token,
            @Path("id") String id,
            @Body ContractExtensionBody body
    );
}
