package com.example.quanlypet.ui.wellcome;

import android.os.Bundle;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.quanlypet.R;
import com.example.quanlypet.database.AdminDB;
import com.example.quanlypet.database.UsersDB;
import com.example.quanlypet.model.AdminObj;
import com.example.quanlypet.model.UsersObj;
import com.google.android.material.textfield.TextInputEditText;

public class SignupUsersActivity extends AppCompatActivity {

    private TextInputEditText edUsername;
    private TextInputEditText edName;
    private TextInputEditText edEmail;
    private TextInputEditText edPhone;
    private RadioButton rdoMale;
    private RadioButton rdoFemale;
    private TextInputEditText edPassword;
    private TextInputEditText edRePassword;
    private Button btnSignup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup_users);

        edUsername = findViewById(R.id.ed_Username);
        edName = findViewById(R.id.ed_Name);
        edEmail = findViewById(R.id.ed_Email);
        edPhone = findViewById(R.id.ed_Phone);
        rdoMale = findViewById(R.id.rdo_Male);
        rdoFemale = findViewById(R.id.rdo_Female);
        edPassword = findViewById(R.id.ed_Password);
        edRePassword = findViewById(R.id.ed_RePassword);
        btnSignup = findViewById(R.id.btn_Signup);
        rdoMale.setSelected(true);

        btnSignup.setOnClickListener(view -> {
            String importName = edUsername.getText().toString().trim();
            String fullName = edName.getText().toString().trim();
            String email = edEmail.getText().toString().trim();
            String phone = edPhone.getText().toString().trim();
            int gender = 0;
            if (rdoMale.isChecked()){
                gender = 0;
            } else if (rdoFemale.isChecked()){
                gender = 1;
            }
            String password = edPassword.getText().toString().trim();
            String Repassword = edRePassword.getText().toString().trim();
            if (importName.isEmpty() || fullName.isEmpty() || email.isEmpty() ||
                    phone.isEmpty() || password.isEmpty()||Repassword.isEmpty()) {
                if (Repassword.equals(password)){
                    Toast.makeText(getApplicationContext(), "Không Khớp", Toast.LENGTH_SHORT).show();
                }
                Toast.makeText(getApplicationContext(), "Không được để trống!", Toast.LENGTH_SHORT).show();
            } else {
                UsersObj usersObj = new UsersObj(importName,fullName,email,phone,gender,password);
                UsersDB.getInstance(getApplicationContext()).Dao().insert(usersObj);
                Toast.makeText(getApplicationContext(), "Thêm thành công!", Toast.LENGTH_SHORT).show();
            }
        });
    }
}