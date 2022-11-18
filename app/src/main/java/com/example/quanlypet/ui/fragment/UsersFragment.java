package com.example.quanlypet.ui.fragment;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.quanlypet.R;
import com.example.quanlypet.adapter.ad_use.UsersAdapter;
import com.example.quanlypet.database.UsersDB;
import com.example.quanlypet.model.UsersObj;
import com.example.quanlypet.ui.activity.InformationUsersActivity;
import com.example.quanlypet.ui.welcome.SignupUsersActivity;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class UsersFragment extends Fragment {
    private TextView tvAnimalManager;
    private TextView tvInforAccount;
    private TextView tvLogOut;

    public UsersFragment() {
    }

    public static UsersFragment newInstance() {
        UsersFragment fragment = new UsersFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_users, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        tvAnimalManager = (TextView) view.findViewById(R.id.tv_animalManager);
        tvInforAccount = (TextView) view.findViewById(R.id.tv_inforAccount);
        tvLogOut = (TextView) view.findViewById(R.id.tv_logOut);

        tvAnimalManager.setOnClickListener(view1 -> {

        });
        tvInforAccount.setOnClickListener(view1 -> {
            startActivity(new Intent(getActivity(), InformationUsersActivity.class));
        });
        tvLogOut.setOnClickListener(view1 -> {

        });
    }

}