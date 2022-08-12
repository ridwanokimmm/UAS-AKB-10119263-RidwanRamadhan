package com.example.uas_10119263_ridwanramadhan.View;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import com.example.uas_10119263_ridwanramadhan.Presenter.MainPresenter;
import com.example.uas_10119263_ridwanramadhan.R;

// NIM : 10119263
// Nama : Ridwan Ramadhan
// Kelas : IF-7

public class SplashScreen extends AppCompatActivity implements MainPresenter.View {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);
        HideTaskbar();
        DoSplashscreen();
    }

    @Override
    public void DoSplashscreen() {
        int SPLASH_DISPLAY_LENGTH = 3000;
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent mainIntent = new Intent(SplashScreen.this, Login.class);
                SplashScreen.this.startActivity(mainIntent);
                SplashScreen.this.finish();
            }
        }, SPLASH_DISPLAY_LENGTH);
    }

    @Override
    public void HideTaskbar() {
        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();
    }
}