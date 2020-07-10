package com.example.qltr.hoadon.TraCuuHoaDon;

import android.content.Context;
import android.widget.Toast;

import com.example.APIHelpers.RetrofitClient;
import com.example.Models.Invoice.CreateInvoiceApiResponse;
import com.example.Models.Invoice.GetInvoiceApiResponse;
import com.example.Models.Invoice.Invoice;
import com.example.Models.Invoice.InvoiceGetAllRoom;
import com.example.Models.Invoice.InvoiceGetByRoomId;
import com.example.Models.Invoice.InvoiceGetByStatus;
import com.example.Models.Invoice.InvoiceStatus;
import com.example.Utils.StatusDesc;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TraCuuHoaDonPresenter implements TraCuuHoaDonContract.Presenter {
    private Context context;
    private TraCuuHoaDonContract.View view;

    public TraCuuHoaDonPresenter(Context context, TraCuuHoaDonContract.View view) {
        this.context = context;
        this.view = view;
    }

//    @Override
//    public void loadNextPage(String token, int page, int limit, InvoiceGetByRoomId body, ArrayList<Invoice> invoices) {
//        RetrofitClient.getInstance().getInvoiceApi().getAllByRoomId(token, body).enqueue(new Callback<GetInvoiceApiResponse>() {
//            @Override
//            public void onResponse(Call<GetInvoiceApiResponse> call, Response<GetInvoiceApiResponse> response) {
//                invoices.remove(invoices.size()-1);
//                view.updateDataSetChanged();
//
//                if(response!=null && response.body()!=null){
//                    if(response.body().isSuccess()){
//                        for(Invoice invoice : response.body().getData()){
//                            invoices.add(invoice);
//                        }
//                        view.updateDataSetChanged();
//                    }
//                    else{
//                        view.raiseNote(StatusDesc.getInstance().getErrDesc(response.code()));
//                    }
//                }
//                view.setIsLoading(false);
//                view.setRefresh(false);
//            }
//
//            @Override
//            public void onFailure(Call<GetInvoiceApiResponse> call, Throwable t) {
//                invoices.remove(invoices.size()-1);
//                view.updateDataSetChanged();
//                view.raiseNote("Vui lòng kiểm tra kết nối và thử lại!");
//                view.setIsLoading(false);
//                view.setRefresh(false);
//            }
//        });
//    }

//    @Override
//    public void loadNextPage(String token, int page, int limit, InvoiceGetByStatus body, ArrayList<Invoice> invoices) {
//        RetrofitClient.getInstance().getInvoiceApi().getAllByStatus(token, body).enqueue(new Callback<GetInvoiceApiResponse>() {
//            @Override
//            public void onResponse(Call<GetInvoiceApiResponse> call, Response<GetInvoiceApiResponse> response) {
//                invoices.remove(invoices.size()-1);
//                view.updateDataSetChanged();
//
//                if(response!=null && response.body()!=null){
//                    if(response.body().isSuccess()){
//                        for(Invoice invoice : response.body().getData()){
//                            invoices.add(invoice);
//                        }
//                        view.updateDataSetChanged();
//                    }
//                    else{
//                        view.raiseNote(StatusDesc.getInstance().getErrDesc(response.code()));
//                    }
//                }
//                view.setIsLoading(false);
//                view.setRefresh(false);
//            }
//
//            @Override
//            public void onFailure(Call<GetInvoiceApiResponse> call, Throwable t) {
//                invoices.remove(invoices.size()-1);
//                view.updateDataSetChanged();
//                view.raiseNote("Vui lòng kiểm tra kết nối và thử lại!");
//                view.setIsLoading(false);
//                view.setRefresh(false);
//            }
//        });
//    }

    @Override
    public void loadInitial(String token, int page, int limit, InvoiceGetAllRoom body, ArrayList<Invoice> invoices) {
        view.setIsLoading(true);

        RetrofitClient.getInstance().getInvoiceApi().getAll(token, body).enqueue(new Callback<GetInvoiceApiResponse>() {
            @Override
            public void onResponse(Call<GetInvoiceApiResponse> call, Response<GetInvoiceApiResponse> response) {
                if(!invoices.isEmpty()){
                    invoices.clear();
                    view.updateDataSetChanged();
                }

                if(response!=null && response.body()!=null){
                    if (response.body().isSuccess()) {
                        for (Invoice invoice : response.body().getData()) {
                            invoices.add(invoice);
                        }
                        view.updateDataSetChanged();
                    }
                    else{
                        Toast.makeText(context, StatusDesc.getInstance().getErrDesc(response.code()), Toast.LENGTH_SHORT).show();
                    }
                }

                view.setRefresh(false);
                view.setIsLoading(false);
                view.setPageNum(1);
            }

            @Override
            public void onFailure(Call<GetInvoiceApiResponse> call, Throwable t) {
                if(!invoices.isEmpty()){
                    invoices.clear();
                    view.updateDataSetChanged();
                }
//                Log.i("t", t.getMessage());
                view.setRefresh(false);
                Toast.makeText(context, "Vui lòng kiểm tra kết nối và thử lại!", Toast.LENGTH_SHORT).show();
                view.setIsLoading(false);
            }
        });
    }

    @Override
    public void loadInitial(String token, InvoiceGetByStatus body, ArrayList<Invoice> invoices) {
        view.setIsLoading(true);

        RetrofitClient.getInstance().getInvoiceApi().getAllByStatus(token, body).enqueue(new Callback<GetInvoiceApiResponse>() {
            @Override
            public void onResponse(Call<GetInvoiceApiResponse> call, Response<GetInvoiceApiResponse> response) {
                if(!invoices.isEmpty()){
                    invoices.clear();
                    view.updateDataSetChanged();
                }

                if(response!=null && response.body()!=null){
                    if (response.body().isSuccess()) {
                        for (Invoice invoice : response.body().getData()) {
                            invoices.add(invoice);
                        }
                        view.updateDataSetChanged();
                    }
                    else{
                        Toast.makeText(context, StatusDesc.getInstance().getErrDesc(response.code()), Toast.LENGTH_SHORT).show();
                    }
                }

                view.setRefresh(false);
                view.setIsLoading(false);
                view.setPageNum(1);
            }

            @Override
            public void onFailure(Call<GetInvoiceApiResponse> call, Throwable t) {
                if(!invoices.isEmpty()){
                    invoices.clear();
                    view.updateDataSetChanged();
                }
//                Log.i("t", t.getMessage());
                view.setRefresh(false);
                Toast.makeText(context, "Vui lòng kiểm tra kết nối và thử lại!", Toast.LENGTH_SHORT).show();
                view.setIsLoading(false);
            }
        });
    }

    @Override
    public void confirm(String token, String id, int index, ArrayList<Invoice> invoices) {
        if(!invoices.get(index).isPaid()){
            RetrofitClient.getInstance().getInvoiceApi().confirm(token, id, new InvoiceStatus(true)).enqueue(new Callback<CreateInvoiceApiResponse>() {
                @Override
                public void onResponse(Call<CreateInvoiceApiResponse> call, Response<CreateInvoiceApiResponse> response) {
                    if(response!=null && response.body()!=null && response.body().isSuccess()){
                        Toast.makeText(context, "Thao tác thành công!", Toast.LENGTH_SHORT).show();
                        invoices.get(index).setPaid(true);
                    }
                    else{
                        Toast.makeText(context, StatusDesc.getInstance().getErrDesc(response.code()), Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onFailure(Call<CreateInvoiceApiResponse> call, Throwable t) {
                    Toast.makeText(context, "Vui lòng kiểm tra kết nối và thử lại!", Toast.LENGTH_SHORT).show();
                }
            });
        }
    }


//    @Override
//    public void loadNextPage(String token, int page, int limit, InvoiceGetAllRoom body, ArrayList<Invoice> invoices) {
//        RetrofitClient.getInstance().getInvoiceApi().getAll(token, body).enqueue(new Callback<GetInvoiceApiResponse>() {
//            @Override
//            public void onResponse(Call<GetInvoiceApiResponse> call, Response<GetInvoiceApiResponse> response) {
//                if(!invoices.isEmpty()){
//                    invoices.remove(invoices.size()-1);
//                    view.updateDataSetChanged();
//                }
//
//                if(response!=null && response.body()!=null){
//                    if(response.body().isSuccess()){
//                        for(Invoice invoice : response.body().getData()){
//                            invoices.add(invoice);
//                        }
//                        view.updateDataSetChanged();
//                    }
//                    else{
//                        view.raiseNote(StatusDesc.getInstance().getErrDesc(response.code()));
//                    }
//                }
//                view.setIsLoading(false);
//                view.setRefresh(false);
//            }
//
//            @Override
//            public void onFailure(Call<GetInvoiceApiResponse> call, Throwable t) {
//                invoices.remove(invoices.size()-1);
//                view.updateDataSetChanged();
//                view.raiseNote("Vui lòng kiểm tra kết nối và thử lại!");
//                view.setIsLoading(false);
//                view.setRefresh(false);
//            }
//        });
//    }
}
