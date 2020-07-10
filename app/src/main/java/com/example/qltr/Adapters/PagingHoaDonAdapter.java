package com.example.qltr.Adapters;

import android.content.Context;
import android.content.Intent;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.Models.Facility.Facility;
import com.example.Models.Invoice.Invoice;
import com.example.Utils.ListContainers;
import com.example.qltr.R;
import com.example.qltr.hoadon.DetailHoaDon.DetailHoaDon;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class PagingHoaDonAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private static final int TYPE_ITEM=1;
    private static final int TYPE_FOOTER=2;

    private Context mContext;
    private ArrayList<Invoice> invoices;

    public PagingHoaDonAdapter(Context mContext, ArrayList<Invoice> invoices) {
        this.mContext = mContext;
        this.invoices = invoices;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if(viewType==TYPE_FOOTER) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_footer, parent, false);

            return new PagingHoaDonAdapter.FooterViewHolder(view);
        }

        View view = LayoutInflater.from(mContext).inflate(R.layout.hoa_don_layout, parent, false);

        return new PagingHoaDonAdapter.InvoiceViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        if(holder instanceof InvoiceViewHolder){
            String[] tmp=invoices.get(position).getCreatedAt().split("T");

            ((InvoiceViewHolder) holder).invoiceRoomName.setText(invoices.get(position).getRoomId().getName());
            ((InvoiceViewHolder) holder).invoiceDate.setText(tmp[0]);

            if(invoices.get(position).isPaid()){
                ((InvoiceViewHolder) holder).status.setBackgroundResource(R.drawable.rounded_button);
            }
            else{
                ((InvoiceViewHolder) holder).status.setBackgroundResource(R.drawable.orange_rounded_button);
            }
        }
    }

    @Override
    public int getItemCount() {
        return (invoices==null)?0:invoices.size();
    }

    @Override
    public int getItemViewType(int position){
        if(isFooter(position)){
            return TYPE_FOOTER;
        }

        return TYPE_ITEM;
    }

    private boolean isFooter(int pos){
        return invoices.get(pos)==null;
    }

    class InvoiceViewHolder extends RecyclerView.ViewHolder implements View.OnCreateContextMenuListener, View.OnClickListener {
        private TextView invoiceRoomName;
        private TextView invoiceDate;
        private Button status;

        public InvoiceViewHolder(@NonNull View itemView) {
            super(itemView);

            invoiceRoomName=itemView.findViewById(R.id.invoice_name_displayer);
            invoiceDate=itemView.findViewById(R.id.invoice_date_displayer);
            status=itemView.findViewById(R.id.invoice_status);

            itemView.setOnCreateContextMenuListener(this);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onCreateContextMenu(ContextMenu contextMenu, View view, ContextMenu.ContextMenuInfo contextMenuInfo) {
            contextMenu.add(this.getAdapterPosition(), 1, 0, "Duyệt");
//            contextMenu.add(this.getAdapterPosition(), 2, 0, "Xóa");
        }

        @Override
        public void onClick(View view) {
            Intent intent=new Intent(mContext, DetailHoaDon.class);

            intent.putExtra("Id", invoices.get(getAdapterPosition()).get_id());
            intent.putExtra("token", ListContainers.getInstance().getToken());
//            intent.putExtra("customerNum", rooms.get(getAdapterPosition()).get)

            mContext.startActivity(intent);
        }
    }

    public class FooterViewHolder extends RecyclerView.ViewHolder {
        public FooterViewHolder(@NonNull View itemView) {
            super(itemView);
        }
    }
}
