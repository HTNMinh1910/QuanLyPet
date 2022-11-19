package com.example.quanlypet.ui.activity;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.quanlypet.R;
import com.example.quanlypet.adapter.animal.AnimalAdapter;
import com.example.quanlypet.database.AnimalDB;
import com.example.quanlypet.model.AnimalObj;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;

public class AnimalActivity extends AppCompatActivity implements AnimalAdapter.Callback{
    private Toolbar idTollBarAnimal;
    private RecyclerView rcvAnimal;
    private Toolbar Tbr;
    private ArrayList<AnimalObj> arrayList = new ArrayList<>();
    private AnimalAdapter adapterAnimal;
    private ImageView imgAnhup;
    private Bitmap bitmap;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_animal);
        idTollBarAnimal = (Toolbar) findViewById(R.id.id_tollBarAnimal);
        rcvAnimal = (RecyclerView) findViewById(R.id.rcv_animal);
        Tbr = findViewById(R.id.id_tollBarAnimal);
        setSupportActionBar(Tbr);
        getSupportActionBar().setTitle("animal");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        fill();
    }
    @Override
    public void onResume() {
        super.onResume();
        arrayList = (ArrayList<AnimalObj>) AnimalDB.getInstance(getApplicationContext()).animalDao().getAllData();
        adapterAnimal.setData(arrayList);
    }
    public void fill() {
        arrayList = (ArrayList<AnimalObj>) AnimalDB.getInstance(getApplicationContext()).animalDao().getAllData();
        adapterAnimal = new AnimalAdapter(getApplicationContext(), this);
        adapterAnimal.setData(arrayList);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.VERTICAL, false);
        rcvAnimal.setLayoutManager(layoutManager);
        rcvAnimal.setAdapter(adapterAnimal);

    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.meu_add_animal, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.add_animal_menu:
                startActivity(new Intent(getApplicationContext(), AddAnimalAcitvity.class));
                break;
        }
        return super.onOptionsItemSelected(item);
    }
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 0 && requestCode == RESULT_OK && data != null){
            Bitmap bp = (Bitmap) data.getExtras().get("data");
            imgAnhup.setImageBitmap(bp);
        }

    }
    @Override
    public void Update(AnimalObj object) {
        Dialog dialog = new Dialog(AnimalActivity.this);
        dialog.setContentView(R.layout.dialog_update_animal);
        dialog.setCancelable(false);
        Window window = dialog.getWindow();
        window.setLayout(RecyclerView.LayoutParams.MATCH_PARENT, RecyclerView.LayoutParams.WRAP_CONTENT);
        dialog.show();
        EditText upnameAnimal = dialog.findViewById(R.id.up_nameAnimal);
        EditText upageAnimal = dialog.findViewById(R.id.up_ageAnimal);
        EditText upspeciesAnimal = dialog.findViewById(R.id.up_speciesAnimal);
        imgAnhup = dialog.findViewById(R.id.up_img_anh);
        Button btnAddAnh = dialog.findViewById(R.id.btn_addanh);
        Button btnAlbumUp = dialog.findViewById(R.id.btn_album_up);
        Button btnUpDate = dialog.findViewById(R.id.btn_updateAnimal);
        Button btnCancel = dialog.findViewById(R.id.btn_cancel);
        LinearLayout linearshare1 = dialog.findViewById(R.id.up_liner_share_animal);
        btnAlbumUp.setOnClickListener(v ->{
            Intent i = new Intent();
            i.setType("image/*");
            i.setAction(Intent.ACTION_GET_CONTENT);

            chooseImage1.launch(i);
        });
        btnAddAnh.setOnClickListener(v -> {
            Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
            startActivityForResult(intent, 0);
        });

        upnameAnimal.setText(object.getName());
        byte[] anh = object.getAvatar();
        Bitmap bitmap1 = BitmapFactory.decodeByteArray(anh, 0, anh.length);
        imgAnhup.setImageBitmap(bitmap1);
        upageAnimal.setText(object.getAge()+"");
        upspeciesAnimal.setText(object.getSpecies());
        btnUpDate.setOnClickListener(v -> {
            String nameAnimal = upnameAnimal.getText().toString().trim();
            BitmapDrawable bitmapDrawableup = (BitmapDrawable) imgAnhup.getDrawable();
            Bitmap bitmap = bitmapDrawableup.getBitmap();
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            bitmap.compress(Bitmap.CompressFormat.PNG, 100, byteArrayOutputStream);
            byte[] anhup = byteArrayOutputStream.toByteArray();
            int age = Integer.parseInt(upageAnimal.getText().toString().trim());
            String speciesAnimal = upspeciesAnimal.getText().toString().trim();
            if (nameAnimal.isEmpty() || speciesAnimal.isEmpty()) {
                Toast.makeText(getApplicationContext(), "ko dc de trong", Toast.LENGTH_SHORT).show();
            } else {
                object.setName(nameAnimal);
                object.setAvatar(anhup);
                object.setAge(age);
                object.setSpecies(speciesAnimal);
                AnimalDB.getInstance(getApplicationContext()).animalDao().edit(object);
                arrayList = (ArrayList<AnimalObj>) AnimalDB.getInstance(getApplicationContext()).animalDao().getAllData();
                adapterAnimal.setData(arrayList);
                Toast.makeText(getApplicationContext(), "sua thanh cong", Toast.LENGTH_SHORT).show();
                dialog.cancel();
            }
        });
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
                            BitmapDrawable bitmapDrawable = (BitmapDrawable) imgAnhup.getDrawable();
                            bitmap = bitmapDrawable.getBitmap();
                        }
                    }
                }
            });
}