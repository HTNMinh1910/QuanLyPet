package com.example.quanlypet.ui.activity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.example.quanlypet.ImageConverter;
import com.example.quanlypet.R;


public class InformationActivity extends AppCompatActivity {
    private TextView tvSpecializeDocter;
    private TextView tvNameDocter;
    private TextView tvPhoneDocter;
    private TextView tvAddressDocter;
    private Toolbar Tbr;
    private ImageView imgDt;


    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_information);
        tvSpecializeDocter = (TextView) findViewById(R.id.tv_specializeDocter);
        tvNameDocter = (TextView) findViewById(R.id.tv_nameDocter);
        imgDt = findViewById(R.id.img_dt);
        tvPhoneDocter = (TextView) findViewById(R.id.tv_phoneDocter);
        tvAddressDocter = (TextView) findViewById(R.id.tv_addressDocter);
        Tbr = findViewById(R.id.id_tollBar);
        setSupportActionBar(Tbr);
        getSupportActionBar().setTitle("Thông tin chi tiết");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        Intent intent = getIntent();
        byte[] byteArray = getIntent().getByteArrayExtra("img");
        Bitmap bitmap = BitmapFactory.decodeByteArray(byteArray, 0, byteArray.length);
        Bitmap circularBitmap = ImageConverter.getRoundedCornerBitmap(bitmap, 100);
        String name =  intent.getStringExtra("name");
        String phone = intent.getStringExtra("phone");
        String specialize =  intent.getStringExtra("specialize");
        String address = intent.getStringExtra("address");
        tvNameDocter.setText(name);
        tvPhoneDocter.setText(phone);
        tvAddressDocter.setText(address);
        tvSpecializeDocter.setText(specialize);
        imgDt.setImageBitmap(circularBitmap);
    }
}