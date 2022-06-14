package category;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.bookstoreapp.Book.BookAdapter;
import com.example.bookstoreapp.DanhMuc.DanhMucSach;
import com.example.bookstoreapp.R;

import java.util.List;

public class CategoryAdapter extends RecyclerView.Adapter<CategoryAdapter.CategoryHolder>{
    private Context mContext;
    private TextView tvSelect;
    private RecyclerView rcv_book;
    private List<Category> mCategoryList;

    public CategoryAdapter(Context mContext) {
        this.mContext = mContext;
    }
    @SuppressLint("NotifyDataSetChanged")
    public void setData(List<Category> list){
        this.mCategoryList = list;
        notifyDataSetChanged();
    }
    @NonNull
    @Override
    public CategoryHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_category, parent, false);
        tvSelect = view.findViewById(R.id.tvSelectAll);
        rcv_book = view.findViewById(R.id.rcv_book);
        return new CategoryHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CategoryHolder holder, @SuppressLint("RecyclerView") int position) {
        Category category = mCategoryList.get(position);
        if(category == null){
            return;
        }
        holder.tvCtgr.setText(category.getNameCtgr());

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(mContext, RecyclerView.HORIZONTAL, false);
        holder.rcvBook.setLayoutManager(linearLayoutManager);

        BookAdapter adapter = new BookAdapter();
        adapter.setData(category.getBooks());
        holder.rcvBook.setAdapter(adapter);
        tvSelect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(view.getContext(), DanhMucSach.class);
                Bundle bundle = new Bundle();
                bundle.putInt("id",category.getBooks().get(position).getId_tl());
                bundle.putString("nameDm", category.getNameCtgr());
                intent.putExtras(bundle);
                view.getContext().startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        if (mCategoryList != null){
            return mCategoryList.size();
        }
        return 0;
    }

    class CategoryHolder extends RecyclerView.ViewHolder {
        private TextView tvCtgr;
        private RecyclerView rcvBook;
        public CategoryHolder(@NonNull View itemView) {
            super(itemView);
            tvCtgr = itemView.findViewById(R.id.tvNameCtg);
            rcvBook = itemView.findViewById(R.id.rcv_book);
        }
    }
}
