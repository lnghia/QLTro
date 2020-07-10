package com.example.qltr.phananh;

import android.content.Context;
import android.widget.Toast;

import com.example.APIHelpers.RetrofitClient;
import com.example.Models.Report.Report;
import com.example.Models.Report.ReportCreateApiResponse;
import com.example.Models.Report.ReportGetApiResponse;
import com.example.Utils.StatusDesc;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PhanAnhPresenter implements PhanAnhContract.Presenter {
    private Context context;
    private PhanAnhContract.View view;

    public PhanAnhPresenter(Context context, PhanAnhContract.View view) {
        this.context = context;
        this.view = view;
    }

    @Override
    public void loadInitial(String token, int page, int limit, ArrayList<Report> reports) {
        view.setIsLoading(true);

        RetrofitClient.getInstance().getReportApi().getAll(token, page, limit).enqueue(new Callback<ReportGetApiResponse>() {
            @Override
            public void onResponse(Call<ReportGetApiResponse> call, Response<ReportGetApiResponse> response) {
                if(!reports.isEmpty()){
                    reports.clear();
                    view.updateDataSetChange();
                }

                if(response!=null && response.body()!=null){
                    if (response.body().isSuccess()) {
                        for (Report rp : response.body().getData()) {
                            reports.add(rp);
                        }
                        view.updateDataSetChange();
                    }
                    else{
                        Toast.makeText(context, StatusDesc.getInstance().getErrDesc(response.code()), Toast.LENGTH_SHORT).show();
                    }
                }

                view.setRefreshingState(false);
                view.setIsLoading(false);
                view.setPageNum(1);
            }

            @Override
            public void onFailure(Call<ReportGetApiResponse> call, Throwable t) {
                if(!reports.isEmpty()){
                    reports.clear();
                    view.updateDataSetChange();
                }
//                Log.i("t", t.getMessage());
                view.setRefreshingState(false);
                view.getFailure("Vui lòng kiểm tra kết nối và thử lại!");
                view.setIsLoading(false);
            }
        });
    }

    @Override
    public void loadInitial(String token, int page, int limit, Report report, ArrayList<Report> reports) {
        view.setIsLoading(true);

        RetrofitClient.getInstance().getReportApi().getAllByStatus(token, page, limit, report).enqueue(new Callback<ReportGetApiResponse>() {
            @Override
            public void onResponse(Call<ReportGetApiResponse> call, Response<ReportGetApiResponse> response) {
                if(!reports.isEmpty()){
                    reports.clear();
                    view.updateDataSetChange();
                }

                if(response!=null && response.body()!=null){
                    if (response.body().isSuccess()) {
                        for (Report rp : response.body().getData()) {
                            reports.add(rp);
                        }
                        view.updateDataSetChange();
                    }
                    else{
                        Toast.makeText(context, StatusDesc.getInstance().getErrDesc(response.code()), Toast.LENGTH_SHORT).show();
                    }
                }

                view.setRefreshingState(false);
                view.setIsLoading(false);
                view.setPageNum(1);
            }

            @Override
            public void onFailure(Call<ReportGetApiResponse> call, Throwable t) {
                if(!reports.isEmpty()){
                    reports.clear();
                    view.updateDataSetChange();
                }
//                Log.i("t", t.getMessage());
                view.setRefreshingState(false);
                view.getFailure("Vui lòng kiểm tra kết nối và thử lại!");
                view.setIsLoading(false);
            }
        });
    }

    @Override
    public void loadNext(String token, int page, int limit, ArrayList<Report> reports) {
        reports.add(null);
        view.updateDataSetChange();

        RetrofitClient.getInstance().getReportApi().getAll(token, page, limit).enqueue(new Callback<ReportGetApiResponse>() {
            @Override
            public void onResponse(Call<ReportGetApiResponse> call, Response<ReportGetApiResponse> response) {

                if(!reports.isEmpty()){
                    reports.remove(reports.size()-1);
                    view.updateDataSetChange();
                }

                if(response!=null && response.body()!=null){
                    if(response.body().isSuccess()){
//                        response.body().getData().stream().forEach(rooms::add);
                        for(Report rp : response.body().getData()){
                            reports.add(rp);
                        }
                        view.updateDataSetChange();
                    }
                    else{
                       Toast.makeText(context,StatusDesc.getInstance().getErrDesc(response.code()), Toast.LENGTH_SHORT).show();
                    }
                }

                view.setIsLoading(false);
            }

            @Override
            public void onFailure(Call<ReportGetApiResponse> call, Throwable t) {
                reports.remove(reports.size()-1);
                view.updateDataSetChange();
                Toast.makeText(context,"Vui lòng kiểm tra kết nối và thử lại!", Toast.LENGTH_SHORT).show();
                view.setIsLoading(false);
            }
        });
    }

    @Override
    public void loadNext(String token, int page, int limit, Report report, ArrayList<Report> reports) {
        reports.add(null);
        view.updateDataSetChange();

        RetrofitClient.getInstance().getReportApi().getAllByStatus(token, page, limit, report).enqueue(new Callback<ReportGetApiResponse>() {
            @Override
            public void onResponse(Call<ReportGetApiResponse> call, Response<ReportGetApiResponse> response) {

                if(!reports.isEmpty()){
                    reports.remove(reports.size()-1);
                    view.updateDataSetChange();
                }

                if(response!=null && response.body()!=null){
                    if(response.body().isSuccess()){
//                        response.body().getData().stream().forEach(rooms::add);
                        for(Report rp : response.body().getData()){
                            reports.add(rp);
                        }
                        view.updateDataSetChange();
                    }
                    else{
                        Toast.makeText(context,StatusDesc.getInstance().getErrDesc(response.code()), Toast.LENGTH_SHORT).show();
                    }
                }

                view.setIsLoading(false);
            }

            @Override
            public void onFailure(Call<ReportGetApiResponse> call, Throwable t) {
                reports.remove(reports.size()-1);
                view.updateDataSetChange();
                Toast.makeText(context,"Vui lòng kiểm tra kết nối và thử lại!", Toast.LENGTH_SHORT).show();
                view.setIsLoading(false);
            }
        });
    }

    @Override
    public void confirm(String token, String id, int index, Report report, ArrayList<Report> reports) {
        RetrofitClient.getInstance().getReportApi().confirm(token, id, report).enqueue(new Callback<ReportCreateApiResponse>() {
            @Override
            public void onResponse(Call<ReportCreateApiResponse> call, Response<ReportCreateApiResponse> response) {
                if(response!=null && response.body()!=null){
                    if(response.body().isSuccess()){
                        Toast.makeText(context,"Thao tác thành công!", Toast.LENGTH_SHORT).show();
                        reports.get(index).setStatus(report.getStatus());
                        view.updateDataSetChange();
                    }
                    else{
                        Toast.makeText(context,StatusDesc.getInstance().getErrDesc(response.code()), Toast.LENGTH_SHORT).show();
                    }
                }
            }

            @Override
            public void onFailure(Call<ReportCreateApiResponse> call, Throwable t) {
                Toast.makeText(context,"Vui lòng kiểm tra kết nối và thử lại!", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public void delete(String token, String id, int index, ArrayList<Report> reports) {
        RetrofitClient.getInstance().getReportApi().delete(token, id).enqueue(new Callback<ReportCreateApiResponse>() {
            @Override
            public void onResponse(Call<ReportCreateApiResponse> call, Response<ReportCreateApiResponse> response) {
                if(response!=null && response.body()!=null){
                    if(response.body().isSuccess()){
                        Toast.makeText(context,"Thao tác thành công!", Toast.LENGTH_SHORT).show();
                        reports.remove(index);
                        view.updateDataSetChange();
                    }
                    else{
                        Toast.makeText(context,StatusDesc.getInstance().getErrDesc(response.code()), Toast.LENGTH_SHORT).show();
                    }
                }
            }

            @Override
            public void onFailure(Call<ReportCreateApiResponse> call, Throwable t) {
                Toast.makeText(context,"Vui lòng kiểm tra kết nối và thử lại!", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
