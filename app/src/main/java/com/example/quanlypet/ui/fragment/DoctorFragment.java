package com.example.quanlypet.ui.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.quanlypet.adapter.doctor.DoctorAdapter;
import com.example.quanlypet.R;
import com.example.quanlypet.database.DoctorDB;
import com.example.quanlypet.model.DoctorObj;
import com.example.quanlypet.ui.Main.AddDoctorActivity;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;



/**
 * A simple {@link Fragment} subclass.
 * Use the {@link DoctorFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class DoctorFragment extends Fragment {
    private FloatingActionButton floatingActionButton;
    private RecyclerView recyclerView;
    private DoctorAdapter adapter;
    private ArrayList<DoctorObj> list = new ArrayList<>();

    public DoctorFragment() {
        // Required empty public constructor
    }

    // TODO: Rename and change types and number of parameters
    public static DoctorFragment newInstance() {
        DoctorFragment fragment = new DoctorFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_docter, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        recyclerView = (RecyclerView) view.findViewById(R.id.rcv_docter);
        floatingActionButton = (FloatingActionButton) view.findViewById(R.id.floatingbutton);
        adapter = new DoctorAdapter(getActivity());
        floatingActionButton.setOnClickListener(v -> {
            startActivity(new Intent(getContext(), AddDoctorActivity.class));
        });
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void onResume() {
        super.onResume();
        list = (ArrayList<DoctorObj>) DoctorDB.getInstance(getContext()).docterDao().getAllData();
        adapter.setDataDocter(list);
    }
}