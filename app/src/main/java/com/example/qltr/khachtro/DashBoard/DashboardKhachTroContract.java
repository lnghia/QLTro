package com.example.qltr.khachtro.DashBoard;

import com.example.Models.Customer.Customer;

import java.util.ArrayList;

import androidx.recyclerview.widget.RecyclerView;

public interface DashboardKhachTroContract {
    interface View{
        void getSuccess();
        void getFailure(String mess);
        void updateDashboard();
        void setIsLoading(boolean value);
        void setPageNum(int value);
        void setRefreshingState(boolean state);
        void updateItemInserted(int pos);
        void updateItemRemoved(int pos);
        void updateDataSetChange();
    }

    interface Presenter{
        void loadInitial(String token, String roomId, ArrayList<Customer> customers, RecyclerView.Adapter adapter);
        void loadNextPage(String token, String roomId, int page, ArrayList<Customer> customers, RecyclerView.Adapter adapter);
        void deleteCustomer(String token, String id);
    }
}
