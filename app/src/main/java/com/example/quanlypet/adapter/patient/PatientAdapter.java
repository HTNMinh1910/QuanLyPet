package com.example.quanlypet.adapter.patient;

import android.app.Dialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.quanlypet.R;
import com.example.quanlypet.database.PatientDB;
import com.example.quanlypet.model.PatientObj;
import com.google.android.material.textfield.TextInputEditText;

import java.text.SimpleDateFormat;
import java.util.ArrayList;


public class PatientAdapter extends RecyclerView.Adapter<PatientAdapter.PatientViewHolder> {
    private Context context;
    private ArrayList<PatientObj> list;
    private PatientObj  patientObjNew;
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
    public void setDataPatient(ArrayList<PatientObj> list){
        this.list=list;
        notifyDataSetChanged();
    }

    public PatientAdapter(Context context) {
        this.context = context;
    }
    @NonNull
    @Override
    public PatientViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_patient,parent,false);
        return new PatientViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PatientViewHolder holder, int position) {
       PatientObj patientObj = list.get(position);
        if(patientObj==null)
            return;
        holder.tvId.setText(patientObj.getId()+"");
        holder.tvIdDocter.setText(patientObj.getId_doctor()+"");
        holder.tvIdAnimal.setText(patientObj.getId_animal()+"");
        holder.tvDateCreate.setText(patientObj.getDate_create());
        holder.tvDateUpdate.setText(patientObj.getDate_update());

        holder.idRelativeLayout.setOnClickListener(v->{
            Dialog dialog = new Dialog(v.getContext(), androidx.appcompat.R.style.ThemeOverlay_AppCompat_Dialog_Alert);
            dialog.setContentView(R.layout.activity_add_patient);
            dialog.setCancelable(false);

             TextInputEditText edIdDocter = dialog.findViewById(R.id.ed_idDocter);
             TextInputEditText edIdAnimal = dialog. findViewById(R.id.ed_idAnimal);
             TextInputEditText edDateCreate = dialog. findViewById(R.id.ed_dateCreate);
            TextInputEditText edDateUpdate= dialog. findViewById(R.id.ed_dateUpdate);
             Button btnAddPatient= dialog.findViewById(R.id.btn_addPatient);
             Button btnCanel= dialog.findViewById(R.id.btn_canel);
            btnAddPatient.setText("Sửa");
            btnAddPatient.setOnClickListener(v1->{

                String idDocter = edIdDocter.getText().toString().trim();
                String idAnimal = edIdAnimal.getText().toString().trim();
                String dateCreate = edDateCreate.getText().toString().trim();
                String dateUpdate = edDateUpdate.getText().toString().trim();

                if (idDocter.isEmpty() || idAnimal.isEmpty() || dateCreate.isEmpty() || dateUpdate.isEmpty()){
                    Toast.makeText(v1.getContext(), "Không được để trống!", Toast.LENGTH_SHORT).show();
                } else {
                    patientObjNew = patientObj;
                    patientObjNew.setId_doctor(Integer.parseInt(idDocter));
                    patientObjNew.setId_animal(Integer.parseInt(idAnimal));
                    patientObjNew.setDate_create(dateCreate);
                    patientObjNew.setDate_update(dateUpdate);
                    PatientDB.getInstance(v.getContext()).Dao().edit(patientObj);
                    list = (ArrayList<PatientObj>) PatientDB.getInstance(v.getContext()).Dao().getAllData();
                    setDataPatient(list);
                    Toast.makeText(context, "Sửa thành công", Toast.LENGTH_SHORT).show();
                    dialog.dismiss();
                }
            });
            btnCanel.setOnClickListener(v1->{
                dialog.cancel();
            });
            dialog.show();
        });
    }

    @Override
    public int getItemCount() {
        return list==null?0:list.size();
    }

    public class PatientViewHolder extends RecyclerView.ViewHolder {
        private RelativeLayout idRelativeLayout;
        private ImageView imgDocter;
        private TextView tvId;
        private TextView tvIdDocter;
        private TextView tvIdAnimal;
        private TextView tvDateCreate;
        private TextView tvDateUpdate;
        public PatientViewHolder(@NonNull View itemView) {
            super(itemView);

            idRelativeLayout = (RelativeLayout) itemView.findViewById(R.id.id_relativeLayout);
            imgDocter = (ImageView) itemView.findViewById(R.id.img_doctor);
            tvId = (TextView) itemView.findViewById(R.id.tv_id);
            tvIdDocter = (TextView) itemView.findViewById(R.id.tv_idDocter);
            tvIdAnimal = (TextView) itemView.findViewById(R.id.tv_idAnimal);
            tvDateCreate = (TextView) itemView.findViewById(R.id.tv_dateCreate);
            tvDateUpdate = (TextView) itemView.findViewById(R.id.tv_dateUpdate);

        }
    }
}
