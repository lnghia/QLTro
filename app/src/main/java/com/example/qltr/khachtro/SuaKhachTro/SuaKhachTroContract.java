package com.example.qltr.khachtro.SuaKhachTro;

import com.example.Models.Customer.Customer;

public interface SuaKhachTroContract {
    interface View{
        void editSuccess();
        void editFailure(String message);
    }

    interface Presenter{
        void edit(String token, Customer customer, String id, int index);
        void getDetail(String token, String id, int index);
    }
}
