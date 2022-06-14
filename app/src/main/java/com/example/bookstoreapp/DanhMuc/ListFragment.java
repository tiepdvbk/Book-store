package com.example.bookstoreapp.DanhMuc;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageButton;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.example.bookstoreapp.Card.Card.CardActivity;
import com.example.bookstoreapp.R;
import com.example.bookstoreapp.Search.SearchFragment;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import all.UrlData;


public class ListFragment extends Fragment {
    public static Fragment newInstance() {
        return new ListFragment();
    }
    private ImageButton btnSearch, btnCard;
    private GridView grd;
    private ArrayList<DanhMuc> dms;
    private ListAdapter adapter;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_list, container,false);

        btnSearch = v.findViewById(R.id.ibtnSearch);
        btnCard = v.findViewById(R.id.ibtnCard);
        grd = (GridView) v.findViewById(R.id.grdV);
        dms = new ArrayList<>();
        setUp();
        readList();
        return v;
    }
    private void readList(){
        RequestQueue queue = Volley.newRequestQueue(getContext());
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, UrlData.urlGetListBook, null,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        for (int i = 0 ; i < response.length(); i++){
                            try {
                                JSONObject jsonObject = response.getJSONObject(i);
                                dms.add(new DanhMuc(jsonObject.getInt("id"),jsonObject.getString("name")));
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                        adapter = new ListAdapter(getActivity(), 0,dms);
                        grd.setAdapter(adapter);
                        adapter.notifyDataSetChanged();
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(getActivity(), error.toString(), Toast.LENGTH_SHORT).show();
                    }
                });
        queue.add(jsonArrayRequest);
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
        grd.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(view.getContext(), DanhMucSach.class);
                Bundle bundle = new Bundle();
                bundle.putInt("id", dms.get(i).getId());
                bundle.putString("nameDm", dms.get(i).getName());
                intent.putExtras(bundle);
                view.getContext().startActivity(intent);
            }
        });
    }
}