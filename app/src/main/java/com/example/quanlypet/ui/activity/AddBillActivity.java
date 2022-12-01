package com.example.quanlypet.ui.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.quanlypet.R;
import com.example.quanlypet.adapter.bill.BillAdapter;
import com.example.quanlypet.database.BillDB;
import com.example.quanlypet.database.UsersDB;
import com.example.quanlypet.model.BillObj;
import com.example.quanlypet.model.UsersObj;
import com.google.android.material.textfield.TextInputEditText;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class AddBillActivity extends AppCompatActivity {
    private TextInputEditText edCaseId;
    private TextInputEditText edPrice;
    private TextInputEditText edNote;
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
        tvNaneUsers = (TextView) findViewById(R.id.tv_naneUsers);
        tvSdtUsers = (TextView) findViewById(R.id.tv_sdtUsers);
        tvTime = (TextView) findViewById(R.id.tv_time);
        tvDate = (TextView) findViewById(R.id.tv_date);
        edPrice = (TextInputEditText) findViewById(R.id.ed_price);
        edNote = (TextInputEditText) findViewById(R.id.ed_note);
        btnAddBill = (Button) findViewById(R.id.btn_Add_bill);
        SharedPreferences sharedPreferences = getSharedPreferences("Users_info_id", MODE_PRIVATE);
        int userid = sharedPreferences.getInt("userId", 0);
        tvDate.setText(sdfdate.format(new Date()));
        tvTime.setText(sdftime.format(new Date()));
        UsersObj usersObj = UsersDB.getInstance(getApplicationContext()).Dao().getID(userid);
        tvNaneUsers.setText(usersObj.getFull_name());
        tvSdtUsers.setText(usersObj.getPhone());
        btnAddBill.setOnClickListener(v -> {

            double price = Double.parseDouble(edPrice.getText().toString().trim());
            String note = edNote.getText().toString().trim();
            if (note.isEmpty()) {
                Toast.makeText(getApplicationContext(), "ban phai nhap het", Toast.LENGTH_SHORT).show();
            } else {
                String time = sdftime.format(new Date());
                String date = sdfdate.format(new Date());
                int users = usersObj.getId();
                BillObj object = new BillObj(users, time, date, price, note);
                BillDB.getInstance(getApplicationContext()).Dao().insertBill(object);
                Toast.makeText(getApplicationContext(), "them thanh cong", Toast.LENGTH_SHORT).show();
                finish();
            }
        });
    }
}