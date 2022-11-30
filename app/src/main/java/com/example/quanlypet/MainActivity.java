package com.example.quanlypet;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.MenuItem;


import com.example.quanlypet.dao.viewpager2.ViewPager2Adapter;

import com.example.quanlypet.effect.DepthPageTransformer;
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
        SharedPreferences sharedPreferences = getSharedPreferences("user_file", MODE_PRIVATE);
        String username = sharedPreferences.getString("Username", "");

        viewPager2 = findViewById(R.id.view_pager2);
        bottomNavigationView = findViewById(R.id.bottom_nav);
        Tbr = findViewById(R.id.id_tollBar);
        setSupportActionBar(Tbr);
        bottomNavigationView.setItemIconTintList(null);
        ViewPager2Adapter adapter = new ViewPager2Adapter(this);
        viewPager2.setAdapter(adapter);
        viewPager2.setUserInputEnabled(false);
        Tbr.setLogo(R.drawable.home);
        if (username.equals("Admin")) {
            bottomNavigationView.getMenu().findItem(R.id.docter).setVisible(true);

        }

        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.home:
                        viewPager2.setCurrentItem(0);
                        Tbr.setLogo(R.drawable.home);
                        viewPager2.setPageTransformer(new DepthPageTransformer());
                        break;
                    case R.id.docter:
                        viewPager2.setCurrentItem(1);
                        Tbr.setLogo(R.drawable.doctor);
                        viewPager2.setPageTransformer(new DepthPageTransformer());
                        break;
                    case R.id.book:
                        viewPager2.setCurrentItem(2);
                        Tbr.setLogo(R.drawable.booking);
                        viewPager2.setPageTransformer(new DepthPageTransformer());
                        break;
                    case R.id.bill:
                        viewPager2.setCurrentItem(3);
                        Tbr.setLogo(R.drawable.bill);
                        viewPager2.setPageTransformer(new DepthPageTransformer());
                        break;
                    case R.id.account:
                        viewPager2.setCurrentItem(4);
                        Tbr.setLogo(R.drawable.users);
                        viewPager2.setPageTransformer(new DepthPageTransformer());
                        break;
                }
                return true;
            }
        });
    }
}


