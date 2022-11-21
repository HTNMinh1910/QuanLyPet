package com.example.quanlypet.ui.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.quanlypet.R;
import com.example.quanlypet.database.AdminDB;
import com.example.quanlypet.database.UsersDB;
import com.example.quanlypet.model.AdminObj;
import com.example.quanlypet.model.UsersObj;
import com.google.android.material.textfield.TextInputEditText;

import java.util.ArrayList;

public class InformationUsersActivity extends AppCompatActivity{
    private Toolbar idTollBar;
    private TextView edImportnameUsers;
    private TextView edFullnameUsers;
    private TextView edEmailUsers;
    private TextView edPhoneUsers;
    private TextView edGenderUsers;
    private Button btnEdit;
    private UsersObj usersObj = new UsersObj();
    private AdminObj adminObj = new AdminObj();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_information_users);

        idTollBar = (Toolbar) findViewById(R.id.id_tollBar);
        edImportnameUsers = (TextView) findViewById(R.id.ed_importnameUsers);
        edFullnameUsers = (TextView) findViewById(R.id.ed_fullnameUsers);
        edEmailUsers = (TextView) findViewById(R.id.ed_emailUsers);
        edPhoneUsers = (TextView) findViewById(R.id.ed_phoneUsers);
        edGenderUsers = (TextView) findViewById(R.id.ed_genderUsers);
        btnEdit = (Button) findViewById(R.id.btn_edit);

        setSupportActionBar(idTollBar);
        getSupportActionBar().setTitle("Personal Information");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        LoadData();
        btnEdit.setOnClickListener(v->{
            startActivity(new Intent(getApplicationContext(), UpdateInformationActivity.class));
        });
    }
    public void LoadData(){
        SharedPreferences sharedPreferences = getSharedPreferences("user_file",MODE_PRIVATE);
        String username = sharedPreferences.getString("Username","");
        if (username.equals("Admin")){
            adminObj = AdminDB.getInstance(getApplicationContext()).Dao().getIdAdmin(username);
            String email = adminObj.getEmail();
            String name = adminObj.getFull_name();
            edImportnameUsers.setText(username);
            edFullnameUsers.setText(name);
            edEmailUsers.setText(email);
            edGenderUsers.setText("null");
            edPhoneUsers.setText("null");
        }else {
            usersObj = UsersDB.getInstance(getApplicationContext()).Dao().getIdUsers(username);
            String phone = usersObj.getPhone();
            String email = usersObj.getEmail();
            String name = usersObj.getFull_name();
            int gender = usersObj.getGender();

            edImportnameUsers.setText(username);
            edFullnameUsers.setText(name);
            edEmailUsers.setText(email);
            edPhoneUsers.setText(phone);
            if (gender == 0){
                edGenderUsers.setText("Male");
            } else {
                edGenderUsers.setText("Female");
            }
        }
    }
    @Override
    protected void onResume() {
        super.onResume();      
        LoadData();
    }
}