package com.example.APIHelpers.Api;

import com.example.Models.Account;
import com.example.Models.RegisteredAcc;
import com.example.Models.SignedInAcc;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface LoginApi {
    @POST
    Call<SignedInAcc> login(
            @Body Account account
    );
}
