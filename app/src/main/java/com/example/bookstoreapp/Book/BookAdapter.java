package com.example.bookstoreapp.Book;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Paint;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.bookstoreapp.MainActivity;
import com.example.bookstoreapp.R;
import com.example.bookstoreapp.dangKyActivity;

import java.util.List;
public class BookAdapter extends RecyclerView.Adapter<BookAdapter.BookViewHoder> {
    private List<MyBook> lb;
    private MainActivity ct;
    public void setData(List<MyBook> lb){
        this.lb = lb;
        notifyDataSetChanged();
    }
    @NonNull
    @Override
    public BookViewHoder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_book2,parent,false);
        return new BookViewHoder(v);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull BookViewHoder holder, int i) {
        MyBook b = lb.get(i);
        if(b == null){
            return;
        }
        Glide.with(holder.imgPic.getContext()).load(b.getUrl_img()).into(holder.imgPic);
        holder.txtTitle.setText(b.getName());
        holder.txtPrice.setText(b.getPrice()+"đ");
        holder.txtGiamGia.setText(b.getGprice()+"đ");
        holder.txtGiamGia.setPaintFlags(holder.txtGiamGia.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
        holder.cv_book.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(view.getContext(), BookInfor.class);
                Bundle bundle = new Bundle();
                bundle.putInt("id",b.getId());
                intent.putExtras(bundle);
                view.getContext().startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        if (lb != null){
            return lb.size();
        }
        return 0;
    }

    public class BookViewHoder extends RecyclerView.ViewHolder{
        private ImageView imgPic;
        private TextView txtTitle, txtPrice, txtGiamGia;
        private CardView cv_book;
        public BookViewHoder(@NonNull View itemView) {
            super(itemView);
            imgPic = itemView.findViewById(R.id.imgBook);
            txtTitle = itemView.findViewById(R.id.txtTitle);
            txtPrice = itemView.findViewById(R.id.txtPrice);
            txtGiamGia = itemView.findViewById(R.id.txtGiam);
            cv_book = itemView.findViewById(R.id.cv_book);
        }
    }
}

