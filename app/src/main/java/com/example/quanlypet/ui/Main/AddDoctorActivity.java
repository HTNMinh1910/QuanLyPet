package com.example.quanlypet.ui.Main;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.example.quanlypet.R;
import com.example.quanlypet.database.DoctorDB;
import com.example.quanlypet.model.DoctorObj;
import com.google.android.material.textfield.TextInputEditText;


public class AddDoctorActivity extends AppCompatActivity {
    private TextInputEditText edNameDocter;
    private TextInputEditText edPhoneDocter;
    private RadioButton rdoBoy;
    private RadioButton rdoGirl;
    private TextInputEditText edEmailDocter;
    private TextInputEditText edAddressDocter;
    private TextInputEditText edSpecializeDocter;
    private Button btnAddDocter;
    private Button btnCanel;
    private int checkGender;
    private Toolbar Tbr;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_docter);
        edNameDocter = (TextInputEditText) findViewById(R.id.ed_nameDocter);
        edPhoneDocter = (TextInputEditText) findViewById(R.id.ed_phoneDocter);
        rdoBoy = (RadioButton) findViewById(R.id.rdo_boy);
        rdoGirl = (RadioButton) findViewById(R.id.rdo_girl);
        edEmailDocter = (TextInputEditText) findViewById(R.id.ed_emailDocter);
        edAddressDocter = (TextInputEditText) findViewById(R.id.ed_addressDocter);
        edSpecializeDocter = (TextInputEditText) findViewById(R.id.ed_specializeDocter);
        btnAddDocter = (Button) findViewById(R.id.btn_addDocter);
        btnCanel = (Button) findViewById(R.id.btn_canel);
        Tbr = findViewById(R.id.id_tollBar);
        setSupportActionBar(Tbr);
        getSupportActionBar().setTitle("Thêm Bác sĩ");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        btnAddDocter.setOnClickListener(v->{
            String name = edNameDocter.getText().toString().trim();
            String phone = edPhoneDocter.getText().toString().trim();
            String email = edEmailDocter.getText().toString().trim();
            String address = edAddressDocter.getText().toString().trim();
            String specialize = edSpecializeDocter.getText().toString().trim();
             checkGender = rdoBoy.isChecked()?1:0;
            if(name.isEmpty() || phone.isEmpty() || email.isEmpty() || address.isEmpty() || specialize.isEmpty()){
                Toast.makeText(this, "Không được để trống", Toast.LENGTH_SHORT).show();
            }else{
                DoctorObj docterObj = new DoctorObj(name,phone,email,address,checkGender,specialize);
                DoctorDB.getInstance(getApplicationContext()).docterDao().insert(docterObj);
                Toast.makeText(getApplicationContext(), "Thêm thành công", Toast.LENGTH_SHORT).show();
            }
        });
        btnCanel.setOnClickListener(v->{
            edNameDocter.setText("");
            edPhoneDocter.setText("");
            edEmailDocter.setText("");
            edAddressDocter.setText("");
            edSpecializeDocter.setText("");
        });

    }
}