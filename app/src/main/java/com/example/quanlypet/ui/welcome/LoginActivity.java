package com.example.quanlypet.ui.welcome;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.Toast;

import com.example.quanlypet.MainActivity;
import com.example.quanlypet.R;
import com.example.quanlypet.database.AdminDB;
import com.example.quanlypet.database.UsersDB;
import com.example.quanlypet.model.AdminObj;
import com.example.quanlypet.model.UsersObj;
import com.google.android.material.textfield.TextInputEditText;

public class LoginActivity extends AppCompatActivity {
    private TextInputEditText edUsername;
    private TextInputEditText edPassword;
    private CheckBox ckbNhoMK;
    private Button btnCancel;
    private Button btnLogin;
    private TextView tvDangky;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        edUsername = findViewById(R.id.ed_Username);
        edPassword = findViewById(R.id.ed_Password);
        ckbNhoMK = findViewById(R.id.ckb_nhoMK);
        btnCancel = findViewById(R.id.btn_cancel);
        btnLogin = findViewById(R.id.btn_login);
        tvDangky = findViewById(R.id.tv_dangky);
        tvDangky.setOnClickListener(v->{
            startActivity(new Intent(getApplicationContext(),SignupUsersActivity.class));
        });

        SharedPreferences preferences = getSharedPreferences("user_file", MODE_PRIVATE);
        edUsername.setText(preferences.getString("Username",""));
        edPassword.setText(preferences.getString("Password",""));
        ckbNhoMK.setChecked(preferences.getBoolean("Remember",false));
        btnCancel.setOnClickListener(view -> {
                startActivity(new Intent(getApplicationContext(), SignupUsersActivity.class));
        });
        btnLogin.setOnClickListener(view -> {
            CheckLogin();
        });
    }
    private AdminObj adminObj = new AdminObj();
    public void CheckLogin() {
        String str_user = edUsername.getText().toString().trim();
        String str_pass = edPassword.getText().toString().trim();
        if (str_user.isEmpty()||str_pass.isEmpty()) {
            Toast.makeText(this, "Không được bỏ trống !", Toast.LENGTH_SHORT).show();
        }else {
            if (AdminDB.getInstance(getApplicationContext()).Dao().checkLogin(str_user,str_pass)>0||
                    UsersDB.getInstance(getApplicationContext()).Dao().checkLogin(str_user,str_pass)>0||
                    str_user.equals("admin")&&str_pass.equals("123")){
                RemeberUser(str_user,str_pass, ckbNhoMK.isChecked());
                startActivity(new Intent(getApplicationContext(), MainActivity.class));
                Toast.makeText(this, "Đăng nhập thành công.", Toast.LENGTH_SHORT).show();
                finish();
            }else {
                Toast.makeText(this, "Tên đăng nhập hoặc mật khẩu không chính xác.", Toast.LENGTH_SHORT).show();
            }
        }
    }
    public void RemeberUser(String R_user, String R_pass, boolean status) {
        SharedPreferences preferences = getSharedPreferences("user_file", MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        if (!status) {
            editor.clear();
        }else {
            editor.putString("Username",R_user);
            editor.putString("Password",R_pass);
            editor.putBoolean("Remember",status);
        }
        editor.commit();
    }
}