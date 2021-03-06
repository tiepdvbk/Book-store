package com.example.bookstoreapp.CaNhan;

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
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.bookstoreapp.Book.BookInfor;
import com.example.bookstoreapp.Card.Card.CardActivity;
import com.example.bookstoreapp.DanhMuc.ListFragment;
import com.example.bookstoreapp.DonHang.DonHangActivity;
import com.example.bookstoreapp.HomeFragment;
import com.example.bookstoreapp.MainActivity;
import com.example.bookstoreapp.R;
import com.example.bookstoreapp.Search.SearchFragment;
import com.google.android.material.bottomsheet.BottomSheetDialog;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import all.ALL;
import all.USER;
import all.UrlData;


public class PersonalFragment extends Fragment {

    public static Fragment newInstance() {
        return new PersonalFragment();
    }
    private ImageButton btnSearch, btnCard;
    private Button btnLogout;
    private ListView lvCn;
    private CaNhanAdapter adapter;
    private TextView txtName, txtMail;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_personal,container,false);
        anhXa(v);
        Init();
        setUp();

        return v;
    }

    private void Init() {
        ArrayList<CaNhan> cns = new ArrayList<>();
        cns.add(new CaNhan(R.drawable.ic_baseline_person_outline_24, "Th??ng tin c?? nh??n"));
        cns.add(new CaNhan(R.drawable.ic_baseline_card_giftcard_24, "Th??ng tin ????n h??ng"));
        cns.add(new CaNhan(R.drawable.ic_baseline_location_on_241, "?????a ch??? giao h??ng"));
        cns.add(new CaNhan(R.drawable.ic_baseline_history_24, "S???n ph???m ???? xem"));
        cns.add(new CaNhan(R.drawable.ic_baseline_headphones_24, "CSKH"));
        adapter = new CaNhanAdapter(getActivity(), 0, cns);
        lvCn.setAdapter(adapter);
    }

    private void anhXa(View v) {
        lvCn = v.findViewById(R.id.lvCaNHan);
        btnSearch = v.findViewById(R.id.ibtnSearch);
        btnCard = v.findViewById(R.id.ibtnCard);
        btnLogout = v.findViewById(R.id.btnLogout);
        txtName = v.findViewById(R.id.txtName);
        txtMail = v.findViewById(R.id.txtEmail);
    }

    private void setUp() {
        txtName.setText(USER.name);
        txtMail.setText(USER.mail);
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
        btnLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(view.getContext(), MainActivity.class);
                view.getContext().startActivity(intent);
            }
        });
        lvCn.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                switch (i){
                    case 0:
                        Intent intent0 = new Intent(view.getContext(), ThongTinCaNhanActivity.class);
                        view.getContext().startActivity(intent0);
                        break;
                    case 1:
                        Intent intent1 = new Intent(view.getContext(), DonHangActivity.class);
                        view.getContext().startActivity(intent1);
                        break;
                    case 2:
                        dialogThayDiaChi();
                        break;
                    case 3:
                        ALL.showDialog("Ch??a c?? ch???c n??ng n??y",getActivity());
                        break;
                    case 4:
                        ALL.showDialog("Ch??a c?? ch???c n??ng n??y",getActivity());
                        break;
                }

            }
        });
    }

    public void dialogThayDiaChi() {
        LayoutInflater layoutInflater = LayoutInflater.from(getContext());
        View v = layoutInflater.inflate(R.layout.thaydc_sheet, null);
        BottomSheetDialog dialog = new BottomSheetDialog(getContext());
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
            ALL.showDialog("Xin nh???p ?????a ch???",getActivity());
        else {
            RequestQueue queue = Volley.newRequestQueue(getContext());
            StringRequest stringRequest = new StringRequest(Request.Method.POST, UrlData.urlDoiDiaChi,
                    new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            if (response.equals("yes")){
                                ALL.showDialog("L??u th??nh c??ng",getActivity());
                                USER.addres = dc;
                            }else ALL.showDialog("L??u th???t b???i",getActivity());
                        }
                    },
                    new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            ALL.showDialog(error.toString(),getActivity());
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
}