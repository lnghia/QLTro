package com.example.qltr.Adapters;

import android.util.Log;

import com.example.qltr.nhatro.NhaTroFragment;
import com.example.qltr.phongtro.PhongTroFragment;
import com.example.qltr.setting.SettingFragment;
import com.example.qltr.statistic.StatisticFragment;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

public class SwipeViewPagerAdapter extends FragmentStateAdapter {
    //private String[] titles=new String[]{"Thống Kê", "Nhà Trọ", "Phòng Trọ", "Cài Đặt"};
    private  int tabNum;

    public SwipeViewPagerAdapter(@NonNull FragmentActivity fragmentActivity) {
        super(fragmentActivity);
    }

    public SwipeViewPagerAdapter(@NonNull FragmentActivity fragmentActivity, int tabNum){
        super(fragmentActivity);
        this.tabNum=tabNum;
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        switch(position){
            case 0:
                return new StatisticFragment();
            case 1:
                return new NhaTroFragment();
            case 2:
                return new PhongTroFragment();
            case 3:
                return new SettingFragment();
        }

        return null;
    }

    @Override
    public int getItemCount() {
        return tabNum;
    }
}
