package com.example.quanlypet.ui.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.quanlypet.R;
import com.example.quanlypet.adapter.bill.BillAdapter;
import com.example.quanlypet.database.BillDB;
import com.example.quanlypet.model.BillObj;
import com.google.android.material.textfield.TextInputEditText;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class AddBillActivity extends AppCompatActivity {
    private TextInputEditText edCaseId;
    private EditText edPrice;
    private EditText edNote;
    private Button btnAddBill;
    private Toolbar Tbr;
    private TextView tvNaneUsers;
    private TextView tvSdtUsers;
    private TextView tvTime;
    private TextView tvDate;

    private SimpleDateFormat sdftime = new SimpleDateFormat("HH:mm");
    private SimpleDateFormat sdfdate = new SimpleDateFormat("yyyy-MM-dd");
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_bill);
        Tbr = findViewById(R.id.id_tollBar_addBill);
        setSupportActionBar(Tbr);
        getSupportActionBar().setTitle("Thêm bill");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        edCaseId = (TextInputEditText) findViewById(R.id.ed_case_id);
        tvNaneUsers = (TextView) findViewById(R.id.tv_naneUsers);
        tvSdtUsers = (TextView) findViewById(R.id.tv_sdtUsers);
        tvTime = (TextView) findViewById(R.id.tv_time);
        tvDate = (TextView) findViewById(R.id.tv_date);
        edPrice = (EditText) findViewById(R.id.ed_price);
        edNote = (EditText) findViewById(R.id.ed_note);
        btnAddBill = (Button) findViewById(R.id.btn_Add_bill);
        tvDate.setText(sdfdate.format(new Date()));
        tvTime.setText(sdftime.format(new Date()));
        btnAddBill.setOnClickListener(v -> {
            int caseid = Integer.parseInt(edCaseId.getText().toString().trim());
            double price = Double.parseDouble(edPrice.getText().toString().trim());
            String note = edNote.getText().toString().trim();
            if (note.isEmpty()) {
                Toast.makeText(getApplicationContext(), "ban phai nhap het", Toast.LENGTH_SHORT).show();
            } else {
                String time = sdftime.format(new Date());
                String date = sdfdate.format(new Date());
                BillObj object = new BillObj(caseid, time, date, price, note);
                BillDB.getInstance(getApplicationContext()).Dao().insertBill(object);
                Toast.makeText(getApplicationContext(), "them thanh cong", Toast.LENGTH_SHORT).show();
                finish();
            }
        });
    }
}