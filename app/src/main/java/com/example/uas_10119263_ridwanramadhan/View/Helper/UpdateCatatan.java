package com.example.uas_10119263_ridwanramadhan.View.Helper;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.uas_10119263_ridwanramadhan.R;
import com.example.uas_10119263_ridwanramadhan.View.Menu;

// NIM : 10119263
// Nama : Ridwan Ramadhan
// Kelas : IF-7

public class UpdateCatatan extends AppCompatActivity {

    EditText judul, kategori, catatan;
    String data_judul;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_catatan);

        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();

        Intent myIntent = getIntent();
        String id = myIntent.getStringExtra("id");
        String judulnya = myIntent.getStringExtra("judul");
        String tanggalnya = myIntent.getStringExtra("tanggal");
        String waktunya  = myIntent.getStringExtra("waktu");
        String kategorinya = myIntent.getStringExtra("kategori");
        String catatannya = myIntent.getStringExtra("catatan");

        judul = findViewById(R.id.Judul);
        kategori = findViewById(R.id.Kategori);
        catatan = findViewById(R.id.Catatan);

        judul.setText(judulnya);
        kategori.setText(kategorinya);
        catatan.setText(catatannya);



        Button fab = (Button) findViewById(R.id.Simpan);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String DataJudul = judul.getText().toString();
                String DataKategori = kategori.getText().toString();
                String DataCatatan = catatan.getText().toString();
                DatabaseHelper databaseHelper = new DatabaseHelper(view.getContext());
                databaseHelper.updateData(id, DataJudul, DataKategori, DataCatatan);
                startActivity(new Intent(UpdateCatatan.this, Menu.class));
            }
        });


    }

    //Metode pesan toast
    private void toastMsg(String msg) {
        Toast t = Toast.makeText(this, msg, Toast.LENGTH_SHORT);
        t.setGravity(Gravity.CENTER,0,0);
        t.show();
    }
}