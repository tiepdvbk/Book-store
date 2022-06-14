package com.example.bookstoreapp;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.bookstoreapp.Card.Card.CardActivity;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import all.ALL;
import all.USER;
import all.UrlData;

public class MainActivity extends AppCompatActivity {
    private Button btnDn, btnDk;
    private EditText edtTk, edtMk;
    private TextView txtSai;
    private Context context;
    private int id_user = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        context = this;
        anhXa();
        khoiTao();
        edtMk.setText("123");
        edtTk.setText("tiepdvbk");
    }
    private void khoiTao() {
        logIn();
        sigIn();
    }

    private void sigIn() {
        btnDk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, dangKyActivity.class);
                startActivity(intent);
            }
        });
    }

    @SuppressLint("ClickableViewAccessibility")
    private void logIn() {
        btnDn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String tk = edtTk.getText().toString();
                String pass = edtMk.getText().toString();
                if(tk.isEmpty() || pass.isEmpty()){
                    txtSai.setText("Không được để trống!");
                }else {
                    dangNhap(tk,pass);
                    readUser(tk,pass);
                }
            }
        });
    }
    private void addIdCard(){
        RequestQueue queue = Volley.newRequestQueue(this);
        StringRequest stringRequest = new StringRequest(Request.Method.POST, UrlData.urlAddIdCard,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(MainActivity.this, error.toString(), Toast.LENGTH_SHORT).show();
                    }
                }){
            @Nullable
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("id", String.valueOf(id_user));
                return params;
            }
        };
        queue.add(stringRequest);
    }

    private void readUser(String tk, String pass) {
        RequestQueue queue = Volley.newRequestQueue(this);
        StringRequest stringRequest = new StringRequest(Request.Method.POST, UrlData.urlGetLogInfor,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONArray jsonArray = new JSONArray(response);
                            JSONObject jsonObject = jsonArray.getJSONObject(0);
                            id_user = jsonObject.getInt("id");
                            USER.id = id_user;
                            USER.name =jsonObject.getString("name");
                            USER.uname =jsonObject.getString("uname");
                            USER.sdt =jsonObject.getString("sdt");
                            USER.mail =jsonObject.getString("mail");
                            USER.addres =jsonObject.getString("address");
                            USER.setPass(jsonObject.getString("pass"));

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        txtSai.setText(error.toString());
                    }
                }){
            @Nullable
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("uname", tk);
                params.put("pass", pass);
                return params;
            }
        };
        queue.add(stringRequest);
    }

    private void dangNhap(String tk, String pass) {
        RequestQueue queue = Volley.newRequestQueue(this);
        StringRequest stringRequest = new StringRequest(Request.Method.POST, UrlData.urlLog,
                new Response.Listener<String>() {
                    @SuppressLint("SetTextI18n")
                    @Override
                    public void onResponse(String response) {
                        if (response.equals("yes")){
                            addIdCard();
                            Intent intent = new Intent(context, home.class);
                            startActivity(intent);
                        }else txtSai.setText("Sai tài khoản hoặc mật khẩu!");
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        txtSai.setText(error.toString());
                    }
                }){
            @Nullable
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("uname", tk);
                params.put("pass", pass);
                return params;
            }
        };
        queue.add(stringRequest);
    }

    private void anhXa() {
        btnDn = findViewById(R.id.btnDangNhap);
        btnDk = findViewById(R.id.btnDangKy);
        edtTk = findViewById(R.id.edtTk);
        edtMk = findViewById(R.id.edtMk);
        txtSai = findViewById(R.id.txtSai);
    }
}