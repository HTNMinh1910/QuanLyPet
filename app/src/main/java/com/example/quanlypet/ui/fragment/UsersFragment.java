package com.example.quanlypet.ui.fragment;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.quanlypet.R;
import com.example.quanlypet.adapter.ad_use.UsersAdapter;
import com.example.quanlypet.database.UsersDB;
import com.example.quanlypet.model.UsersObj;
import com.example.quanlypet.ui.welcome.SignupUsersActivity;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class UsersFragment extends Fragment{
    private FrameLayout idLayoutcontent;
    private TextView tvAnimalManager;
    private TextView tvInforAccount;
    private TextView tvLogOut;
    private Toolbar Tbr;
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
        idLayoutcontent = (FrameLayout) view.findViewById(R.id.id_layoutcontent);
        tvAnimalManager = (TextView) view.findViewById(R.id.tv_animalManager);
        tvAnimalManager.setOnClickListener(v ->{
//            Tbr = view.findViewById(R.id.id_tollBar);
//            setSupportActionBar(Tbr);
//            getSupportActionBar().setTitle("them animal");
//            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
//            Tbr.setTitle("Animal");
            replaceFragmet(AnimalFragment.newInstance());
        });
        tvInforAccount = (TextView) view.findViewById(R.id.tv_inforAccount);
        tvLogOut = (TextView) view.findViewById(R.id.tv_logOut);
    }
    public void replaceFragmet(Fragment fragment){
        FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.id_layoutcontent, fragment);
        transaction.commit();
    }

}