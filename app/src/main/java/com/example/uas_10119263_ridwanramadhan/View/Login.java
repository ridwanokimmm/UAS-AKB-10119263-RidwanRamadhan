package com.example.uas_10119263_ridwanramadhan.View;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.example.uas_10119263_ridwanramadhan.Presenter.MainPresenter;
import com.example.uas_10119263_ridwanramadhan.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

// NIM : 10119263
// Nama : Ridwan Ramadhan
// Kelas : IF-7

public class Login extends AppCompatActivity implements MainPresenter.View{
    EditText mEmail,mPassword;
    Button mLoginBtn;
    TextView mCreateBtn;
    ProgressBar progressBar;
    FirebaseAuth fAuth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        HideTaskbar();

        mEmail = findViewById(R.id.Email);
        mPassword = findViewById(R.id.password);
        progressBar = findViewById(R.id.progressBar);
        fAuth = FirebaseAuth.getInstance();
        mLoginBtn = findViewById(R.id.loginBtn);
        mCreateBtn = findViewById(R.id.createText);


        mLoginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String email = mEmail.getText().toString().trim();
                String password = mPassword.getText().toString().trim();

                if(TextUtils.isEmpty(email)){
                    mEmail.setError("Email tidak boleh kosong.");
                    return;
                }

                if(TextUtils.isEmpty(password)){
                    mPassword.setError("Password tidak boleh kosong.");
                    return;
                }

                if(password.length() < 6){
                    mPassword.setError("Password harus lebih >= 6 karakter");
                    return;
                }

                progressBar.setVisibility(View.VISIBLE);

                // authenticate the user

                fAuth.signInWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            Toast.makeText(Login.this, "Logged Berhasil", Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(getApplicationContext(),Menu.class));
                        }else {
                            Toast.makeText(Login.this, "Error ! " + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                            progressBar.setVisibility(View.GONE);
                        }

                    }
                });

            }
        });

        mCreateBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),Register.class));
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
