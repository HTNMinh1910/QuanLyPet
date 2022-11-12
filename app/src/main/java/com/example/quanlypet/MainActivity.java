package com.example.quanlypet;

import android.os.Bundle;
import android.view.MenuItem;
import android.widget.FrameLayout;

import com.example.quanlypet.ui.Fragment.AnimalFragment;
import com.example.quanlypet.ui.Fragment.BillFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.google.android.material.navigation.NavigationView;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    private DrawerLayout idDrawlayout;
    private Toolbar idToolbar;
    private FrameLayout idLayoutcontent;
    private NavigationView idNaviView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        idDrawlayout = (DrawerLayout) findViewById(R.id.id_drawlayout);
        idToolbar = (Toolbar) findViewById(R.id.id_toolbar);
        idLayoutcontent = (FrameLayout) findViewById(R.id.id_layoutcontent);
        idNaviView = (NavigationView) findViewById(R.id.id_naviView);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, idDrawlayout, idToolbar, R.string.openNav, R.string.closeNav);
        toggle.syncState();
        idNaviView = findViewById(R.id.id_naviView);
        idNaviView.setNavigationItemSelectedListener(this);
        replaceFragmet(AnimalFragment.newInstance());
        idToolbar.setTitle("Animal");
    }
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.animal){
            idToolbar.setTitle("Animal");
            replaceFragmet(AnimalFragment.newInstance());
        }
        if (id == R.id.bild){
            idToolbar.setTitle("Bill");
            replaceFragmet(BillFragment.newInstance());
        }
        idDrawlayout.closeDrawer(idNaviView);
        return true;
    }
    public void replaceFragmet(Fragment fragment){
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.id_layoutcontent, fragment);
        transaction.commit();
    }
}