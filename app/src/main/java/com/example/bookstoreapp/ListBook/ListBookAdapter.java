package com.example.bookstoreapp.ListBook;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.graphics.Paint;
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
import com.example.bookstoreapp.Book.MyBook;
import com.example.bookstoreapp.R;

import java.util.List;

public class ListBookAdapter extends ArrayAdapter<MyBook> {
    private Context ct;
    private List<MyBook> lbs = null;
    private int resource;
    public ListBookAdapter(@NonNull Context context, int resource, @NonNull List<MyBook> objects) {
        super(context,resource,objects);
        this.ct = context;
        this.lbs = objects;
        this.resource = resource;
    }

    @SuppressLint({"InflateParams", "SetTextI18n"})
    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        ImageView imgPic;
        TextView txtTitle, txtPrice, txtGiamGia;
        CardView cv_book;
        MyBook b = lbs.get(position);
        if(convertView==null)
            convertView = LayoutInflater.from(ct).inflate(R.layout.item_book2, null);

        imgPic = convertView.findViewById(R.id.imgBook);
        txtTitle = convertView.findViewById(R.id.txtTitle);
        txtPrice = convertView.findViewById(R.id.txtPrice);
        txtGiamGia = convertView.findViewById(R.id.txtGiam);
        cv_book = convertView.findViewById(R.id.cv_book);

        Glide.with(ct).load(b.getUrl_img()).into(imgPic);
        txtTitle.setText(b.getName());
        txtPrice.setText(b.getPrice()+"đ");
        txtGiamGia.setText(b.getGprice()+"đ");
        txtGiamGia.setPaintFlags(txtGiamGia.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
        cv_book.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(view.getContext(), BookInfor.class);
                Bundle bundle = new Bundle();
                bundle.putInt("id",b.getId());
                intent.putExtras(bundle);
                view.getContext().startActivity(intent);
            }
        });
        return convertView;
    }
}
