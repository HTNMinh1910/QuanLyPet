package com.example.quanlypet.ui.fragment;

import android.app.Dialog;
import android.content.Intent;
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
import android.widget.TextView;
import android.widget.Toast;

import com.example.quanlypet.adapter.bill.BillAdapter;
import com.example.quanlypet.R;
import com.example.quanlypet.database.BillDB;
import com.example.quanlypet.model.BillObj;
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
    private CheckBox chkThanhToanUp;
    private Button btnUpdateBill;
    private TextView title;
    private EditText edCaseId;

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

    private SimpleDateFormat sdfdate = new SimpleDateFormat("dd-MM-yyyy");
    public BillFragment() {
        // Required empty public constructor
    }

    // TODO: Rename and change types and number of parameters
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
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_bill, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        rcvBill = (RecyclerView) view.findViewById(R.id.rcv_bill);
        bbtn = (FloatingActionButton) view.findViewById(R.id.bbtn);
        fill();
        bbtn.setOnClickListener(v -> {
           startActivity(new Intent(getContext(), AddBillActivity.class));
        });
    }

    public void fill() {
        arrayList = (ArrayList<BillObj>) BillDB.getInstance(getActivity()).Dao().getAllDataBill();
        adapterBill = new BillAdapter(getContext(), this);
        adapterBill.setData(arrayList);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
        rcvBill.setLayoutManager(layoutManager);
        rcvBill.setAdapter(adapterBill);
    }

    @Override
    public void onResume() {
        super.onResume();
        arrayList = (ArrayList<BillObj>) BillDB.getInstance(getActivity()).Dao().getAllDataBill();
        adapterBill.setData(arrayList);
    }

    public void AddBill() {
        Dialog dialog = new Dialog(getActivity());
        dialog.setContentView(R.layout.dialog_add_bill);
        dialog.setCancelable(false);
        Window window = dialog.getWindow();
        window.setLayout(RecyclerView.LayoutParams.MATCH_PARENT, RecyclerView.LayoutParams.WRAP_CONTENT);
        dialog.show();
        title = dialog.findViewById(R.id.title);
        edCaseId = dialog.findViewById(R.id.ed_case_id);

        edPrice = dialog.findViewById(R.id.ed_price);
        edNote = dialog.findViewById(R.id.ed_note);

        edNote.setText("");
        btnAddBill = dialog.findViewById(R.id.btn_Add_bill);
        btnAddBill.setOnClickListener(v -> {
            int caseid = Integer.parseInt(edCaseId.getText().toString().trim());


            double price = Double.parseDouble(edPrice.getText().toString().trim());
            String note = edNote.getText().toString().trim();
            if (note.isEmpty()) {
                Toast.makeText(getActivity(), "ban phai nhap het", Toast.LENGTH_SHORT).show();
            } else {

                String time = sdftime.format(new Date());

                String date = sdfdate.format(new Date());
                BillObj object = new BillObj(caseid, time, date, price, note);
                BillDB.getInstance(getActivity()).Dao().insertBill(object);
                Toast.makeText(getActivity(), "them thanh cong", Toast.LENGTH_SHORT).show();
                fill();
            }
            dialog.cancel();
        });
        btnCancel = (Button) dialog.findViewById(R.id.btn_cancel);
        btnCancel.setOnClickListener(v -> {
            dialog.dismiss();
        });
    }

    @Override
    public void Update(BillObj object) {
        final Dialog dialog = new Dialog(getContext());
        dialog.setContentView(R.layout.dialog_update_bill);
        dialog.setCancelable(false);
        Window window = dialog.getWindow();
        window.setLayout(RecyclerView.LayoutParams.MATCH_PARENT, RecyclerView.LayoutParams.WRAP_CONTENT);
        dialog.show();
        Calendar thoigian = Calendar.getInstance();
        Calendar lich = Calendar.getInstance();
        title = (TextView) dialog.findViewById(R.id.title);
        upCaseId = (EditText) dialog.findViewById(R.id.up_case_id);
        upPrice = (EditText) dialog.findViewById(R.id.up_price);
        upNote = (EditText) dialog.findViewById(R.id.up_note);

        upCaseId.setText(object.getId_case_file()+"");
        upNote.setText(object.getNote());
        upPrice.setText(object.getPrice()+"");

        btnUpdateBill = (Button) dialog.findViewById(R.id.btn_Update_bill);
        btnUpdateBill.setOnClickListener(v -> {
            int idcaseup = Integer.parseInt(upCaseId.getText().toString().trim());
            double priceup = Double.parseDouble(upPrice.getText().toString().trim());
            String noteup = upNote.getText().toString().trim();
            if (noteup.isEmpty()) {
                Toast.makeText(getActivity(), "ko dc de trong", Toast.LENGTH_SHORT).show();
            } else {
                object.setId_case_file(idcaseup);
//                chkThanhToanUp = (chk.isChecked() == true) ? "Đã trả" : "Chưa trả";
//                object.setStatus_obj(chkThanhToanUp);
                String time = sdftime.format(new Date());
                String date = sdfdate.format(new Date());
                object.setTime(time);
                object.setDate(date);
                object.setPrice(priceup);
                object.setNote(noteup);
//                object.setStatus_obj(status);
                BillDB.getInstance(getActivity()).Dao().editBill(object);
                arrayList = (ArrayList<BillObj>) BillDB.getInstance(getActivity()).Dao().getAllDataBill();
                adapterBill.setData(arrayList);
                Toast.makeText(getActivity(), "sua thanh cong", Toast.LENGTH_SHORT).show();
                dialog.cancel();
            }

        });
        btnCancel = (Button) dialog.findViewById(R.id.btn_cancel);
        btnCancel.setOnClickListener(v -> {
            dialog.dismiss();
        });
    }
}