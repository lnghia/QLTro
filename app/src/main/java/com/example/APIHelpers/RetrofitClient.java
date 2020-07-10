package com.example.APIHelpers;

import com.example.APIHelpers.Api.ContractApi;
import com.example.APIHelpers.Api.CustomerApi;
import com.example.APIHelpers.Api.FacilityApi;
import com.example.APIHelpers.Api.InvoiceApi;
import com.example.APIHelpers.Api.LoginApi;
import com.example.APIHelpers.Api.ReportApi;
import com.example.APIHelpers.Api.RoomApi;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitClient {
    private static final String BASE_URL = "https://quanlytro.herokuapp.com/";
    private static RetrofitClient mInstance;
    private Retrofit retrofit;

    private RetrofitClient() {
        retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    public static synchronized RetrofitClient getInstance() {
        if (mInstance == null) {
            mInstance = new RetrofitClient();
        }

        return mInstance;
    }

    public FacilityApi getFacilityApi() {
        return retrofit.create(FacilityApi.class);
    }

    public RoomApi getRoomApi() {
        return retrofit.create(RoomApi.class);
    }

    public CustomerApi getCustomerApi() {
        return retrofit.create(CustomerApi.class);
    }

    public LoginApi getLoginApi() {
        return retrofit.create(LoginApi.class);
    }

    public ContractApi getContractApi(){
        return retrofit.create(ContractApi.class);
    }

    public InvoiceApi getInvoiceApi() { return retrofit.create(InvoiceApi.class); }

    public ReportApi getReportApi() { return retrofit.create(ReportApi.class); }
}
