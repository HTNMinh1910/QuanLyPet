package com.example.quanlypet.adapter.bill;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.quanlypet.R;
import com.example.quanlypet.database.UsersDB;
import com.example.quanlypet.model.BillObj;
import com.example.quanlypet.model.UsersObj;

import java.util.ArrayList;

public class BillAdapter extends RecyclerView.Adapter<BillAdapter.BillViewHolder>{
    private Context context;
    private Callback callback;
    private ArrayList<BillObj> arrayList;
    public BillAdapter(Context context, Callback callback) {
        this.context = context;
        this.callback = callback;
    }
    public void setData(ArrayList<BillObj> arrayList){
        this.arrayList = arrayList;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public BillViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_bill, parent, false);
        return new BillViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull BillViewHolder holder, int position) {
        BillObj object = arrayList.get(position);
        if (object == null)
            return;
        UsersObj usersObj = UsersDB.getInstance(context).Dao().getID(object.getId_users());
        holder.tvNameUsers.setText(usersObj.getFull_name());
        holder.tvDate.setText(object.getDate());
        holder.tvPriceBill.setText(object.getPrice()+"");
        holder.idItemBill.setOnClickListener(v -> {
            callback.Update(object);
        });
    }

    @Override
    public int getItemCount() {
        return arrayList == null?0:arrayList.size();
    }

    public class BillViewHolder extends RecyclerView.ViewHolder {
        private TextView tvPriceBill;
        private TextView tvDate;
        private CardView idItemBill;
        private TextView tvNameUsers;

        public BillViewHolder(@NonNull View itemView) {
            super(itemView);

            idItemBill = (CardView) itemView.findViewById(R.id.id_item_bill);
            tvNameUsers = (TextView) itemView.findViewById(R.id.tv_nameUsers);
            tvDate = (TextView) itemView.findViewById(R.id.tv_date);
            tvPriceBill = (TextView) itemView.findViewById(R.id.tv_priceBill);
        }
    }
    public interface Callback{
        void Update(BillObj object);
    }
}