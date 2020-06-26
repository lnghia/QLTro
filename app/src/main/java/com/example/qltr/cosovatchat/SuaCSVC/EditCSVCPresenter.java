package com.example.qltr.cosovatchat.SuaCSVC;

import android.content.Context;

import com.example.APIHelpers.RetrofitClient;
import com.example.Models.Facility.Facility;
import com.example.Models.Facility.FacilityApiResponse;
import com.example.Utils.ListContainers;
import com.example.Utils.StatusDesc;
import com.example.qltr.R;

import java.util.regex.Pattern;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class EditCSVCPresenter implements EditCSVCContract.EditCSVCPresenter {
    EditCSVCContract.EditCSVCView view;
    Context context;

    public EditCSVCPresenter(EditCSVCContract.EditCSVCView view, Context context) {
        this.view = view;
        this.context = context;
    }

    @Override
    public void edit(String token, int id, Facility editedData) {
        if(!Pattern.matches(context.getString(R.string.facility_unit_price_regex), Integer.toString(editedData.getUnitPrice())) ||
            !Pattern.matches(context.getString(R.string.facility_quantity_regex), Integer.toString(editedData.getQuantity()))){
            view.editFailure("Dữ liệu nhập không hợp lệ");
        }

        RetrofitClient.getInstance().getFacilityApi().editFacility(token, ListContainers.getInstance().getFacilities().get(id).get_id(), editedData).enqueue(new Callback<FacilityApiResponse>() {
            @Override
            public void onResponse(Call<FacilityApiResponse> call, Response<FacilityApiResponse> response) {
                if(response.body()!=null){
                    if(response.body().getSuccess()){
                        ListContainers.getInstance().getFacilities().get(id).setName(editedData.getName());
                        ListContainers.getInstance().getFacilities().get(id).setUnitPrice(editedData.getUnitPrice());
                        ListContainers.getInstance().getFacilities().get(id).setQuantity(editedData.getQuantity());
                        ListContainers.getInstance().getFacilities().get(id).setDescription(editedData.getDescription());
                        view.editSuccess(StatusDesc.getInstance().getErrDesc(response.code()));
                    }
                    else{
                        view.editFailure(StatusDesc.getInstance().getErrDesc(response.code()));
                    }
                }
            }

            @Override
            public void onFailure(Call<FacilityApiResponse> call, Throwable t) {
                view.editFailure("Vui lòng kiểm tra kết nối và thử lại !");
            }
        });
    }
}
