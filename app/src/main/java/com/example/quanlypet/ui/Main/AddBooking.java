package com.example.quanlypet.ui.Main;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.app.Dialog;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;

import com.example.quanlypet.R;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

public class AddBooking extends AppCompatActivity {
    Toolbar tlb_booking;
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
    private TextInputLayout TIPTIME;
    private TextInputEditText TIEDTime;
    private TextInputLayout TIPAddress;
    private TextInputEditText TIEDAddress;
    private TextInputLayout TIPService;
    private TextInputEditText TIEDService;
    private Button btnBooking;
    private ImageView imgClose;
    private Button btnKhamvachua;
    private Button btnKiemtrasuckhoe;
    private Button btnTiemphong;
    private Button btnPhauthuat;
    private Button btnSieuam;
    private Button btnSpa;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_booking);
        findID();
        tlb_booking.setTitle("Đặt Lịch");
        TIEDService.setFocusable(false);
        TIEDService.setFocusableInTouchMode(false);
        TIEDService.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDiaLogSerVice();
            }
        });

    }

    public void findID() {
        tlb_booking = findViewById(R.id.tlb_booking);
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
        TIPTIME = (TextInputLayout) findViewById(R.id.TIP_TIME);
        TIEDTime = (TextInputEditText) findViewById(R.id.TIED_Time);
        TIPAddress = (TextInputLayout) findViewById(R.id.TIP_Address);
        TIEDAddress = (TextInputEditText) findViewById(R.id.TIED_Address);
        TIPService = (TextInputLayout) findViewById(R.id.TIP_Service);
        TIEDService = (TextInputEditText) findViewById(R.id.TIED_Service);
        btnBooking = (Button) findViewById(R.id.btn_booking);

    }
    public void showDiaLogSerVice(){


        Dialog dialog = new Dialog(this);
        dialog.setContentView(R.layout.dialog_chondichvu_booking);
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