package com.example.quanlypet;

import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;

import com.example.quanlypet.adapter.viewpager2.ViewPagerAdapter;

import com.example.quanlypet.tranfomation.DepthPageTransformer;
import com.google.android.material.bottomnavigation.BottomNavigationView;

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
        Tbr.setBackgroundColor(Color.WHITE);
        Tbr.setBackgroundResource(R.drawable.bg_tabarlayout);
        setSupportActionBar(Tbr);
        Tbr.setLogo(R.drawable.home);
        bottomNavigationView.setItemIconTintList(null);
        ViewPagerAdapter adapter = new ViewPagerAdapter(this);
        viewPager2.setAdapter(adapter);
        viewPager2.setUserInputEnabled(false);
        viewPager2.setOrientation(ViewPager2.ORIENTATION_HORIZONTAL);
        if (username.equals("Admin")) {
            bottomNavigationView.getMenu().findItem(R.id.docter).setVisible(true);
            bottomNavigationView.getMenu().findItem(R.id.bill).setVisible(true);
        }

        bottomNavigationView.setOnNavigationItemSelectedListener(item -> {

                switch (item.getItemId()) {
                    case R.id.home:
                        Tbr.setLogo(R.drawable.home);
//                        Tbr.setTitleTextColor(Color.WHITE);
//                        Tbr.setTitle("home");
                        viewPager2.setCurrentItem(0);
                        viewPager2.setPageTransformer(new DepthPageTransformer());
                        break;
                    case R.id.docter:
//                        Tbr.setTitle("docter");
//                        Tbr.setTitleTextColor(Color.WHITE);
                        Tbr.setLogo(R.drawable.doctor);
                        viewPager2.setCurrentItem(1);
                        viewPager2.setPageTransformer(new DepthPageTransformer());
                        break;
                    case R.id.book:
//                        Tbr.setTitle("book");
//                        Tbr.setTitleTextColor(Color.WHITE);
                        Tbr.setLogo(R.drawable.booking);
                        viewPager2.setCurrentItem(2);
                        viewPager2.setPageTransformer(new DepthPageTransformer());
                        break;
                    case R.id.bill:
//                        Tbr.setTitle("bill");
//                        Tbr.setTitleTextColor(Color.WHITE);
                        Tbr.setLogo(R.drawable.bill);
                        viewPager2.setCurrentItem(3);
                        viewPager2.setPageTransformer(new DepthPageTransformer());
                        break;
                    case R.id.account:
//                        Tbr.setTitle("account");
//                        Tbr.setTitleTextColor(Color.WHITE);
                        Tbr.setLogo(R.drawable.users);
                        viewPager2.setCurrentItem(4);
                        viewPager2.setPageTransformer(new DepthPageTransformer());
                        break;
                }
                return true;
        });
    }
}


