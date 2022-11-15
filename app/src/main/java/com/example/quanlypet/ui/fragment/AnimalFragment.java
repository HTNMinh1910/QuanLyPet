package com.example.quanlypet.ui.fragment;

import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.quanlypet.adapter.animal.AnimalAdapter;
import com.example.quanlypet.R;
import com.example.quanlypet.dao.AnimalDao;
import com.example.quanlypet.database.AnimalDB;
import com.example.quanlypet.model.AnimalObj;
import com.example.quanlypet.ui.AnimalActivity.AddAnimalAcitvity;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;

public class AnimalFragment extends Fragment implements AnimalAdapter.Callback {
    private RecyclerView rcvAnimal;
    private FloatingActionButton bbtn;
    private ArrayList<AnimalObj> arrayList = new ArrayList<>();
    private AnimalAdapter adapterAnimal;
    private AnimalDao loaiSachDao;

    private Bitmap bitmap;
    private ImageView imgAnh;
    private ImageView imgAnhup;

    public AnimalFragment() {
        // Required empty public constructor
    }


    // TODO: Rename and change types and number of parameters
    public static AnimalFragment newInstance() {
        AnimalFragment fragment = new AnimalFragment();
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
        return inflater.inflate(R.layout.fragment_animal, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        rcvAnimal = (RecyclerView) view.findViewById(R.id.rcv_animal);
        bbtn = (FloatingActionButton) view.findViewById(R.id.bbtn);
        fill();
        bbtn.setOnClickListener(v -> {
            startActivity(new Intent(getContext(), AddAnimalAcitvity.class));
        });
    }

    public void fill() {
        arrayList = (ArrayList<AnimalObj>) AnimalDB.getInstance(getActivity()).animalDao().getAllData();
        adapterAnimal = new AnimalAdapter(getContext(), this);
        adapterAnimal.setData(arrayList);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
        rcvAnimal.setLayoutManager(layoutManager);
        rcvAnimal.setAdapter(adapterAnimal);

    }

    @Override
    public void onResume() {
        super.onResume();
        arrayList = (ArrayList<AnimalObj>) AnimalDB.getInstance(getActivity()).animalDao().getAllData();
    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        Bitmap bp = (Bitmap) data.getExtras().get("data");
        imgAnh.setImageBitmap(bp);
    }
    @Override
    public void Update(AnimalObj object) {
        final Dialog dialog = new Dialog(getContext());
        dialog.setContentView(R.layout.dialog_update_animal);
        dialog.setCancelable(false);
        Window window = dialog.getWindow();
        window.setLayout(RecyclerView.LayoutParams.MATCH_PARENT, RecyclerView.LayoutParams.WRAP_CONTENT);
        dialog.show();
        EditText upIdUser = dialog.findViewById(R.id.up_idUsers);
        EditText upnameAnimal = dialog.findViewById(R.id.up_nameAnimal);
        imgAnhup = dialog.findViewById(R.id.up_img_anh);
        LinearLayout linearshare1 = dialog.findViewById(R.id.up_liner_share_animal);
        linearshare1.setOnClickListener(v -> {
            Intent i = new Intent();
            i.setType("image/*");
            i.setAction(Intent.ACTION_GET_CONTENT);

            chooseImage1.launch(i);
        });
        Button btnAddAnh = dialog.findViewById(R.id.btn_addanh);
        btnAddAnh.setOnClickListener(v -> {
            Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
            startActivityForResult(intent, 0);
        });
        EditText upageAnimal = dialog.findViewById(R.id.up_ageAnimal);
        EditText upspeciesAnimal = dialog.findViewById(R.id.up_speciesAnimal);
        upIdUser.setText(object.getId_users()+"");
        upnameAnimal.setText(object.getName());
        byte[] anh = object.getAvatar();
        Bitmap bitmap1 = BitmapFactory.decodeByteArray(anh, 0, anh.length);
        imgAnhup.setImageBitmap(bitmap1);
        upageAnimal.setText(object.getAge()+"");
        upspeciesAnimal.setText(object.getSpecies());
        Button btnUpDate = dialog.findViewById(R.id.btn_updateAnimal);
        btnUpDate.setOnClickListener(v -> {
            int iduser = Integer.parseInt(upIdUser.getText().toString().trim());
            String nameAnimal = upnameAnimal.getText().toString().trim();
            BitmapDrawable bitmapDrawableup = (BitmapDrawable) imgAnhup.getDrawable();
            Bitmap bitmap = bitmapDrawableup.getBitmap();
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            bitmap.compress(Bitmap.CompressFormat.PNG, 100, byteArrayOutputStream);
            byte[] anhup = byteArrayOutputStream.toByteArray();
            int age = Integer.parseInt(upageAnimal.getText().toString().trim());
            String speciesAnimal = upspeciesAnimal.getText().toString().trim();
            if (nameAnimal.isEmpty() || speciesAnimal.isEmpty()) {
                Toast.makeText(getActivity(), "ko dc de trong", Toast.LENGTH_SHORT).show();
            } else {
                object.setId_users(iduser);
                object.setName(nameAnimal);
                object.setAvatar(anhup);
                object.setAge(age);
                object.setSpecies(speciesAnimal);
                AnimalDB.getInstance(getActivity()).animalDao().edit(object);
                arrayList = (ArrayList<AnimalObj>) AnimalDB.getInstance(getActivity()).animalDao().getAllData();
                adapterAnimal.setData(arrayList);
                Toast.makeText(getActivity(), "sua thanh cong", Toast.LENGTH_SHORT).show();
                dialog.cancel();
            }
        });
        Button btnCancel = dialog.findViewById(R.id.btn_cancel);
        btnCancel.setOnClickListener(v -> {
            dialog.cancel();
        });
        dialog.show();
    }

    ActivityResultLauncher<Intent> chooseImage1 = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(),
            new ActivityResultCallback<ActivityResult>() {
                @Override
                public void onActivityResult(ActivityResult result) {
                    if (result.getResultCode() == Activity.RESULT_OK) {
                        Intent data1 = result.getData();
                        Uri selectedImageUri1 = data1.getData();
                        if (null != selectedImageUri1) {
                            imgAnhup.setImageURI(selectedImageUri1);
//                            txtImage.setText("Lựa chọn lại hình ảnh");

                            BitmapDrawable bitmapDrawable = (BitmapDrawable) imgAnhup.getDrawable();
                            bitmap = bitmapDrawable.getBitmap();
                        }
                    }
                }
            });
}