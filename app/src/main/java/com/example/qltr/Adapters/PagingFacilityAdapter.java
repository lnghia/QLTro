package com.example.qltr.Adapters;

import android.content.Context;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.Models.Facility.Facility;
import com.example.qltr.R;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class PagingFacilityAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private static final int TYPE_HEADER=0;
    private static final int TYPE_ITEM=1;
    private static final int TYPE_FOOTER=2;

    private Context mContext;
    private ArrayList<Facility> facilities;

    public PagingFacilityAdapter(Context context, ArrayList<Facility> facilities){
        this.mContext=context;
        this.facilities=facilities;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if(viewType==TYPE_HEADER){
            View view=LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_footer, parent, false);

            return new FooterViewHolder(view);
        }
        else if(viewType==TYPE_FOOTER) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_footer, parent, false);

            return new FooterViewHolder(view);
        }

        View view = LayoutInflater.from(mContext).inflate(R.layout.facility_viewholder_layout, parent, false);

        return new FacilityViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        if(holder instanceof FacilityViewHolder){
            ((FacilityViewHolder) holder).facilityPriceDisplayer.setText(Integer.toString(facilities.get(position).getUnitPrice()));
            ((FacilityViewHolder) holder).facilityNameDisplayer.setText(facilities.get(position).getName());

//            mContext.regis
        }
    }

    @Override
    public int getItemCount() {
        return (facilities==null)?0:facilities.size();
    }

    @Override
    public int getItemViewType(int position){
        if(isFooter(position)){
            return TYPE_FOOTER;
        }

        return TYPE_ITEM;
    }

    private boolean isHeader(int pos){
        return pos==0;
    }

    private boolean isFooter(int pos){
        return facilities.get(pos)==null;
    }

    class FacilityViewHolder extends RecyclerView.ViewHolder implements View.OnCreateContextMenuListener {
        private TextView facilityNameDisplayer;
        private TextView facilityPriceDisplayer;

        public FacilityViewHolder(@NonNull View itemView) {
            super(itemView);

            facilityNameDisplayer=itemView.findViewById(R.id.facility_name_displayer);
            facilityPriceDisplayer=itemView.findViewById(R.id.facility_price_displayer);

            itemView.setOnCreateContextMenuListener(this);
        }

        @Override
        public void onCreateContextMenu(ContextMenu contextMenu, View view, ContextMenu.ContextMenuInfo contextMenuInfo) {
            contextMenu.add(this.getAdapterPosition(), 1, 0, "Sửa");
            contextMenu.add(this.getAdapterPosition(), 2, 0, "Xóa");
        }
    }

    public class FooterViewHolder extends RecyclerView.ViewHolder {
        public FooterViewHolder(@NonNull View itemView) {
            super(itemView);
        }
    }

    public void deleteFacility(int id){
        facilities.remove(id);
        notifyDataSetChanged();
    }
}
