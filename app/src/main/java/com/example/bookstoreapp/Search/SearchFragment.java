package com.example.bookstoreapp.Search;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.bookstoreapp.Book.BookInfor;
import com.example.bookstoreapp.Book.MyBook;
import com.example.bookstoreapp.Card.Card.CardActivity;
import com.example.bookstoreapp.R;
import com.example.bookstoreapp.home;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import all.UrlData;


public class SearchFragment extends Fragment {
    private ImageView imgBack, imgCard;
    private EditText edtTk;
    private GridView grvSearch;
    private List<MyBook> lb;
    private SearchAdapter searchAdapter;
    private static int id;
    public static Fragment newInstance() {
        return new SearchFragment();
    }
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_search, container,false);
        imgBack = v.findViewById(R.id.imgBack);
        imgCard = v.findViewById(R.id.imgCard);
        grvSearch = v.findViewById(R.id.grvSearch);
        edtTk = v.findViewById(R.id.edtSearch);
        edtTk.requestFocus();

        lb = new ArrayList<>();
        if (edtTk.getText().equals("")){
            Toast.makeText(getActivity(), "clear", Toast.LENGTH_SHORT).show();
        }else {
            edtTk.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                }

                @Override
                public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
//
                }

                @Override
                public void afterTextChanged(Editable editable) {
                    lb.clear();
                    search();
                }
            });
        }


        imgCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(view.getContext(), CardActivity.class);
                view.getContext().startActivity(intent);
            }
        });
        imgBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(v.getContext(), home.class);
                startActivity(intent);
            }
        });
        grvSearch.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(view.getContext(), BookInfor.class);
                view.getContext().startActivity(intent);
            }
        });
        return v;
    }

    private void search() {
        RequestQueue queue = Volley.newRequestQueue(getContext());
        StringRequest stringRequest = new StringRequest(Request.Method.POST, UrlData.urlFindByName,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        if (!response.equals("")){
                            try {
                                JSONArray jsonArray = new JSONArray(response);
                                for (int i = 0 ; i < jsonArray.length(); i++){
                                    JSONObject jsonObject = jsonArray.getJSONObject(i);
                                    int id = jsonObject.getInt("id");
                                    String name = jsonObject.getString("name");
                                    String url = jsonObject.getString("img_url");
                                    double price = jsonObject.getDouble("price");
                                    double gprice = jsonObject.getDouble("gprice");
                                    lb.add(new MyBook(id,name,price,gprice,url));
                                    searchAdapter = new SearchAdapter(getActivity(),0, lb);
                                    grvSearch.setAdapter(searchAdapter);
                                    searchAdapter.notifyDataSetChanged();
                                }
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                        else
                            Toast.makeText(getActivity(), "Không có kết quả", Toast.LENGTH_SHORT).show();
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
                params.put("name", edtTk.getText().toString());
                return params;
            }
        };
        queue.add(stringRequest);
    }
}