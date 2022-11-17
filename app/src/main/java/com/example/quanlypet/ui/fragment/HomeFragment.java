package com.example.quanlypet.ui.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.quanlypet.R;
import com.example.quanlypet.ui.activity.AddAnimalAcitvity;
import com.example.quanlypet.ui.activity.AddBooking;

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
        linerBooking.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getActivity(), AddBooking.class);
                startActivity(i);
            }
        });
        imgAddAnimal.setOnClickListener(view1 -> {
            startActivity(new Intent(getActivity(), AddAnimalAcitvity.class));
        });
        linerAmbulance.setOnClickListener(view1 -> {
        });
        linerMess.setOnClickListener(view1 -> {
        });

    }
}