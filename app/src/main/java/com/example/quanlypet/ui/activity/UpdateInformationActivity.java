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
    private RadioButton rdoBoy;
    private RadioButton rdoGirl;
    private Button btnUpdateUser;
    private Button btnCanel;
    private TextView tvThongbao;

    private UsersObj usersObj = new UsersObj();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_information);

        SharedPreferences sharedPreferences = getSharedPreferences("user_file",MODE_PRIVATE);
        String username = sharedPreferences.getString("Username","");


        usersObj = UsersDB.getInstance(this).Dao().getIdUsers(username);

        idTollBar = (Toolbar) findViewById(R.id.id_tollBar);
        edUsername = (TextInputEditText) findViewById(R.id.ed_username);
        edFullnameUser = (TextInputEditText) findViewById(R.id.ed_fullnameUser);
        edEmailUsers = (TextInputEditText) findViewById(R.id.ed_emailUsers);
        edPhoneUsers = (TextInputEditText) findViewById(R.id.ed_phoneUsers);
        rdoBoy = (RadioButton) findViewById(R.id.rdo_boy);
        rdoGirl = (RadioButton) findViewById(R.id.rdo_girl);
        btnUpdateUser = (Button) findViewById(R.id.btn_updateUser);
        btnCanel = (Button) findViewById(R.id.btn_canel);
        tvThongbao = (TextView) findViewById(R.id.tv_thongbao);

        tvThongbao.setVisibility(View.INVISIBLE);

        setSupportActionBar(idTollBar);
        getSupportActionBar().setTitle("Personal Information");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        edUsername.setText(username);
        edFullnameUser.setText(usersObj.getFull_name());
        edPhoneUsers.setText(usersObj.getPhone());
        edEmailUsers.setText(usersObj.getEmail());
        if (usersObj.getGender() == 0){
            rdoBoy.setChecked(true);
        } else {
            rdoGirl.setChecked(true);
        }

        btnUpdateUser.setOnClickListener(v->{
            String usernamenew = edUsername.getText().toString();
            String fullname = edFullnameUser.getText().toString().trim();
            String email = edEmailUsers.getText().toString().trim();
            String phone = edPhoneUsers.getText().toString().trim();
            int gender = rdoBoy.isChecked()?0:1;
            if (usernamenew.isEmpty()||fullname.isEmpty()||email.isEmpty()||phone.isEmpty()){
                tvThongbao.setVisibility(View.VISIBLE);
            } else {
                usersObj.setImport_name(usernamenew);
                usersObj.setFull_name(fullname);
                usersObj.setEmail(email);
                usersObj.setPhone(phone);
                usersObj.setGender(gender);
                UsersDB.getInstance(this).Dao().edit(usersObj);
                tvThongbao.setVisibility(View.INVISIBLE);
                Toast.makeText(this, "Sua thanh cong", Toast.LENGTH_SHORT).show();
                finish();
            }
        });

    }
}