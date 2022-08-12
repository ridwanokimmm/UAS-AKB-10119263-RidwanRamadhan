package com.example.uas_10119263_ridwanramadhan.View.Helper;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.uas_10119263_ridwanramadhan.R;

import java.util.ArrayList;

// NIM : 10119263
// Nama : Ridwan Ramadhan
// Kelas : IF-7
public class ItemAdapter extends BaseAdapter {

    private Context context;
    private ArrayList<ModelData> arrayList;

    public ItemAdapter(Context context, ArrayList<ModelData> arrayList) {
        super();
        this.context = context;
        this.arrayList = arrayList;
    }

    @Override
    public int getCount() {
        return this.arrayList.size();
    }

    @Override
    public Object getItem(int position) {
        return arrayList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @SuppressLint({"ViewHolder", "InflateParams"})
    @Override
    public View getView(int position, View convertView, final ViewGroup parent) {
        LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        assert layoutInflater != null;
        convertView = layoutInflater.inflate(R.layout.daftar_todo, null);
        TextView titleTextView = convertView.findViewById(R.id.title);
        TextView dateTextView = convertView.findViewById(R.id.dateTitle);
        TextView timeTextView = convertView.findViewById(R.id.timeTitle);
        TextView kategoriTextView = convertView.findViewById(R.id.kategori);
        TextView catatanTextView = convertView.findViewById(R.id.Catatan);
        final ImageView delImageView = convertView.findViewById(R.id.delete);
        final ImageView updateImageView = convertView.findViewById(R.id.update);
        delImageView.setTag(position);
        updateImageView.setTag(position);

        //Menghapus tugas dari database saat icon hapus di klik
        delImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final int pos = (int) v.getTag();
                deleteItem(pos);
            }
        });

        ModelData modelData = arrayList.get(position);

        //Mengupdate tugas dari database saat icon hapus di klik
        updateImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final int pos = (int) view.getTag();
                int idnya = arrayList.get(position).getId();
                String id = String.valueOf(idnya);
                Intent intent = new Intent(view.getContext(), UpdateCatatan.class);
                intent.putExtra("id",id);
                intent.putExtra("judul",modelData.getTitle());
                intent.putExtra("tanggal",modelData.getCatatan());
                intent.putExtra("waktu",modelData.getTime());
                intent.putExtra("kategori",modelData.getKategorinya());
                intent.putExtra("catatan",modelData.getCatatanya());
                view.getContext().startActivity(intent);
                //updateItem(pos);
            }
        });

        titleTextView.setText(modelData.getTitle());
        dateTextView.setText(modelData.getCatatan());
        timeTextView.setText(modelData.getTime());
        kategoriTextView.setText(modelData.getKategorinya());
        catatanTextView.setText(modelData.getCatatanya());
        return convertView;
    }

    //Menghapus tugas dari listview
    private void deleteItem(int position) {
        deleteItemFromDb(arrayList.get(position).getId());
        arrayList.remove(position);
        notifyDataSetChanged();
    }


    //Menghapus tugas dari database
    private void deleteItemFromDb(int id) {
        DatabaseHelper databaseHelper = new DatabaseHelper(context);
        try {
            databaseHelper.deleteData(id);
            toastMsg("Tugas di hapus");
        } catch (Exception e) {
            e.printStackTrace();
            toastMsg("Oppss.. ada kesalahan saat menghapus");
        }
    }


    //Metode pesan toast
    private void toastMsg(String msg) {
        Toast t = Toast.makeText(context, msg, Toast.LENGTH_SHORT);
        t.setGravity(Gravity.CENTER,0,0);
        t.show();
    }


}
