package com.example.qltr.cosovatchat.QLCSVC;

import com.example.Models.Facility.Facility;
import com.example.qltr.Adapters.PagingFacilityAdapter;

import java.util.ArrayList;

import androidx.recyclerview.widget.RecyclerView;

public interface QLCSVCContract {
    interface QLCSVCView{
        void editSuccess();
        void editFailure(String err);
        void raiseNotification(String note);
    }

    interface Presenter{
        void delete(String token, int id, ArrayList<Facility> facilities, RecyclerView.Adapter adapter);

//        void editFacility(String token, int id, ArrayList<Facility> facilities, String name, int price, int quantity, String desc, RecyclerView.Adapter adapter);
    }
}
