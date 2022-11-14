package com.example.quanlypet.adapter.doctor;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.quanlypet.R;
import com.example.quanlypet.database.DoctorDB;
import com.example.quanlypet.model.DoctorObj;
import com.example.quanlypet.ui.activity.InformationActivity;
import com.google.android.material.textfield.TextInputEditText;

import java.util.ArrayList;


public class DoctorAdapter extends RecyclerView.Adapter<DoctorAdapter.DocterViewHolder> {
    private Context context;
    private ArrayList<DoctorObj> list;
    private int checkGender;
    private DoctorObj docterObjNew;
    public void setDataDocter(ArrayList<DoctorObj> list){
        this.list=list;
        notifyDataSetChanged();
    }

    public DoctorAdapter(Context context) {
        this.context = context;
    }

    @NonNull
    @Override
    public DocterViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_docter,parent,false);
        return new DocterViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull DocterViewHolder holder, int position) {
        DoctorObj docterObj = list.get(position);
        if(docterObj==null)
            return;
        holder.tv_Id.setText(docterObj.getId()+"");
        holder.tv_Name.setText(docterObj.getName());
        holder.tv_Phone.setText(docterObj.getPhone());
        holder.tv_Email.setText(docterObj.getEmail());

        if(docterObj.getGender()==1){
            holder.tv_Gender.setText("Nam");
        }else{
            holder.tv_Gender.setText("Nữ");
        }
        holder.tv_Specialize.setText(docterObj.getSpecialize());

        holder.id_RelativeLayout.setOnClickListener(v->{
            Dialog dialog = new Dialog(v.getContext(), androidx.appcompat.R.style.ThemeOverlay_AppCompat_Dialog_Alert);
            dialog.setContentView(R.layout.activity_add_docter);
            dialog.setCancelable(false);
            TextInputEditText ed_NameDocter = dialog.findViewById(R.id.ed_nameDocter);
            TextInputEditText ed_PhoneDocter =  dialog.findViewById(R.id.ed_phoneDocter);
            TextInputEditText ed_EmailDocter =  dialog.findViewById(R.id.ed_emailDocter);
            RadioButton rdo_Boy =  dialog.findViewById(R.id.rdo_boy);
            TextInputEditText ed_SpecializeDocter =  dialog.findViewById(R.id.ed_specializeDocter);
            Button btn_AddDocter = (Button) dialog.findViewById(R.id.btn_addDocter);
            Button btn_Canel = (Button) dialog.findViewById(R.id.btn_canel);
            btn_AddDocter.setText("Sửa");
            btn_AddDocter.setOnClickListener(v1->{

                String name = ed_NameDocter.getText().toString().trim();
                String phone = ed_PhoneDocter.getText().toString().trim();
                String email = ed_EmailDocter.getText().toString().trim();
                String specialize = ed_SpecializeDocter.getText().toString().trim();

                checkGender = (rdo_Boy.isChecked()==true)?1:0;

                if (name.isEmpty() || phone.isEmpty() || email.isEmpty() || specialize.isEmpty()){
                    Toast.makeText(v1.getContext(), "Không được để trống!", Toast.LENGTH_SHORT).show();
                } else {
                    docterObjNew = docterObj;
                    docterObjNew.setName(name);
                    docterObjNew.setPhone(phone);
                    docterObjNew.setEmail(email);
                    docterObjNew.setSpecialize(specialize);
                    docterObjNew.setGender(checkGender);
                    DoctorDB.getInstance(v.getContext()).docterDao().edit(docterObj);
                    list = (ArrayList<DoctorObj>) com.example.quanlypet.database.DoctorDB.getInstance(v.getContext()).docterDao().getAllData();
                    setDataDocter(list);
                    Toast.makeText(context, "Sửa thành công", Toast.LENGTH_SHORT).show();
                    dialog.dismiss();
                }
            });
            btn_Canel.setOnClickListener(v1->{
                dialog.cancel();
            });
            dialog.show();
        });
        holder.img_Information.setOnClickListener(v->{
            Intent intent = new Intent(context, InformationActivity.class);
            intent.putExtra("name",docterObj.getName());
            intent.putExtra("phone",docterObj.getPhone());
            intent.putExtra("address",docterObj.getAddress());
            intent.putExtra("specialize",docterObj.getSpecialize());
            context.startActivity(intent);
        });

    }

    @Override
    public int getItemCount() {
        return list==null?0:list.size();
    }

    public class DocterViewHolder extends RecyclerView.ViewHolder {
        private ImageView img_Docter;
        private TextView tv_Id;
        private TextView tv_Name;
        private TextView tv_Phone;
        private TextView tv_Email;
        private TextView tv_Gender;
        private TextView tv_Specialize;
        private RelativeLayout id_RelativeLayout;
        private ImageView img_Information;



        public DocterViewHolder(@NonNull View itemView) {
            super(itemView);
            id_RelativeLayout = (RelativeLayout) itemView.findViewById(R.id.id_relativeLayout);
            img_Docter = (ImageView) itemView.findViewById(R.id.img_docter);
            img_Information = (ImageView) itemView.findViewById(R.id.img_information);
            tv_Id = (TextView) itemView.findViewById(R.id.tv_id);
            tv_Name = (TextView) itemView.findViewById(R.id.tv_name);
            tv_Phone = (TextView) itemView.findViewById(R.id.tv_phone);
            tv_Email = (TextView) itemView.findViewById(R.id.tv_email);
            tv_Gender = (TextView) itemView.findViewById(R.id.tv_gender);
            tv_Specialize = (TextView) itemView.findViewById(R.id.tv_specialize);
        }
    }
}
