package com.example.quanlypet.ui.activity;

import android.annotation.SuppressLint;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.content.res.AppCompatResources;
import androidx.appcompat.widget.SearchView;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.quanlypet.R;
import com.example.quanlypet.adapter.doctor.ListDoctorAdapter;
import com.example.quanlypet.adapter.doctor.DoctorAdapter;
import com.example.quanlypet.database.DoctorDB;
import com.example.quanlypet.model.DoctorObj;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;

public class ListDoctorActivity extends AppCompatActivity {
    private RecyclerView rcvDanhsachDoctor;
    private ArrayList<DoctorObj> list= new ArrayList<>();
    private ListDoctorAdapter listDoctorAdapter;
    private Toolbar idTollBar;
    private SearchView searchDanhsachDoctor;
    private DoctorAdapter adapter;
    @SuppressLint("WrongThread")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_doctor);
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
        listDoctorAdapter = new ListDoctorAdapter(getBaseContext());
        adapter = new DoctorAdapter(getBaseContext(),null);
        list = (ArrayList<DoctorObj>) DoctorDB.getInstance(getBaseContext()).Dao().getAllData();
        listDoctorAdapter.setDataDanhSach(list);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getBaseContext(), LinearLayoutManager.VERTICAL, false);
        rcvDanhsachDoctor.setLayoutManager(layoutManager);
        rcvDanhsachDoctor.setAdapter(listDoctorAdapter);
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