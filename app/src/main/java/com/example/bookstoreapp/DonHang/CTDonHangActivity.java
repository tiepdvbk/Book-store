package com.example.bookstoreapp.DonHang;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.bookstoreapp.Card.Card.CTCard;
import com.example.bookstoreapp.Card.Card.CardActivity;
import com.example.bookstoreapp.Card.Card.CardAdapter;
import com.example.bookstoreapp.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import all.USER;
import all.UrlData;

public class CTDonHangActivity extends AppCompatActivity {
    private List<CTDonHang> dhs;
    private CTDHAdapter ctdhAdapter;
    private ListView lvCTDH;
    private TextView txtNameDH;
    private ImageView imgBack;
    private static int id_donhang;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ctdon_hang);
        Init();
        setUp();
    }

    @SuppressLint("SetTextI18n")
    private void Init() {
        dhs = new ArrayList<>();
        lvCTDH = findViewById(R.id.lvCTDHang);
        txtNameDH = findViewById(R.id.txtNameDH);
        imgBack = findViewById(R.id.imgBack);
        Intent intent = getIntent();
        id_donhang = intent.getIntExtra("id_donhang",0);
        txtNameDH.setText("Mã đơn hàng: "+id_donhang);
    }

    private void setUp() {
        getCTDonHang();
        imgBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
    }

    private void getCTDonHang() {
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        StringRequest stringRequest = new StringRequest(Request.Method.POST, UrlData.urlGetCTDonHang,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONArray jsonArray = new JSONArray(response);
                            for (int i = 0 ; i < jsonArray.length(); i++){
                                JSONObject jsonObject = jsonArray.getJSONObject(i);
                                int id = jsonObject.getInt("id");
                                int id_donhang = jsonObject.getInt("id_donhang");
                                int id_book = jsonObject.getInt("id_book");
                                String url = jsonObject.getString("image_url");
                                String name = jsonObject.getString("name");
                                double price = jsonObject.getDouble("price");
                                double total_price = jsonObject.getDouble("total_price");
                                int sl = jsonObject.getInt("sl");
                                dhs.add(new CTDonHang(id,id_donhang,id_book,url,name,price,sl,total_price));
                                ctdhAdapter = new CTDHAdapter(CTDonHangActivity.this, 0,dhs);
                                lvCTDH.setAdapter(ctdhAdapter);
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                            Toast.makeText(CTDonHangActivity.this, e.toString(), Toast.LENGTH_SHORT).show();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(CTDonHangActivity.this, error.toString(), Toast.LENGTH_SHORT).show();
                    }
                }){
            @Nullable
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("id_donhang", String.valueOf(id_donhang));
                return params;
            }
        };
        requestQueue.add(stringRequest);
    }
}