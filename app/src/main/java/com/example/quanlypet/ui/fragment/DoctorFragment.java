package com.example.quanlypet.ui.fragment;

import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.Toast;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.quanlypet.adapter.doctor.DoctorAdapter;
import com.example.quanlypet.R;
import com.example.quanlypet.database.DoctorDB;
import com.example.quanlypet.model.DoctorObj;
import com.example.quanlypet.ui.activity.AddDoctorActivity;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.textfield.TextInputEditText;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;



/**
 * A simple {@link Fragment} subclass.
 * Use the {@link DoctorFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class DoctorFragment extends Fragment implements DoctorAdapter.Callback {
    private FloatingActionButton floatingActionButton;
    private RecyclerView recyclerView;
    private DoctorAdapter adapter;
    private ArrayList<DoctorObj> list = new ArrayList<>();
    private int checkGender;
    private Bitmap bitmap;
    ImageView imgPicture;
    ImageView img;
    Button btnCamera;
    Button btnAlbum;
    public DoctorFragment() {
        // Required empty public constructor
    }

    // TODO: Rename and change types and number of parameters
    public static DoctorFragment newInstance() {
        DoctorFragment fragment = new DoctorFragment();
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
        return inflater.inflate(R.layout.fragment_docter, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        recyclerView = (RecyclerView) view.findViewById(R.id.rcv_docter);
        floatingActionButton = (FloatingActionButton) view.findViewById(R.id.floatingbutton);
        adapter = new DoctorAdapter(getActivity(), this);
        floatingActionButton.setOnClickListener(v -> {
            startActivity(new Intent(getContext(), AddDoctorActivity.class));
        });
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);
    }
    @Override
    public void onResume() {
        super.onResume();
        list = (ArrayList<DoctorObj>) DoctorDB.getInstance(getContext()).Dao().getAllData();
        adapter.setDataDocter(list);
    }
    @Override
    public void update(DoctorObj doctorObj) {
        final Dialog dialog = new Dialog(getContext());
        dialog.setContentView(R.layout.dialog_update_docter);
        dialog.setCancelable(false);
        Window window = dialog.getWindow();
        window.setLayout(RecyclerView.LayoutParams.MATCH_PARENT, RecyclerView.LayoutParams.WRAP_CONTENT);

        TextInputEditText edNameDocter = (TextInputEditText) dialog.findViewById(R.id.ed_nameDocter);
        imgPicture = dialog.findViewById(R.id.img_picture);
        btnCamera = dialog.findViewById(R.id.btn_camera);
        btnAlbum = dialog.findViewById(R.id.btn_album);

        TextInputEditText edPhoneDocter = (TextInputEditText) dialog.findViewById(R.id.ed_phoneDocter);
        RadioButton rdoBoy = (RadioButton) dialog.findViewById(R.id.rdo_boy);
        TextInputEditText edEmailDocter = (TextInputEditText) dialog.findViewById(R.id.ed_emailDocter);
        TextInputEditText edAddressDocter = (TextInputEditText) dialog.findViewById(R.id.ed_addressDocter);
        TextInputEditText edSpecializeDocter = (TextInputEditText) dialog.findViewById(R.id.ed_specializeDocter);
        Button btnUpdateDocter = (Button) dialog.findViewById(R.id.btn_updateDocter);
        Button btnCanel = (Button) dialog.findViewById(R.id.btn_canel);

        btnAlbum.setOnClickListener(v -> {
            Intent i = new Intent();
            i.setType("image/*");
            i.setAction(Intent.ACTION_GET_CONTENT);
            chooseImage1.launch(i);
        });
        btnCamera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(intent,0);
            }
        });
        btnUpdateDocter.setOnClickListener(v -> {

            String name = edNameDocter.getText().toString().trim();
            String phone = edPhoneDocter.getText().toString().trim();
            String email = edEmailDocter.getText().toString().trim();
            String address = edAddressDocter.getText().toString().trim();
            String specialize = edSpecializeDocter.getText().toString().trim();
            checkGender = rdoBoy.isChecked()?1:0;

            BitmapDrawable bitmapDrawableup = (BitmapDrawable) imgPicture.getDrawable();
            Bitmap bitmap = bitmapDrawableup.getBitmap();
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            bitmap.compress(Bitmap.CompressFormat.PNG, 100, byteArrayOutputStream);
            byte[] hinhanh = byteArrayOutputStream.toByteArray();

            if (name.isEmpty() || phone.isEmpty() || email.isEmpty() || address.isEmpty() || specialize.isEmpty()) {
                Toast.makeText(getActivity(), "Không được để trống", Toast.LENGTH_SHORT).show();
            } else {
                doctorObj.setName(name);
                doctorObj.setImg(hinhanh);
                doctorObj.setPhone(phone);
                doctorObj.setEmail(email);
                doctorObj.setAddress(address);
                doctorObj.setSpecialize(specialize);
                DoctorDB.getInstance(getActivity()).Dao().edit(doctorObj);
                list = (ArrayList<DoctorObj>) DoctorDB.getInstance(getActivity()).Dao().getAllData();
                adapter.setDataDocter(list);
                Toast.makeText(getActivity(), "Sửa thành công", Toast.LENGTH_SHORT).show();
                dialog.cancel();
            }
        });
        btnCanel.setOnClickListener(v -> {
            dialog.cancel();
        });
        dialog.show();
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Bitmap bitmap = (Bitmap) data.getExtras().get("data");
        imgPicture.setImageBitmap(bitmap);
    }

    ActivityResultLauncher<Intent> chooseImage1 = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(),
            new ActivityResultCallback<ActivityResult>() {
                @Override
                public void onActivityResult(ActivityResult result) {
                    if (result.getResultCode() == Activity.RESULT_OK) {
                        Intent data = result.getData();
                        Uri selectedImageUri = data.getData();
                        if (null != selectedImageUri) {
                            imgPicture.setImageURI(selectedImageUri);
//                            txtImage.setText("Lựa chọn lại hình ảnh");
                            BitmapDrawable bitmapDrawable = (BitmapDrawable) imgPicture.getDrawable();
                            bitmap = bitmapDrawable.getBitmap();
                        }
                    }
                }
            });
}