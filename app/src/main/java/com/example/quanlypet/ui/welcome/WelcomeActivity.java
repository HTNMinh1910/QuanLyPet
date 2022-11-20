package com.example.quanlypet.ui.welcome;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.widget.Button;

import com.example.quanlypet.MainActivity;
import com.example.quanlypet.R;
import com.example.quanlypet.adapter.viewpager2.SlideAdapter;
import com.example.quanlypet.database.AdminDB;
import com.example.quanlypet.model.AdminObj;
import com.example.quanlypet.model.Photo;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import me.relex.circleindicator.CircleIndicator;

public class WelcomeActivity extends AppCompatActivity {
    private Button btnAdmin;
    private Button btnUsers;
    private ViewPager vpr;
    private CircleIndicator circleIndicator;
    private SlideAdapter slideAdapter;
    private List<Photo> photoList;
    private Timer timer;
    private List<Photo> getListPhoto(){
        List<Photo> list = new ArrayList<>();
        list.add(new Photo(R.drawable.one));
        list.add(new Photo(R.drawable.two));
        list.add(new Photo(R.drawable.three));
        list.add(new Photo(R.drawable.four));
        list.add(new Photo(R.drawable.five));
        list.add(new Photo(R.drawable.six));
        return list;
    }
    private void autoSlide(){
        if (photoList == null||photoList.isEmpty()||vpr == null){
            return;
        }
        if (timer == null){
            timer = new Timer();
        }
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                new Handler(Looper.getMainLooper()).post(new Runnable() {
                    @Override
                    public void run() {
                        int curentItem = vpr.getCurrentItem();
                        int toltalItem = photoList.size() - 1;
                        if (curentItem < toltalItem){
                            curentItem++;
                            vpr.setCurrentItem(curentItem);
                        }else {
                            vpr.setCurrentItem(0);
                        }
                    }
                });
            }
        },500,3000);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (timer != null){
            timer.cancel();
            timer = null;
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wellcome);

        btnAdmin = (Button) findViewById(R.id.btn_admin);
        btnUsers = (Button) findViewById(R.id.btn_users);
        vpr = (ViewPager) findViewById(R.id.vpr);
        circleIndicator = (CircleIndicator) findViewById(R.id.circle_indicator);
        photoList = getListPhoto();
        slideAdapter = new SlideAdapter(this,photoList);
        vpr.setAdapter(slideAdapter);
        circleIndicator.setViewPager(vpr);
        slideAdapter.registerDataSetObserver(circleIndicator.getDataSetObserver());
        autoSlide();

        btnAdmin.setOnClickListener(view -> {
            AdminDB.getInstance(getApplicationContext()).Dao().insert(new AdminObj("Admin","Account_QLPV","qlpvip@gmail.com","petvip"));
            startActivity(new Intent(getApplicationContext(),LoginActivity.class));
        });
        btnUsers.setOnClickListener(view -> {
            AdminDB.getInstance(getApplicationContext()).Dao().insert(new AdminObj("Admin","Account_QLPV","qlpvip@gmail.com","petvip"));
            startActivity(new Intent(getApplicationContext(),LoginActivity.class));
        });
    }
}