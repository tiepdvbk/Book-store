package com.example.bookstoreapp.Card.Card;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.text.format.Time;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.baoyz.swipemenulistview.SwipeMenu;
import com.baoyz.swipemenulistview.SwipeMenuCreator;
import com.baoyz.swipemenulistview.SwipeMenuItem;
import com.baoyz.swipemenulistview.SwipeMenuListView;
import com.example.bookstoreapp.Book.BookInfor;
import com.example.bookstoreapp.DanhMuc.DanhMuc;
import com.example.bookstoreapp.DanhMuc.ListAdapter;
import com.example.bookstoreapp.DonHang.DonHang;
import com.example.bookstoreapp.DonHang.DonHangActivity;
import com.example.bookstoreapp.DonHang.DonHangAdapter;
import com.example.bookstoreapp.MainActivity;
import com.example.bookstoreapp.R;
import com.google.android.material.bottomsheet.BottomSheetDialog;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import all.ALL;
import all.USER;
import all.UrlData;

public class CardActivity extends AppCompatActivity {
    private Button btnDatHang;
    SwipeMenuListView lv;
    CardAdapter adapter;
    ImageView imgBack;
    TextView txtTotalPrice;
    List<CTCard> lcs = new ArrayList<>();
    private BottomSheetDialog dialog;
    private static int id;
    @SuppressLint("SimpleDateFormat")
    private static final DateFormat df = new SimpleDateFormat("EEE, d MMM yyyy, HH:mm");
    String date;
    @SuppressLint({"SetTextI18n", "ClickableViewAccessibility"})
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_card);
        date = df.format(Calendar.getInstance().getTime());
        anhXa();
        txtTotalPrice.setText(ALL.allPrice+"đ");
        setUp();
        readCard();
        sumPrice();
        SwipeToDelete();

    }

    private void SwipeToDelete() {
        SwipeMenuCreator creator = new SwipeMenuCreator() {
            @Override
            public void create(SwipeMenu menu) {
                SwipeMenuItem deleteItem = new SwipeMenuItem(getApplicationContext());
                deleteItem.setBackground(new ColorDrawable(Color.rgb(255, 255, 255)));
                deleteItem.setWidth(200);
                deleteItem.setIcon(R.drawable.ic_baseline_delete_forever_24);
                menu.addMenuItem(deleteItem);
            }
        };
        lv.setMenuCreator(creator);
        lv.setOnMenuItemClickListener(new SwipeMenuListView.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(int position, SwipeMenu menu, int index) {
                switch (index) {
                    case 0:
                        int idb = lcs.get(position).getId();
                        delete(idb);
                        break;
                }
                return false;
            }
        });
    }
    private void readCard() {
        RequestQueue queue = Volley.newRequestQueue(this);
        StringRequest stringRequest = new StringRequest(Request.Method.POST, UrlData.urlReadCard,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONArray jsonArray = new JSONArray(response);
                            for (int i = 0 ; i < jsonArray.length(); i++){
                                JSONObject jsonObject = jsonArray.getJSONObject(i);
                                lcs.add(new CTCard(jsonObject.getInt("id"), jsonObject.getInt("id_card"), jsonObject.getInt("id_book"),jsonObject.getString("image_url"), jsonObject.getString("name"), jsonObject.getDouble("price"), jsonObject.getInt("sl"), jsonObject.getDouble("total_price")));
                                adapter = new CardAdapter(CardActivity.this, 0,lcs,lv);
                                lv.setAdapter(adapter);
                                adapter.notifyDataSetChanged();
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                            Toast.makeText(CardActivity.this, e.toString(), Toast.LENGTH_SHORT).show();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(CardActivity.this, error.toString(), Toast.LENGTH_SHORT).show();
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

    private void anhXa() {
        lv = findViewById(R.id.lvCard);
        btnDatHang = findViewById(R.id.btnDatHang);
        imgBack = findViewById(R.id.imgBack);
        txtTotalPrice = findViewById(R.id.txtTotalPrice);
    }

    private void setUp() {
        imgBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
        btnDatHang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(lcs.size() == 0)
                    ALL.showDialog("Giỏ hàng trống",CardActivity.this);
                else
                    datHangSheet();
            }
        });
    }

    @SuppressLint("SetTextI18n")
    public void datHangSheet(){
        LayoutInflater layoutInflater = LayoutInflater.from(CardActivity.this);
        @SuppressLint("InflateParams") View v = layoutInflater.inflate(R.layout.dat_hang_sheet, null);
        LinearLayout lnDc = v.findViewById(R.id.lnDc);
        Button btnDat = v.findViewById(R.id.btnDatHang);
        TextView txtDiaChi = v.findViewById(R.id.txtDiaChi);
        TextView txtThanhTien = v.findViewById(R.id.txtThanhTien);
        txtDiaChi.setText(USER.addres);
        txtThanhTien.setText(txtTotalPrice.getText());
        dialog = new BottomSheetDialog(CardActivity.this);
        dialog.setContentView(v);
        dialog.show();
        lnDc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialogThayDiaChi();
            }
        });
        btnDat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addDon();
            }
        });
    }
    private void dialogThayDiaChi() {
        LayoutInflater layoutInflater = LayoutInflater.from(CardActivity.this);
        View v = layoutInflater.inflate(R.layout.thaydc_sheet, null);
        BottomSheetDialog dialog = new BottomSheetDialog(CardActivity.this);
        dialog.setContentView(v);
        dialog.show();
        Button btnLuu = v.findViewById(R.id.btnLuu);
        TextView txtDc = v.findViewById(R.id.txtDiaChi);
        txtDc.setText(USER.addres);
        btnLuu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addDiaChi(txtDc.getText().toString().trim());
            }
        });
    }

    private void addDiaChi(String dc) {
        if (dc.matches("")||dc.equals("null"))
            ALL.showDialog("Xin nhập địa chỉ",CardActivity.this);
        else {
            RequestQueue queue = Volley.newRequestQueue(CardActivity.this);
            StringRequest stringRequest = new StringRequest(Request.Method.POST, UrlData.urlDoiDiaChi,
                    new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            if (response.equals("yes")){
                                ALL.showDialog("Lưu thành công",CardActivity.this);
                                USER.addres = dc;
                            }else ALL.showDialog("Lưu thất bại",CardActivity.this);
                        }
                    },
                    new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            ALL.showDialog(error.toString(),CardActivity.this);
                        }
                    }){
                @Nullable
                @Override
                protected Map<String, String> getParams() throws AuthFailureError {
                    Map<String, String> params = new HashMap<>();
                    params.put("id", String.valueOf(USER.id));
                    params.put("address", dc);
                    return params;
                }
            };
            queue.add(stringRequest);
        }
    }

    private void addDon(){
        RequestQueue queue = Volley.newRequestQueue(CardActivity.this);
        StringRequest stringRequest = new StringRequest(Request.Method.POST, UrlData.urlAddDonHang,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        if (response.equals("yes")){
                            getLastIDDonHang();
                            showDialog("Đặt hàng thành công");
                        }else showDialog("Error");
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        showDialog(error.toString());
                    }
                }){
            @Nullable
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("id_user", String.valueOf(USER.id));
                params.put("date_time", date);
                params.put("price", txtTotalPrice.getText().toString());
                params.put("trangthai", "Chờ xác nhận");
                return params;
            }
        };
        queue.add(stringRequest);
    }

    private void deleteCard() {
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        StringRequest stringRequest = new StringRequest(Request.Method.POST, UrlData.urlDelateCTCard,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        if(response.equals("yes")){
                            lcs.clear();
                            readCard();
                            txtTotalPrice.setText("0đ");
                            adapter.notifyDataSetChanged();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(CardActivity.this, error.toString(), Toast.LENGTH_SHORT).show();
                    }
                }){
            @Nullable
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("id_card", String.valueOf(USER.id));
                return params;
            }
        };
        requestQueue.add(stringRequest);
    }

    public void getLastIDDonHang() {
        RequestQueue queue = Volley.newRequestQueue(this);
        StringRequest stringRequest = new StringRequest(Request.Method.POST, UrlData.urlLastIDDonHang,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONArray jsonArray = new JSONArray(response);
                            JSONObject jsonObject = jsonArray.getJSONObject(0);
                            ALL.id_donhang = jsonObject.getInt("id");
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        addCardToCTDonHang(ALL.id_donhang);
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(CardActivity.this, error.toString(), Toast.LENGTH_SHORT).show();
                    }
                });
        queue.add(stringRequest);
    }

    private void addCardToCTDonHang(int id_donhang) {
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        StringRequest stringRequest = new StringRequest(Request.Method.POST, UrlData.urlAddCardToDonHang,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        if (response.equals("yes"))
                            deleteCard();
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(CardActivity.this, error.toString(), Toast.LENGTH_SHORT).show();
                    }
                }){
            @Nullable
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("id_card", String.valueOf(USER.id));
                params.put("id_donhang", String.valueOf(id_donhang));
                return params;
            }
        };
        requestQueue.add(stringRequest);
    }

    private void showDialog(String s) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle(s);
        builder.setIcon(R.drawable.ic_baseline_sentiment_very_satisfied_24);
        builder.setNegativeButton("ok", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialog.cancel();
            }
        });
        builder.show();
    }

    @SuppressLint("SetTextI18n")
    public void setAllPrice(String price){
        txtTotalPrice.setText(price+"đ");
    }
    public void sumPrice(){
        RequestQueue queue = Volley.newRequestQueue(this);
        StringRequest stringRequest = new StringRequest(Request.Method.POST, UrlData.urlSumPrice,
                new Response.Listener<String>() {
                    @SuppressLint("SetTextI18n")
                    @Override
                    public void onResponse(String response) {
                        txtTotalPrice.setText(response+"đ");
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(CardActivity.this, error.toString(), Toast.LENGTH_SHORT).show();
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
    public void delete(int id){
        RequestQueue queue = Volley.newRequestQueue(this);
        StringRequest stringRequest = new StringRequest(Request.Method.POST, UrlData.urlXoaSach,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        if(response.equals("yes")){
                            lcs.clear();
                            readCard();
                            sumPrice();
                            adapter.notifyDataSetChanged();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(CardActivity.this, error.toString(), Toast.LENGTH_SHORT).show();
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