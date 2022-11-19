package com.example.quanlypet.ui.fragment;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import com.example.quanlypet.R;
import com.example.quanlypet.ui.activity.AddAnimalAcitvity;
import com.example.quanlypet.ui.activity.AnimalActivity;
import com.example.quanlypet.adapter.ad_use.UsersAdapter;
import com.example.quanlypet.database.UsersDB;
import com.example.quanlypet.model.UsersObj;
import com.example.quanlypet.ui.activity.InformationUsersActivity;
import com.example.quanlypet.ui.welcome.ChangePasswordActivity;
import com.example.quanlypet.ui.welcome.WelcomeActivity;

public class UsersFragment extends Fragment {
    private FrameLayout lnInforAccount;
    private FrameLayout lnAnimalManager;
    private FrameLayout lnChangePass;
    private FrameLayout lnLogOut;

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

        lnInforAccount = (FrameLayout) view.findViewById(R.id.ln_inforAccount);
        lnAnimalManager = (FrameLayout) view.findViewById(R.id.ln_animalManager);
        lnChangePass = (FrameLayout) view.findViewById(R.id.ln_changePass);
        lnLogOut = (FrameLayout) view.findViewById(R.id.ln_logOut);

        lnAnimalManager.setOnClickListener(view1 -> {
            startActivity(new Intent(getContext(), AnimalActivity.class));
        });
        tvInforAccount.setOnClickListener(view1 -> {
            startActivity(new Intent(getActivity(), InformationUsersActivity.class));

        lnInforAccount.setOnClickListener(view1 -> {

        });
        lnChangePass.setOnClickListener(view1 -> {
            startActivity(new Intent(getContext(), ChangePasswordActivity.class));
        });
        lnLogOut.setOnClickListener(view1 -> {
            startActivity(new Intent(getContext(), WelcomeActivity.class));
        });
    }
    public void replaceFragmet(Fragment fragment){
        FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.ln_inforAccount, fragment);
        transaction.commit();
    }
}