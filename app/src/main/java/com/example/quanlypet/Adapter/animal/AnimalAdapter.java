package com.example.quanlypet.adapter.animal;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.quanlypet.R;
import com.example.quanlypet.model.AnimalObj;

import java.util.ArrayList;

public class AnimalAdapter extends RecyclerView.Adapter<AnimalAdapter.AnimalViewHolder>{
    private Context context;
    private Callback callback;
    private ArrayList<AnimalObj> arrayList;

    public AnimalAdapter(Context context, Callback callback) {
        this.context = context;
        this.callback = callback;
    }
    public void setData(ArrayList<AnimalObj> arrayList){
        this.arrayList = arrayList;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public AnimalViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_animal, parent, false);
        return new AnimalViewHolder(view);
    }
    @Override
    public void onBindViewHolder(@NonNull AnimalViewHolder holder, int position) {
        AnimalObj object = arrayList.get(position);
        if (object == null)
            return;
        holder.idUsers.setText(object.getId_users()+"");
        holder.tvNameAnimal.setText(object.getName());
        byte[] anh = object.getAvatar();
        Bitmap bitmap = BitmapFactory.decodeByteArray(anh, 0, anh.length);
        holder.imgAnhItem.setImageBitmap(bitmap);
        holder.tvAge.setText(object.getAge()+"");
        holder.tvLoai.setText(object.getSpecies());
        holder.imgUpdate.setOnClickListener(v ->{
            callback.Update(object);
        });

    }
    @Override
    public int getItemCount() {
        return arrayList == null ? 0: arrayList.size();
    }

    public class AnimalViewHolder extends RecyclerView.ViewHolder {
        private RelativeLayout relyAnimal;
        private TextView idUsers;
        private TextView tvNameAnimal;
        private ImageView imgAnhItem;
        private ImageView imgUpdate;
        private TextView tvAge;
        private TextView tvLoai;
        private ImageView imgDelete;

        public AnimalViewHolder(@NonNull View itemView) {
            super(itemView);
            relyAnimal = (RelativeLayout) itemView.findViewById(R.id.rely_animal);
            idUsers = (TextView) itemView.findViewById(R.id.id_users);
            tvNameAnimal = (TextView) itemView.findViewById(R.id.tv_nameAnimal);
            imgAnhItem = (ImageView) itemView.findViewById(R.id.img_anh_item);
            imgUpdate = (ImageView) itemView.findViewById(R.id.img_update);
            imgDelete = itemView.findViewById(R.id.img_delete);
            tvAge = (TextView) itemView.findViewById(R.id.tv_age);
            tvLoai = (TextView) itemView.findViewById(R.id.tv_loai);
        }
    }
    public interface Callback{
        void Update(AnimalObj object);
    }
}
