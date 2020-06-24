package com.example.qltr.Adapters;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.Models.Room;
import com.example.qltr.R;
import com.example.qltr.phongtro.PhongTroFragment;

import java.util.ArrayList;
import java.util.Locale;

import androidx.annotation.NonNull;
import androidx.appcompat.content.res.AppCompatResources;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

public class RoomButtonViewAdapter extends RecyclerView.Adapter<RoomButtonViewAdapter.MyViewHolder> {
    private static final String TAG = "RoomButtonViewAdapter";

    private ArrayList<Room> rooms;
    private Context mContext;

    public  RoomButtonViewAdapter(Context context, ArrayList<Room> roomList){
        mContext=context;
        rooms=roomList;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.room_button, parent, false);

//        Log.d(TAG, "init viewholder: successful");

        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, final int position) {
//        Log.d(TAG, "onBindViewHolder: called.");

        holder.roomNum.setText(String.format(Locale.US, "%d", rooms.get(position).getId()));
        holder.area.setText(String.format(Locale.US, "%d", rooms.get(position).getArea()));
        holder.debt.setText(String.format(Locale.US, "%dtr", rooms.get(position).getDebt()));
        //Drawable icon = AppCompatResources.getDrawable(mContext, R.drawable.price);
        //holder.debt.setCompoundDrawablesWithIntrinsicBounds(icon, null, null, null);
        holder.vehicleNum.setText(String.format(Locale.US, "%d/%d", rooms.get(position).getVehicleNum(), 4));
        holder.takenSpot.setText(String.format(Locale.US, "%d/%d", rooms.get(position).getTakenSpot(), 4));

        holder.parentLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d(TAG, "onClick: clicked on: " + Integer.toString(rooms.get(position).getId()));

                Toast.makeText(mContext, Integer.toString(rooms.get(position).getId()), Toast.LENGTH_SHORT).show();
            }
        });

        Log.d(TAG, "onBindViewHolder: successful.");
    }

    @Override
    public int getItemCount() {
        return rooms.size();
    }

    public class MyViewHolder extends  RecyclerView.ViewHolder{
        TextView roomNum;
        TextView debt;
        TextView takenSpot;
        TextView vehicleNum;
        TextView area;
        ConstraintLayout parentLayout;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            roomNum=itemView.findViewById(R.id.room_num);
            debt=itemView.findViewById(R.id.debt);
            takenSpot=itemView.findViewById(R.id.taken_spots);
            vehicleNum=itemView.findViewById(R.id.vehicle_num);
            area=itemView.findViewById(R.id.area);
            parentLayout=itemView.findViewById(R.id.parent_layout);
        }
    }
}
