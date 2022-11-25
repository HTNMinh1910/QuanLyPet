package com.example.quanlypet.ui.fragment;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.example.quanlypet.R;
import com.example.quanlypet.adapter.viewpager2.SlideAdapterHome;
import com.example.quanlypet.model.Photo;
import com.example.quanlypet.ui.activity.AddBookingActivity;
import com.example.quanlypet.ui.activity.DanhSachDoctor;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import me.relex.circleindicator.CircleIndicator;

public class HomeFragment extends Fragment {
    private LinearLayout linerBooking;
    private LinearLayout linerAmbulance;
    private LinearLayout linerMess;
    private ImageView imgAddAnimal;

    public HomeFragment() {
    }

    public static HomeFragment newInstance() {
        HomeFragment fragment = new HomeFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_home, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        linerBooking = (LinearLayout) view.findViewById(R.id.liner_booking);
        linerAmbulance = (LinearLayout) view.findViewById(R.id.liner_ambulance);
        linerMess = view.findViewById(R.id.liner_mess);
        linerBooking.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getActivity(), AddBookingActivity.class);
                startActivity(i);
            }
        });
        linerMess.setOnClickListener(v->{
            Uri uri = Uri.parse("http://m.me/100088046954126");
            startActivity(new Intent(Intent.ACTION_VIEW,uri));
        });
        linerAmbulance.setOnClickListener(v->{
            startActivity(new Intent(getActivity(), DanhSachDoctor.class));
        });
        vpr = (ViewPager) view.findViewById(R.id.vpr);
        circleIndicator = (CircleIndicator) view.findViewById(R.id.circle_indicator);
        photoList = getListPhoto();
        slideAdapter = new SlideAdapterHome(getContext(),photoList);
        vpr.setAdapter(slideAdapter);
        circleIndicator.setViewPager(vpr);
        slideAdapter.registerDataSetObserver(circleIndicator.getDataSetObserver());
        autoSlide();
    }
    private ViewPager vpr;
    private CircleIndicator circleIndicator;
    private SlideAdapterHome slideAdapter;
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
        },5000,6000);
    }
}