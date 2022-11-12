package com.example.quanlypet.ui.wellcome;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.RadioButton;

import com.example.quanlypet.R;
import com.google.android.material.textfield.TextInputEditText;

public class SignupActivity extends AppCompatActivity {

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
        setContentView(R.layout.activity_signup);

        edUsername = findViewById(R.id.ed_Username);
        edName = findViewById(R.id.ed_Name);
        edEmail = findViewById(R.id.ed_Email);
        edPhone = findViewById(R.id.ed_Phone);
        rdoMale = findViewById(R.id.rdo_Male);
        rdoFemale = findViewById(R.id.rdo_Female);
        edPassword = findViewById(R.id.ed_Password);
        edRePassword = findViewById(R.id.ed_RePassword);
        btnSignup = findViewById(R.id.btn_Signup);
    }
}