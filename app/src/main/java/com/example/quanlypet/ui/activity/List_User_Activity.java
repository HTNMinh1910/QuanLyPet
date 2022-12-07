package com.example.quanlypet.ui.activity;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;

import com.example.quanlypet.R;
import com.example.quanlypet.adapter.ad_use.List_user_Adapter;
import com.example.quanlypet.database.UsersDB;
import com.example.quanlypet.model.UsersObj;

import java.util.ArrayList;
import java.util.List;

public class List_User_Activity extends AppCompatActivity {
    private static final int MY_REQUESTCODE = 111;
    RecyclerView recyclerView;
    List_user_Adapter adapter;
    List<UsersObj> list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_user);
        Toolbar toolbar = findViewById(R.id.toolbar_user);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Danh Sách Người Dùng");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        recyclerView = findViewById(R.id.recy_list_user);
        adapter = new List_user_Adapter(this, new List_user_Adapter.ClickItem() {
            @Override
            public void update(UsersObj obj) {
                updateUser(obj);
            }
        });
        list = new ArrayList<>();
        list = UsersDB.getInstance(this).Dao().getAllData();
        adapter.setData(list);
        recyclerView.setAdapter(adapter);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(linearLayoutManager);

    }

    private void updateUser(UsersObj obj) {
        Intent i = new Intent(this, DetailUsersActivity.class);
        Bundle bundle = new Bundle();
        bundle.putSerializable("object_user", obj);
        i.putExtras(bundle);
        startActivityForResult(i, MY_REQUESTCODE);
    }

    public void loadDATA() {
        list = UsersDB.getInstance(this).Dao().getAllData();
        adapter.setData(list);
    }


    @Override
    protected void onResume() {
        super.onResume();
        loadDATA();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == MY_REQUESTCODE && resultCode == RESULT_OK) ;
        loadDATA();
    }
}