package com.vk77492.bigbasketclone.ui.basket;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.vk77492.bigbasketclone.R;

public class BasketViewHolder extends RecyclerView.ViewHolder {

    private ImageView mIvProductImage;
    private TextView mTvTotalItems;
    private TextView mTvProductName;
    private TextView mTvPrductWight;
    private TextView mTvProductPrice;
    private ImageView mIvRemoveProduct;
    private TextView mTvCountItems;
    private ImageView mIvAddProduct;

    public BasketViewHolder(@NonNull View itemView) {
        super(itemView);
        initCiew(itemView);
    }

    private void initCiew(View itemView) {
        mIvAddProduct= itemView.findViewById(R.id.ivAddProduct);
        mIvProductImage= itemView.findViewById(R.id.ivProductImage);
        mIvRemoveProduct= itemView.findViewById(R.id.ivRemoveProduct);
        mTvCountItems= itemView.findViewById(R.id.tvCountItems);
        mTvPrductWight= itemView.findViewById(R.id.tvProductWight);
        mTvProductName= itemView.findViewById(R.id.tvProductName);
        mTvProductPrice= itemView.findViewById(R.id.tvProductPrice);
        mTvTotalItems= itemView.findViewById(R.id.tvTotalItems);
    }
    public void setData(BasketViewModel basketModelClass) {
    }
}
