package com.example.qltr.Adapters;

import android.content.Context;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.Models.Report.Report;
import com.example.qltr.R;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class PagingPhanAnhAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private static final int TYPE_ITEM=1;
    private static final int TYPE_FOOTER=2;

    private Context mContext;
    private ArrayList<Report> reports;

    public PagingPhanAnhAdapter(Context mContext, ArrayList<Report> reports) {
        this.mContext = mContext;
        this.reports = reports;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if(viewType==TYPE_FOOTER) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_footer, parent, false);

            return new PagingPhanAnhAdapter.FooterViewHolder(view);
        }

        View view = LayoutInflater.from(mContext).inflate(R.layout.phan_anh_cardview, parent, false);

        return new PagingPhanAnhAdapter.ReportViewHoler(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        if(holder instanceof ReportViewHoler){
            ((ReportViewHoler) holder).name.setText(reports.get(position).getName());
            ((ReportViewHoler) holder).desc.setText(reports.get(position).getDescription());

            if(reports.get(position).getStatus().equals("done")){
                ((ReportViewHoler) holder).status.setBackgroundResource(R.drawable.rounded_button);
            }
            else if(reports.get(position).getStatus().equals("processing")){
                ((ReportViewHoler) holder).status.setBackgroundResource(R.drawable.yellow_rounded_button);
            }
            else{
                ((ReportViewHoler) holder).status.setBackgroundResource(R.drawable.orange_rounded_button);
            }
//            mContext.regis
        }
    }

    @Override
    public int getItemCount() {
        return (reports==null)?0:reports.size();
    }

    @Override
    public int getItemViewType(int position){
        if(isFooter(position)){
            return TYPE_FOOTER;
        }

        return TYPE_ITEM;
    }

    private boolean isFooter(int pos){
        return reports.get(pos)==null;
    }

    class ReportViewHoler extends RecyclerView.ViewHolder implements View.OnCreateContextMenuListener {
        private TextView name;
        private TextView desc;
        private Button status;

        public ReportViewHoler(@NonNull View itemView) {
            super(itemView);

            name=itemView.findViewById(com.example.qltr.R.id.report_name_displayer);
            desc=itemView.findViewById(com.example.qltr.R.id.report_desc_displayer);
            status=itemView.findViewById(com.example.qltr.R.id.report_status);

            itemView.setOnCreateContextMenuListener(this);
        }

        @Override
        public void onCreateContextMenu(ContextMenu contextMenu, View view, ContextMenu.ContextMenuInfo contextMenuInfo) {
            contextMenu.add(this.getAdapterPosition(), 1, 0, "Duyệt");
            contextMenu.add(this.getAdapterPosition(), 2, 0, "Xóa");
        }
    }

    public class FooterViewHolder extends RecyclerView.ViewHolder {
        public FooterViewHolder(@NonNull View itemView) {
            super(itemView);
        }
    }
}
