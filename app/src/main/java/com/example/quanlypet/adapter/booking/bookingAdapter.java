package com.example.quanlypet.adapter.booking;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.quanlypet.R;
import com.example.quanlypet.database.AnimalDB;
import com.example.quanlypet.database.DoctorDB;
import com.example.quanlypet.model.AnimalObj;
import com.example.quanlypet.model.BookObj;
import com.example.quanlypet.model.DoctorObj;

import java.util.List;

public class bookingAdapter extends RecyclerView.Adapter<bookingAdapter.ViewHolder> {
    List<BookObj> list;
    Context mContext;
    private ClickItem clickItem;
    public interface ClickItem{
        void update(BookObj bookObj, int index);
    }


    public bookingAdapter(ClickItem clickItem) {
        this.clickItem = clickItem;
    }
    public bookingAdapter() {

    }

    public void setDATA(List<BookObj> list) {
        this.list = list;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_booking, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        BookObj obj = list.get(position);
        int index = position;
        holder.tvTime.setText(obj.getTime());
        holder.tvService.setText(obj.getService());
        DoctorObj doctorObj = DoctorDB.getInstance(mContext).docterDao().getIdDoctor(obj.getId_doctor() + "");
        holder.nameDoctor.setText(doctorObj.getName());
        holder.tvAddress.setText(doctorObj.getAddress());
        AnimalObj animalObj = AnimalDB.getInstance(mContext).animalDao().getIDAnimal(obj.getId_dnimal()+"");
        holder.tvNamePet.setText(animalObj.getName());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              clickItem.update(obj,index);
            }
        });

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private CardView CVTinhtrang;
        private TextView titleDoctor;
        private TextView nameDoctor;
        private TextView gachngang;
        private TextView tvAddress;
        private TextView tvNamePet;
        private TextView gachngang2;
        private TextView tvService;
        private TextView tvTime;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            CVTinhtrang = (CardView) itemView.findViewById(R.id.CV_tinhtrang);
            titleDoctor = (TextView) itemView.findViewById(R.id.titleDoctor);
            nameDoctor = (TextView) itemView.findViewById(R.id.nameDoctor);
            gachngang = (TextView) itemView.findViewById(R.id.gachngang);
            tvAddress = (TextView) itemView.findViewById(R.id.tv_address);
            tvNamePet = (TextView) itemView.findViewById(R.id.tv_namePet);
            gachngang2 = (TextView) itemView.findViewById(R.id.gachngang2);
            tvService = (TextView) itemView.findViewById(R.id.tv_service);
            tvTime = (TextView) itemView.findViewById(R.id.tv_time);
        }
    }
}
