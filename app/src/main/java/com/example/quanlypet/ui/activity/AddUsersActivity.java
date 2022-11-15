package com.example.quanlypet.ui.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.os.Bundle;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.Toast;

import com.example.quanlypet.R;
import com.example.quanlypet.adapter.ad_use.UsersAdapter;
import com.example.quanlypet.database.UsersDB;
import com.example.quanlypet.model.UsersObj;
import com.google.android.material.textfield.TextInputEditText;

import java.util.ArrayList;

public class AddUsersActivity extends AppCompatActivity {
    private UsersAdapter usersAdapter;
    private ArrayList<UsersObj> list;

    private Toolbar idTollBar;
    private TextInputEditText edImportnameUsers;
    private TextInputEditText edFullnameUsers;
    private TextInputEditText edEmailUsers;
    private TextInputEditText edPhoneUsers;
    private TextInputEditText edPassUsers;
    private RadioButton rdoMaleAdd;
    private RadioButton rdoFemaleAdd;
    private Button tvAddUsers;
    private Button tvCancelAddUsers;

    private int gender;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_users);

        idTollBar = (Toolbar) findViewById(R.id.id_tollBar);
        edImportnameUsers = (TextInputEditText) findViewById(R.id.ed_importnameUsers);
        edFullnameUsers = (TextInputEditText) findViewById(R.id.ed_fullnameUsers);
        edEmailUsers = (TextInputEditText) findViewById(R.id.ed_emailUsers);
        edPhoneUsers = (TextInputEditText) findViewById(R.id.ed_phoneUsers);
        edPassUsers = (TextInputEditText) findViewById(R.id.ed_passUsers);
        rdoMaleAdd = (RadioButton) findViewById(R.id.rdo_maleAdd);
        rdoFemaleAdd = (RadioButton) findViewById(R.id.rdo_femaleAdd);
        tvAddUsers = (Button) findViewById(R.id.tv_addUsers);
        tvCancelAddUsers = (Button) findViewById(R.id.tv_cancelAddUsers);

        setSupportActionBar(idTollBar);
        getSupportActionBar().setTitle("Thêm Users");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        tvAddUsers.setOnClickListener(v->{
            String importName = edImportnameUsers.getText().toString().trim();
            String fullName = edFullnameUsers.getText().toString().trim();
            String email = edEmailUsers.getText().toString().trim();
            String phone = edPhoneUsers.getText().toString().trim();
            String pass = edPassUsers.getText().toString().trim();
            gender = rdoMaleAdd.isChecked()?0:1;

            if (importName.isEmpty() || fullName.isEmpty() || email.isEmpty() || phone.isEmpty() || pass.isEmpty()){
                Toast.makeText(this, "Không được để trống", Toast.LENGTH_SHORT).show();
            } else {
                UsersObj usersObj = new UsersObj(importName,fullName,email,phone,gender,pass);
                UsersDB.getInstance(getApplicationContext()).Dao().insert(usersObj);
                Toast.makeText(getApplicationContext(), "Thêm thành công", Toast.LENGTH_SHORT).show();
            }
        });
        tvCancelAddUsers.setOnClickListener(v->{
            edImportnameUsers.setText("");
            edFullnameUsers.setText("");
            edEmailUsers.setText("");
            edPhoneUsers.setText("");
            edPassUsers.setText("");
        });
    }
}