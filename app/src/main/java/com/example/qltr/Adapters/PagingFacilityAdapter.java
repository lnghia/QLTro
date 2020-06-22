package com.example.qltr.Adapters;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.Models.Facility;
import com.example.qltr.R;

import androidx.annotation.NonNull;
import androidx.paging.PagedListAdapter;
import androidx.recyclerview.widget.DiffUtil.ItemCallback;
import androidx.recyclerview.widget.RecyclerView;

public class PagingFacilityAdapter extends PagedListAdapter<Facility, PagingFacilityAdapter.FacilityViewHolder> {
    private Context mContext;
    private TextView facilityNameDisplayer;
    private TextView facilityPriceDisplayer;

    public PagingFacilityAdapter(Context context) {
        super(DIFF_CALLBACK);
        mContext = context;
    }

    @NonNull
    @Override
    public FacilityViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.facility_viewholder_layout, parent, false);

        return new FacilityViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull FacilityViewHolder holder, int position) {
        Facility item = getItem(position);

        if (item != null) {
            holder.facilityNameDisplayer.setText(item.getName());
//            holder.facilityPriceDisplayer.setText(/*item.getPrice()/*);
        }
        else {
            Toast.makeText(mContext, "item is null", Toast.LENGTH_LONG).show();
        }
    }

    private static ItemCallback<Facility> DIFF_CALLBACK = new ItemCallback<Facility>() {

        @Override
        public boolean areItemsTheSame(@NonNull Facility oldItem, @NonNull Facility newItem) {
            return oldItem.get_id() == newItem.get_id();
        }

        @SuppressLint("DiffUtilEquals")
        @Override
        public boolean areContentsTheSame(@NonNull Facility oldItem, @NonNull Facility newItem) {
            return oldItem.equals(newItem);
        }
    };

    class FacilityViewHolder extends RecyclerView.ViewHolder {
        private TextView facilityNameDisplayer;
        private TextView facilityPriceDisplayer;

        public FacilityViewHolder(@NonNull View itemView) {
            super(itemView);

            facilityNameDisplayer=itemView.findViewById(R.id.facility_name_displayer);
            facilityPriceDisplayer=itemView.findViewById(R.id.facility_price_displayer);
        }
    }
}
