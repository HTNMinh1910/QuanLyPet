package com.example.quanlypet.ui.activity;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.quanlypet.R;
import com.example.quanlypet.adapter.animal.AnimalAdapter;
import com.example.quanlypet.dao.AnimalDao;
import com.example.quanlypet.database.AnimalDB;
import com.example.quanlypet.model.AnimalObj;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;

public class AddAnimalAcitvity extends AppCompatActivity {
    private RecyclerView rcvAnimal;
    private FloatingActionButton bbtn;
    private ArrayList<AnimalObj> arrayList = new ArrayList<>();
    private AnimalAdapter adapterAnimal;
    private AnimalDao loaiSachDao;
    private Bitmap bitmap;
    private TextView title;
    private EditText edIdUsers;
    private EditText edNameAnimal;
    private ImageView imgAnh;
    private ImageView imgAnhup;
    private Button btnAddanh;
    private EditText edAgeAnimal;
    private EditText edSpeciesAnimal;
    private Button btnAddAnimal;
    private Button btnCancel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_animal);

        edIdUsers = (EditText) findViewById(R.id.ed_idUsers);
        edNameAnimal = (EditText)findViewById(R.id.ed_nameAnimal);
        imgAnh = (ImageView) findViewById(R.id.img_anh);
        LinearLayout linearshare = findViewById(R.id.liner_share_animal);
        linearshare.setOnClickListener(v -> {
            Intent i = new Intent();
            i.setType("image/*");
            i.setAction(Intent.ACTION_GET_CONTENT);
            chooseImage.launch(i);
        });
        btnAddanh = (Button) findViewById(R.id.btn_addanh);
        btnAddanh.setOnClickListener(v -> {
            Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
            onActivityResult(intent,0);
        });
        edAgeAnimal = (EditText) findViewById(R.id.ed_ageAnimal);
        edSpeciesAnimal = (EditText) findViewById(R.id.ed_speciesAnimal);
        btnAddAnimal = (Button) findViewById(R.id.btn_addAnimal);
        btnAddAnimal.setOnClickListener(v -> {
            int idUser = Integer.parseInt(edIdUsers.getText().toString().trim());
            String namean = edNameAnimal.getText().toString().trim();
            BitmapDrawable bitmapDrawable = (BitmapDrawable) imgAnh.getDrawable();
            Bitmap bitmap = bitmapDrawable.getBitmap();
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            bitmap.compress(Bitmap.CompressFormat.PNG, 100, byteArrayOutputStream);
            byte[] anh = byteArrayOutputStream.toByteArray();
            int age = Integer.parseInt(edAgeAnimal.getText().toString().trim());
            String species = edSpeciesAnimal.getText().toString().trim();
            if (namean.isEmpty() || species.isEmpty()) {
                Toast.makeText(getApplicationContext(), "ko dc de trong", Toast.LENGTH_SHORT).show();
            } else {
                AnimalObj object = new AnimalObj(idUser, namean, anh, age, species,1);
                AnimalDB.getInstance(getApplicationContext()).animalDao().insert(object);
                Toast.makeText(getApplicationContext(), "them thanh cong", Toast.LENGTH_SHORT).show();
            }
        });
        btnCancel = (Button) findViewById(R.id.btn_cancel);
        btnCancel.setOnClickListener(v -> {
            edIdUsers.setText("");
            edNameAnimal.setText("");
            edAgeAnimal.setText("");
            edSpeciesAnimal.setText("");
        });
    }

    private void onActivityResult(Intent intent, int i) {
        Bitmap bp = (Bitmap) intent.getExtras().get("data");
        imgAnh.setImageBitmap(bp);
    }

    ActivityResultLauncher<Intent> chooseImage = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(),
            new ActivityResultCallback<ActivityResult>() {
                @Override
                public void onActivityResult(ActivityResult result) {
                    if (result.getResultCode() == Activity.RESULT_OK) {
                        Intent data = result.getData();
                        Uri selectedImageUri = data.getData();
                        if (null != selectedImageUri) {
                            imgAnh.setImageURI(selectedImageUri);
                            BitmapDrawable bitmapDrawable = (BitmapDrawable) imgAnh.getDrawable();
                            bitmap = bitmapDrawable.getBitmap();
                        }
                    }
                }
            });
}