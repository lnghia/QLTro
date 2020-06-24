package com.example.qltr.cosovatchat;

import android.content.Context;

import com.example.Models.Facility.Facility;

import androidx.lifecycle.MutableLiveData;
import androidx.paging.DataSource;
import androidx.paging.PageKeyedDataSource;

public class FacilityDataSourceFactory extends DataSource.Factory {
    private MutableLiveData<PageKeyedDataSource<Integer, Facility>> facilityLiveDataSource;
    private String token;
    private Context mContext;

    public FacilityDataSourceFactory(Context mContext){
        facilityLiveDataSource=new MutableLiveData<>();
        this.mContext=mContext;
        this.token=mContext.getSharedPreferences("com.example.qltr.login", Context.MODE_PRIVATE).getString("token", null);
    }

    @Override
    public DataSource create() {
        FacilityDataSource facilityDataSource=new FacilityDataSource(mContext);

        facilityLiveDataSource.postValue(facilityDataSource);

        return facilityDataSource;
    }

    public MutableLiveData<PageKeyedDataSource<Integer, Facility>> getFacilityLiveDataSource() {
        return facilityLiveDataSource;
    }
}
