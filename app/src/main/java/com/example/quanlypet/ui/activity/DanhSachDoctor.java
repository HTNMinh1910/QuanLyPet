package com.example.quanlypet.ui.activity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.media.Image;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Filter;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.content.res.AppCompatResources;
import androidx.appcompat.widget.SearchView;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.quanlypet.ImageConverter;
import com.example.quanlypet.R;
import com.example.quanlypet.adapter.doctor.DanhSachDoctorAdapter;
import com.example.quanlypet.adapter.doctor.DoctorAdapter;
import com.example.quanlypet.database.AdminDB;
import com.example.quanlypet.database.DoctorDB;
import com.example.quanlypet.model.AdminObj;
import com.example.quanlypet.model.DoctorObj;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;

public class DanhSachDoctor extends AppCompatActivity {
    private RecyclerView rcvDanhsachDoctor;
    private ArrayList<DoctorObj> list= new ArrayList<>();
    private DanhSachDoctorAdapter danhSachDoctorAdapter;
    private Toolbar idTollBar;
    private SearchView searchDanhsachDoctor;

    @SuppressLint({"MissingInflatedId", "WrongThread"})
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_danh_sach_doctor);
        rcvDanhsachDoctor = (RecyclerView) findViewById(R.id.rcv_danhsachDoctor);
        idTollBar = (Toolbar) findViewById(R.id.id_tollBar);
        searchDanhsachDoctor = (SearchView) findViewById(R.id.search_danhsachDoctor);
        setSupportActionBar(idTollBar);
        getSupportActionBar().setTitle("Thông tin bác sĩ");
        BitmapDrawable bitmapDrawableup = (BitmapDrawable) AppCompatResources.getDrawable(this,R.drawable.doctor);
        Bitmap bitmap = bitmapDrawableup.getBitmap();
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, byteArrayOutputStream);
        byte[] hinhanh = byteArrayOutputStream.toByteArray();
        if (DoctorDB.getInstance(getApplicationContext()).Dao().getAllData().isEmpty()){
            DoctorDB.getInstance(getApplicationContext()).Dao().
                    insert(new DoctorObj("Hệ  thống hỗ trợ",hinhanh,"0999999999",null,null,2,null));
        }
        danhSachDoctorAdapter = new DanhSachDoctorAdapter(getBaseContext());
        adapter = new DoctorAdapter(getBaseContext(),null);
        list = (ArrayList<DoctorObj>) DoctorDB.getInstance(getBaseContext()).Dao().getAllData();
        danhSachDoctorAdapter.setDataDanhSach(list);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getBaseContext(), LinearLayoutManager.VERTICAL, false);
        rcvDanhsachDoctor.setLayoutManager(layoutManager);
        rcvDanhsachDoctor.setAdapter(danhSachDoctorAdapter);
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_huy_docter, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.error:
                finish();
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}