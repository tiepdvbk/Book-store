package com.example.bookstoreapp.CaNhan;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.bookstoreapp.R;
import com.google.android.material.bottomsheet.BottomSheetDialog;

import java.util.HashMap;
import java.util.Map;

import all.USER;
import all.UrlData;

public class ThongTinCaNhanActivity extends AppCompatActivity {

    private TextView txtName, txtUser, txtSdt, txtEmail, txtDc, txtDoiPass, txtDoiTT;
    private BottomSheetDialog dialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_thong_tin_ca_nhan);
        anhXa();
        setUp();
    }

    private void setUp() {
        txtName.setText(USER.name);
        txtSdt.setText(USER.sdt);
        txtEmail.setText(USER.mail);
        txtUser.setText(USER.uname);
        txtDoiPass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialogRepass();
            }
        });
        txtDoiTT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialogDoiTT();
            }
        });
    }

    private void dialogDoiTT() {
        LayoutInflater layoutInflater = LayoutInflater.from(ThongTinCaNhanActivity.this);
        @SuppressLint("InflateParams") View v = layoutInflater.inflate(R.layout.rett_sheet, null);
        dialog = new BottomSheetDialog(ThongTinCaNhanActivity.this);
        dialog.setContentView(v);

        Button btnReTT = v.findViewById(R.id.btnDoiTT);
        ImageView imgX = v.findViewById(R.id.imgX);
        EditText edtMK = v.findViewById(R.id.edtMk);
        EditText edtHt = v.findViewById(R.id.edtHt);
        EditText edtSdt = v.findViewById(R.id.edtSdt);
        EditText edtMail = v.findViewById(R.id.edtMail);
        TextView txtRp = v.findViewById(R.id.txtBao);

        edtMail.setText(USER.mail);
        edtHt.setText(USER.name);
        edtSdt.setText(USER.sdt);

        imgX.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.cancel();
            }
        });
        btnReTT.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onClick(View view) {
                if(edtSdt.getText().toString().matches("")||edtHt.getText().toString().matches("")||edtMail.getText().toString().matches("")||edtMK.getText().toString().matches("")){
                    txtRp.setText("Bạn chưa nhập đủ thông tin");
                }
                else
                {
                    if (!edtMK.getText().toString().equals(USER.getPass())){
                        txtRp.setText("Mật khẩu không đúng");
                    }else {
                            RequestQueue queue = Volley.newRequestQueue(getApplicationContext());
                            StringRequest stringRequest = new StringRequest(Request.Method.POST, UrlData.urlReTT,
                                    new Response.Listener<String>() {
                                        @Override
                                        public void onResponse(String response) {
                                            if (response.equals("yes")){
                                                RpDialog("Đổi thành công");
                                                USER.name = edtHt.getText().toString().trim();
                                                USER.sdt = edtSdt.getText().toString().trim();
                                                USER.mail = edtMail.getText().toString().trim();
                                                txtName.setText(USER.name);
                                                txtSdt.setText(USER.sdt);
                                                txtEmail.setText(USER.mail);
                                            }else RpDialog("Đổi thất bại");
                                        }
                                    },
                                    new Response.ErrorListener() {
                                        @Override
                                        public void onErrorResponse(VolleyError error) {
                                            RpDialog(error.toString());
                                        }
                                    }){
                                @Nullable
                                @Override
                                protected Map<String, String> getParams() throws AuthFailureError {
                                    Map<String, String> params = new HashMap<>();
                                    params.put("id", String.valueOf(USER.id));
                                    params.put("name", edtHt.getText().toString().trim());
                                    params.put("sdt", edtSdt.getText().toString().trim());
                                    params.put("mail", edtMail.getText().toString().trim());
                                    return params;
                                }
                            };
                            queue.add(stringRequest);
                    }
                }
            }
        });
        dialog.show();
    }

    private void dialogRepass() {
        LayoutInflater layoutInflater = LayoutInflater.from(ThongTinCaNhanActivity.this);
        @SuppressLint("InflateParams") View v = layoutInflater.inflate(R.layout.repass_sheet, null);
        dialog = new BottomSheetDialog(ThongTinCaNhanActivity.this);
        dialog.setContentView(v);

        Button btnRepass = v.findViewById(R.id.btnDoiPass);
        ImageView imgX = v.findViewById(R.id.imgX);
        EditText edtCu = v.findViewById(R.id.edtMkCu);
        EditText edtMoi = v.findViewById(R.id.edtMkMoi);
        EditText edtLai = v.findViewById(R.id.edtNhapLaiMkMoi);
        TextView txtRp = v.findViewById(R.id.txtBao);
        imgX.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.cancel();
            }
        });
        btnRepass.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onClick(View view) {
                if(edtCu.getText().toString().matches("")||edtMoi.getText().toString().matches("")||edtLai.getText().toString().matches("")){
                    txtRp.setText("Bạn chưa nhập đủ thông tin");
                }
                else
                {
                    if (!edtCu.getText().toString().equals(USER.getPass())){
                        txtRp.setText("Mật khẩu cũ không đúng");
                    }else {
                        if (!edtMoi.getText().toString().equals(edtLai.getText().toString()))
                            txtRp.setText("Mật khẩu mới không khớp");
                        else
                        {
                            RequestQueue queue = Volley.newRequestQueue(getApplicationContext());
                            StringRequest stringRequest = new StringRequest(Request.Method.POST, UrlData.urlRePass,
                                    new Response.Listener<String>() {
                                        @Override
                                        public void onResponse(String response) {
                                            if (response.equals("yes")){
                                                RpDialog("Đổi thành công");
                                                USER.setPass(edtMoi.getText().toString());
                                            }else RpDialog("Đổi thất bại");
                                        }
                                    },
                                    new Response.ErrorListener() {
                                        @Override
                                        public void onErrorResponse(VolleyError error) {
                                            RpDialog(error.toString());
                                        }
                                    }){
                                @Nullable
                                @Override
                                protected Map<String, String> getParams() throws AuthFailureError {
                                    Map<String, String> params = new HashMap<>();
                                    params.put("id", String.valueOf(USER.id));
                                    params.put("uname", String.valueOf(USER.uname));
                                    params.put("pass", String.valueOf(edtMoi.getText().toString()));
                                    return params;
                                }
                            };
                            queue.add(stringRequest);
                        }
                    }
                }
            }
        });

        dialog.show();
    }
    private void RpDialog(String rp){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle(rp);
        builder.setIcon(R.drawable.ic_baseline_sentiment_very_satisfied_24);
        builder.setNegativeButton("ok", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
               dialog.cancel();
            }
        });
        builder.show();
    }

    private void anhXa() {
        txtName = findViewById(R.id.txtName);
        txtUser = findViewById(R.id.txtUser);
        txtSdt = findViewById(R.id.txtSdt);
        txtEmail = findViewById(R.id.txtEmail);
        txtDoiPass = findViewById(R.id.txtDoiPass);
        txtDoiTT = findViewById(R.id.txtDoiTT);
    }
}