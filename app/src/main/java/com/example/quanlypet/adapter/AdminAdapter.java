package com.example.quanlypet.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.quanlypet.R;
import com.example.quanlypet.model.AdminObj;

import java.util.ArrayList;

public class AdminAdapter extends RecyclerView.Adapter<AdminAdapter.AdminViewHolder>{
    private Context context;
    private Callback callback;
    private ArrayList<AdminObj> list;

    public AdminAdapter(Context context, Callback callback) {
        this.context = context;
        this.callback = callback;
    }

    public void setData(ArrayList<AdminObj> list){
        this.list = list;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public AdminViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_admin,parent,false);
        return new AdminViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AdminViewHolder holder, int position) {
        AdminObj adminObj = list.get(position);
        if (adminObj == null){
            return;
        }
        holder.tvImportnameAdmin.setText(adminObj.getImport_name());
        holder.tvFullnameAdmin.setText(adminObj.getFull_name());
        holder.tvEmailAdmin.setText(adminObj.getEmail());
        holder.tvStatusAdmin.setText(adminObj.getStatus_obj()+"");
        holder.imgEditAdmin.setOnClickListener(v->{
            callback.editAdmin(adminObj);
        });
    }

    @Override
    public int getItemCount() {
        return list == null? 0:list.size();
    }

    public class AdminViewHolder extends RecyclerView.ViewHolder {
        private TextView tvImportnameAdmin;
        private TextView tvFullnameAdmin;
        private TextView tvEmailAdmin;
        private TextView tvStatusAdmin;
        private ImageView imgEditAdmin;

        public AdminViewHolder(@NonNull View itemView) {
            super(itemView);

            tvImportnameAdmin = (TextView) itemView.findViewById(R.id.tv_importnameAdmin);
            tvFullnameAdmin = (TextView) itemView.findViewById(R.id.tv_fullnameAdmin);
            tvEmailAdmin = (TextView) itemView.findViewById(R.id.tv_emailAdmin);
            tvStatusAdmin = (TextView) itemView.findViewById(R.id.tv_statusAdmin);
            imgEditAdmin = (ImageView) itemView.findViewById(R.id.img_editAdmin);

        }
    }
    public interface Callback{
        void editAdmin(AdminObj adminObj);
    }
}
