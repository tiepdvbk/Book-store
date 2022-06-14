package com.example.bookstoreapp;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.HashMap;
import java.util.Map;

import all.USER;
import all.UrlData;

public class dangKyActivity extends AppCompatActivity {

    private Button btnDk;
    private TextView txtBao;
    private EditText edtTk, edtName,edtMail,edtSdt, edtPass, edtPassAgain;
    private CheckBox cbDk;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dang_ky);
        btnDk = findViewById(R.id.btnDangKy);
        txtBao = findViewById(R.id.txtBao);
        cbDk = findViewById(R.id.cbDieuKhoan);
        edtTk = findViewById(R.id.edtTk);
        edtMail = findViewById(R.id.edtMail);
        edtName = findViewById(R.id.edtHt);
        edtSdt = findViewById(R.id.edtSdt);
        edtPass = findViewById(R.id.edtMk);
        edtPassAgain = findViewById(R.id.edtNhapLaiMk);
        btnDk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sigin();
            }
        });
    }

    @SuppressLint("SetTextI18n")
    private void sigin() {
        if(edtName.getText().toString().matches("")|| edtTk.getText().toString().matches("")||edtMail.getText().toString().matches("")||
        edtSdt.getText().toString().matches("")||edtPass.getText().toString().matches("")||edtPassAgain.getText().toString().matches(""))
        {
            txtBao.setText("Vui lòng nhập đầy đủ thông tin");
        }else
        {
            if(edtPass.getText().toString().equals(edtPassAgain.getText().toString())){
                if(!cbDk.isChecked()){
                    txtBao.setText("Vui lòng đồng ý điều khoản của chúng tôi");
                }
                else {
                    RequestQueue queue = Volley.newRequestQueue(this);
                    StringRequest stringRequest = new StringRequest(Request.Method.POST, UrlData.urlDangKy,
                            new Response.Listener<String>() {
                                @Override
                                public void onResponse(String response) {
                                    if(response.equals("yes")) {
                                        showDialog();
                                    }
                                }
                            },
                            new Response.ErrorListener() {
                                @Override
                                public void onErrorResponse(VolleyError error) {
                                    txtBao.setText(error.toString());
                                }
                            }){
                        @Nullable
                        @Override
                        protected Map<String, String> getParams() throws AuthFailureError {
                            Map<String, String> params = new HashMap<>();
                            params.put("uname", edtTk.getText().toString().trim());
                            params.put("name", edtName.getText().toString().trim());
                            params.put("pass", edtPass.getText().toString().trim());
                            params.put("sdt", edtSdt.getText().toString().trim());
                            params.put("mail", edtMail.getText().toString().trim());
                            return params;
                        }
                    };
                    queue.add(stringRequest);
                }
            }
            else {
                txtBao.setText("Mật khẩu không khớp");
            }
        }

    }
//    private void getIdUser()
//    {
//        RequestQueue queue = Volley.newRequestQueue(this);
//        StringRequest stringRequest = new StringRequest(Request.Method.POST, UrlData.urlAddIdCard,
//                new Response.Listener<String>() {
//                    @Override
//                    public void onResponse(String response) {
//
//                    }
//                },
//                new Response.ErrorListener() {
//                    @Override
//                    public void onErrorResponse(VolleyError error) {
//                        txtBao.setText(error.toString());
//                    }
//                }){
//            @Nullable
//            @Override
//            protected Map<String, String> getParams() throws AuthFailureError {
//                Map<String, String> params = new HashMap<>();
//                params.put("id", edtTk.getText().toString().trim());
//                return params;
//            }
//        };
//        queue.add(stringRequest);
//    }
//    private void addIdCard(){
//        RequestQueue queue = Volley.newRequestQueue(this);
//        StringRequest stringRequest = new StringRequest(Request.Method.POST, UrlData.urlAddIdCard,
//                new Response.Listener<String>() {
//                    @Override
//                    public void onResponse(String response) {
//
//                    }
//                },
//                new Response.ErrorListener() {
//                    @Override
//                    public void onErrorResponse(VolleyError error) {
//                        txtBao.setText(error.toString());
//                    }
//                }){
//            @Nullable
//            @Override
//            protected Map<String, String> getParams() throws AuthFailureError {
//                Map<String, String> params = new HashMap<>();
//                params.put("id", String.valueOf(USER.id));
//                return params;
//            }
//        };
//        queue.add(stringRequest);
//    }
    private void showDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Đăng ký thành công!");
        builder.setIcon(R.drawable.ic_baseline_sentiment_very_satisfied_24);
        builder.setMessage("Đăng nhập ngay?");
        builder.setPositiveButton("Có", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                Intent intent = new Intent(dangKyActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });
        builder.setNegativeButton("Không", null);
        builder.show();
    }
}