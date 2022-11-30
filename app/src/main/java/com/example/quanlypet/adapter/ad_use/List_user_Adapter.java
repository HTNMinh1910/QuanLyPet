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
import com.example.quanlypet.model.UsersObj;
import com.example.quanlypet.ui.activity.DetailUsersActivity;

import java.util.List;

public class List_user_Adapter extends RecyclerView.Adapter<List_user_Adapter.ViewHolder> {
    List<UsersObj> list;
    Context mContext;

    public List_user_Adapter(Context mContext) {
        this.mContext = mContext;
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
        holder.tvImportnameUsers.setText(obj.getImport_name());
        holder.tvFullnameUsers.setText(obj.getFull_name());
        holder.tvEmailUsers.setText(obj.getEmail());
        holder.tvPhoneUsers.setText(obj.getPhone());
        holder.cardViewItem.setOnLongClickListener(view -> {
            Intent intent = new Intent(mContext, DetailUsersActivity.class);
            intent.putExtra("id",obj.getId());
            mContext.startActivity(intent);
            return false;
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView tvImportnameUsers;
        private TextView tvFullnameUsers;
        private TextView tvEmailUsers;
        private TextView tvPhoneUsers;
        private CardView cardViewItem;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvImportnameUsers = (TextView) itemView.findViewById(R.id.tv_importnameUsers);
            tvFullnameUsers = (TextView) itemView.findViewById(R.id.tv_fullnameUsers);
            tvEmailUsers = (TextView) itemView.findViewById(R.id.tv_emailUsers);
            tvPhoneUsers = (TextView) itemView.findViewById(R.id.tv_phoneUsers);
            cardViewItem = (CardView) itemView.findViewById(R.id.card_view_item);

        }
    }
}
