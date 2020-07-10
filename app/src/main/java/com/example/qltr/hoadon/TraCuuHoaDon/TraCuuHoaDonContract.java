package com.example.qltr.hoadon.TraCuuHoaDon;

import com.example.Models.Invoice.Invoice;
import com.example.Models.Invoice.InvoiceGetAllRoom;
import com.example.Models.Invoice.InvoiceGetByRoomId;
import com.example.Models.Invoice.InvoiceGetByStatus;

import java.util.ArrayList;

public interface TraCuuHoaDonContract {
    interface View{
        void assignOnRefreshListenner();
        void raiseNote(String note);
        void configureRecyclerView();
        void assignOnScrollEvent();
        void initViews();
        void updateDataSetChanged();
        void setIsLoading(boolean val);
        void setRefresh(boolean value);
        void setPageNum(int i);
    }

    interface Presenter{
        void loadInitial(String token, int page, int limit, InvoiceGetAllRoom body, ArrayList<Invoice> invoices);
        void loadInitial(String token, InvoiceGetByStatus body, ArrayList<Invoice> invoices);
        void confirm(String token, String id, int index, ArrayList<Invoice> invoices);
//        void loadNextPage(String token, int page, int limit, InvoiceGetAllRoom body, ArrayList<Invoice> invoices);
//        void loadNextPage(String token, int page, int limit, InvoiceGetByRoomId body, ArrayList<Invoice> invoices);
//        void loadNextPage(String token, int page, int limit, InvoiceGetByStatus body, ArrayList<Invoice> invoices);
    }
}
