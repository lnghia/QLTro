package com.example.qltr.Adapters;

import android.content.Context;
import android.media.Image;
import android.util.Log;
import android.util.Pair;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.qltr.R;

import java.util.ArrayList;

public class CustomGridViewAdapter extends BaseAdapter {
    ArrayList<Pair<String, Integer>> buttonsInfo;
    ImageView icon;
    TextView title;
    Context mContext;

    public CustomGridViewAdapter(ArrayList<Pair<String, Integer>> buttonsInfo, Context mContext){
        this.buttonsInfo=buttonsInfo;
        this.mContext=mContext;
    }

    @Override
    public int getCount() {
        return buttonsInfo.size();
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        View rsView= LayoutInflater.from(mContext).inflate(R.layout.custom_button, null);

        icon=rsView.findViewById(R.id.button_icon);
        title=rsView.findViewById(R.id.button_text);

        icon.setImageResource(buttonsInfo.get(i).second);
        title.setText(buttonsInfo.get(i).first);

        return rsView;
    }

}
