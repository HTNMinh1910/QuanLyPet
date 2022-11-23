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
import android.widget.LinearLayout;

import com.example.quanlypet.R;
import com.example.quanlypet.ui.activity.AnimalActivity;
import com.example.quanlypet.ui.activity.InformationUsersActivity;
import com.example.quanlypet.ui.activity.UpdateInformationActivity;
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

        lnInforAccount = (LinearLayout) view.findViewById(R.id.ln_inforAccount);
        lnAnimalManager = (LinearLayout) view.findViewById(R.id.ln_animalManager);
        lnUserManager = (LinearLayout) view.findViewById(R.id.ln_userManager);
        lnChangePass = (LinearLayout) view.findViewById(R.id.ln_changePass);
        lnLogOut = (LinearLayout) view.findViewById(R.id.ln_logOut);

        lnAnimalManager.setOnClickListener(view1 -> {
            startActivity(new Intent(getContext(), AnimalActivity.class));
        });
        lnInforAccount.setOnClickListener(view1 -> {
            startActivity(new Intent(getActivity(), UpdateInformationActivity.class));
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