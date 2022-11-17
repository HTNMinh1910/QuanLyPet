package com.example.quanlypet.ui.welcome;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.quanlypet.R;
import com.example.quanlypet.database.AdminDB;
import com.google.android.material.textfield.TextInputEditText;

public class ChangePasswordActivity extends AppCompatActivity {
    private TextInputEditText NowPass;
    private TextInputEditText newPass;
    private TextInputEditText newPassAganin;
    private ImageView btnCancel;
    private ImageView btnSave;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_password);

        NowPass = (TextInputEditText) findViewById(R.id.ed_nowPass);
        newPass = (TextInputEditText) findViewById(R.id.newPass);
        newPassAganin = (TextInputEditText) findViewById(R.id.newPassAganin);
        btnCancel = (ImageView) findViewById(R.id.btn_cancel);
        btnSave = (ImageView) findViewById(R.id.btn_save);

        btnCancel.setOnClickListener(view1 -> {
            NowPass.setText("");
            newPass.setText("");
            newPassAganin.setText("");
        });
        btnSave.setOnClickListener(view1 -> {
            SharedPreferences preferences = getSharedPreferences("user_file", MODE_PRIVATE);
            String user = preferences.getString("Username", "");
            String MKcu = NowPass.getText().toString();
            String MKmoi = newPass.getText().toString();
            String MKlai = newPassAganin.getText().toString();

            if (Validate() > 0) {
                AdminDB.getInstance(getApplicationContext()).Dao().changePass(user, MKmoi);
                Toast.makeText(getApplicationContext(), "Đổi mật khẩu thành công.", Toast.LENGTH_SHORT).show();
                NowPass.setText("");
                newPass.setText("");
                newPassAganin.setText("");
            } else {
                Toast.makeText(getApplicationContext(), "Đổi mật khẩu thất bại.", Toast.LENGTH_SHORT).show();
            }
        });
    }

    public int Validate(){
        int check = 1;
        if (NowPass.getText().toString().isEmpty() || newPass.getText().toString().isEmpty() || newPassAganin.getText().toString().isEmpty()){
            Toast.makeText(getApplicationContext(), "Không để trống !", Toast.LENGTH_SHORT).show();
            check = -1;
        }else {
            SharedPreferences preferences = getSharedPreferences("user_file",MODE_PRIVATE);
            String MKcu = preferences.getString("Password","");
            String MKmoi = newPass.getText().toString();
            String MKlai = newPassAganin.getText().toString();
            if (!MKcu.equals(NowPass.getText().toString())){
                Toast.makeText(getApplicationContext(), "Mật khẩu hiện tại không đúng !", Toast.LENGTH_SHORT).show();
                check = -1;
            }
            if (!MKmoi.equals(MKlai)){
                Toast.makeText(getApplicationContext(), "Mật khẩu mới không trùng khớp !", Toast.LENGTH_SHORT).show();
                check = -1;
            }
        }
        return check;
    }
}