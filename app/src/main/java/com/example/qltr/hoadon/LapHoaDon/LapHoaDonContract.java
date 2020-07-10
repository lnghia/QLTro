package com.example.qltr.hoadon.LapHoaDon;

import android.util.Pair;

import com.example.Models.Invoice.Invoice;
import com.example.Models.Room.Room;

import java.util.ArrayList;
import java.util.HashMap;

public interface LapHoaDonContract {
    interface View {
        void finishActivity();
    }

    interface Presenter {
        void getRooms(String token, HashMap<String, String> rooms);

        void loadRooms(String token, HashMap<String, String> rooms);

        void createInvoice(String token, String roomId, Invoice invoice);
    }
}
