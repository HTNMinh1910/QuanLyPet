package com.example.quanlypet.ui.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import com.example.quanlypet.R;
import com.example.quanlypet.database.UsersDB;
import com.example.quanlypet.model.UsersObj;

public class DetailUsersActivity extends AppCompatActivity{
    private Toolbar idTollBar;
    private TextView edImportnameUsers;
    private TextView edFullnameUsers;
    private TextView edEmailUsers;
    private TextView edPhoneUsers;
    private TextView edGenderUsers;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_users);

        idTollBar = findViewById(R.id.id_tollBar);
        edImportnameUsers = findViewById(R.id.ed_importnameUsers);
        edFullnameUsers = findViewById(R.id.ed_fullnameUsers);
        edEmailUsers = findViewById(R.id.ed_emailUsers);
        edPhoneUsers = findViewById(R.id.ed_phoneUsers);
        edGenderUsers = findViewById(R.id.ed_genderUsers);

        setSupportActionBar(idTollBar);
        getSupportActionBar().setTitle("Personal Information");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        LoadData();
    }
    public void LoadData(){
        Intent intent = getIntent();
        int id = intent.getIntExtra("id",0);
        UsersObj usersObj = UsersDB.getInstance(getApplicationContext()).Dao().getID(id);
        String import_name = usersObj.getImport_name();
        String phone = usersObj.getPhone();
        String email = usersObj.getEmail();
        String name = usersObj.getFull_name();
        int gender = usersObj.getGender();
        edImportnameUsers.setText(import_name);
        edFullnameUsers.setText(name);
        edEmailUsers.setText(email);
        edPhoneUsers.setText(phone);
        if (gender == 0){
            edGenderUsers.setText("Male");
        } else {
            edGenderUsers.setText("Female");
        }
    }
    @Override
    protected void onResume() {
        super.onResume();      
        LoadData();
    }
}