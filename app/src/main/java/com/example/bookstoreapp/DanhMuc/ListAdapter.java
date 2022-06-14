package com.example.bookstoreapp.DanhMuc;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.bookstoreapp.R;

import java.util.List;

public class ListAdapter extends ArrayAdapter<DanhMuc> {
    private Context ct;
    private List<DanhMuc> dms = null;
    private int resource;
    public ListAdapter(@NonNull Context context, int resource, @NonNull List<DanhMuc> objects) {
        super(context, resource, objects);
        this.ct = context;
        this.dms = objects;
        this.resource = resource;
    }

    @SuppressLint("InflateParams")
    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        DanhMuc dm = dms.get(position);
        if(convertView==null)
            convertView = LayoutInflater.from(ct).inflate(R.layout.custom_danhmuc2, null);
        TextView tvDm = (TextView) convertView.findViewById(R.id.tvDm);
        tvDm.setText(dm.getName());
        return convertView;
    }
}
