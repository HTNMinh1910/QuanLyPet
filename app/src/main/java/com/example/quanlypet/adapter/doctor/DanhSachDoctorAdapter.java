package com.example.quanlypet.adapter.doctor;

import static com.example.quanlypet.R.drawable.bg_dialog_call;
import static com.example.quanlypet.R.drawable.doctor;

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
import com.example.quanlypet.model.DSDoctorObj;
import com.example.quanlypet.model.DoctorObj;
import com.example.quanlypet.ui.activity.DanhSachDoctor;
import com.example.quanlypet.ui.activity.DoctorInforActivity;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;

public class DanhSachDoctorAdapter extends RecyclerView.Adapter<DanhSachDoctorAdapter.DSDocterViewHolder> {
    private Context context;
    private ArrayList<DSDoctorObj> listDS;


    public DanhSachDoctorAdapter(Context context) {
        this.context = context;
    }

    public void  setDataDS(ArrayList<DSDoctorObj> listDS) {
        this.listDS=listDS;
        notifyDataSetChanged();
    }
    @NonNull
    @Override
    public DSDocterViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_danhsachdocter,parent,false);
        return new DSDocterViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull DSDocterViewHolder holder, int position) {
        DSDoctorObj dsDoctorObj = listDS.get(position);
        if(dsDoctorObj==null) {
            return;
        }
        holder.tvName.setText(dsDoctorObj.getName());
        holder.imgDoctor.setTag(dsDoctorObj.getImg());

        holder.idRelativeLayout.setOnClickListener(v->{
            Dialog dialog = new Dialog(v.getContext());
            dialog.setContentView(R.layout.dialog_callphone);
            Button btnCall = (Button) dialog.findViewById(R.id.btn_call);
            Button btnCancel = (Button) dialog.findViewById(R.id.btn_cancel);

            Window window = dialog.getWindow();
            dialog.getWindow().setBackgroundDrawableResource(bg_dialog_call);
            window.setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.WRAP_CONTENT);
            WindowManager.LayoutParams windowAttributes = window.getAttributes();
            window.setAttributes(windowAttributes);
            windowAttributes.gravity = Gravity.BOTTOM;
            btnCall.setText(dsDoctorObj.getPhone());
            btnCall.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent sharingIntent = new Intent(Intent.ACTION_CALL,Uri.parse("tel: "+dsDoctorObj.getPhone()));
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
        return listDS==null?0:listDS.size();
    }

    public class DSDocterViewHolder extends RecyclerView.ViewHolder {

        private CircleImageView imgDoctor;
        private TextView tvName;
        private ImageView imgInformation;
        private RelativeLayout idRelativeLayout;


        public DSDocterViewHolder(@NonNull View itemView) {
            super(itemView);
            idRelativeLayout = (RelativeLayout) itemView.findViewById(R.id.id_relativeLayout);

            imgDoctor = (CircleImageView) itemView.findViewById(R.id.img_doctor);
            tvName = (TextView) itemView.findViewById(R.id.tv_name);
            imgInformation = (ImageView) itemView.findViewById(R.id.img_information);

        }
    }
}
