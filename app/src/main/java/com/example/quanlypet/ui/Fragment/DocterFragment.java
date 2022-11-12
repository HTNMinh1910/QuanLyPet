package com.example.quanlypet.ui.Fragment;

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

import com.example.quanlypet.Adapter.docter.DocterAdapter;
import com.example.quanlypet.R;
import com.example.quanlypet.database.DocterDB;
import com.example.quanlypet.model.DocterObj;
import com.example.quanlypet.ui.Main.AddDocterActivity;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;



/**
 * A simple {@link Fragment} subclass.
 * Use the {@link DocterFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class DocterFragment extends Fragment {
    private FloatingActionButton floatingActionButton;
    private RecyclerView recyclerView;
    private DocterAdapter adapter;
    private ArrayList<DocterObj> list = new ArrayList<>();

    public DocterFragment() {
        // Required empty public constructor
    }

    // TODO: Rename and change types and number of parameters
    public static DocterFragment newInstance() {
        DocterFragment fragment = new DocterFragment();
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
        adapter = new DocterAdapter(getActivity());
        floatingActionButton.setOnClickListener(v -> {
            startActivity(new Intent(getContext(), AddDocterActivity.class));
        });
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void onResume() {
        super.onResume();
        list = (ArrayList<DocterObj>) DocterDB.getInstance(getContext()).docterDao().getAllData();
        adapter.setDataDocter(list);
    }
}