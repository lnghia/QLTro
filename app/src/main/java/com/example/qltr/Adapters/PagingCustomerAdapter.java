package com.example.qltr.Adapters;

import android.content.Context;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.Models.Customer.Customer;
import com.example.qltr.R;

import org.w3c.dom.Text;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class PagingCustomerAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private static final int TYPE_ITEM = 1;
    private static final int TYPE_FOOTER = 2;

    private Context context;
    private ArrayList<Customer> customers;

    public PagingCustomerAdapter(Context context, ArrayList<Customer> customers) {
        this.context = context;
        this.customers = customers;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if (viewType == TYPE_FOOTER) {
            View view = LayoutInflater.from(context).inflate(R.layout.recycler_footer, parent, false);

            return new FooterViewHolder(view);
        }

        View view=LayoutInflater.from(context).inflate(R.layout.khach_tro_card_view, parent, false);

        return new CustomerViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        if(holder instanceof CustomerViewHolder){
            ((CustomerViewHolder)holder).name.setText(customers.get(position).getName());
        }

    }

    @Override
    public int getItemViewType(int position) {
        if (isFooter(position)) {
            return TYPE_FOOTER;
        }

        return TYPE_ITEM;
    }

    private boolean isFooter(int pos) {
        return customers.get(pos) == null;
    }

    @Override
    public int getItemCount() {
        return (customers == null) ? 0 : customers.size();
    }

    class CustomerViewHolder extends RecyclerView.ViewHolder implements View.OnCreateContextMenuListener, View.OnClickListener {
        private TextView name;
        private TextView birthday;
        private TextView phone;

        public CustomerViewHolder(@NonNull View itemView) {
            super(itemView);

            name = itemView.findViewById(R.id.customer_name_displayer);
            birthday = itemView.findViewById(R.id.customer_birth_day);
            phone = itemView.findViewById(R.id.customer_phone_displayer);

            itemView.setOnClickListener(this);
            itemView.setOnCreateContextMenuListener(this);
        }

        @Override
        public void onClick(View view) {

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
}
