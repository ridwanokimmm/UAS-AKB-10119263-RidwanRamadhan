package com.example.uas_10119263_ridwanramadhan.View;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import com.example.uas_10119263_ridwanramadhan.Presenter.MainPresenter;
import com.example.uas_10119263_ridwanramadhan.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

// NIM : 10119263
// Nama : Ridwan Ramadhan
// Kelas : IF-7

public class Register extends AppCompatActivity implements MainPresenter.View{
    public static final String TAG = "TAG";
    EditText mEmail,mPassword,mPassword2;
    Button mRegisterBtn;
    TextView mLoginBtn;
    FirebaseAuth fAuth;
    ProgressBar progressBar;
    FirebaseFirestore fStore;
    String userID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        HideTaskbar();

        mEmail       = findViewById(R.id.Email);
        mPassword    = findViewById(R.id.password);
        mPassword2    = findViewById(R.id.password2);
        mRegisterBtn = findViewById(R.id.registerBtn);
        mLoginBtn    = findViewById(R.id.createText);

        fAuth = FirebaseAuth.getInstance();
        fStore = FirebaseFirestore.getInstance();
        progressBar = findViewById(R.id.progressBar2);

//        if(fAuth.getCurrentUser() != null){
//            startActivity(new Intent(getApplicationContext(),Register.class));
//            finish();
//        }


        mRegisterBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String email = mEmail.getText().toString().trim();
                final String password  = mPassword.getText().toString().trim();
                final String password2 = mPassword2.getText().toString().trim();

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

                if(!password.contains(password2)){
                    mPassword.setError(password);
                    return;
                }

                progressBar.setVisibility(View.VISIBLE);

                // Registrasi user di database

                fAuth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){

                            FirebaseUser fuser = fAuth.getCurrentUser();
                            Toast.makeText(Register.this, "User berhasil terdaftar.", Toast.LENGTH_SHORT).show();
                            userID = fAuth.getCurrentUser().getUid();
                            DocumentReference documentReference = fStore.collection("users").document(userID);
                            Map<String,Object> user = new HashMap<>();
                            user.put("email",email);
                            documentReference.set(user).addOnSuccessListener(new OnSuccessListener<Void>() {
                                @Override
                                public void onSuccess(Void aVoid) {
                                    Log.d(TAG, "onSuccess: User memiliki id "+ userID);
                                }
                            }).addOnFailureListener(new OnFailureListener() {
                                @Override
                                public void onFailure(@NonNull Exception e) {
                                    Log.d(TAG, "Error : " + e.toString());
                                }
                            });
                            startActivity(new Intent(getApplicationContext(),Menu.class));

                        }else {
                            Toast.makeText(Register.this, "Error ! " + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                            progressBar.setVisibility(View.GONE);
                        }
                    }
                });
            }
        });



        mLoginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),Login.class));
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
