package com.vk77492.bigbasketclone.recyclerviews;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.vk77492.bigbasketclone.R;
import com.vk77492.bigbasketclone.models.product_model.ProductItem;

import java.util.List;

public class ProductListAdapter extends RecyclerView.Adapter<ProductListViewHolder> {

    List<ProductItem> productLists;

    public ProductListAdapter(List<ProductItem> productLists) {
        this.productLists = productLists;
    }


    @NonNull
    @Override
    public ProductListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.items_list_layout,
                parent, false);
        return new ProductListViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ProductListViewHolder holder, int position) {
        ProductItem productItem = productLists.get(position);
        holder.setData(productItem);
    }

    @Override
    public int getItemCount() {
        return productLists.size();
    }
}
