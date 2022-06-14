package com.example.bookstoreapp.CaNhan;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.bookstoreapp.R;

import java.util.List;

public class CaNhanAdapter extends ArrayAdapter<CaNhan> {
    private Context ct;
    private List<CaNhan> cns = null;
    private int resource;
    public CaNhanAdapter(@NonNull Context context, int resource, @NonNull List<CaNhan> objects) {
        super(context, resource, objects);
        this.ct = context;
        this.cns = objects;
        this.resource = resource;
    }

    @SuppressLint("InflateParams")
    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        CaNhan cn = cns.get(position);
        if(convertView==null)
            convertView = LayoutInflater.from(ct).inflate(R.layout.custom_lv_canhan, null);
        ImageView imgIc = (ImageView) convertView.findViewById(R.id.imgIc);
        imgIc.setImageResource(cn.getIc());
        TextView tvTitle = (TextView) convertView.findViewById(R.id.tvTitle);
        tvTitle.setText(cn.getTitle());
        return convertView;
    }
}
