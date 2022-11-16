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

import com.example.quanlypet.adapter.patient.PatientAdapter;
import com.example.quanlypet.R;
import com.example.quanlypet.database.PatientDB;
import com.example.quanlypet.model.PatientObj;
import com.example.quanlypet.ui.activity.AddPatientActivity;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class PatientFragment extends Fragment {
    private FloatingActionButton floatingActionButton;
    private RecyclerView recyclerView;
    private PatientAdapter adapter;
    private ArrayList<PatientObj> list = new ArrayList<>();

    public PatientFragment() {
    }

    public static PatientFragment newInstance() {
        PatientFragment fragment = new PatientFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_patient, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        recyclerView = (RecyclerView) view.findViewById(R.id.rcv_patient);
        floatingActionButton = (FloatingActionButton) view.findViewById(R.id.floatingbutton);
        adapter = new PatientAdapter(getActivity());
        floatingActionButton.setOnClickListener(v -> {
            startActivity(new Intent(getContext(), AddPatientActivity.class));
        });
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void onResume() {
        super.onResume();
        list = (ArrayList<PatientObj>) PatientDB.getInstance(getContext()).patientDao().getAllData();
        adapter.setDataPatient(list);
    }
}