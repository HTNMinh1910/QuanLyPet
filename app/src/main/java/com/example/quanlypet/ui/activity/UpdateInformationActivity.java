package com.example.quanlypet.ui.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.quanlypet.R;
import com.example.quanlypet.database.UsersDB;
import com.example.quanlypet.model.UsersObj;
import com.google.android.material.textfield.TextInputEditText;

public class UpdateInformationActivity extends AppCompatActivity {
    private Toolbar idTollBar;
    private TextInputEditText edUsername;
    private TextInputEditText edFullnameUser;
    private TextInputEditText edEmailUsers;
    private TextInputEditText edPhoneUsers;
    private TextInputEditText edGenderUsers;
    private Button btnUpdateUser;
    private TextView tvThongbao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_information);

        SharedPreferences sharedPreferences = getSharedPreferences("Users_info",MODE_PRIVATE);
        String username = sharedPreferences.getString("Username","");


        UsersObj usersObj = UsersDB.getInstance(this).Dao().getIdUsers(username);

        idTollBar = findViewById(R.id.id_tollBar);
        edUsername = findViewById(R.id.ed_username);
        edFullnameUser = findViewById(R.id.ed_fullnameUser);
        edEmailUsers = findViewById(R.id.ed_emailUsers);
        edPhoneUsers = findViewById(R.id.ed_phoneUsers);
        edGenderUsers = findViewById(R.id.ed_genderUsers);
        btnUpdateUser = findViewById(R.id.btn_updateUser);
        tvThongbao = findViewById(R.id.tv_thongbao);

        tvThongbao.setVisibility(View.INVISIBLE);

        setSupportActionBar(idTollBar);
        getSupportActionBar().setTitle("Personal Information");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        edUsername.setText(username);
        edFullnameUser.setText(usersObj.getFull_name());
        edPhoneUsers.setText(usersObj.getPhone());
        edEmailUsers.setText(usersObj.getEmail());
        if (usersObj.getGender() == 0){
            edGenderUsers.setText("Male");
        } else {
            edGenderUsers.setText("Female");
        }

        btnUpdateUser.setOnClickListener(v->{
            String usernamenew = edUsername.getText().toString();
            String fullname = edFullnameUser.getText().toString().trim();
            String email = edEmailUsers.getText().toString().trim();
            String phone = edPhoneUsers.getText().toString().trim();
            int gender = edGenderUsers.getText().toString().trim().equals("Male")?0:1;
            if (usernamenew.isEmpty()||fullname.isEmpty()||email.isEmpty()||phone.isEmpty()){
                tvThongbao.setVisibility(View.VISIBLE);
            } else {
                usersObj.setImport_name(usernamenew);
                usersObj.setFull_name(fullname);
                usersObj.setEmail(email);
                usersObj.setPhone(phone);
                usersObj.setGender(gender);
                UsersDB.getInstance(this).Dao().edit(usersObj);
                tvThongbao.setText("Update successfully");
                Toast.makeText(this, "Update successfully", Toast.LENGTH_SHORT).show();
                finish();
            }
        });

    }
}