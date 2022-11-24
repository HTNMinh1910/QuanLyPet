package com.example.quanlypet.ui.activity;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.quanlypet.R;
import com.example.quanlypet.adapter.Spinner.SpinnerAnimal;
import com.example.quanlypet.adapter.Spinner.SpinnerDoctor;
import com.example.quanlypet.database.AnimalDB;
import com.example.quanlypet.database.BookDB;
import com.example.quanlypet.database.DoctorDB;
import com.example.quanlypet.database.UsersDB;
import com.example.quanlypet.model.AnimalObj;
import com.example.quanlypet.model.BookObj;
import com.example.quanlypet.model.DoctorObj;
import com.example.quanlypet.model.UsersObj;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import java.io.ByteArrayOutputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

public class AddBooking extends AppCompatActivity {
    private ImageView imgClose;
    private Button btnKhamvachua;
    private Button btnKiemtrasuckhoe;
    private Button btnTiemphong;
    private Button btnPhauthuat;
    private Button btnSieuam;
    private Button btnSpa;
    private Toolbar toolbar_booking;
    private Spinner spnDoctor;
    private TextInputLayout TIPNameDoctor;
    private TextInputEditText TIEDNameDoctor;
    private TextInputLayout TIPPhoneNumber;
    private TextInputEditText TIEDPhoneNumber;
    private Spinner spnPet;
    private TextInputLayout TIPNamePet;
    private TextInputEditText TIEDNamePet;
    private TextInputLayout TIPTypePet;
    private TextInputEditText TIEDTypePet;
    private TextInputLayout TIPStatus;
    private TextInputEditText TIEDStatus;
    private ImageView imgPicture;
    private Button btnCamera;
    private Button btnAlbum;
    private RadioGroup rdogr;
    private RadioButton rdoPhongkham;
    private RadioButton rdoTainha;
    private TextInputLayout TIPTime;
    private TextInputEditText TIEDTime;
    private Bitmap bitmap;


    private TextInputLayout TIPAddress;
    private TextInputEditText TIEDAddress;
    private TextInputLayout TIPService;
    private TextInputEditText TIEDService;
    private Button btnBooking;
    SpinnerAnimal adapterSPNAnimal;
    SpinnerDoctor adapterSPNDoctor;
    private String noikham;
    private int idDoctor;
    private int idPet;
    private ImageView imgDate;

    List<AnimalObj> listAnimal = new ArrayList<>();
    List<DoctorObj> listDoctor = new ArrayList<>();
    UsersObj usersObj = new UsersObj();
    private SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm dd-MM-yyyy");
    int mYear, mMonth, mDate, mHour, mMinute;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_booking);
        findID();
        DatePickerDialog.OnDateSetListener date = (datePicker, year, monthOfYear, dayOfMonth) -> {
            mYear = year;
            mMonth = monthOfYear;
            mDate = dayOfMonth;
        };
        TimePickerDialog.OnTimeSetListener time = ((timePicker, hourOfDay, minute) -> {
            mHour = hourOfDay;
            mMinute = minute;
            GregorianCalendar calendar = new GregorianCalendar(mYear, mMonth, mDate, mHour, mMinute);
            TIEDTime.setText(dateFormat.format(calendar.getTime()));
        });
        btnCamera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);

                startActivityForResult(intent, 1);
            }
        });
        btnAlbum.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent();
                i.setType("image/*");
                i.setAction(Intent.ACTION_GET_CONTENT);
                chooseImg.launch(i);
            }
        });
        imgDate.setOnClickListener(view -> {
            Calendar calendar = Calendar.getInstance();
            mYear = calendar.get(Calendar.YEAR);
            mMonth = calendar.get(Calendar.MONTH);
            mDate = calendar.get(Calendar.DAY_OF_MONTH);
            mHour = calendar.get(Calendar.HOUR_OF_DAY);
            mMinute = calendar.get(Calendar.MINUTE);
            DatePickerDialog datePickerDialog = new DatePickerDialog(this, 0, date, mYear, mMonth, mDate);
            TimePickerDialog timePickerDialog = new TimePickerDialog(this,
                    android.R.style.Theme_Holo_Light_Dialog_NoActionBar,
                    time, mHour, mMinute, true);
            datePickerDialog.show();
            timePickerDialog.show();
        });
        adapterSPNAnimal = new SpinnerAnimal();
        listAnimal = AnimalDB.getInstance(this).Dao().getAllData();
        adapterSPNAnimal.setData(listAnimal);
        spnPet.setAdapter(adapterSPNAnimal);
        adapterSPNDoctor = new SpinnerDoctor();
        listDoctor = DoctorDB.getInstance(this).Dao().getAllData();
        adapterSPNDoctor.setDATA(listDoctor);
        spnDoctor.setAdapter(adapterSPNDoctor);
        toolbar_booking = findViewById(R.id.tbl_booking);
        toolbar_booking.setTitle("Đặt Lịch");

        TIEDService.setFocusable(false);
        TIEDService.setFocusableInTouchMode(false);
        TIEDNameDoctor.setFocusable(false);
        TIEDNameDoctor.setFocusableInTouchMode(false);
        TIEDNamePet.setFocusable(false);
        TIEDNamePet.setFocusableInTouchMode(false);
        TIEDPhoneNumber.setFocusable(false);
        TIEDPhoneNumber.setFocusableInTouchMode(false);
        TIEDTypePet.setFocusable(false);
        TIEDTypePet.setFocusableInTouchMode(false);
        rdogr.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId) {
                    case R.id.rdo_phongkham:
                        TIPAddress.setEnabled(false);
                        break;
                    case R.id.rdo_tainha:
                        TIPAddress.setEnabled(true);
                        break;
                }
            }
        });


        TIEDService.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDiaLogSerVice();
            }
        });
        SlectedSpinner();


        btnBooking.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addBooking();
            }
        });
    }

    ActivityResultLauncher<Intent> chooseImg = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(),
            new ActivityResultCallback<ActivityResult>() {
                @Override
                public void onActivityResult(ActivityResult result) {
                    if (result.getResultCode() == Activity.RESULT_OK) {
                        Intent data = result.getData();
                        Uri selectImgUri = data.getData();
                        if (selectImgUri != null) {
                            imgPicture.setImageURI(selectImgUri);
                        }
                        BitmapDrawable bitmapDrawable = (BitmapDrawable) imgPicture.getDrawable();
                        bitmap = bitmapDrawable.getBitmap();
                    }
                }
            }
    );

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1 && resultCode == RESULT_OK && data != null) {
            Bitmap bitmap = (Bitmap) data.getExtras().get("data");
            imgPicture.setImageBitmap(bitmap);

        }
    }

    private void addBooking() {

        String strTT = TIEDStatus.getText().toString();

        if (rdoPhongkham.isChecked()) {
            noikham = "Phòng Khám";
        } else if (rdoTainha.isChecked()) {
            noikham = "Tại nhà";
        }
        String strTime = TIEDTime.getText().toString();
        String strDiaChi = TIEDAddress.getText().toString();
        String strDichVU = TIEDService.getText().toString();

        BitmapDrawable bitmapDrawable = (BitmapDrawable) imgPicture.getDrawable();
        Bitmap bitmap = bitmapDrawable.getBitmap();
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, byteArrayOutputStream);
        byte[] anh = byteArrayOutputStream.toByteArray();
        SharedPreferences sharedPreferences = getSharedPreferences("user_file", MODE_PRIVATE);
        String user = sharedPreferences.getString("Username", "");

        usersObj = UsersDB.getInstance(this).Dao().getIdUsers(user);
        int id = usersObj.getId();
        BookObj bookObj = new BookObj(id, idDoctor, idPet, strTT, anh, strTime, noikham, strDiaChi, strDichVU, 1);
        BookDB.getInstance(this).Dao().insert(bookObj);
        Toast.makeText(this, "Đã thêm thành công", Toast.LENGTH_SHORT).show();

    }

    public void SlectedSpinner() {
        spnDoctor.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                listDoctor = DoctorDB.getInstance(getApplicationContext()).Dao().getAllData();
                idDoctor = listDoctor.get(position).getId();
                TIEDNameDoctor.setText(listDoctor.get(position).getName());
                TIEDPhoneNumber.setText(listDoctor.get(position).getPhone());
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });
        spnPet.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                listAnimal = AnimalDB.getInstance(getApplicationContext()).Dao().getAllData();
                idPet = listAnimal.get(position).getId();
                TIEDNamePet.setText(listAnimal.get(position).getName());
                TIEDTypePet.setText(listAnimal.get(position).getSpecies());
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });
    }

    public void findID() {
        spnDoctor = (Spinner) findViewById(R.id.spn_doctor);
        TIPNameDoctor = (TextInputLayout) findViewById(R.id.TIP_NameDoctor);
        TIEDNameDoctor = (TextInputEditText) findViewById(R.id.TIED_NameDoctor);
        TIPPhoneNumber = (TextInputLayout) findViewById(R.id.TIP_PhoneNumber);
        TIEDPhoneNumber = (TextInputEditText) findViewById(R.id.TIED_PhoneNumber);
        spnPet = (Spinner) findViewById(R.id.spn_Pet);
        TIPNamePet = (TextInputLayout) findViewById(R.id.TIP_NamePet);
        TIEDNamePet = (TextInputEditText) findViewById(R.id.TIED_NamePet);
        TIPTypePet = (TextInputLayout) findViewById(R.id.TIP_TypePet);
        TIEDTypePet = (TextInputEditText) findViewById(R.id.TIED_TypePet);
        TIPStatus = (TextInputLayout) findViewById(R.id.TIP_Status);
        TIEDStatus = (TextInputEditText) findViewById(R.id.TIED_Status);
        imgPicture = (ImageView) findViewById(R.id.img_picture);
        btnCamera = (Button) findViewById(R.id.btn_camera);
        btnAlbum = (Button) findViewById(R.id.btn_album);
        rdogr = (RadioGroup) findViewById(R.id.rdogr);
        rdoPhongkham = (RadioButton) findViewById(R.id.rdo_phongkham);
        rdoTainha = (RadioButton) findViewById(R.id.rdo_tainha);
        TIPAddress = (TextInputLayout) findViewById(R.id.TIP_Address);
        TIEDAddress = (TextInputEditText) findViewById(R.id.TIED_Address);
        TIPService = (TextInputLayout) findViewById(R.id.TIP_Service);
        TIEDService = (TextInputEditText) findViewById(R.id.TIED_Service);
        btnBooking = (Button) findViewById(R.id.btn_booking);
        TIPTime = (TextInputLayout) findViewById(R.id.TIP_Time);
        TIEDTime = (TextInputEditText) findViewById(R.id.TIED_Time);
        imgDate = (ImageView) findViewById(R.id.img_date);
    }

    public void showDiaLogSerVice() {
        Dialog dialog = new Dialog(this);
        dialog.setContentView(R.layout.dialog_chon_dich_vu);
        dialog.getWindow().setBackgroundDrawable(getDrawable(R.drawable.bg_dialog_dichvu));
        Window window = dialog.getWindow();
        window.setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.WRAP_CONTENT);
        WindowManager.LayoutParams windowAttributes = window.getAttributes();
        window.setAttributes(windowAttributes);
        windowAttributes.gravity = Gravity.BOTTOM;
        imgClose = (ImageView) dialog.findViewById(R.id.img_close);
        btnKhamvachua = (Button) dialog.findViewById(R.id.btn_khamvachua);
        btnKiemtrasuckhoe = (Button) dialog.findViewById(R.id.btn_kiemtrasuckhoe);
        btnTiemphong = (Button) dialog.findViewById(R.id.btn_tiemphong);
        btnPhauthuat = (Button) dialog.findViewById(R.id.btn_phauthuat);
        btnSieuam = (Button) dialog.findViewById(R.id.btn_sieuam);
        btnSpa = (Button) dialog.findViewById(R.id.btn_spa);
        imgClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
        btnKhamvachua.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TIEDService.setText("Khám và Chữa");
                dialog.dismiss();
            }
        });
        btnKiemtrasuckhoe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TIEDService.setText("Kiểm tra sức khỏe");
                dialog.dismiss();
            }
        });
        btnTiemphong.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TIEDService.setText("Tiêm Phòng");
                dialog.dismiss();
            }
        });
        btnPhauthuat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TIEDService.setText("Phẫu Thuật");
                dialog.dismiss();
            }
        });

        btnSieuam.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TIEDService.setText("Siêu Âm");
                dialog.dismiss();
            }
        });
        btnSpa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TIEDService.setText("Spa - Cắt & Tỉa");
                dialog.dismiss();
            }
        });
        dialog.show();
    }
}