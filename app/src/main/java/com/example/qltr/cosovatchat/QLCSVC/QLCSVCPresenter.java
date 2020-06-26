package com.example.qltr.cosovatchat.QLCSVC;

import android.content.Context;

import com.example.APIHelpers.RetrofitClient;
import com.example.Models.Facility.Facility;
import com.example.Models.Facility.FacilityApiResponse;
import com.example.Utils.StatusDesc;
import com.example.qltr.Adapters.PagingFacilityAdapter;
import com.example.qltr.R;

import java.util.ArrayList;
import java.util.regex.Pattern;

import androidx.recyclerview.widget.RecyclerView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class QLCSVCPresenter implements QLCSVCContract.Presenter {
    QLCSVCContract.QLCSVCView view;
    Context context;

    public QLCSVCPresenter(QLCSVCContract.QLCSVCView view, Context context){
        this.context=context;
        this.view=view;
    }

    @Override
    public void delete(String token, int id, ArrayList<Facility> facilities, RecyclerView.Adapter adapter) {
        RetrofitClient.getInstance().getFacilityApi().delete(token, facilities.get(id).get_id()).enqueue(new Callback<FacilityApiResponse>() {
            @Override
            public void onResponse(Call<FacilityApiResponse> call, Response<FacilityApiResponse> response) {
                view.raiseNotification("Thao tác thành công!");
                facilities.remove(id);
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onFailure(Call<FacilityApiResponse> call, Throwable t) {
                view.editFailure("Vui lòng kiểm tra kết nối mạng và thử lại!");
            }
        });
    }

//    @Override
//    public void editFacility(String token, String id, ArrayList<Facility> facilities, String name, int price, int quantity, String desc, RecyclerView.Adapter adapter) {
//        if(!Pattern.matches(context.getString(R.string.facility_quantity_regex), Integer.toString(quantity)) ||
//                !Pattern.matches(context.getString(R.string.facility_unit_price_regex), Integer.toString(price))){
//            view.editFailure("Dữ liệu nhập không hợp lệ, vui lòng kiểm tra và thử lại!");
//
//            return;
//        }
//
//        RetrofitClient.getInstance().getFacilityApi().editFacility(token,  new Facility(name, price, quantity, desc)).enqueue(new Callback<FacilityApiResponse>() {
//            @Override
//            public void onResponse(Call<FacilityApiResponse> call, Response<FacilityApiResponse> response) {
//                if(response!=null){
//                    if(response.body().getSuccess()){
//                        view.editSuccess();
//                    }
//                    else{
//                        view.editFailure(StatusDesc.getInstance().getErrDesc(response.code()));
//                    }
//
//                    facilities.set(id, response.body().getData());
//                    adapter.notifyDataSetChanged();
//                }
//                else{
//                    view.editFailure("");
//                }
//            }
//
//            @Override
//            public void onFailure(Call<FacilityApiResponse> call, Throwable t) {
//                view.editFailure("Vui lòng kiểm tra kết nối mạng và thử lại!");
//            }
//        });
//    }
}
