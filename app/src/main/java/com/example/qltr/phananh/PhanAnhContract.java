package com.example.qltr.phananh;

import com.example.Models.Report.Report;

import java.util.ArrayList;

public interface PhanAnhContract {
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
        void loadInitial(String token, int page, int limit, ArrayList<Report> reports);
        void loadInitial(String token, int page, int limit, Report report, ArrayList<Report> reports);
        void loadNext(String token, int page, int limit, ArrayList<Report> reports);
        void loadNext(String token, int page, int limit, Report report, ArrayList<Report> reports);
        void confirm(String token, String id, int index, Report report, ArrayList<Report> reports);
        void delete(String token, String id, int index, ArrayList<Report> reports);
    }
}
