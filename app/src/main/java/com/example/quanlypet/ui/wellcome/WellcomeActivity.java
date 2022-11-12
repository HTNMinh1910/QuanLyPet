package com.example.quanlypet.ui.wellcome;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import com.example.quanlypet.MainActivity;
import com.example.quanlypet.R;

public class WellcomeActivity extends AppCompatActivity {
    private Button btnAdmin;
    private Button btnUsers;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wellcome);

        btnAdmin = (Button) findViewById(R.id.btn_admin);
        btnUsers = (Button) findViewById(R.id.btn_users);
        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
        btnAdmin.setOnClickListener(view -> {
            intent.putExtra("requet", "admin");
            startActivity(new Intent(getApplicationContext(),LoginActivity.class));
        });
        btnUsers.setOnClickListener(view -> {
            intent.putExtra("requet", "users");
            startActivity(new Intent(getApplicationContext(),LoginActivity.class));
        });

    }
}