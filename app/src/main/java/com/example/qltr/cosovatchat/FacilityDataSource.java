package com.example.qltr.cosovatchat;

import android.app.ProgressDialog;
import android.content.Context;

import com.example.APIHelpers.RetrofitClient;
import com.example.Models.Facility.Facility;
import com.example.Models.Facility.GetFacilityApiResponse;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.paging.PageKeyedDataSource;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FacilityDataSource extends PageKeyedDataSource<Integer, Facility> {
    public static final int PAGE_SIZE = 10;
    private static final int FIRST_PAGE = 1;
    private String token;
    Context mContext;
    ProgressDialog progressDialog;

    public FacilityDataSource(Context mContext) {
        token=mContext.getSharedPreferences("com.example.qltr.login", Context.MODE_PRIVATE).getString("token", null);
        this.mContext=mContext;
//        progressDialog=new ProgressDialog(mContext);
////        progressDialog.setMessage("Đang thực thi...");
//        progressDialog.setCancelable(false);
//        progressDialog.show();
    }

    @Override
    public void loadInitial(@NonNull LoadInitialParams<Integer> params, @NonNull LoadInitialCallback<Integer, Facility> callback) {
        RetrofitClient.getInstance().getApi().getItemsInPage(token, FIRST_PAGE, PAGE_SIZE).enqueue(new Callback<GetFacilityApiResponse>() {
            @Override
            public void onResponse(Call<GetFacilityApiResponse> call, Response<GetFacilityApiResponse> response) {
//                progressDialog.dismiss();
                if (response.body() != null) {
                    callback.onResult(response.body().getData(), null, FIRST_PAGE + 1);
                }
            }

            @Override
            public void onFailure(Call<GetFacilityApiResponse> call, Throwable t) {

            }
        });
    }

    @Override
    public void loadBefore(@NonNull LoadParams<Integer> params, @NonNull LoadCallback<Integer, Facility> callback) {
        RetrofitClient.getInstance().getApi().getItemsInPage(token, params.key, PAGE_SIZE).enqueue(new Callback<GetFacilityApiResponse>() {
            @Override
            public void onResponse(Call<GetFacilityApiResponse> call, Response<GetFacilityApiResponse> response) {
                if(response.body()!=null){
                    callback.onResult(response.body().getData(), (params.key>1)?params.key-1:null);
                }
            }

            @Override
            public void onFailure(Call<GetFacilityApiResponse> call, Throwable t) {

            }
        });
    }

    @Override
    public void loadAfter(@NonNull LoadParams<Integer> params, @NonNull LoadCallback<Integer, Facility> callback) {
        progressDialog.show();
        RetrofitClient.getInstance().getApi().getItemsInPage(token, params.key, PAGE_SIZE).enqueue(new Callback<GetFacilityApiResponse>() {
            @Override
            public void onResponse(Call<GetFacilityApiResponse> call, Response<GetFacilityApiResponse> response) {
//                progressDialog.dismiss();
                if(response.body()!=null){
                    callback.onResult(response.body().getData(), params.key+1);
                }
            }

            @Override
            public void onFailure(Call<GetFacilityApiResponse> call, Throwable t) {

            }
        });
    }
}
