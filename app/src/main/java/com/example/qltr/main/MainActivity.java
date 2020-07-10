package com.example.qltr.main;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager2.widget.ViewPager2;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.Utils.ListContainers;
import com.example.qltr.Adapters.SwipeViewPagerAdapter;
import com.example.qltr.R;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

public class MainActivity extends AppCompatActivity {
    TabLayout tabLayout;
    ViewPager2 viewPager;
    Bundle extras;
    String[] titles=new String[]{"Thống Kê", "Nhà Trọ", "Phòng Trọ", "Cài Đặt"};
    int[] tabIcons={
            R.drawable.statistic_icon,
            R.drawable.home_icon,
            R.drawable.room_icon,
            R.drawable.setting_icon
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setView();
        init();

//        Toast.makeText(this, extras.getString("token"), Toast.LENGTH_LONG).show();
        ListContainers.getInstance().setToken(extras.getString("token"));
    }

    private void setView(){
        viewPager=findViewById(R.id.pager);
        tabLayout=findViewById(R.id.tab_layout);
    }

    private void init(){
        viewPager.setAdapter(new SwipeViewPagerAdapter(this, 4));
        new TabLayoutMediator(tabLayout, viewPager, (tab, position) -> tab.setText(titles[position])).attach();
        setTabIcon();

        extras=getIntent().getExtras();
    }

    private void setTabIcon(){
        tabLayout.getTabAt(0).setIcon(tabIcons[0]);
        tabLayout.getTabAt(2).setIcon(tabIcons[2]);
        tabLayout.getTabAt(1).setIcon(tabIcons[1]);
        tabLayout.getTabAt(3).setIcon(tabIcons[3]);
    }
}
