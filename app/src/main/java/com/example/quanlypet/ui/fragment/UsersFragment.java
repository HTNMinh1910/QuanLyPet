package com.example.quanlypet.ui.fragment;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.example.quanlypet.R;
import com.example.quanlypet.ui.activity.AnimalActivity;
import com.example.quanlypet.ui.activity.List_User_Activity;
import com.example.quanlypet.ui.activity.UsersInforActivity;
import com.example.quanlypet.ui.welcome.ChangePasswordActivity;
import com.example.quanlypet.ui.welcome.WelcomeActivity;

public class UsersFragment extends Fragment {
    private LinearLayout lnInforAccount;
    private LinearLayout lnAnimalManager;
    private LinearLayout lnUserManager;
    private LinearLayout lnChangePass;
    private LinearLayout lnLogOut;

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

        lnInforAccount = view.findViewById(R.id.ln_inforAccount);
        lnAnimalManager = view.findViewById(R.id.ln_animalManager);
        lnUserManager = view.findViewById(R.id.ln_userManager);
        lnChangePass = view.findViewById(R.id.ln_changePass);
        lnLogOut = view.findViewById(R.id.ln_logOut);
        SharedPreferences preferences = requireActivity().getSharedPreferences("user_file", Context.MODE_PRIVATE);
        String username = preferences.getString("Username", "");
        if (username.equals("Admin")) {
            lnUserManager.setVisibility(View.VISIBLE);
        }

        lnAnimalManager.setOnClickListener(view1 -> {
            startActivity(new Intent(getContext(), AnimalActivity.class));
        });
        lnInforAccount.setOnClickListener(view1 -> {
            startActivity(new Intent(getActivity(), UsersInforActivity.class));
        });
        lnUserManager.setOnClickListener(view1 -> {
            startActivity(new Intent(getActivity(), List_User_Activity.class));
        });
        lnChangePass.setOnClickListener(view1 -> {
            startActivity(new Intent(getContext(), ChangePasswordActivity.class));
        });
        lnLogOut.setOnClickListener(view1 -> {
            startActivity(new Intent(getContext(), WelcomeActivity.class));
        });
    }
        public void replaceFragmet (Fragment fragment){
            FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
            transaction.replace(R.id.ln_inforAccount, fragment);
            transaction.commit();
        }
}