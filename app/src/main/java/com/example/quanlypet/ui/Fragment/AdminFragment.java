package com.example.quanlypet.ui.Fragment;

import android.app.Dialog;
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
import android.widget.TextView;
import android.widget.Toast;

import com.example.quanlypet.R;
import com.example.quanlypet.adapter.ad_use.AdminAdapter;
import com.example.quanlypet.database.AdminDB;
import com.example.quanlypet.model.AdminObj;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class AdminFragment extends Fragment implements AdminAdapter.Callback {

    private ArrayList<AdminObj> list = new ArrayList<>();
    private AdminAdapter adminAdapter;

    private RecyclerView rcvListAdmin;
    private FloatingActionButton bbtnAddAdmin;

    private EditText edImportnameAdmin;
    private EditText edFullnameAdmin;
    private EditText edEmailAdmin;
    private EditText edStatusAdmin;
    private TextView tvEditAdmin;
    private TextView tvCancelEditAdmin;

    private TextView tvAddAdmin;
    private TextView tvCancelAddAdmin;


    public AdminFragment() {
        // Required empty public constructor
    }

    public static AdminFragment newInstance() {
        AdminFragment fragment = new AdminFragment();
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
        return inflater.inflate(R.layout.fragment_admin, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        rcvListAdmin = (RecyclerView) view.findViewById(R.id.rcv_listAdmin);
        bbtnAddAdmin = (FloatingActionButton) view.findViewById(R.id.bbtn_addAdmin);

        bbtnAddAdmin.setOnClickListener(v -> {
            final Dialog dialog = new Dialog(getActivity());
            dialog.setCancelable(false);
            dialog.setContentView(R.layout.dialog_addadmin);
            Window window = dialog.getWindow();
            window.setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            if (dialog != null && dialog.getWindow() != null) {
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
            }

            edImportnameAdmin = (EditText) dialog.findViewById(R.id.ed_importnameAdmin);
            edFullnameAdmin = (EditText) dialog.findViewById(R.id.ed_fullnameAdmin);
            edEmailAdmin = (EditText) dialog.findViewById(R.id.ed_emailAdmin);
            edStatusAdmin = (EditText) dialog.findViewById(R.id.ed_statusAdmin);
            tvAddAdmin = (TextView) dialog.findViewById(R.id.tv_addAdmin);
            tvCancelAddAdmin = (TextView) dialog.findViewById(R.id.tv_cancelAddAdmin);

            tvAddAdmin.setOnClickListener(v1 -> {
                String importName = edImportnameAdmin.getText().toString().trim();
                String fullName = edFullnameAdmin.getText().toString().trim();
                String email = edEmailAdmin.getText().toString().trim();
                int status = Integer.parseInt(edStatusAdmin.getText().toString().trim());
                if (importName.isEmpty() || fullName.isEmpty() || email.isEmpty() || edStatusAdmin.getText().toString().trim().isEmpty()) {
                    Toast.makeText(getActivity(), "Không được để trống!", Toast.LENGTH_SHORT).show();
                } else {
                    AdminObj adminObj = new AdminObj(importName, fullName, email, "");
                    AdminDB.getInstance(getActivity()).Dao().insert(adminObj);
                    Toast.makeText(getActivity(), "Thêm thành công!", Toast.LENGTH_SHORT).show();
                    list = (ArrayList<AdminObj>) AdminDB.getInstance(getActivity()).Dao().getAllData();
                    adminAdapter.setData(list);
                    dialog.cancel();
                }
            });
            tvCancelAddAdmin.setOnClickListener(v1 -> {
                dialog.cancel();
            });
            dialog.show();

        });

        adminAdapter = new AdminAdapter(getActivity(), this);
        list = (ArrayList<AdminObj>) AdminDB.getInstance(getActivity()).Dao().getAllData();
        adminAdapter.setData(list);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
        rcvListAdmin.setAdapter(adminAdapter);
        rcvListAdmin.setLayoutManager(linearLayoutManager);
    }

    @Override
    public void onResume() {
        super.onResume();
        list = (ArrayList<AdminObj>) AdminDB.getInstance(getActivity()).Dao().getAllData();
        adminAdapter.setData(list);
    }

    @Override
    public void editAdmin(AdminObj adminObj) {
        final Dialog dialog = new Dialog(getActivity());
        dialog.setCancelable(false);
        dialog.setContentView(R.layout.dialog_editadmin);
        Window window = dialog.getWindow();
        window.setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        if (dialog != null && dialog.getWindow() != null) {
            dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        }

        edImportnameAdmin = (EditText) dialog.findViewById(R.id.ed_importnameAdmin);
        edFullnameAdmin = (EditText) dialog.findViewById(R.id.ed_fullnameAdmin);
        edEmailAdmin = (EditText) dialog.findViewById(R.id.ed_emailAdmin);
        edStatusAdmin = (EditText) dialog.findViewById(R.id.ed_statusAdmin);
        tvEditAdmin = (TextView) dialog.findViewById(R.id.tv_editAdmin);
        tvCancelEditAdmin = (TextView) dialog.findViewById(R.id.tv_cancelEditAdmin);

        edImportnameAdmin.setText(adminObj.getImport_name());
        edFullnameAdmin.setText(adminObj.getFull_name());
        edEmailAdmin.setText(adminObj.getEmail());

        tvEditAdmin.setOnClickListener(v -> {
            String importName = edImportnameAdmin.getText().toString().trim();
            String fullName = edFullnameAdmin.getText().toString().trim();
            String email = edEmailAdmin.getText().toString().trim();
            int status = Integer.parseInt(edStatusAdmin.getText().toString().trim());
            if (importName.isEmpty() || fullName.isEmpty() || email.isEmpty() || edStatusAdmin.getText().toString().trim().isEmpty()) {
                Toast.makeText(getActivity(), "Không được để trống!", Toast.LENGTH_SHORT).show();
            } else {
                adminObj.setImport_name(importName);
                adminObj.setFull_name(fullName);
                adminObj.setEmail(email);
                AdminDB.getInstance(getActivity()).Dao().edit(adminObj);
                Toast.makeText(getActivity(), "Sửa thành công!", Toast.LENGTH_SHORT).show();
                list = (ArrayList<AdminObj>) AdminDB.getInstance(getActivity()).Dao().getAllData();
                adminAdapter.setData(list);
                dialog.cancel();
            }
        });
        tvCancelEditAdmin.setOnClickListener(v -> {
            dialog.cancel();
        });
        dialog.show();
    }
}
