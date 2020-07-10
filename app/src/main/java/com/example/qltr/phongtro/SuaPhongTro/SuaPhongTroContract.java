package com.example.qltr.phongtro.SuaPhongTro;

import com.example.Models.Room.Room;

public interface SuaPhongTroContract {
    interface View{
        void editSuccess();
        void editFailure(String mess);
    }

    interface Presenter{
        void editRoom(String token, int id, Room room);
    }
}
