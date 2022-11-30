package com.example.quanlypet.adapter.doctor;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.quanlypet.R;
import com.example.quanlypet.model.DoctorObj;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;

public class ListDoctorAdapter extends RecyclerView.Adapter<ListDoctorAdapter.DocterViewHolder> implements Filterable {
    private Context context;
    private ArrayList<DoctorObj> list;
    private ArrayList<DoctorObj> listDotor;

    public void setDataDanhSach(ArrayList<DoctorObj> list){
        this.list=list;
        notifyDataSetChanged();
    }

    public ListDoctorAdapter(Context context) {
        this.context = context;
    }
    @Override
    public Filter getFilter() {
        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence constraint) {
                String search = constraint.toString();
                if (search.isEmpty()){
                    list = listDotor;
                }else{
                    ArrayList<DoctorObj> listdt = new ArrayList<>();
                    for (DoctorObj object: listDotor){
                        if (object.getName().toLowerCase().contains(search.toLowerCase())||
                                object.getEmail().toLowerCase().contains(search.toLowerCase())||
                                object.getPhone().toLowerCase().contains(search.toLowerCase())||
                                object.getSpecialize().toLowerCase().contains(search.toLowerCase())){
                            listdt.add(object);
                        }
                    }
                    list = listdt;
                }
                FilterResults filterResults = new FilterResults();
                filterResults.values = list;
                return filterResults;
            }
            @Override
            protected void publishResults(CharSequence constraint, FilterResults results) {
                list = (ArrayList<DoctorObj>) results.values;
                notifyDataSetChanged();
            }
        };
    }
    @NonNull
    @Override
    public DocterViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_emergency,parent,false);
        return new ListDoctorAdapter.DocterViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull DocterViewHolder holder, int position) {
        DoctorObj docterObj = list.get(position);
        if(docterObj==null)
            return;
        holder.tvName.setText(docterObj.getName());
        byte[] hinhanh = docterObj.getImg();
        Bitmap bitmap = BitmapFactory.decodeByteArray(hinhanh, 0, hinhanh.length);
        holder.imgDoctor.setImageBitmap(bitmap);
        holder.idRelativeLayout.setOnClickListener(v->{
            Dialog dialog = new Dialog(v.getContext());
            dialog.setContentView(R.layout.dialog_call_phone);

            Button btnCall = (Button) dialog.findViewById(R.id.btn_call);
            Button btnCancel = (Button) dialog.findViewById(R.id.btn_cancel);
            dialog.getWindow().setBackgroundDrawable(context.getDrawable(R.drawable.bg_dialog_call));
            Window window = dialog.getWindow();
            window.setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.WRAP_CONTENT);
            WindowManager.LayoutParams windowAttributes = window.getAttributes();
            window.setAttributes(windowAttributes);
            windowAttributes.gravity = Gravity.BOTTOM;
            String phone1 = "0999999999";
            btnCall.setText(phone1);
            btnCall.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent sharingIntent = new Intent(Intent.ACTION_CALL,Uri.parse("tel: "+phone1));
                    sharingIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    context.startActivity( sharingIntent);
                }
            });
            btnCancel.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    dialog.dismiss();
                }
            });
            dialog.show();
        });
    }

    @Override
    public int getItemCount() {
        return list==null?0:list.size();
    }

    public class DocterViewHolder extends RecyclerView.ViewHolder {

        private CircleImageView imgDoctor;
        private TextView tvName;
        private ImageView imgInformation;
        private RelativeLayout idRelativeLayout;


        public DocterViewHolder(@NonNull View itemView) {
            super(itemView);
            idRelativeLayout = (RelativeLayout) itemView.findViewById(R.id.id_relativeLayout);

            imgDoctor = (CircleImageView) itemView.findViewById(R.id.img_doctor);
            tvName = (TextView) itemView.findViewById(R.id.tv_name);
            imgInformation = (ImageView) itemView.findViewById(R.id.img_information);

        }
    }
}
