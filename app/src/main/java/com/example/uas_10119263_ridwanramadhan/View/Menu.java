package com.example.uas_10119263_ridwanramadhan.View;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.view.MenuItem;

import com.example.uas_10119263_ridwanramadhan.Presenter.MainPresenter;
import com.example.uas_10119263_ridwanramadhan.R;
import com.example.uas_10119263_ridwanramadhan.View.Fragment.CatatanFragment;
import com.example.uas_10119263_ridwanramadhan.View.Fragment.InformasiAppFragment;
import com.example.uas_10119263_ridwanramadhan.View.Fragment.LogoutFragment;
import com.example.uas_10119263_ridwanramadhan.View.Fragment.ProfileFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

// NIM : 10119263
// Nama : Ridwan Ramadhan
// Kelas : IF-7

public class Menu extends AppCompatActivity implements MainPresenter.View{

    BottomNavigationView bottomNavigation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        HideTaskbar();

        bottomNavigation = findViewById(R.id.bottom_navigation);

        //seleksi pertama kali aplikasi dijalankan
        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new CatatanFragment()).commit();
        bottomNavigation.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                Fragment selectedFragment = null;

                switch (item.getItemId()){
                    case R.id.profile:
                        selectedFragment = new ProfileFragment();
                        break;
                    case R.id.catatan:
                        selectedFragment = new CatatanFragment();
                        break;
                    case R.id.about:
                        selectedFragment = new InformasiAppFragment();
                        break;
                    case R.id.logout:
                        selectedFragment = new LogoutFragment();
                        break;
                }

                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, selectedFragment).commit();

                return true;
            }
        });

    }

    @Override
    public void DoSplashscreen() {

    }

    @Override
    public void HideTaskbar() {
        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();
    }
}