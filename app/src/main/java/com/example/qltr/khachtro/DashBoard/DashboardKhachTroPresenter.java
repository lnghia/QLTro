package com.example.qltr.khachtro.DashBoard;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import com.example.APIHelpers.RetrofitClient;
import com.example.Models.Customer.AddCustomerApiResponse;
import com.example.Models.Customer.Customer;
import com.example.Models.Customer.CustomerApiResponse;
import com.example.Models.Customer.DeleteCustomerApiResponse;
import com.example.Models.Room.ApiRoomFilter;
import com.example.Models.Room.Room;
import com.example.Utils.StatusDesc;

import java.util.ArrayList;
import java.util.Locale;

import androidx.recyclerview.widget.RecyclerView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DashboardKhachTroPresenter implements DashboardKhachTroContract.Presenter {
    Context context;
    DashboardKhachTroContract.View view;

    public DashboardKhachTroPresenter(Context context, DashboardKhachTroContract.View view) {
        this.context = context;
        this.view = view;
    }

    @Override
    public void loadInitial(String token, String roomId, ArrayList<Customer> customers, RecyclerView.Adapter adapter) {
        view.setIsLoading(true);
//        Log.i("room", roomId);

        RetrofitClient.getInstance().getCustomerApi().getCustomer(token, 1, 10, new ApiRoomFilter(roomId)).enqueue(new Callback<CustomerApiResponse>() {
            @Override
            public void onResponse(Call<CustomerApiResponse> call, Response<CustomerApiResponse> response) {
                if(!customers.isEmpty()){
                    customers.clear();
                    view.updateDataSetChange();
                }

                if(response!=null && response.body()!=null){
                    if (response.body().isSuccess()) {
                        for (Customer customer : response.body().getData()) {
                            customers.add(customer);
                        }
                        view.updateDataSetChange();
                    }
                    else{
                        Toast.makeText(context, StatusDesc.getInstance().getErrDesc(response.code()), Toast.LENGTH_SHORT).show();
                    }
                }

                view.setRefreshingState(false);
                view.setIsLoading(false);
                view.setPageNum(1);
            }

            @Override
            public void onFailure(Call<CustomerApiResponse> call, Throwable t) {
                if(!customers.isEmpty()){
                    customers.clear();
                    view.updateDataSetChange();
                }
//                Log.i("t", t.getMessage());
                view.setRefreshingState(false);
                view.getFailure("Vui lòng kiểm tra kết nối và thử lại!");
                view.setIsLoading(false);
            }
        });
    }

    @Override
    public void loadNextPage(String token, String roomId, int page, ArrayList<Customer> customers, RecyclerView.Adapter adapter) {
        customers.add(null);
        view.updateDataSetChange();
//        adapter.notifyItemInserted(customers.size()-1);

        RetrofitClient.getInstance().getCustomerApi().getCustomer(token, page, 10, new ApiRoomFilter(roomId)).enqueue(new Callback<CustomerApiResponse>() {
            @Override
            public void onResponse(Call<CustomerApiResponse> call, Response<CustomerApiResponse> response) {
                customers.remove(customers.size()-1);
                view.updateDataSetChange();

                if(response!=null && response.body()!=null){
                    if(response.body().isSuccess()){
                        for(Customer customer : response.body().getData()){
                            customers.add(customer);
                        }
                        view.updateDataSetChange();
                    }
                    else {
                        Toast.makeText(context, StatusDesc.getInstance().getErrDesc(response.code()), Toast.LENGTH_SHORT).show();
                    }
                }

                view.setIsLoading(false);
            }

            @Override
            public void onFailure(Call<CustomerApiResponse> call, Throwable t) {
                customers.remove(customers.size()-1);
                view.updateDataSetChange();
                view.getFailure("Vui lòng kiểm tra kết nối và thử lại!");
                view.setIsLoading(false);
            }
        });
    }

    @Override
    public void deleteCustomer(String token, String id) {
        RetrofitClient.getInstance().getCustomerApi().deleteCustomer(token, id).enqueue(new Callback<DeleteCustomerApiResponse>() {
            @Override
            public void onResponse(Call<DeleteCustomerApiResponse> call, Response<DeleteCustomerApiResponse> response) {
                if(response!=null && response.body()!=null){
                    if(response.body().isSuccess()){
                        Toast.makeText(context, "Thao tác thành công!", Toast.LENGTH_SHORT).show();
                    }
                }
                Toast.makeText(context, StatusDesc.getInstance().getErrDesc(response.code()), Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Call<DeleteCustomerApiResponse> call, Throwable t) {
                Log.i("t", t.getMessage());
                Toast.makeText(context, "Vui lòng kiểm tra kết nối và thử lại!", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
