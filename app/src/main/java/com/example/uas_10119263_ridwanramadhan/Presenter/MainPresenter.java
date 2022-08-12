package com.example.uas_10119263_ridwanramadhan.Presenter;

import com.example.uas_10119263_ridwanramadhan.Model.MainModel;

// NIM : 10119263
// Nama : Ridwan Ramadhan
// Kelas : IF-7

public class MainPresenter {

    private View view;

    public MainPresenter(View view) {
        this.view = view;
    }

    public interface View{
        void DoSplashscreen();
        void HideTaskbar();
    }

}
