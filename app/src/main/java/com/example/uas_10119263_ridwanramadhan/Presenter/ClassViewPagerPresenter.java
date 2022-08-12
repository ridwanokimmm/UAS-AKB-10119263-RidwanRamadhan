package com.example.uas_10119263_ridwanramadhan.Presenter;

import androidx.viewpager.widget.ViewPager;

import com.example.uas_10119263_ridwanramadhan.Model.ClassViewPagerModel;

// NIM : 10119263
// Nama : Ridwan Ramadhan
// Kelas : IF-7

public class ClassViewPagerPresenter {
    private ClassViewPagerModel viewnya;
    private View view;

    public ClassViewPagerPresenter(View view) {
        this.viewnya = new ClassViewPagerModel();
        this.view = view;
    }

    public void updateJudulPage1(String judul_page){ viewnya.setJudulPage1(judul_page); }
    public void updateJudulPage2(String judul_page){ viewnya.setJudulPage2(judul_page); }
    public void updateJudulPage3(String judul_page){ viewnya.setJudulPage3(judul_page); }
    public void updateDeskripsiPage1(String deskripsi_page){ viewnya.setDeskripsiPage1(deskripsi_page); }
    public void updateDeskripsiPage2(String deskripsi_page){ viewnya.setDeskripsiPage2(deskripsi_page); }
    public void updateDeskripsiPage3(String deskripsi_page){ viewnya.setDeskripsiPage3(deskripsi_page); }

    public String getJudulPage1(){ return viewnya.getJudulPage1(); }
    public String getJudulPage2(){ return viewnya.getJudulPage2(); }
    public String getJudulPage3(){ return viewnya.getJudulPage3(); }
    public String getDeskripsiPage1(){ return viewnya.getDeskripsiPage1(); }
    public String getDeskripsiPage2(){ return viewnya.getDeskripsiPage2(); }
    public String getDeskripsiPage3(){ return viewnya.getDeskripsiPage3(); }

    public interface View{
        void HideTaskbar();
        void ButtonListen();
        void InisialisasiVariable();
        void InisialisasiValueVariable();
        ViewPager.OnPageChangeListener ViewPager();
        void PanggilViewPager();
    }

}
