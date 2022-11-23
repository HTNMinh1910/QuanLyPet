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
        bottomNavigationView.setItemIconTintList(null);
        ViewPager2Adapter adapter = new ViewPager2Adapter(this);
        viewPager2.setAdapter(adapter);

        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
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
                        viewPager2.setCurrentItem(4);
                        break;
                    case R.id.bill:
                        viewPager2.setCurrentItem(3);
                        break;
                }
                return true;
            }
        });
    }


}


