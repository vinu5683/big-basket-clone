package com.vk77492.bigbasketclone.recyclerviews;

import android.content.Intent;
import android.graphics.Paint;
import android.os.SystemClock;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.vk77492.bigbasketclone.BottomNavigation;
import com.vk77492.bigbasketclone.LoginRegisterActivity;
import com.vk77492.bigbasketclone.ProductActivity;
import com.vk77492.bigbasketclone.R;
import com.vk77492.bigbasketclone.RemoveItemListener;
import com.vk77492.bigbasketclone.UpdatePrice;
import com.vk77492.bigbasketclone.models.product_model.ProductItem;
import com.vk77492.bigbasketclone.sharedpreference.PreferenceHelper;
import com.vk77492.bigbasketclone.ui.basket.BasketFragment;

public class CartViewHolder extends RecyclerView.ViewHolder {

    private TextView tvRvCartListOffer, tvProdName, tvCartProductQuantity,
            tvCartProdPrice, tvCartProdPriceMRP, tvQty;
    private Button btnDec, btnInc;
    private ImageView ivRvCartListImage;
    private RemoveItemListener removeItemFromCart;
    private UpdatePrice updatePrice;

    public CartViewHolder(@NonNull View itemView, RemoveItemListener removeItemFromCart, UpdatePrice updatePrice) {
        super(itemView);
        this.removeItemFromCart = removeItemFromCart;
        this.updatePrice = updatePrice;
        initViews(itemView);
    }

    private void initViews(View itemView) {
        tvProdName = itemView.findViewById(R.id.tvProdName);
        tvRvCartListOffer = itemView.findViewById(R.id.tvRvCartListOffer);
        tvCartProductQuantity = itemView.findViewById(R.id.tvCartProductQuantity);
        tvCartProdPrice = itemView.findViewById(R.id.tvCartProdPrice);
        tvCartProdPriceMRP = itemView.findViewById(R.id.tvCartProdPriceMRP);
        tvQty = itemView.findViewById(R.id.tvQty);
        btnDec = itemView.findViewById(R.id.btnDec);
        btnInc = itemView.findViewById(R.id.btnInc);
        ivRvCartListImage = itemView.findViewById(R.id.ivRvCartListImage);
        PreferenceHelper.getSharedPreferences(itemView.getContext());
    }

    public void setData(ProductItem productItem) {
        tvProdName.setText(productItem.getTitle() + " " + productItem.getSubCategory());
        tvCartProdPriceMRP.setText("\u20B9" + productItem.getPrice());
        tvCartProdPriceMRP.setPaintFlags(Paint.STRIKE_THRU_TEXT_FLAG);
        double price = productItem.getPrice();
        BasketFragment.totMrp += price;
        price = Math.floor(price - (price * productItem.getOffer()) / 100);
        BasketFragment.totPrice += price;
        tvCartProdPrice.setText("\u20B9" + price);
        tvRvCartListOffer.setText(productItem.getDiscount());
        tvCartProductQuantity.setText(productItem.getAttribute() + " per Pack");
        Glide.with(ivRvCartListImage).load(productItem.getImages().get(0)).into(ivRvCartListImage);
        ivRvCartListImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), ProductActivity.class);
                intent.putExtra("id", productItem.getId());
                v.getContext().startActivity(intent);
            }
        });

        btnInc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int q = Integer.parseInt(tvQty.getText().toString()) + 1;
                tvQty.setText(q + "");
                updatePrice.onFinish(productItem.getId(), q);
            }
        });

        btnDec.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int q = Integer.parseInt(tvQty.getText().toString()) - 1;
                tvQty.setText(q + "");
                if (q == 0) {
                    removeItemFromCart.removeItemFromCart(productItem.getId());
                }
                updatePrice.onFinish(productItem.getId(), q);
            }
        });

    }

}
