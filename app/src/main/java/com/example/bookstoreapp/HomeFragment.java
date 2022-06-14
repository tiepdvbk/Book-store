package com.example.bookstoreapp;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.bookstoreapp.Book.Book;
import com.example.bookstoreapp.Book.MyBook;
import com.example.bookstoreapp.Card.Card.CardActivity;
import com.example.bookstoreapp.Search.SearchAdapter;
import com.example.bookstoreapp.Search.SearchFragment;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import all.ALL;
import all.UrlData;
import category.Category;
import category.CategoryAdapter;


public class HomeFragment extends Fragment {
    private ImageButton btnSearch, btnCard;
    private RecyclerView rcv_ctgr;
    private CategoryAdapter adapter;
    List<MyBook> lbNew,lbLove,lbNauAn;
    List<Category> listCategories;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.home_frm,container,false);
        btnSearch = v.findViewById(R.id.ibtnSearch);
        btnCard = v.findViewById(R.id.ibtnCard);

        rcv_ctgr = v.findViewById(R.id.rcv_ctgr);
        adapter = new CategoryAdapter(getContext());
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext(), RecyclerView.VERTICAL, false);
        rcv_ctgr.setLayoutManager(linearLayoutManager);
        listCategories = new ArrayList<>();
        getMoiPhatHanh();
        getTinhYeu();
        getNauAn();
        setUp();
        return v;
    }
    private void getMoiPhatHanh(){
        RequestQueue queue = Volley.newRequestQueue(getContext());
        StringRequest stringRequest = new StringRequest(Request.Method.POST, UrlData.urlGetBookByIDTl,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        lbNew = new ArrayList<>();
                        if (!response.equals("")){
                            try {
                                JSONArray jsonArray = new JSONArray(response);
                                for (int i = 0 ; i < jsonArray.length(); i++){
                                    JSONObject jsonObject = jsonArray.getJSONObject(i);
                                    lbNew.add(new MyBook(jsonObject.getInt("id"),jsonObject.getString("name"), jsonObject.getDouble("price"), jsonObject.getDouble("gprice"), jsonObject.getInt("id_tl"), jsonObject.getString("author"), jsonObject.getString("img_url")));
                                }
                                listCategories.add(new Category("Mới phát hành",lbNew));
                                adapter.setData(listCategories);
                                rcv_ctgr.setAdapter(adapter);
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(getActivity(), error.toString(), Toast.LENGTH_SHORT).show();
                    }
                }){
            @Nullable
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("idtl", String.valueOf(1));
                return params;
            }
        };
        queue.add(stringRequest);
    }
    private void getTinhYeu(){
        RequestQueue queue = Volley.newRequestQueue(getContext());
        StringRequest stringRequest = new StringRequest(Request.Method.POST, UrlData.urlGetBookByIDTl,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        lbLove = new ArrayList<>();
                        if (!response.equals("")){
                            try {
                                JSONArray jsonArray = new JSONArray(response);
                                for (int i = 0 ; i < jsonArray.length(); i++){
                                    JSONObject jsonObject = jsonArray.getJSONObject(i);
                                    lbLove.add(new MyBook(jsonObject.getInt("id"),jsonObject.getString("name"), jsonObject.getDouble("price"), jsonObject.getDouble("gprice"), jsonObject.getInt("id_tl"), jsonObject.getString("author"), jsonObject.getString("img_url")));
                                }
                                listCategories.add(new Category("Tình yêu",lbLove));
                                adapter.setData(listCategories);
                                rcv_ctgr.setAdapter(adapter);
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(getActivity(), error.toString(), Toast.LENGTH_SHORT).show();
                    }
                }){
            @Nullable
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("idtl", String.valueOf(3));
                return params;
            }
        };
        queue.add(stringRequest);
    }
    private void getNauAn(){
        RequestQueue queue = Volley.newRequestQueue(getContext());
        StringRequest stringRequest = new StringRequest(Request.Method.POST, UrlData.urlGetBookByIDTl,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        lbNauAn = new ArrayList<>();
                        if (!response.equals("")){
                            try {
                                JSONArray jsonArray = new JSONArray(response);
                                for (int i = 0 ; i < jsonArray.length(); i++){
                                    JSONObject jsonObject = jsonArray.getJSONObject(i);
                                    lbNauAn.add(new MyBook(jsonObject.getInt("id"),jsonObject.getString("name"), jsonObject.getDouble("price"), jsonObject.getDouble("gprice"), jsonObject.getInt("id_tl"), jsonObject.getString("author"), jsonObject.getString("img_url")));
                                }
                                listCategories.add(new Category("Nấu ăn",lbNauAn));
                                adapter.setData(listCategories);
                                rcv_ctgr.setAdapter(adapter);
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(getActivity(), error.toString(), Toast.LENGTH_SHORT).show();
                    }
                }){
            @Nullable
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("idtl", String.valueOf(6));
                return params;
            }
        };
        queue.add(stringRequest);
    }

    private void setUp() {
        btnSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Fragment selectedFragment = null;
                selectedFragment = SearchFragment.newInstance();
                FragmentManager fragmentManager = getFragmentManager();
                fragmentManager.beginTransaction().replace(R.id.frHome, selectedFragment).commit();
            }
        });
        btnCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(view.getContext(), CardActivity.class);
                view.getContext().startActivity(intent);
            }
        });
    }

    public static HomeFragment newInstance(){
        return new HomeFragment();
    }
}