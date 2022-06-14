package com.example.bookstoreapp.Card.Card;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.baoyz.swipemenulistview.SwipeMenu;
import com.baoyz.swipemenulistview.SwipeMenuCreator;
import com.baoyz.swipemenulistview.SwipeMenuItem;
import com.baoyz.swipemenulistview.SwipeMenuListView;
import com.bumptech.glide.Glide;
import com.example.bookstoreapp.Book.Book;
import com.example.bookstoreapp.Book.BookInfor;
import com.example.bookstoreapp.MainActivity;
import com.example.bookstoreapp.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import all.ALL;
import all.USER;
import all.UrlData;

public class CardAdapter extends ArrayAdapter<CTCard> {
    private CardActivity ct;
    private List<CTCard> lcs = null;
    private SwipeMenuListView lv;
    private int resource;
    private static double totalPrice;
    public CardAdapter(@NonNull CardActivity context, int resource, @NonNull List<CTCard> objects, SwipeMenuListView list) {
        super(context, resource, objects);
        this.ct = context;
        this.lcs = objects;
        this.resource = resource;
        this.lv = list;
    }

    @SuppressLint({"InflateParams", "SetTextI18n"})
    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        ImageView imgPic, imgTru, imgCong, imgXoa;
        TextView txtTitle, txtPrice, txtAllPrice;
        EditText edtSl;
        CardView cv_book;
        CTCard ctc = lcs.get(position);
        if(convertView==null)
            convertView = LayoutInflater.from(ct).inflate(R.layout.card_custom2, null);
        imgCong = convertView.findViewById(R.id.cong);
        imgTru = convertView.findViewById(R.id.tru);
        imgPic = convertView.findViewById(R.id.imgBia);
        txtTitle = convertView.findViewById(R.id.txtName);
        txtPrice = convertView.findViewById(R.id.txtPrice);
        txtAllPrice = convertView.findViewById(R.id.txtAllPrice);
        edtSl = convertView.findViewById(R.id.edtSl);
        imgXoa = convertView.findViewById(R.id.imgXoa);

        Glide.with(getContext()).load(ctc.getImage_url()).into(imgPic);
        txtTitle.setText(ctc.getName());
        txtPrice.setText(ctc.getPrice()+"đ");
        edtSl.setText(String.valueOf(ctc.getSl()));
        txtAllPrice.setText(ctc.getTotal_price()+"đ");


        imgPic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(view.getContext(), BookInfor.class);
                Bundle bundle = new Bundle();
                bundle.putInt("id", ctc.getId_book());
                intent.putExtras(bundle);
                view.getContext().startActivity(intent);
            }
        });
        imgCong.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onClick(View view) {
                edtSl.setText(""+(ctc.getSl()+1));
                ctc.setSl(Integer.parseInt(edtSl.getText().toString().trim()));
                totalPrice = ctc.getPrice() * ctc.getSl();
                txtAllPrice.setText(totalPrice+"đ");
                editTangGiam(ctc.getId(), txtAllPrice.getText().toString(), edtSl.getText().toString());
            }
        });
        imgTru.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onClick(View view) {

                edtSl.setText(""+(ctc.getSl()-1));
                ctc.setSl(Integer.parseInt(edtSl.getText().toString().trim()));
                if(ctc.getSl()==0)
                    ct.delete(ctc.getId());
                totalPrice = ctc.getPrice() * ctc.getSl();
                txtAllPrice.setText(totalPrice+"đ");
                editTangGiam(ctc.getId(), txtAllPrice.getText().toString(), edtSl.getText().toString());
            }
        });
        return convertView;
    }
    public void sumPrice(){
        RequestQueue queue = Volley.newRequestQueue(ct);
        StringRequest stringRequest = new StringRequest(Request.Method.POST, UrlData.urlSumPrice,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        ct.setAllPrice(response.trim());
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(getContext(), error.toString(), Toast.LENGTH_SHORT).show();
                    }
                }){
            @Nullable
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String> params = new HashMap<>();
                params.put("id_card",String.valueOf(USER.id));
                return params;
            }
        };
        queue.add(stringRequest);
    }
    private void editTangGiam(int id, String allPrice, String sl) {
        RequestQueue queue = Volley.newRequestQueue(getContext());
        StringRequest stringRequest = new StringRequest(Request.Method.POST, UrlData.urlEditTangGiam,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        if (response.equals("yes")){
                            sumPrice();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(getContext(), error.toString(), Toast.LENGTH_SHORT).show();
                    }
                }){
            @Nullable
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String> params = new HashMap<>();
                params.put("id_card",String.valueOf(USER.id));
                params.put("id_book",String.valueOf(id));
                params.put("sl",sl);
                params.put("total_price",allPrice);
                return params;
            }

        };
        queue.add(stringRequest);

    }
}
