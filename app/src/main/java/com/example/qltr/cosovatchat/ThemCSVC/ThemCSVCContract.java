package com.example.qltr.cosovatchat.ThemCSVC;

import com.example.Models.Facility.Facility;

public interface ThemCSVCContract {
    interface ThemCSVCView{
        void addSuccess(Facility newFacility);
        void addFail(String err);
    }

    interface Presenter{
        void handleAddFacility(Facility newFacility);
        Boolean validateUnitPriceInput(String inp);
        Boolean validateQuantityInput(String inp);
    }
}
