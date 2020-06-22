package com.example.qltr.cosovatchat;

import android.content.Context;

import com.example.Models.Facility;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;
import androidx.paging.LivePagedListBuilder;
import androidx.paging.PageKeyedDataSource;
import androidx.paging.PagedList;

public class FacilityViewModel extends ViewModel {
    private LiveData<PagedList<Facility>> facilityPagedList;
    private LiveData<PageKeyedDataSource<Integer, Facility>> liveDataSource;
    private String token;
    private Context context;

    public FacilityViewModel(Context context){
        this.context=context;
        token=context.getSharedPreferences("com.example.qltr.login", Context.MODE_PRIVATE).getString("token", null);
    }

    public FacilityViewModel(){
        FacilityDataSourceFactory facilityDataSourceFactory=new FacilityDataSourceFactory(context);
        liveDataSource=facilityDataSourceFactory.getFacilityLiveDataSource();

        PagedList.Config config=(new PagedList.Config.Builder())
                .setEnablePlaceholders(false)
                .setPageSize(FacilityDataSource.PAGE_SIZE)
                .build();

        facilityPagedList=(new LivePagedListBuilder(facilityDataSourceFactory, config)).build();
    }
}
