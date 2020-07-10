package com.example.qltr.khachtro.SuaKhachTro;

import android.content.Context;
import android.util.Log;

import com.example.APIHelpers.RetrofitClient;
import com.example.Models.Customer.AddCustomerApiResponse;
import com.example.Models.Customer.Customer;
import com.example.Models.Customer.CustomerGetDetailApiResponse;
import com.example.Utils.ListContainers;
import com.example.Utils.StatusDesc;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SuaKhachTroPresenter implements SuaKhachTroContract.Presenter {
    private Context context;
    private SuaKhachTroContract.View view;

    public SuaKhachTroPresenter(Context context, SuaKhachTroContract.View view) {
        this.context = context;
        this.view = view;
    }

    @Override
    public void edit(String token, Customer customer, String id, int index) {
        RetrofitClient.getInstance().getCustomerApi().editCustomer(token, id, customer).enqueue(new Callback<AddCustomerApiResponse>() {
            @Override
            public void onResponse(Call<AddCustomerApiResponse> call, Response<AddCustomerApiResponse> response) {
                if(response!=null && response.body()!=null){
                    if(response.body().isSuccess()){
                        ListContainers.getInstance().getCustomers().set(index, response.body().getData());
                        view.editSuccess();
                    }
                }
                view.editFailure(StatusDesc.getInstance().getErrDesc(response.code()));
            }

            @Override
            public void onFailure(Call<AddCustomerApiResponse> call, Throwable t) {
                view.editFailure("Vui lòng kiểm tra kết nối và thử lại!");
            }
        });
    }

    @Override
    public void getDetail(String token, String id, int index) {
        RetrofitClient.getInstance().getCustomerApi().getCustomerDetail(token, id).enqueue(new Callback<CustomerGetDetailApiResponse>() {
            @Override
            public void onResponse(Call<CustomerGetDetailApiResponse> call, Response<CustomerGetDetailApiResponse> response) {
                if(response!=null && response.body()!=null){
                    if(response.body().isSuccess()){
//                        ListContainers.getInstance().getCustomers().set(index, response.body().getData());
//                        view.editSuccess();
                    }
                }
                else view.editFailure(StatusDesc.getInstance().getErrDesc(response.code()));
            }

            @Override
            public void onFailure(Call<CustomerGetDetailApiResponse> call, Throwable t) {
//                Log.i("T", t.getMessage());
                view.editFailure("Vui lòng kiểm tra kết nối và thử lại!");
            }
        });
    }
}
