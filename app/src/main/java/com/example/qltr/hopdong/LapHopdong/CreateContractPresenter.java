package com.example.qltr.hopdong.LapHopdong;

import android.content.Context;
import android.util.Log;

import com.example.APIHelpers.RetrofitClient;
import com.example.Models.Contract.Contract;
import com.example.Models.Contract.CreateContractApiResponse;
import com.example.Models.Customer.Customer;
import com.example.Models.Room.RoomId;
import com.example.Utils.ListContainers;
import com.example.Utils.StatusDesc;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CreateContractPresenter implements CreateContractContract.Presenter {
    private Context context;
    private CreateContractContract.View view;

    public CreateContractPresenter(Context context, CreateContractContract.View view) {
        this.context = context;
        this.view = view;
    }

    @Override
    public void createContract(String token, int index, Contract contract) {
        RetrofitClient.getInstance().getContractApi().createContract(token, contract).enqueue(new Callback<CreateContractApiResponse>() {
            @Override
            public void onResponse(Call<CreateContractApiResponse> call, Response<CreateContractApiResponse> response) {
                if (response != null && response.body() != null) {
                    if (response.body().isSuccess()) {
//                        if(ListContainers.getInstance().getContracts()!=null && !ListContainers.getInstance().getContracts().isEmpty()){
//                            ListContainers.getInstance().getContracts().add(response.body().getData());
//
//                        }
                        if(ListContainers.getInstance().getRooms()!=null && !ListContainers.getInstance().getRooms().isEmpty()){
                            ListContainers.getInstance().getRooms().get(index).setSlotStatus("available");
                        }
                        view.createSuccess();
                    }
                    else {
                        view.createFailure(StatusDesc.getInstance().getErrDesc(response.code()));
                    }
                }
                else{
                    view.createFailure(StatusDesc.getInstance().getErrDesc(response.code()));
                }
            }

            @Override
            public void onFailure(Call<CreateContractApiResponse> call, Throwable t) {
                Log.i("t", t.getMessage());
                view.createFailure("Vui lòng kiểm tra kết nối và thử lại!");
            }
        });
    }
}
