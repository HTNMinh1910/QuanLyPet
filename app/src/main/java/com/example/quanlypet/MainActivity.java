package com.example.quanlypet;

import android.os.Bundle;
import android.view.MenuItem;


import com.example.quanlypet.adapter.viewpager2.ViewPager2Adapter;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import androidx.annotation.NonNull;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import androidx.viewpager2.widget.ViewPager2;

public class MainActivity extends AppCompatActivity {
    private ViewPager2 viewPager2;
    private BottomNavigationView bottomNavigationView;
    private Toolbar Tbr;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        viewPager2 = findViewById(R.id.view_pager2);
        bottomNavigationView = findViewById(R.id.bottom_nav);
        Tbr = findViewById(R.id.id_tollBar);
        setSupportActionBar(Tbr);
        ViewPager2Adapter adapter = new ViewPager2Adapter(this);
        viewPager2.setAdapter(adapter);
        viewPager2.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {
                super.onPageSelected(position);
                switch (position){
                    case 0:
                        bottomNavigationView.getMenu().findItem(R.id.home).setChecked(true);
                        Tbr.setTitle("Home");
                        break;
                    case 1:
                        bottomNavigationView.getMenu().findItem(R.id.docter).setChecked(true);
                        Tbr.setTitle("Bác sĩ");
                        break;

                    case 2:
                        bottomNavigationView.getMenu().findItem(R.id.book).setChecked(true);
                        Tbr.setTitle("Đặt Lịch");
                        break;
                    case 3:
                        bottomNavigationView.getMenu().findItem(R.id.account).setChecked(true);
                        Tbr.setTitle("Tài Khoản");
                        break;
                    case 4:
                        bottomNavigationView.getMenu().findItem(R.id.bill).setChecked(true);
                        Tbr.setTitle("bill");
                        break;
                }
            }
        });
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){
                    case R.id.home:
                        viewPager2.setCurrentItem(0);
                        break;
                    case R.id.docter:
                        viewPager2.setCurrentItem(1);
                        break;

                    case R.id.book:
                        viewPager2.setCurrentItem(2);
                        break;
                    case R.id.account:
                        viewPager2.setCurrentItem(3);
                        break;
                    case R.id.bill:
                        viewPager2.setCurrentItem(4);
                        break;
                }
                return true;
            }
        });
    }

}