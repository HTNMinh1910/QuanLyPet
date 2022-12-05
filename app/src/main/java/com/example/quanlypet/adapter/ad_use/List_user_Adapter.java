package com.example.quanlypet.adapter.ad_use;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.quanlypet.R;
import com.example.quanlypet.adapter.booking.booking_admin_Adapter;
import com.example.quanlypet.model.BookObj;
import com.example.quanlypet.model.UsersObj;
import com.example.quanlypet.ui.activity.DetailUsersActivity;

import java.util.List;

public class List_user_Adapter extends RecyclerView.Adapter<List_user_Adapter.ViewHolder> {
    List<UsersObj> list;
    Context mContext;
    ClickItem clickItem;

    public interface ClickItem {
        void update(UsersObj obj);
    }

    public List_user_Adapter(Context mContext, ClickItem clickItem) {
        this.mContext = mContext;
        this.clickItem = clickItem;
    }

    public void setData(List<UsersObj> list) {
        this.list = list;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_users, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        UsersObj obj = list.get(position);
        holder.tvFullnameUsers.setText(obj.getFull_name());
        holder.tvPhoneUsers.setText(obj.getPhone());
        holder.itemView.setOnLongClickListener(view -> {
            clickItem.update(obj);
            return false;
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView tvFullnameUsers;
        private TextView tvPhoneUsers;
        private CardView cardViewItem;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvFullnameUsers = (TextView) itemView.findViewById(R.id.tv_fullnameUsers);
            tvPhoneUsers = (TextView) itemView.findViewById(R.id.tv_phoneUsers);
            cardViewItem = (CardView) itemView.findViewById(R.id.card_view_item);

        }
    }
}
