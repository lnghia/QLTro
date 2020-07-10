package com.example.qltr.khachtro.KhachTroDetail;

import android.content.Context;
import android.graphics.Color;

import com.example.APIHelpers.RetrofitClient;
import com.example.Models.Customer.AddCustomerApiResponse;
import com.example.Models.Customer.CreateCustomerApiBody;
import com.example.Models.Customer.Customer;
import com.example.Utils.ListContainers;
import com.example.Utils.StatusDesc;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DetailKhachTroPresenter implements DetailKhachTroContract.Presenter {
    private Context context;
    private DetailKhachTroContract.View view;

    public DetailKhachTroPresenter(Context context, DetailKhachTroContract.View view) {
        this.context = context;
        this.view = view;
    }

    @Override
    public void addNewCustomer(String token, CreateCustomerApiBody customer) {
        RetrofitClient.getInstance().getCustomerApi().createNewCustomer(token, customer).enqueue(new Callback<AddCustomerApiResponse>() {
            @Override
            public void onResponse(Call<AddCustomerApiResponse> call, Response<AddCustomerApiResponse> response) {
                if (response != null && response.body() != null) {
                    if (response.body().isSuccess()) {
                        ListContainers.getInstance().getCustomers().add(response.body().getData());
                        view.raiseMessage("Đã thêm thành công!");
                        view.resetViews();
                    } else {
                        view.raiseMessage(StatusDesc.getInstance().getErrDesc(response.code()));
                    }
                }
            }

            @Override
            public void onFailure(Call<AddCustomerApiResponse> call, Throwable t) {
                view.raiseMessage("Vui lòng kiểm tra kết nối và thử lại!");
            }
        });
    }


}
