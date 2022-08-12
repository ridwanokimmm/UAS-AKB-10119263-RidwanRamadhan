package com.example.uas_10119263_ridwanramadhan.View.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;

import com.example.uas_10119263_ridwanramadhan.R;
import com.example.uas_10119263_ridwanramadhan.View.Login;
import com.google.firebase.auth.FirebaseAuth;

// NIM : 10119263
// Nama : Ridwan Ramadhan
// Kelas : IF-7

public class LogoutFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.activity_logout, container, false);
        FirebaseAuth.getInstance().signOut();//logout
        startActivity(new Intent(view.getContext(), Login.class));
        return view;
    }
}
