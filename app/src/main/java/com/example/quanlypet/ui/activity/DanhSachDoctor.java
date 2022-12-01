package com.example.quanlypet.ui.activity;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.appcompat.widget.Toolbar;
import androidx.core.app.ActivityCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.quanlypet.R;
import com.example.quanlypet.adapter.doctor.DanhSachDoctorAdapter;
import com.example.quanlypet.adapter.doctor.DoctorAdapter;
import com.example.quanlypet.database.DoctorDB;
import com.example.quanlypet.model.DSDoctorObj;
import com.example.quanlypet.model.DoctorObj;

import java.util.ArrayList;

public class DanhSachDoctor extends AppCompatActivity {
    private RecyclerView rcvDanhsachDoctor;
    private RecyclerView rcvDoctor;
    private ArrayList<DoctorObj> list = new ArrayList<>();
    private ArrayList<DSDoctorObj> list1 = new ArrayList<>();
    private DanhSachDoctorAdapter danhSachDoctorAdapter;
    private Toolbar idTollBar;
    private DoctorAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_danh_sach_doctor);
        phanQuyen();
        rcvDoctor = (RecyclerView) findViewById(R.id.rcv_Doctor);

        idTollBar = (Toolbar) findViewById(R.id.id_tollBar);
        setSupportActionBar(idTollBar);
        getSupportActionBar().setTitle("Thông tin bác sĩ");
        adapter = new DoctorAdapter(getBaseContext(), null);
        list = (ArrayList<DoctorObj>) DoctorDB.getInstance(getBaseContext()).Dao().getAllData();
        adapter.setDataDocter(list);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getBaseContext(), LinearLayoutManager.VERTICAL, false);
        rcvDoctor.setLayoutManager(layoutManager);
        rcvDoctor.setAdapter(adapter);

        rcvDanhsachDoctor = (RecyclerView) findViewById(R.id.rcv_danhsachDoctor);
        getDS();
        danhSachDoctorAdapter = new DanhSachDoctorAdapter(getBaseContext());
        danhSachDoctorAdapter.setDataDS(list1);
        LinearLayoutManager layoutManager1 = new LinearLayoutManager(getBaseContext(), LinearLayoutManager.VERTICAL, false);
        rcvDanhsachDoctor.setLayoutManager(layoutManager1);
        rcvDanhsachDoctor.setAdapter(danhSachDoctorAdapter);

    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_huy_docter, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.error:
                finish();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    public void getDS() {
        list1.add(new DSDoctorObj("Hệ thống hỗ trợ", R.drawable.android, "0999999999"));
    }
    public boolean phanQuyen() {
        if (Build.VERSION.SDK_INT > 23) {
            if (checkSelfPermission(Manifest.permission.CAMERA) == PackageManager.PERMISSION_GRANTED
                    && checkSelfPermission(Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED
                    && checkSelfPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED
                    && checkSelfPermission(Manifest.permission.INTERNET) == PackageManager.PERMISSION_GRANTED
                    && checkSelfPermission(Manifest.permission.CALL_PHONE) == PackageManager.PERMISSION_GRANTED) {
                return true;
            }else {
                ActivityCompat.requestPermissions(this, new String[]{
                        Manifest.permission.CAMERA,
                        Manifest.permission.READ_EXTERNAL_STORAGE,
                        Manifest.permission.WRITE_EXTERNAL_STORAGE,
                        Manifest.permission.INTERNET,
                        Manifest.permission.CALL_PHONE
                }, 1);
                return false;
            }
        } else {
            return true;
        }
    }
}