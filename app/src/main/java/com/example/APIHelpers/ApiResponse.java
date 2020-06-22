package com.example.APIHelpers;

import android.app.ProgressDialog;
import android.content.Context;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ApiResponse {
    public static <T> void callRetrofit(Call<T> call, final String strApiName, Context mContext){
        final ProgressDialog progressDialog=new ProgressDialog(mContext);

        progressDialog.setMessage("Đang thực thi...");
        progressDialog.setCancelable(false);
        progressDialog.show();

        call.enqueue(new Callback<T>() {
            @Override
            public void onResponse(Call<T> call, Response<T> response) {
                T apiResponse=response.body();
                progressDialog.dismiss();
            }

            @Override
            public void onFailure(Call<T> call, Throwable t) {

            }
        });
    }
}
