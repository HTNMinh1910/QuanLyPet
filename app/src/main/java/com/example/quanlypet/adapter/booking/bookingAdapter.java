package com.example.quanlypet.adapter.booking;

import android.app.DatePickerDialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
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

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class bookingAdapter extends RecyclerView.Adapter<bookingAdapter.ViewHolder> implements Filterable {
    List<BookObj> list;
    List<BookObj> listBoock;
    Context mContext;
    private Callback callback;

    @Override
    public Filter getFilter() {
        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence constraint) {
                String search = constraint.toString();
                if (search.isEmpty()){
                    list = listBoock;
                }else{
                    ArrayList<BookObj> listbk = new ArrayList<>();
                    for (BookObj object: listBoock){
                        if (object.getTime().toLowerCase().contains(search.toLowerCase())){
                            listbk.add(object);
                        }
                    }
                    list = listbk;
                }
                FilterResults filterResults = new FilterResults();
                filterResults.values = list;
                return filterResults;
            }

            @Override
            protected void publishResults(CharSequence constraint, FilterResults results) {
                list = (ArrayList<BookObj>) results.values;
                notifyDataSetChanged();
            }
        };
    }

    public interface Callback{
        void update(BookObj bookObj, int index);
    }

    public bookingAdapter(Callback callback, Context mContext) {
        this.mContext = mContext;
        this.callback = callback;
    }

    public void setDATA(List<BookObj> list) {
        this.list = list;
        this.listBoock = list;
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
        DoctorObj doctorObj = DoctorDB.getInstance(mContext).Dao().getIdDoctor(obj.getId_doctor() + "");
        holder.nameDoctor.setText(doctorObj.getName());
        holder.tvAddress.setText(doctorObj.getAddress());
        AnimalObj animalObj = AnimalDB.getInstance(mContext).Dao().getIDAnimal(obj.getId_animal()+"");
        holder.tvNamePet.setText(animalObj.getName());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                callback.update(obj,index);
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
