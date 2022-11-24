package com.example.quanlypet.ui.fragment;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.SearchView;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.ViewPager2;

import com.example.quanlypet.R;
import com.example.quanlypet.adapter.Spinner.SpinnerAnimal;
import com.example.quanlypet.adapter.Spinner.SpinnerDoctor;
import com.example.quanlypet.adapter.booking.ViewPager2_Booking_Adapter;
import com.example.quanlypet.adapter.booking.bookingAdapter;
import com.example.quanlypet.adapter.booking.booking_admin_Adapter;
import com.example.quanlypet.database.AnimalDB;
import com.example.quanlypet.database.BookDB;
import com.example.quanlypet.database.DoctorDB;
import com.example.quanlypet.database.UsersDB;
import com.example.quanlypet.model.AnimalObj;
import com.example.quanlypet.model.BookObj;
import com.example.quanlypet.model.DoctorObj;
import com.example.quanlypet.model.UsersObj;
import com.google.android.material.tabs.TabItem;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import java.io.ByteArrayOutputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 * Use the {@link BookFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class BookFragment extends Fragment implements bookingAdapter.Callback {
    private int REQUEST_CAMERA = 111;
    private Bitmap bitmap;
    private ImageView imgClose;
    private Button btnKhamvachua;
    private Button btnKiemtrasuckhoe;
    private Button btnTiemphong;
    private Button btnPhauthuat;
    private Button btnSieuam;
    private Button btnSpa;
    List<BookObj> list;
    List<BookObj> list2;
    bookingAdapter adapter;
    booking_admin_Adapter adapterAdmin;
    RecyclerView reCy_booking;
    UsersObj usersObj = new UsersObj();
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
    private TextInputLayout TIPAddress;
    private TextInputEditText TIEDAddress;
    private TextInputLayout TIPService;
    private TextInputEditText TIEDService;
    private Button btnUpdate;
    private Button btnHuy;
    private String noikham;
    private int idPet;
    SpinnerAnimal adapterSPNAnimal;
    SpinnerDoctor adapterSPNDoctor;
    List<AnimalObj> listAnimal;
    List<DoctorObj> listDoctor;
    private int idDoctor;
    private SimpleDateFormat formatDate = new SimpleDateFormat("yyyy-MM-dd");
    int itYear, itMonth, itDate;
    private ImageView imgDate;
    private SearchView searchDateBoock;
    private TabLayout tablayout;
    private TabItem tab1;
    private TabItem tab2;
    private TabItem tab3;
    private TabItem tab4;
    private ViewPager2 viewpagerTablayout;

    public BookFragment() {
    }

    public static BookFragment newInstance() {
        BookFragment fragment = new BookFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_book, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        reCy_booking = view.findViewById(R.id.recy_booking);
        searchDateBoock = (SearchView) view.findViewById(R.id.search_date_boock);
        list = new ArrayList<>();

        searchDateBoock.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                adapter.getFilter().filter(query);
                return false;
            }
            @Override
            public boolean onQueryTextChange(String newText) {
                adapter.getFilter().filter(newText);
                return false;
            }
        });
        SharedPreferences sharedPreferences = getActivity().getSharedPreferences("user_file", Context.MODE_PRIVATE);
        String user = sharedPreferences.getString("Username", "");
        Toast.makeText(getActivity(), "" + user, Toast.LENGTH_SHORT).show();
        if (user.equalsIgnoreCase("Admin")) {
            list = BookDB.getInstance(getActivity()).Dao().getAllData();
            adapter = new bookingAdapter(this,getActivity());
            adapter.setDATA(list);
            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
            reCy_booking.setAdapter(adapter);
            reCy_booking.setLayoutManager(linearLayoutManager);
        } else {
            usersObj = UsersDB.getInstance(getActivity()).Dao().getIdUsers(user);
            int id = usersObj.getId();
            list = BookDB.getInstance(getActivity()).Dao().getAllDataFromID(id);
            adapter = new bookingAdapter(this,getActivity());
            adapter.setDATA(list);
            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
            reCy_booking.setAdapter(adapter);
            reCy_booking.setLayoutManager(linearLayoutManager);
        }
    }
    @Override
    public void update(BookObj bookObj, int index) {
        updateBooking(bookObj,index);
    }
    private void updateBooking(BookObj bookObj, int index) {
        final Dialog dialog = new Dialog(getActivity(), com.google.android.material.R.style.Widget_Material3_MaterialCalendar_Fullscreen);

        dialog.setContentView(R.layout.update_booking);
        Toolbar toolbar = dialog.findViewById(R.id.tbl_booking);
        toolbar.setTitle("Chi tiết Lịch Đặt");
        spnDoctor = (Spinner) dialog.findViewById(R.id.spn_doctor);
        TIPNameDoctor = (TextInputLayout) dialog.findViewById(R.id.TIP_NameDoctor);
        TIEDNameDoctor = (TextInputEditText) dialog.findViewById(R.id.TIED_NameDoctor);
        TIPPhoneNumber = (TextInputLayout) dialog.findViewById(R.id.TIP_PhoneNumber);
        TIEDPhoneNumber = (TextInputEditText) dialog.findViewById(R.id.TIED_PhoneNumber);
        spnPet = (Spinner) dialog.findViewById(R.id.spn_Pet);
        TIPNamePet = (TextInputLayout) dialog.findViewById(R.id.TIP_NamePet);
        TIEDNamePet = (TextInputEditText) dialog.findViewById(R.id.TIED_NamePet);
        TIPTypePet = (TextInputLayout) dialog.findViewById(R.id.TIP_TypePet);
        TIEDTypePet = (TextInputEditText) dialog.findViewById(R.id.TIED_TypePet);
        TIPStatus = (TextInputLayout) dialog.findViewById(R.id.TIP_Status);
        TIEDStatus = (TextInputEditText) dialog.findViewById(R.id.TIED_Status);
        imgPicture = (ImageView) dialog.findViewById(R.id.img_picture);
        btnCamera = (Button) dialog.findViewById(R.id.btn_camera);
        btnAlbum = (Button) dialog.findViewById(R.id.btn_album);
        rdogr = (RadioGroup) dialog.findViewById(R.id.rdogr);
        rdoPhongkham = (RadioButton) dialog.findViewById(R.id.rdo_phongkham);
        rdoTainha = (RadioButton) dialog.findViewById(R.id.rdo_tainha);
        TIPTime = (TextInputLayout) dialog.findViewById(R.id.TIP_Time);
        TIEDTime = (TextInputEditText) dialog.findViewById(R.id.TIED_Time);
        TIPAddress = (TextInputLayout) dialog.findViewById(R.id.TIP_Address);
        TIEDAddress = (TextInputEditText) dialog.findViewById(R.id.TIED_Address);
        TIPService = (TextInputLayout) dialog.findViewById(R.id.TIP_Service);
        TIEDService = (TextInputEditText) dialog.findViewById(R.id.TIED_Service);
        btnUpdate = (Button) dialog.findViewById(R.id.btn_update);
        btnHuy = (Button) dialog.findViewById(R.id.btn_huy);
        SlectedSpinner();
        TIEDStatus.setText(bookObj.getStatus());
        TIEDAddress.setText(bookObj.getAddress());
        TIEDTime.setText(bookObj.getTime());
        TIEDService.setText(bookObj.getService());
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
                        TIEDAddress.setText("");
        tablayout = (TabLayout) view.findViewById(R.id.tablayout);
        tab1 = (TabItem) view.findViewById(R.id.tab1);
        tab2 = (TabItem) view.findViewById(R.id.tab2);
        tab3 = (TabItem) view.findViewById(R.id.tab3);
        tab4 = (TabItem) view.findViewById(R.id.tab4);
        viewpagerTablayout = (ViewPager2) view.findViewById(R.id.viewpager_tablayout);
        ViewPager2_Booking_Adapter viewPager2_booking_adapter = new ViewPager2_Booking_Adapter(getActivity());
        viewpagerTablayout.setAdapter(viewPager2_booking_adapter);

        TabLayoutMediator tabLayoutMediator = new TabLayoutMediator(tablayout, viewpagerTablayout, new TabLayoutMediator.TabConfigurationStrategy() {
            @Override
            public void onConfigureTab(@NonNull TabLayout.Tab tab, int position) {
                switch (position) {
                    case 0:
                        tab.setText("Lịch Chờ");
                        break;
                    case 1:
                        tab.setText("Lịch Đã Xác Nhận");
                        break;
                    case 2:
                        tab.setText("Lịch Đã Hoàn Thành");
                        break;
                    case 3:
                        tab.setText("Lịch Đã Hủy ");
                        break;
                }
            }
        });
        tabLayoutMediator.attach();
    }
    @Override
    public void update(BookObj bookObj, int index) {
    }
}
