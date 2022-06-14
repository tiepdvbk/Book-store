package com.example.bookstoreapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.RelativeLayout;

import com.example.bookstoreapp.CaNhan.PersonalFragment;
import com.example.bookstoreapp.DanhMuc.ListFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

public class home extends AppCompatActivity {

    private BottomNavigationView nav;
    RelativeLayout rlTop ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        anhXa();
        khoiTao();
        setUp();
    }
    private void anhXa(){
        nav = findViewById(R.id.nav_btn);
        rlTop = findViewById(R.id.rlTop);
    }
    private void setUp() {
        nav.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @SuppressLint("NonConstantResourceId")
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                Fragment selectedFragment = null;
                switch (item.getItemId()){
                    case R.id.itHome:
                        selectedFragment = HomeFragment.newInstance();
                        break;
                    case R.id.itList:
                        selectedFragment = ListFragment.newInstance();
                        break;
                    case R.id.itMy:
                        selectedFragment = PersonalFragment.newInstance();
                        break;
                }
                int commit = getSupportFragmentManager().beginTransaction().replace(R.id.frHome, selectedFragment).commit();
                return true;
            }
        });

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        this.finishAffinity();
    }

    private void khoiTao() {
        Fragment selectedFragment = null;
        selectedFragment = HomeFragment.newInstance();
        int commit = getSupportFragmentManager().beginTransaction().replace(R.id.frHome, selectedFragment).commit();
    }

}