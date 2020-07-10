package com.example.qltr.phongtro.ThemPhTr;

import com.example.Models.Room.Room;

public interface ThemPhongTroContract {
    interface View{
        void raiseNotification(String note);
        void initPresenter();
        void createSuccess(String note);
    }

    interface Presenter{
        void createNewRoom(String token, Room room, int filterType);
    }
}
