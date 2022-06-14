package com.example.bookstoreapp.DonHang;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;

import com.bumptech.glide.Glide;
import com.example.bookstoreapp.Book.BookInfor;
import com.example.bookstoreapp.R;

import java.util.List;

public class CTDHAdapter extends ArrayAdapter<CTDonHang> {
    private Context ct;
    private int resource;
    private List<CTDonHang> dhs;
    public CTDHAdapter(@NonNull Context context, int resource, @NonNull List<CTDonHang> objects) {
        super(context, resource, objects);
        this.ct = context;
        this.resource = resource;
        this.dhs = objects;
    }

    @SuppressLint({"ViewHolder", "InflateParams", "SetTextI18n"})
    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        CTDonHang ctdh = dhs.get(position);
        convertView = LayoutInflater.from(ct).inflate(R.layout.item_ctdhang, null);
        TextView txtName, txtPrice, txtSl,txtAllPrice;
        ImageView imgBia;
        CardView cv_book = convertView.findViewById(R.id.cv_book);
        txtName = convertView.findViewById(R.id.txtName);
        txtAllPrice = convertView.findViewById(R.id.txtAllPrice);
        txtPrice = convertView.findViewById(R.id.txtPrice);
        txtSl = convertView.findViewById(R.id.txtSl);
        imgBia = convertView.findViewById(R.id.imgBia);

        txtName.setText(""+ctdh.getId());
        txtPrice.setText(""+ctdh.getPrice());
        txtAllPrice.setText(""+ctdh.getTotal_price());
        txtSl.setText(""+ctdh.getSl());
        txtName.setText(ctdh.getName());
        Glide.with(getContext()).load(ctdh.getImage_url()).into(imgBia);
        cv_book.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(view.getContext(), BookInfor.class);
                Bundle bundle = new Bundle();
                bundle.putInt("id", ctdh.getId_book());
                intent.putExtras(bundle);
                view.getContext().startActivity(intent);
            }
        });
        return convertView;
    }
}
