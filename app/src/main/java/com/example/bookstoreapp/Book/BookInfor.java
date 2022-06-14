package com.example.bookstoreapp.Book;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Paint;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.bumptech.glide.Glide;
import com.example.bookstoreapp.Card.Card.CardActivity;
import com.example.bookstoreapp.R;
import com.google.android.material.bottomsheet.BottomSheetDialog;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

import all.ALL;
import all.USER;
import all.UrlData;

public class BookInfor extends AppCompatActivity {
    private TextView mTvGiam, txtPrice, txtName, txtAuthor,txtTotalPrice;
    private Button mBtnAdd, mBtnBuy;
    private ImageView imgBack, back, imgBia;
    private ImageButton search, card;
    private LinearLayout lnIf;
    private static int id,sl;
    private static String nameBook, img_Url;
    private static double price,totalPrice;
    private BottomSheetDialog dialog;
    @SuppressLint("SimpleDateFormat")
    private static final DateFormat df = new SimpleDateFormat("EEE, d MMM yyyy, HH:mm");
    String date;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_infor);
        date = df.format(Calendar.getInstance().getTime());
        anhXa();
        Init();
        getBook(UrlData.urlGetBookByID);
        setUp();
    }

    private void getBook(String url) {
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        StringRequest stringRequest = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONArray jsonArray = new JSONArray(response);
                            JSONObject jsonObject= jsonArray.getJSONObject(0);
                            img_Url = jsonObject.getString("img_url");
                            Glide.with(BookInfor.this).load(img_Url).into(imgBia);
                            nameBook = jsonObject.getString("name");
                            txtName.setText(nameBook);
                            txtAuthor.setText(jsonObject.getString("author"));
                            price = jsonObject.getDouble("price");
                            txtPrice.setText(String.valueOf(price));
                            mTvGiam.setText(String.valueOf(jsonObject.getDouble("gprice")));
                        } catch (JSONException e) {
                            e.printStackTrace();
                            Toast.makeText(BookInfor.this, e.toString(), Toast.LENGTH_SHORT).show();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(BookInfor.this, error.toString(), Toast.LENGTH_SHORT).show();
                    }
                }){
            @Nullable
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("id", String.valueOf(id));
                return params;
            }
        };
        requestQueue.add(stringRequest);
    }

    private void anhXa() {
        txtAuthor = findViewById(R.id.txtAuthor);
        txtName = findViewById(R.id.txtName);
        txtPrice = findViewById(R.id.txtPrice);
        imgBia = findViewById(R.id.imgBiaSach);
        mTvGiam = findViewById(R.id.txtGprice);
        mBtnAdd = findViewById(R.id.btnAdd);
        mBtnBuy = findViewById(R.id.btnBuy);
        back = findViewById(R.id.back);
        card = findViewById(R.id.ibtnCard);
    }

    private void Init() {
        Intent intent = getIntent();
        id = intent.getIntExtra("id",-1);
    }

    private void setUp() {
        mTvGiam.setPaintFlags(mTvGiam.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
        mBtnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addCard();
            }

        });
        mBtnBuy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                buy();
            }
        });
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
        card.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(view.getContext(), CardActivity.class);
                view.getContext().startActivity(intent);
            }
        });
    }



    public void buy() {
        LayoutInflater layoutInflater = LayoutInflater.from(BookInfor.this);
        @SuppressLint("InflateParams")
        View v = layoutInflater.inflate(R.layout.buy_sheet, null);
        dialog = new BottomSheetDialog(BookInfor.this);
        dialog.setContentView(v);
        dialog.show();
        Button btnBuy = v.findViewById(R.id.btnBuy);
        imgBack = v.findViewById(R.id.imgBack);
        txtName = v.findViewById(R.id.txtName);
        txtPrice = v.findViewById(R.id.txtPrice);
        txtTotalPrice = v.findViewById(R.id.txtTotalPrice);
        ImageView imgPic = v.findViewById(R.id.imgBia);
        ImageView imgGiam, imgTang;
        imgGiam = v.findViewById(R.id.imgGiam);
        imgTang = v.findViewById(R.id.imgTang);
        EditText edtSl = v.findViewById(R.id.edtSl);

        Glide.with(BookInfor.this).load(img_Url).into(imgPic);
        txtName.setText(nameBook);
        txtPrice.setText(String.valueOf(price));
        sl = 1;
        edtSl.setText(String.valueOf(sl));
        edtSl.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override
            public void afterTextChanged(Editable editable) {
                String s = edtSl.getText().toString().trim();
                if(!s.matches(""))
                    sl = Integer.parseInt(s);
            }
        });
        txtTotalPrice.setText(String.valueOf(price));
        imgBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.cancel();
            }
        });
        btnBuy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                datHang();
            }
        });
        totalPrice = price *sl;
        imgGiam.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(sl>1){
                    sl--;
                    edtSl.setText(String.valueOf(sl));
                    totalPrice = price *sl;
                    txtTotalPrice.setText(String.valueOf(totalPrice));
                }
            }
        });
        imgTang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sl++;
                edtSl.setText(String.valueOf(sl));
                totalPrice = price *sl;
                txtTotalPrice.setText(String.valueOf(totalPrice));
            }
        });
    }

    private void datHang() {
        RequestQueue queue = Volley.newRequestQueue(BookInfor.this);
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
                        addBookToCTDonHang(ALL.id_donhang);
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(BookInfor.this, error.toString(), Toast.LENGTH_SHORT).show();
                    }
                });
        queue.add(stringRequest);
    }

    private void addBookToCTDonHang(int id_donhang) {
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        StringRequest stringRequest = new StringRequest(Request.Method.POST, UrlData.urlAddBookToDonHang,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        if (!response.equals("yes"))
                            showDialog("Error");
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(BookInfor.this, error.toString(), Toast.LENGTH_SHORT).show();
                    }
                }){
            @Nullable
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("id_donhang", String.valueOf(id_donhang));
                params.put("id_book", String.valueOf(id));
                params.put("image_url", String.valueOf(img_Url));
                params.put("name", String.valueOf(nameBook));
                params.put("price", String.valueOf(price));
                params.put("sl", String.valueOf(sl));
                params.put("total_price", String.valueOf(totalPrice));
                return params;
            }
        };
        requestQueue.add(stringRequest);
    }

    public void addCard(){
        LayoutInflater layoutInflater = LayoutInflater.from(BookInfor.this);
        View v = layoutInflater.inflate(R.layout.add_sheet, null);
        BottomSheetDialog dialog = new BottomSheetDialog(BookInfor.this);
        dialog.setContentView(v);
        dialog.show();
        Button btnAdd = v.findViewById(R.id.btnAdd);
        imgBack = v.findViewById(R.id.imgBack);
        TextView txtName, txtPrice, txtTotalPrice;
        txtName = v.findViewById(R.id.txtName);
        txtPrice = v.findViewById(R.id.txtPrice);
        txtTotalPrice = v.findViewById(R.id.txtTotalPrice);
        ImageView imgPic = v.findViewById(R.id.imgBia);
        ImageView imgGiam, imgTang;
        imgGiam = v.findViewById(R.id.imgGiam);
        imgTang = v.findViewById(R.id.imgTang);
        EditText edtSl = v.findViewById(R.id.edtSl);

        Glide.with(BookInfor.this).load(img_Url).into(imgPic);
        txtName.setText(nameBook);
        txtPrice.setText(String.valueOf(price));
        sl = 1;
        edtSl.setText(String.valueOf(sl));
        edtSl.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override
            public void afterTextChanged(Editable editable) {
                String s = edtSl.getText().toString().trim();
                if(!s.matches(""))
                    sl = Integer.parseInt(s);
            }
        });
        txtTotalPrice.setText(String.valueOf(price));
        imgBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.cancel();
            }
        });
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                checkCard();
            }
        });
        totalPrice = price *sl;
        imgGiam.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(sl>1){
                    sl--;
                    edtSl.setText(String.valueOf(sl));
                    totalPrice = price *sl;
                    txtTotalPrice.setText(String.valueOf(totalPrice));
                }
            }
        });
        imgTang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sl++;
                edtSl.setText(String.valueOf(sl));
                totalPrice = price *sl;
                txtTotalPrice.setText(String.valueOf(totalPrice));
            }
        });
    }

    private void addToCard() {
        RequestQueue queue = Volley.newRequestQueue(BookInfor.this);
        StringRequest stringRequest = new StringRequest(Request.Method.POST, UrlData.urlAddToCard,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        if (response.equals("yes")){
                            showAlertDialog("Thêm thành công");
                        }else Toast.makeText(BookInfor.this, "Thêm thất bại", Toast.LENGTH_SHORT).show();
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(BookInfor.this, error.toString(), Toast.LENGTH_SHORT).show();
                    }
                }){
            @Nullable
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("id_card", String.valueOf(USER.id));
                params.put("id_book", String.valueOf(id));
                params.put("image_url", String.valueOf(img_Url));
                params.put("name", String.valueOf(nameBook));
                params.put("price", String.valueOf(price));
                params.put("sl", String.valueOf(sl));
                params.put("total_price", String.valueOf(totalPrice));
                return params;
            }
        };
        queue.add(stringRequest);
    }
    private void checkCard(){
        RequestQueue queue = Volley.newRequestQueue(BookInfor.this);
        StringRequest stringRequest = new StringRequest(Request.Method.POST, UrlData.urlCheckCard,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        if (response.equals("yes")){
                            addSoLuong();
                        }else
                            addToCard();
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(BookInfor.this, error.toString(), Toast.LENGTH_SHORT).show();
                    }
                }){
            @Nullable
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("id_book", String.valueOf(id));
                params.put("id_card", String.valueOf(USER.id));
                return params;
            }
        };
        queue.add(stringRequest);
    }

    private void addSoLuong() {
        RequestQueue queue = Volley.newRequestQueue(BookInfor.this);
        StringRequest stringRequest = new StringRequest(Request.Method.POST, UrlData.urlTangKhiTrungSp,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        if (response.equals("yes")) {
                            showAlertDialog("Thêm thành công");
                        }else showAlertDialog("Thêm thất bại");
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(BookInfor.this, error.toString(), Toast.LENGTH_SHORT).show();
                    }
                }){
            @Nullable
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("id_book", String.valueOf(id));
                params.put("id_card", String.valueOf(USER.id));
                params.put("Sl", String.valueOf(sl));
                return params;
            }
        };
        queue.add(stringRequest);
    }

    public void datHangSheet(){
        LayoutInflater layoutInflater = LayoutInflater.from(this);
        @SuppressLint("InflateParams") View v = layoutInflater.inflate(R.layout.dat_hang_sheet, null);
        Button btnDat = v.findViewById(R.id.btnDatHang);
        BottomSheetDialog dialog = new BottomSheetDialog(this);
        dialog.setContentView(v);
        dialog.show();
        btnDat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(v.getContext());
                builder.setTitle("Đặt hàng thành công!");
                builder.setIcon(R.drawable.ic_baseline_sentiment_very_satisfied_24);
                builder.setNegativeButton("ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialog.cancel();
                    }
                });
                builder.show();
            }
        });
    }
    private void showAlertDialog(String s) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle(s);
        builder.setIcon(R.drawable.ic_baseline_sentiment_very_satisfied_24);
//        builder.setMessage("Bạn có muốn đi xem giỏ hàng?");
//        builder.setPositiveButton("Có", new DialogInterface.OnClickListener() {
//            @Override
//            public void onClick(DialogInterface dialogInterface, int i) {
//                Intent intent = new Intent(BookInfor.this, CardActivity.class);
//                startActivity(intent);
//            }
//        });
        builder.setNegativeButton("ok", null);
        builder.show();
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
}