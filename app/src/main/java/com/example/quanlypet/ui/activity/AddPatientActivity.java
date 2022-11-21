package com.example.quanlypet.ui.activity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.example.quanlypet.R;
import com.example.quanlypet.database.PatientDB;
import com.example.quanlypet.model.PatientObj;
import com.google.android.material.textfield.TextInputEditText;


public class AddPatientActivity extends AppCompatActivity {
    private TextInputEditText edIdDocter;
    private TextInputEditText edIdAnimal;
    private TextInputEditText edDateCreate;
    private TextInputEditText edDateUpdate;
    private Button btnAddPatient;
    private Button btnCanel;
    private Toolbar Tbr;


    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_patient);

        edIdDocter = (TextInputEditText) findViewById(R.id.ed_idDocter);
        edIdAnimal = (TextInputEditText) findViewById(R.id.ed_idAnimal);
        edDateCreate = (TextInputEditText) findViewById(R.id.ed_dateCreate);
        edDateUpdate = (TextInputEditText) findViewById(R.id.ed_dateUpdate);
        btnAddPatient = (Button) findViewById(R.id.btn_addPatient);
        btnCanel = (Button) findViewById(R.id.btn_canel);
        Tbr = findViewById(R.id.id_tollBar);
        setSupportActionBar(Tbr);
        getSupportActionBar().setTitle("Thêm Bệnh án");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        btnAddPatient.setOnClickListener(v->{
            String idDocter = edIdDocter.getText().toString().trim();
            String idAnimal = edIdAnimal.getText().toString().trim();
            String dateCreate = edDateCreate.getText().toString().trim();
            String dateUpdate = edDateUpdate.getText().toString().trim();

            if(idAnimal.isEmpty() || idDocter.isEmpty() || dateCreate.isEmpty() || dateUpdate.isEmpty()){
                Toast.makeText(this, "Không được để trống", Toast.LENGTH_SHORT).show();
            }else{
                PatientObj patientObj = new PatientObj(Integer.parseInt(idDocter),Integer.parseInt(idAnimal),dateCreate,dateUpdate);
                PatientDB.getInstance(getApplicationContext()).Dao().insert(patientObj);
                Toast.makeText(getApplicationContext(), "Thêm thành công", Toast.LENGTH_SHORT).show();
            }
        });
        btnCanel.setOnClickListener(v->{
            edIdDocter.setText("");
            edIdAnimal.setText("");
            edDateCreate.setText("");
            edDateUpdate.setText("");
        });
    }
}