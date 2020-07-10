package com.example.qltr.khachtro.KhachTroDetail;

import com.example.Models.Customer.CreateCustomerApiBody;
import com.example.Models.Customer.Customer;

import java.util.ArrayList;

public interface DetailKhachTroContract {
    interface View{
        void raiseMessage(String mess);
        void updateItemInserted(int pos);
        void updateDataSetChanged();
        void resetViews();
    }

    interface Presenter{
        void addNewCustomer(String token, CreateCustomerApiBody customer);
    }
}
