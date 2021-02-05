package com.vk77492.bigbasketclone.recyclerviews;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.vk77492.bigbasketclone.R;
import com.vk77492.bigbasketclone.RemoveItemListener;
import com.vk77492.bigbasketclone.UpdatePrice;
import com.vk77492.bigbasketclone.models.product_model.ProductItem;

import java.util.List;

public class CartListAdapter extends RecyclerView.Adapter<CartViewHolder> {

    List<ProductItem> productLists;
    private RemoveItemListener removeItemFromCart;
    private UpdatePrice updatePrice;

    public CartListAdapter(List<ProductItem> productLists, RemoveItemListener removeItemFromCart, UpdatePrice updatePrice) {
        this.removeItemFromCart = removeItemFromCart;
        this.updatePrice = updatePrice;

        this.productLists = productLists;

    }

    @NonNull
    @Override
    public CartViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.cart_item_layout,
                parent, false);
        return new CartViewHolder(view, removeItemFromCart,updatePrice);
    }

    @Override
    public void onBindViewHolder(@NonNull CartViewHolder holder, int position) {
        ProductItem productItem = productLists.get(position);
        holder.setData(productItem);
    }

    @Override
    public int getItemCount() {
        return productLists.size();
    }
}
