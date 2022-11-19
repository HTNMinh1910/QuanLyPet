package com.example.quanlypet.ui.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.os.Bundle;
import android.widget.Button;
import android.widget.RadioButton;

import com.example.quanlypet.R;
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_information);

        idTollBar = (Toolbar) findViewById(R.id.id_tollBar);
        edUsername = (TextInputEditText) findViewById(R.id.ed_username);
        edFullnameUser = (TextInputEditText) findViewById(R.id.ed_fullnameUser);
        edEmailUsers = (TextInputEditText) findViewById(R.id.ed_emailUsers);
        edPhoneUsers = (TextInputEditText) findViewById(R.id.ed_phoneUsers);
        rdoBoy = (RadioButton) findViewById(R.id.rdo_boy);
        rdoGirl = (RadioButton) findViewById(R.id.rdo_girl);
        btnUpdateUser = (Button) findViewById(R.id.btn_updateUser);
        btnCanel = (Button) findViewById(R.id.btn_canel);


    }
}