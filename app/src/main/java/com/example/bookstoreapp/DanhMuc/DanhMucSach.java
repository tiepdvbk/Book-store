package com.example.bookstoreapp.DanhMuc;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentContainerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.GridView;
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
import com.example.bookstoreapp.Book.MyBook;
import com.example.bookstoreapp.Card.Card.CardActivity;
import com.example.bookstoreapp.ListBook.ListBookAdapter;
import com.example.bookstoreapp.R;
import com.example.bookstoreapp.Search.SearchFragment;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import all.UrlData;

public class DanhMucSach extends AppCompatActivity {
    private GridView gv;
    private ImageButton btnSearch, btnCard;
    private ImageView imgBack;
    private LinearLayout lnHide;
    private TextView txtNameDm;
    private FragmentContainerView fr;
    private int idtl = -1;
    private String nameDm;
    List<MyBook> lb;
    ListBookAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_danh_muc_sach);
        Intent intent = getIntent();
        idtl = intent.getIntExtra("id",-1);
        nameDm = intent.getStringExtra("nameDm");
        lb = new ArrayList<>();
        anhXa();
        setUp();
        getBook(UrlData.urlGetDanhMuc);
    }

    private void anhXa() {
        imgBack = findViewById(R.id.imgBack);
        txtNameDm = findViewById(R.id.txtNameDm);
        btnSearch = findViewById(R.id.ibtnSearch);
        btnCard = findViewById(R.id.ibtnCard);
        lnHide = findViewById(R.id.lnOk);
        fr = findViewById(R.id.frHome);
        gv=findViewById(R.id.grd_book);
    }

    private void getBook(String url) {
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        StringRequest stringRequest = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONArray jsonArray = new JSONArray(response);
                            for (int i = 0 ; i < jsonArray.length(); i++){
                                JSONObject jsonObject= jsonArray.getJSONObject(i);
//                                lb.add(new MyBook(jsonObject.getString("name"),jsonObject.getDouble("price"),jsonObject.getDouble("gprice"),jsonObject.getString("img_url")));
                                lb.add(new MyBook(jsonObject.getInt("id"),jsonObject.getString("name"), jsonObject.getDouble("price"), jsonObject.getDouble("gprice"), jsonObject.getInt("id_tl"), jsonObject.getString("author"), jsonObject.getString("img_url")));
                            }
                            adapter = new ListBookAdapter(DanhMucSach.this, 0,lb);
                            gv.setAdapter(adapter);
                            adapter.notifyDataSetChanged();

                        } catch (JSONException e) {
                            e.printStackTrace();
                            Toast.makeText(DanhMucSach.this, e.toString(), Toast.LENGTH_SHORT).show();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(DanhMucSach.this, error.toString(), Toast.LENGTH_SHORT).show();
                    }
                }){
            @Nullable
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("id_tl", String.valueOf(idtl));
                return params;
            }
        };
        requestQueue.add(stringRequest);
    }

    private void setUp() {
        btnSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                lnHide.setVisibility(View.GONE);
                fr.setVisibility(View.VISIBLE);
                Fragment selectedFragment = null;
                selectedFragment = SearchFragment.newInstance();
                getSupportFragmentManager().beginTransaction().replace(R.id.frHome, selectedFragment).commit();
            }
        });
        btnCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(view.getContext(), CardActivity.class);
                view.getContext().startActivity(intent);
            }
        });
        imgBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
        txtNameDm.setText(nameDm);
    }
}