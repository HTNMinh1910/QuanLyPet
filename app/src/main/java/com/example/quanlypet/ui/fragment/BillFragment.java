package com.example.quanlypet.ui.fragment;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
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
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.quanlypet.adapter.bill.BillAdapter;
import com.example.quanlypet.R;
import com.example.quanlypet.database.BillDB;
import com.example.quanlypet.database.UsersDB;
import com.example.quanlypet.model.BillObj;
import com.example.quanlypet.model.UsersObj;
import com.example.quanlypet.ui.activity.AddBillActivity;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class BillFragment extends Fragment implements BillAdapter.Callback {
    private EditText upCaseId;
    private EditText upPrice;
    private EditText upNote;
    private LinearLayout lineUpBill;
    private CheckBox chkThanhToanUp;
    private TextView tvNaneUsers;
    private TextView tvSdtUsers;
    private TextView tvTime;
    private TextView tvDate;
    private Button btnUpdateBill;
    private TextView title;
    private EditText edCaseId;
    private String users;
    private EditText edPrice;
    private EditText edNote;
    private int chkThanhToan;
    private Button btnAddBill;
    private Button btnCancel;
    private RecyclerView rcvBill;
    private FloatingActionButton bbtn;
    private ArrayList<BillObj> arrayList = new ArrayList<>();
    private BillAdapter adapterBill;
    private SimpleDateFormat sdftime = new SimpleDateFormat("HH:mm");
    private SimpleDateFormat sdfdate = new SimpleDateFormat("HH:mm dd-MM-yyyy");

    public BillFragment() {
    }

    public static BillFragment newInstance() {
        BillFragment fragment = new BillFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_bill, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        rcvBill = (RecyclerView) view.findViewById(R.id.rcv_bill);

        fill();
    }
    public void fill() {
        SharedPreferences sharedPreferences = getContext().getSharedPreferences("user_file", Context.MODE_PRIVATE);
        String username = sharedPreferences.getString("Username", "");
        if (username.equals("Admin")) {
            adapterBill = new BillAdapter(getContext(), this);
            arrayList = (ArrayList<BillObj>) BillDB.getInstance(getContext()).Dao().getAllData();
            adapterBill.setData(arrayList);
            LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
            rcvBill.setLayoutManager(layoutManager);
            rcvBill.setAdapter(adapterBill);
        } else {
            UsersObj userobj = UsersDB.getInstance(getContext()).Dao().getIdUsers(username);
            int id = userobj.getId();
            adapterBill = new BillAdapter(getContext(), this);
            arrayList = (ArrayList<BillObj>) BillDB.getInstance(getContext()).Dao().getbyUsers(id);
            adapterBill.setData(arrayList);
            LinearLayoutManager layoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
            rcvBill.setLayoutManager(layoutManager);
            rcvBill.setAdapter(adapterBill);
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        SharedPreferences sharedPreferences = getContext().getSharedPreferences("user_file", Context.MODE_PRIVATE);
        String username = sharedPreferences.getString("Username", "");
        if (username.equals("Admin")) {
            adapterBill = new BillAdapter(getContext(), this);
            arrayList = (ArrayList<BillObj>) BillDB.getInstance(getContext()).Dao().getAllData();
            adapterBill.setData(arrayList);
        } else {
            UsersObj userobj = UsersDB.getInstance(getContext()).Dao().getIdUsers(username);
            int id = userobj.getId();
            adapterBill = new BillAdapter(getContext(), this);
            arrayList = (ArrayList<BillObj>) BillDB.getInstance(getContext()).Dao().getbyUsers(id);
            adapterBill.setData(arrayList);
        }
    }

    @Override
    public void Update(BillObj object) {
        final Dialog dialog = new Dialog(getContext());
        dialog.setContentView(R.layout.dialog_update_bill);
        dialog.setCancelable(false);
        Window window = dialog.getWindow();
        window.setLayout(RecyclerView.LayoutParams.MATCH_PARENT, RecyclerView.LayoutParams.MATCH_PARENT);
        dialog.show();
        Calendar thoigian = Calendar.getInstance();
        Calendar lich = Calendar.getInstance();

        title = (TextView) dialog.findViewById(R.id.title);
        tvNaneUsers = (TextView) dialog.findViewById(R.id.tv_naneUsers);
        tvSdtUsers = (TextView)  dialog.findViewById(R.id.tv_sdtUsers);
        tvTime = (TextView)  dialog.findViewById(R.id.tv_time);
        tvDate = (TextView)  dialog.findViewById(R.id.tv_date);
        upPrice = (EditText) dialog.findViewById(R.id.up_price);
        upNote = (EditText) dialog.findViewById(R.id.up_note);
        lineUpBill = (LinearLayout) dialog.findViewById(R.id.line_upBill);
        SharedPreferences sharedPreferences = getActivity().getSharedPreferences("user_file", Context.MODE_PRIVATE);
        users = sharedPreferences.getString("Username", "");
        if (!users.equals("Admin")){
            lineUpBill.setVisibility(View.GONE);
        }
        upNote.setText(object.getNote());
        upPrice.setText(object.getPrice() + "");
        btnUpdateBill = (Button) dialog.findViewById(R.id.btn_Update_bill);
        SharedPreferences sharedPreferences1 = requireActivity().getSharedPreferences("Users_info_id", Context.MODE_PRIVATE);
        int userid = sharedPreferences1.getInt("userId", 0);
        tvDate.setText(sdfdate.format(new Date()));
        tvTime.setText(sdftime.format(new Date()));
        UsersObj usersObj = UsersDB.getInstance(getActivity()).Dao().getID(userid);
        tvNaneUsers.setText(usersObj.getFull_name());
        tvSdtUsers.setText(usersObj.getPhone());
        btnUpdateBill.setOnClickListener(v -> {
            double priceup = Double.parseDouble(upPrice.getText().toString().trim());
            String noteup = upNote.getText().toString().trim();
            if (noteup.isEmpty()) {
                Toast.makeText(getActivity(), "ko dc de trong", Toast.LENGTH_SHORT).show();
            } else {
                String time = sdftime.format(new Date());
                String date = sdfdate.format(new Date());
                object.setTime(time);
                object.setDate(date);
                object.setPrice(priceup);
                object.setNote(noteup);
                BillDB.getInstance(getContext()).Dao().editBill(object);
                arrayList = (ArrayList<BillObj>) BillDB.getInstance(getContext()).Dao().getAllData();
                adapterBill.setData(arrayList);
                Toast.makeText(getActivity(), "sua thanh cong", Toast.LENGTH_SHORT).show();
                dialog.dismiss();
            }
        });
        btnCancel = (Button) dialog.findViewById(R.id.btn_cancel);
        btnCancel.setOnClickListener(v -> {
            dialog.cancel();
        });
    }
}