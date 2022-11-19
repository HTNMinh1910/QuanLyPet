package com.example.quanlypet.ui.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.quanlypet.R;
import com.example.quanlypet.adapter.booking.bookingAdapter;
import com.example.quanlypet.database.BookDB;
import com.example.quanlypet.model.BookObj;

import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 * Use the {@link BookFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class BookFragment extends Fragment {
    List<BookObj> list;
    bookingAdapter adapter;
    RecyclerView reCy_booking;


    public BookFragment() {
        // Required empty public constructor
    }

    // TODO: Rename and change types and number of parameters
    public static BookFragment newInstance() {
        BookFragment fragment = new BookFragment();
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
        return inflater.inflate(R.layout.fragment_book, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        reCy_booking = view.findViewById(R.id.recy_booking);
        list = new ArrayList<>();
        list = BookDB.getInstance(getActivity()).Dao().getAllData();
        adapter = new bookingAdapter();
        adapter.setDATA(list);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity(),LinearLayoutManager.VERTICAL,false);
        reCy_booking.setAdapter(adapter);
        reCy_booking.setLayoutManager(linearLayoutManager);
    }

    @Override
    public void onResume() {
        super.onResume();
        list = BookDB.getInstance(getActivity()).Dao().getAllData();
        adapter.setDATA(list);
    }
}