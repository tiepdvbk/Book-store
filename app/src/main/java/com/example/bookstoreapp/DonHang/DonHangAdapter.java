package com.example.bookstoreapp.DonHang;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.ContentView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;

import com.example.bookstoreapp.Book.BookInfor;
import com.example.bookstoreapp.R;

import java.util.List;

public class DonHangAdapter extends ArrayAdapter<DonHang> {
    private Context context;
    List<DonHang> ldh;
    private int resource;

    public DonHangAdapter(@NonNull Context context, int resource, @NonNull List<DonHang> objects) {
        super(context, resource, objects);
        this.context = context;
        this.ldh = objects;
        this.resource = resource;
    }

    @SuppressLint({"InflateParams", "ViewHolder", "SetTextI18n"})
    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        DonHang dh = ldh.get(position);
        convertView = LayoutInflater.from(context).inflate(R.layout.item_donhang, null);
        TextView txtId, txtTime, txtDonGia, txtTrangThai;
        ImageView imgBia = convertView.findViewById(R.id.imgBia);
        txtId = convertView.findViewById(R.id.txtId);
        txtDonGia = convertView.findViewById(R.id.txtDonGia);
        txtTime = convertView.findViewById(R.id.txtTime);
        txtTrangThai = convertView.findViewById(R.id.txtTrangThai);
        txtId.setText(String.valueOf(dh.getId()));
        txtDonGia.setText(dh.getPrice()+"đ");
        txtTime.setText(dh.getDateTime());
        txtTrangThai.setText(dh.getTrangThai());
        imgBia.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(view.getContext(), CTDonHangActivity.class);
                Bundle bundle = new Bundle();
                bundle.putInt("id_donhang",dh.getId());
                intent.putExtras(bundle);
                view.getContext().startActivity(intent);
            }
        });
        return convertView;
    }
}
