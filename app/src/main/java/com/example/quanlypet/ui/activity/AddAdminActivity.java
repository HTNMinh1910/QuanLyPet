package com.example.quanlypet.ui.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.os.Bundle;
import android.widget.Button;
import android.widget.Toast;

import com.example.quanlypet.R;
import com.example.quanlypet.adapter.ad_use.AdminAdapter;
import com.example.quanlypet.database.AdminDB;
import com.example.quanlypet.model.AdminObj;
import com.google.android.material.textfield.TextInputEditText;

import java.util.ArrayList;

public class AddAdminActivity extends AppCompatActivity {
    private ArrayList<AdminObj> list = new ArrayList<>();
    private AdminAdapter adminAdapter;

    private Toolbar idTollBar;
    private TextInputEditText edImportnameAdmin;
    private TextInputEditText edFullnameAdmin;
    private TextInputEditText edEmailAdmin;
    private TextInputEditText edPassAdmin;
    private Button tvAddAdmin;
    private Button tvCancelAddAdmin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_admin);

        idTollBar = (Toolbar) findViewById(R.id.id_tollBar);
        edImportnameAdmin = (TextInputEditText) findViewById(R.id.ed_importnameAdmin);
        edFullnameAdmin = (TextInputEditText) findViewById(R.id.ed_fullnameAdmin);
        edEmailAdmin = (TextInputEditText) findViewById(R.id.ed_emailAdmin);
        edPassAdmin = (TextInputEditText) findViewById(R.id.ed_passAdmin);
        tvAddAdmin = (Button) findViewById(R.id.tv_addAdmin);
        tvCancelAddAdmin = (Button) findViewById(R.id.tv_cancelAddAdmin);

        setSupportActionBar(idTollBar);
        getSupportActionBar().setTitle("Thêm Admin");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        //hello
        tvAddAdmin.setOnClickListener(v1 -> {
            String importName = edImportnameAdmin.getText().toString().trim();
            String fullName = edFullnameAdmin.getText().toString().trim();
            String email = edEmailAdmin.getText().toString().trim();
            String pass = edPassAdmin.getText().toString().trim();
            if (importName.isEmpty() || fullName.isEmpty() || email.isEmpty() || pass.isEmpty()) {
                Toast.makeText(getApplicationContext(), "Không được để trống!", Toast.LENGTH_SHORT).show();
            } else {
                AdminObj adminObj = new AdminObj(importName, fullName, email, pass);
                AdminDB.getInstance(getApplicationContext()).Dao().insert(adminObj);
                Toast.makeText(getApplicationContext(), "Thêm thành công!", Toast.LENGTH_SHORT).show();
            }
        });
        tvCancelAddAdmin.setOnClickListener(v->{
            edImportnameAdmin.setText("");
            edFullnameAdmin.setText("");
            edEmailAdmin.setText("");
            edPassAdmin.setText("");
        });
    }
}