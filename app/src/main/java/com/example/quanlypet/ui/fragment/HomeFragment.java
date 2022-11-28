package com.example.quanlypet.ui.fragment;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import com.example.quanlypet.R;
import com.example.quanlypet.dao.viewpager2.SlideAdapterHome;

import com.example.quanlypet.adapter.booking.bookingAdapter;
import com.example.quanlypet.adapter.booking.booking_admin_Adapter;
import com.example.quanlypet.adapter.viewpager2.SlideAdapterHome;
import com.example.quanlypet.database.BookDB;
import com.example.quanlypet.database.UsersDB;
import com.example.quanlypet.model.BookObj;
import com.example.quanlypet.model.Photo;
import com.example.quanlypet.model.UsersObj;
import com.example.quanlypet.ui.activity.AddBookingActivity;
import com.example.quanlypet.ui.activity.DanhSachDoctor;
import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.highlight.Highlight;
import com.github.mikephil.charting.listener.OnChartValueSelectedListener;
import com.github.mikephil.charting.utils.ColorTemplate;
import com.example.quanlypet.ui.activity.MapsActivity;
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
    private ImageView imgMap;
    RecyclerView id_recyNear;
    List<BookObj> list;
    bookingAdapter adapter;
    UsersObj usersObj = new UsersObj();
    TextView titleNear;
    booking_admin_Adapter adapter2;
    List<BookObj> list2 = new ArrayList<>();

    private BarChart barChart;

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
        linerBooking = view.findViewById(R.id.liner_booking);
        linerAmbulance = view.findViewById(R.id.liner_ambulance);
        linerMess = view.findViewById(R.id.liner_mess);
        titleNear = view.findViewById(R.id.titleNear);
        imgMap = view.findViewById(R.id.img_map);
        list = new ArrayList<>();
        id_recyNear = view.findViewById(R.id.recy_bookingNear);
        adapter = new bookingAdapter(new bookingAdapter.Callback() {
            @Override
            public void update(BookObj bookObj, int index) {

            }
        }, getActivity());
        SharedPreferences sharedPreferences = getActivity().getSharedPreferences("user_file", Context.MODE_PRIVATE);
        String user = sharedPreferences.getString("Username", "");
        if (user.equalsIgnoreCase("Admin")) {
            titleNear.setText("Lịch Đang Chờ Gần Đây");
            list2 = BookDB.getInstance(getActivity()).Dao().getStatus4(1);
            adapter2 = new booking_admin_Adapter(list2, getActivity(), new booking_admin_Adapter.Callback() {
                @Override
                public void updateAdmin(BookObj bookObj, int index) {

                }
            });
            adapter2.setDATA(list2);
            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false);
            id_recyNear.setAdapter(adapter2);
            id_recyNear.setLayoutManager(linearLayoutManager);

        } else {
            usersObj = UsersDB.getInstance(getActivity()).Dao().getIdUsers(user);
            int id = usersObj.getId();
            list = BookDB.getInstance(getActivity()).Dao().getStatus3(4, id);
            adapter = new bookingAdapter(new bookingAdapter.Callback() {
                @Override
                public void update(BookObj bookObj, int index) {

                }
            }, getActivity());
            adapter.setDATA(list);
            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false);
            id_recyNear.setAdapter(adapter);
            id_recyNear.setLayoutManager(linearLayoutManager);

        }


        linerBooking.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getActivity(), AddBookingActivity.class);
                startActivity(i);
            }
        });
        linerMess.setOnClickListener(v -> {
            Uri uri = Uri.parse("http://m.me/100088046954126");
            startActivity(new Intent(Intent.ACTION_VIEW, uri));
        });
        linerAmbulance.setOnClickListener(v -> {
            startActivity(new Intent(getActivity(), DanhSachDoctor.class));
        });
        imgMap.setOnClickListener(view1 -> {
            startActivity(new Intent(getActivity(), MapsActivity.class));
        });
        vpr = (ViewPager) view.findViewById(R.id.vpr);
        circleIndicator = (CircleIndicator) view.findViewById(R.id.circle_indicator);
        photoList = getListPhoto();
        slideAdapter = new SlideAdapterHome(getContext(), photoList);
        vpr.setAdapter(slideAdapter);
        circleIndicator.setViewPager(vpr);
        slideAdapter.registerDataSetObserver(circleIndicator.getDataSetObserver());
        autoSlide();

        //BarChart
        SharedPreferences sharedPreferences = getActivity().getSharedPreferences("user_file", Context.MODE_PRIVATE);
        String username = sharedPreferences.getString("Username", "");

        barChart = (BarChart) view.findViewById(R.id.barChart);
        ArrayList<BarEntry> visitor = new ArrayList<>();

        visitor.add(new BarEntry(1,110));
        visitor.add(new BarEntry(2,320));
        visitor.add(new BarEntry(3,100));
        visitor.add(new BarEntry(4,70));
        visitor.add(new BarEntry(5,200));
        visitor.add(new BarEntry(6,140));
        visitor.add(new BarEntry(7,190));
        visitor.add(new BarEntry(8,400));
        visitor.add(new BarEntry(9,350));
        visitor.add(new BarEntry(10,450));
        visitor.add(new BarEntry(11,200));
        visitor.add(new BarEntry(12,170));

        BarDataSet barDataSet = new BarDataSet(visitor,"Thống kê doanh thu theo tháng");
        barDataSet.setColors(ColorTemplate.MATERIAL_COLORS);
        barDataSet.setValueTextColor(Color.BLACK);
        barDataSet.setValueTextSize(14f);
        BarData barData = new BarData( barDataSet);
        barChart.setFitBars(true);
        barChart.setData(barData);
        barChart.getDescription().setText("Thống kê doanh thu theo tháng");
        barChart.getDescription().setTextSize(20f);
        barChart.animateY(2000);

        XAxis xAxis = barChart.getXAxis();

        barChart.setOnChartValueSelectedListener(new OnChartValueSelectedListener() {
            @Override
            public void onValueSelected(Entry e, Highlight h) {
                Toast.makeText(getActivity(), ""+h.getY(), Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected() {

            }
        });
        if (username.equals("Admin")){
            barChart.setVisibility(View.VISIBLE);
        } else {
            barChart.setVisibility(View.GONE);
        }


    }

    private ViewPager vpr;
    private CircleIndicator circleIndicator;
    private SlideAdapterHome slideAdapter;
    private List<Photo> photoList;
    private Timer timer;

    private List<Photo> getListPhoto() {
        List<Photo> list = new ArrayList<>();
        list.add(new Photo(R.drawable.one));
        list.add(new Photo(R.drawable.two));
        list.add(new Photo(R.drawable.three));
        list.add(new Photo(R.drawable.four));
        list.add(new Photo(R.drawable.five));
        list.add(new Photo(R.drawable.six));
        return list;
    }

    private void autoSlide() {
        if (photoList == null || photoList.isEmpty() || vpr == null) {
            return;
        }
        if (timer == null) {
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
                        if (curentItem < toltalItem) {
                            curentItem++;
                            vpr.setCurrentItem(curentItem);
                        } else {
                            vpr.setCurrentItem(0);
                        }
                    }
                });
            }
        }, 5000, 6000);
    }

    @Override
    public void onResume() {
        super.onResume();
        SharedPreferences sharedPreferences = getActivity().getSharedPreferences("user_file", Context.MODE_PRIVATE);
        String user = sharedPreferences.getString("Username", "");
        if (user.equalsIgnoreCase("Admin")) {
            list2 = BookDB.getInstance(getActivity()).Dao().getStatus4(1);
            adapter2.setDATA(list2);
        } else {
            usersObj = UsersDB.getInstance(getActivity()).Dao().getIdUsers(user);
            int id = usersObj.getId();
            list = BookDB.getInstance(getActivity()).Dao().getStatus3(4, id);
            adapter.setDATA(list);


        }
    }

}
