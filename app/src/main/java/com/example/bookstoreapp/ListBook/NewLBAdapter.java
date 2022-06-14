package com.example.bookstoreapp.ListBook;

import android.graphics.Paint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.cardview.widget.CardView;

import com.bumptech.glide.Glide;
import com.example.bookstoreapp.Book.MyBook;
import com.example.bookstoreapp.DanhMuc.DanhMucSach;
import com.example.bookstoreapp.R;

import java.util.List;

public class NewLBAdapter extends BaseAdapter {
    private DanhMucSach ct;
    private List<MyBook> lb;

    public NewLBAdapter(DanhMucSach ct, List<MyBook> lb) {
        this.ct = ct;
        this.lb = lb;
    }

    @Override
    public int getCount() {
        return lb.size();
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        LayoutInflater inflater = LayoutInflater.from(ct);
        view = inflater.inflate(R.layout.item_book, null);
        ImageView imgPic;
        TextView txtTitle, txtPrice, txtGiamGia;
        CardView cv_book;
        imgPic = view.findViewById(R.id.imgBook);
        txtTitle = view.findViewById(R.id.txtTitle);
        txtPrice = view.findViewById(R.id.txtPrice);
        txtGiamGia = view.findViewById(R.id.txtGiam);
        cv_book = view.findViewById(R.id.cv_book);
        Glide.with(ct).load(lb.get(i).getUrl_img()).into(imgPic);
        txtTitle.setText(lb.get(i).getName());
        txtPrice.setText(String.valueOf(lb.get(i).getPrice()));
        txtGiamGia.setText(String.valueOf(lb.get(i).getGprice()));
        txtGiamGia.setPaintFlags(txtGiamGia.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
        return view;
    }
}
