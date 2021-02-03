package com.vk77492.bigbasketclone.ui.basket;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.vk77492.bigbasketclone.R;

import java.util.ArrayList;

public class basketAdapter extends RecyclerView.Adapter<BasketViewHolder> {
    private ArrayList<BasketViewModel>basketModelClassList;

    public basketAdapter(ArrayList<BasketViewModel> basketModelClassList) {
        this.basketModelClassList = basketModelClassList;
    }




    @NonNull
    @Override
    public BasketViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.basket_items_layout,
                parent,false);
        return new BasketViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull BasketViewHolder holder, int position) {
BasketViewModel basketModelClass = basketModelClassList.get(position);
holder.setData(basketModelClass);
    }

    @Override
    public int getItemCount() {
        return basketModelClassList.size();
    }
}
