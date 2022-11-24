package com.example.quanlypet.ui.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.example.quanlypet.MainActivity;
import com.example.quanlypet.R;
import com.example.quanlypet.adapter.doctor.DoctorAdapter;
import com.example.quanlypet.database.DoctorDB;
import com.example.quanlypet.model.DoctorObj;

import java.util.ArrayList;

public class DanhSachDoctor extends AppCompatActivity {
    private RecyclerView rcvDanhsachDoctor;
    private ArrayList<DoctorObj> list= new ArrayList<>();
    private DoctorAdapter adapter;
    private Toolbar idTollBar;
    private SearchView searchNamedotor;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_danh_sach_doctor);
        rcvDanhsachDoctor = (RecyclerView) findViewById(R.id.rcv_danhsachDoctor);
        idTollBar = (Toolbar) findViewById(R.id.id_tollBar);
        setSupportActionBar(idTollBar);
        getSupportActionBar().setTitle("Thông tin bác sĩ");


        searchNamedotor = (SearchView) findViewById(R.id.search_namedotor);
        searchNamedotor.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                adapter.getFilter().filter(query);
                return false;
            }
            @Override
            public boolean onQueryTextChange(String newText) {
                adapter.getFilter().filter(newText);
                return false;
            }
        });

        adapter = new DoctorAdapter(getBaseContext(),null);
        list = (ArrayList<DoctorObj>) DoctorDB.getInstance(getBaseContext()).Dao().getAllData();
        adapter.setDataDocter(list);


        LinearLayoutManager layoutManager = new LinearLayoutManager(getBaseContext(), LinearLayoutManager.VERTICAL, false);
        rcvDanhsachDoctor.setLayoutManager(layoutManager);
        rcvDanhsachDoctor.setAdapter(adapter);
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