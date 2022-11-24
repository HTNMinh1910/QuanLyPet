package com.example.quanlypet.adapter.ad_use;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.quanlypet.R;
import com.example.quanlypet.model.UsersObj;

import java.util.ArrayList;

public class UsersAdapter extends RecyclerView.Adapter<UsersAdapter.UsersViewHolder>{
    private Context context;
    private Callback callback;
    private ArrayList<UsersObj> list;

    public UsersAdapter(Context context, Callback callback) {
        this.context = context;
        this.callback = callback;
    }
    public void setData(ArrayList<UsersObj> list){
        this.list = list;
        notifyDataSetChanged();
    }
    @NonNull
    @Override
    public UsersViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_users,parent,false);
        return new UsersViewHolder(view);
    }
    @Override
    public void onBindViewHolder(@NonNull UsersViewHolder holder, int position) {
        UsersObj usersObj = list.get(position);
        if (usersObj == null){
            return;
        }
        holder.tvImportnameUsers.setText(usersObj.getImport_name());
        holder.tvFullnameUsers.setText(usersObj.getFull_name());
        holder.tvEmailUsers.setText(usersObj.getEmail());
        holder.tvPhoneUsers.setText(usersObj.getPhone());

        if (usersObj.getGender() == 0){
            holder.tvGenderUsers.setText("Male");
        } else if (usersObj.getGender() == 1){
            holder.tvGenderUsers.setText("Female");
        }
        holder.imgEditUsers.setOnClickListener(v->{
            callback.editUsers(usersObj);
        });
    }
    @Override
    public int getItemCount() {
        return list == null?0:list.size();
    }

    public class UsersViewHolder extends RecyclerView.ViewHolder {
        private TextView tvImportnameUsers;
        private TextView tvFullnameUsers;
        private TextView tvEmailUsers;
        private TextView tvPhoneUsers;
        private TextView tvGenderUsers;
        private ImageView imgEditUsers;

        public UsersViewHolder(@NonNull View itemView) {
            super(itemView);

            tvImportnameUsers = itemView.findViewById(R.id.tv_importnameUsers);
            tvFullnameUsers = itemView.findViewById(R.id.tv_fullnameUsers);
            tvEmailUsers = itemView.findViewById(R.id.tv_emailUsers);
            tvPhoneUsers = itemView.findViewById(R.id.tv_phoneUsers);
            tvGenderUsers = itemView.findViewById(R.id.tv_genderUsers);
            imgEditUsers = itemView.findViewById(R.id.img_editUsers);
        }
    }
    public interface Callback{
        void editUsers(UsersObj usersObj);
    }
}
