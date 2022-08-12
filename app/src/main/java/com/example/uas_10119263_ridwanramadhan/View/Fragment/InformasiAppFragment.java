package com.example.uas_10119263_ridwanramadhan.View.Fragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.example.uas_10119263_ridwanramadhan.Presenter.ClassViewPagerPresenter;
import com.example.uas_10119263_ridwanramadhan.R;
import com.example.uas_10119263_ridwanramadhan.View.Helper.ViewPagerAdapter;
import com.google.android.gms.tagmanager.Container;

// NIM : 10119263
// Nama : Ridwan Ramadhan
// Kelas : IF-7

public class InformasiAppFragment extends Fragment implements ClassViewPagerPresenter.View {

    ViewPager viewPager;
    Button btnNext;
    int[] layouts;
    ViewPagerAdapter adapter;
    private ClassViewPagerPresenter presenter;
    private TextView JudulPage;
    private TextView DeskripsiPage;
    Context context;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.activity_informasi_app, container, false);
        presenter = new ClassViewPagerPresenter(this);
        context = container.getContext();
        viewPager = view.findViewById(R.id.pager);
        layouts = new int[] {
                R.layout.slide1,
                R.layout.slide2,
                R.layout.slide3
        };
        adapter = new ViewPagerAdapter(context, layouts);
        viewPager.setAdapter(adapter);

        return view;
    }

    @Override
    public void HideTaskbar() {
        ActionBar actionBar = ((AppCompatActivity)getActivity()).getSupportActionBar();
        actionBar.hide();
    }

    @Override
    public void ButtonListen() {

    }

    public void InisialisasiVariable() {

    }

    public void InisialisasiValueVariable() {
        //Inisialisasi Judul dan Deskripsi pada onboard
        presenter.updateJudulPage1("Telusuri Wisata");
        presenter.updateDeskripsiPage1("Melihat berbagai tempat wisata \n dalam satu apilikasi");
        presenter.updateJudulPage2("Mudah Digunakan");
        presenter.updateDeskripsiPage2("Mudahnya penggunaan apilikasi \n dengan tampilan yang responsive");
        presenter.updateJudulPage3("Melihat Detail Wisata");
        presenter.updateDeskripsiPage3("Menampilkan photo lokasi\n wisata dengan detail");

        JudulPage.setText(presenter.getJudulPage1());
        DeskripsiPage.setText(presenter.getDeskripsiPage1());
    }

    public ViewPager.OnPageChangeListener ViewPager() {
        ViewPager.OnPageChangeListener viewPagerChangeListener = new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int i, float v, int il) {

            }

            @Override
            public void onPageSelected(int i) {
                //JudulPage1.setText("tesss");
                if(i == layouts.length - 1){
                    JudulPage.setText(presenter.getJudulPage3());
                    DeskripsiPage.setText(presenter.getDeskripsiPage3());
                    btnNext.setText("Continue");
                } else {
                    JudulPage.setText(presenter.getJudulPage2());
                    DeskripsiPage.setText(presenter.getDeskripsiPage2());
                    btnNext.setText("Next");
                }
            }

            @Override
            public void onPageScrollStateChanged(int i) {

            }
        };
        return viewPagerChangeListener;
    }

    public void PanggilViewPager() {
        viewPager.addOnPageChangeListener(ViewPager());
    }
}
