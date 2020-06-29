package com.example.qltr.Adapters;

import android.annotation.SuppressLint;
import android.content.Context;
import android.util.Log;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.Models.Room.Room;
import com.example.qltr.R;

import java.util.ArrayList;
import java.util.Locale;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

public class RoomButtonViewAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private static final String TAG = "RoomButtonViewAdapter";
    private static final int TYPE_HEADER = 0;
    private static final int TYPE_ITEM = 1;
    private static final int TYPE_FOOTER = 2;

    private ArrayList<Room> rooms;
    private Context mContext;

    public RoomButtonViewAdapter(Context context, ArrayList<Room> roomList) {
        mContext = context;
        rooms = roomList;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if (viewType == TYPE_FOOTER) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_footer, parent, false);

            return new FooterViewHolder(view);
        }

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.room_button, parent, false);

//        Log.d(TAG, "init viewholder: successful");

        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof MyViewHolder) {
            MyViewHolder temp = (MyViewHolder) holder;

            temp.roomNum.setText(rooms.get(position).getName());
            temp.area.setText(Float.toString(rooms.get(position).getSquare()));
            temp.debt.setText(Integer.toString(rooms.get(position).getPrice()));
            //Drawable icon = AppCompatResources.getDrawable(mContext, R.drawable.price);
            //holder.debt.setCompoundDrawablesWithIntrinsicBounds(icon, null, null, null);
            temp.vehicleNum.setText(String.format(Locale.US, "%d/%d", rooms.get(position).getVehicleNumber(), 4));
            temp.takenSpot.setText(Integer.toString(rooms.get(position).getCapacity()));

            switch (rooms.get(position).getSlotStatus()) {
                case "full":
                    ((MyViewHolder) holder).availableSlot.setBackgroundResource(R.drawable.orange_rounded_button);
                    break;
                case "available":
                    ((MyViewHolder) holder).availableSlot.setBackgroundResource(R.drawable.yellow_rounded_button);
                    break;
                default:
                    ((MyViewHolder) holder).availableSlot.setBackgroundResource(R.drawable.rounded_button);
                    break;
            }
        }
    }

    @Override
    public int getItemCount() {
        return (rooms == null || rooms.isEmpty()) ? 0 : rooms.size();
    }

    @Override
    public int getItemViewType(int position) {
        if (isFooter(position)) {
            return TYPE_FOOTER;
        }

        return TYPE_ITEM;
    }

    private boolean isFooter(int pos) {
        return rooms.get(pos) == null;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnCreateContextMenuListener {
        TextView roomNum;
        TextView debt;
        TextView takenSpot;
        TextView vehicleNum;
        TextView area;
        ConstraintLayout parentLayout;
        Button availableSlot;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            roomNum = itemView.findViewById(R.id.room_num);
            debt = itemView.findViewById(R.id.debt);
            takenSpot = itemView.findViewById(R.id.taken_spots);
            vehicleNum = itemView.findViewById(R.id.vehicle_num);
            area = itemView.findViewById(R.id.area);
            parentLayout = itemView.findViewById(R.id.parent_layout);
            availableSlot = itemView.findViewById(R.id.available_slot);

            itemView.setOnCreateContextMenuListener(this);
        }

        @Override
        public void onCreateContextMenu(ContextMenu contextMenu, View view, ContextMenu.ContextMenuInfo contextMenuInfo) {
            contextMenu.add(getAdapterPosition(), 1, 0, "Sửa");
            contextMenu.add(getAdapterPosition(), 2, 0, "Xóa");
        }
    }

    public class FooterViewHolder extends RecyclerView.ViewHolder {
        public FooterViewHolder(@NonNull View itemView) {
            super(itemView);
        }
    }
}
