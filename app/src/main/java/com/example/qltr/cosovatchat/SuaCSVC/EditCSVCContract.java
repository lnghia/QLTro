package com.example.qltr.cosovatchat.SuaCSVC;

import com.example.Models.Facility.Facility;

public interface EditCSVCContract {
    interface EditCSVCView{
        void editSuccess(String note);
        void editFailure(String err);
    }

    interface EditCSVCPresenter{
        void edit(String token, int id, Facility editedData);
    }
}
