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
import com.example.quanlypet.ui.wellcome.SignupUsersActivity;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class UsersFragment extends Fragment implements UsersAdapter.Callback {
    private UsersAdapter usersAdapter;
    private ArrayList<UsersObj> list = new ArrayList<>();

    private RecyclerView rcvListUsers;
    private FloatingActionButton bbtnAddUsers;

    private EditText edImportnameUsers;
    private EditText edFullnameUsers;
    private EditText edEmailUsers;
    private EditText edPhoneUsers;
    private EditText edStatusUsers;
    private RadioButton rdoMaleEdit;
    private RadioButton rdoFemaleEdit;
    private TextView tvEditUsers;
    private TextView tvCancelEditUsers;



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

        rcvListUsers = (RecyclerView) view.findViewById(R.id.rcv_listUsers);
        bbtnAddUsers = (FloatingActionButton) view.findViewById(R.id.bbtn_addUsers);
        usersAdapter = new UsersAdapter(getActivity(),this);
        LoadData();
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity(),LinearLayoutManager.VERTICAL,false);
        rcvListUsers.setLayoutManager(linearLayoutManager);
        rcvListUsers.setAdapter(usersAdapter);

        bbtnAddUsers.setOnClickListener(v->{
            startActivity(new Intent(getContext(), SignupUsersActivity.class));
        });
    }
    public void LoadData(){
        list = (ArrayList<UsersObj>) UsersDB.getInstance(getActivity()).Dao().getAllData();
        usersAdapter.setData(list);
    }
    @Override
    public void onResume() {
        super.onResume();
        LoadData();
    }

    @Override
    public void editUsers(UsersObj usersObj) {
        final Dialog dialog = new Dialog(getActivity());
        dialog.setCancelable(false);
        dialog.setContentView(R.layout.dialog_editusers);
        Window window = dialog.getWindow();
        window.setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        if (dialog != null && dialog.getWindow() != null){
            dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        }

        edImportnameUsers = (EditText) dialog.findViewById(R.id.ed_importnameUsers);
        edFullnameUsers = (EditText) dialog.findViewById(R.id.ed_fullnameUsers);
        edEmailUsers = (EditText) dialog.findViewById(R.id.ed_emailUsers);
        edPhoneUsers = (EditText) dialog.findViewById(R.id.ed_phoneUsers);
        rdoMaleEdit = (RadioButton) dialog.findViewById(R.id.rdo_maleEdit);
        rdoFemaleEdit = (RadioButton) dialog.findViewById(R.id.rdo_femaleEdit);
        edStatusUsers = (EditText) dialog.findViewById(R.id.ed_statusUsers);
        tvEditUsers = (TextView) dialog.findViewById(R.id.tv_editUsers);
        tvCancelEditUsers = (TextView) dialog.findViewById(R.id.tv_cancelEditUsers);

        edImportnameUsers.setText(usersObj.getImport_name());
        edFullnameUsers.setText(usersObj.getFull_name());
        edEmailUsers.setText(usersObj.getEmail());
        edPhoneUsers.setText(usersObj.getPhone());
        if (usersObj.getGender() == 0){
            rdoMaleEdit.setChecked(true);
        } else if(usersObj.getGender() == 1){
            rdoFemaleEdit.setChecked(true);
        }

        tvEditUsers.setOnClickListener(v->{
            String importName = edImportnameUsers.getText().toString().trim();
            String fullName = edFullnameUsers.getText().toString().trim();
            String email = edEmailUsers.getText().toString().trim();
            String phone = edPhoneUsers.getText().toString().trim();
            int gender = 0;
            if (rdoMaleEdit.isChecked() == true){
                gender = 0;
            } else if (rdoFemaleEdit.isChecked() == true){
                gender = 1;
            }
            int status = Integer.parseInt(edStatusUsers.getText().toString().trim());
            if (importName .isEmpty() || fullName.isEmpty() || email.isEmpty() || phone.isEmpty() || edStatusUsers.getText().toString().trim().isEmpty()){
                Toast.makeText(getActivity(), "Không được để trống!", Toast.LENGTH_SHORT).show();
            } else {
                usersObj.setImport_name(importName);
                usersObj.setFull_name(fullName);
                usersObj.setEmail(email);
                usersObj.setPhone(phone);
                usersObj.setGender(gender);
                UsersDB.getInstance(getActivity()).Dao().edit(usersObj);
                Toast.makeText(getActivity(), "Sửa thành công!", Toast.LENGTH_SHORT).show();
                LoadData();
                dialog.cancel();
            }
        });
        tvCancelEditUsers.setOnClickListener(v1->{
            dialog.cancel();
        });
        dialog.show();
    }
}