package com.example.bookstoreapp.DonHang;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

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
import com.example.bookstoreapp.Book.BookInfor;
import com.example.bookstoreapp.Card.Card.CardActivity;
import com.example.bookstoreapp.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import all.ALL;
import all.USER;
import all.UrlData;

public class DonHangActivity extends AppCompatActivity {
    private List<DonHang> ldh;
    private DonHangAdapter donHangAdapter;
    private SwipeMenuListView lvDonHang;
    private ImageView imgBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_don_hang);
        Init();
        setUp();
        SwipeToDelete();

    }

    private void setUp() {
        getDonHang();
        imgBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
    }
    private void getDonHang(){
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        StringRequest stringRequest = new StringRequest(Request.Method.POST, UrlData.urlGetDonHang,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONArray jsonArray = new JSONArray(response);
                            for (int i = 0 ; i < jsonArray.length(); i++){
                                JSONObject jsonObject= jsonArray.getJSONObject(i);
                                int id = jsonObject.getInt("id");
                                String datetime = jsonObject.getString("date_time");
                                String trangthai = jsonObject.getString("trangthai");
                                double price = jsonObject.getDouble("price");
                                ldh.add(new DonHang(id,USER.id,datetime,price,trangthai));
                            }
                            donHangAdapter = new DonHangAdapter(DonHangActivity.this, 0, ldh);
                            lvDonHang.setAdapter(donHangAdapter);
                        } catch (JSONException e) {
                            e.printStackTrace();
                            Toast.makeText(DonHangActivity.this, e.toString(), Toast.LENGTH_SHORT).show();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(DonHangActivity.this, error.toString(), Toast.LENGTH_SHORT).show();
                    }
                }){
            @Nullable
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("id_user", String.valueOf(USER.id));
                return params;
            }
        };
        requestQueue.add(stringRequest);
    }

    private void Init() {
        ldh = new ArrayList<>();
        lvDonHang = findViewById(R.id.lvDonHang);
        imgBack = findViewById(R.id.imgBack);
    }
    private void SwipeToDelete() {
        SwipeMenuCreator creator = new SwipeMenuCreator() {
            @Override
            public void create(SwipeMenu menu) {
                SwipeMenuItem deleteItem = new SwipeMenuItem(getApplicationContext());
                deleteItem.setBackground(new ColorDrawable(Color.rgb(255, 255, 255)));
                deleteItem.setWidth(200);
                deleteItem.setIcon(R.drawable.ic_baseline_cancel_24);
                menu.addMenuItem(deleteItem);
            }
        };
        lvDonHang.setMenuCreator(creator);
        lvDonHang.setOnMenuItemClickListener(new SwipeMenuListView.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(int position, SwipeMenu menu, int index) {
                switch (index) {
                    case 0:
                        int iddon = ldh.get(position).getId();
                        delete(iddon);
                        break;
                }
                return false;
            }
        });
    }

    private void delete(int id) {
        RequestQueue queue = Volley.newRequestQueue(this);
        StringRequest stringRequest = new StringRequest(Request.Method.POST, UrlData.urlXoaCTDon,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        if(response.equals("yes")){
                            deleteSauKhiXoaCTDon(id);
                        }else Toast.makeText(DonHangActivity.this, "Error", Toast.LENGTH_SHORT).show();
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(DonHangActivity.this, error.toString(), Toast.LENGTH_SHORT).show();
                    }
                }){
            @NonNull
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String> params = new HashMap<>();
                params.put("id_donhang",String.valueOf(id));
                return params;
            }
        };
        queue.add(stringRequest);
    }

    private void deleteSauKhiXoaCTDon(int id) {
        RequestQueue queue = Volley.newRequestQueue(this);
        StringRequest stringRequest = new StringRequest(Request.Method.POST, UrlData.urlXoaDon,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        if(response.equals("yes")){
                            ldh.clear();
                            getDonHang();
                            ALL.showDialog("Hủy thành công",DonHangActivity.this);
                        }else Toast.makeText(DonHangActivity.this, "Error", Toast.LENGTH_SHORT).show();
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(DonHangActivity.this, error.toString(), Toast.LENGTH_SHORT).show();
                    }
                }){
            @NonNull
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String> params = new HashMap<>();
                params.put("id",String.valueOf(id));
                return params;
            }
        };
        queue.add(stringRequest);
    }
}