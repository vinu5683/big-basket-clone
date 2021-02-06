package com.vk77492.bigbasketclone.recyclerviews;

import android.content.Intent;
import android.graphics.Paint;
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
import com.vk77492.bigbasketclone.models.product_model.ProductItem;
import com.vk77492.bigbasketclone.sharedpreference.PreferenceHelper;

public class ProductListViewHolder extends RecyclerView.ViewHolder {

    private TextView tvProdName, tvProdPriceMRP, tvProdPrice, tvAttribute, tvRvListOffer;
    private Button btnAddList;
    private ImageView ivRvListImage;

    public ProductListViewHolder(@NonNull View itemView) {
        super(itemView);
        initViews(itemView);
    }

    private void initViews(View itemView) {
        tvProdName = itemView.findViewById(R.id.tvProdName);
        tvProdPriceMRP = itemView.findViewById(R.id.tvProdPriceMRP);
        tvRvListOffer = itemView.findViewById(R.id.tvRvListOffer);
        tvProdPrice = itemView.findViewById(R.id.tvProdPrice);
        tvAttribute = itemView.findViewById(R.id.tvProductQuantity);
        ivRvListImage = itemView.findViewById(R.id.ivRvListImage);
        btnAddList = itemView.findViewById(R.id.btnAddList);
    }

    public void setData(ProductItem productItem) {
        tvProdName.setText(productItem.getTitle() + " " + productItem.getSubCategory());
        tvProdPrice.setText("\u20B9" + productItem.getPrice() + "");
        tvProdPriceMRP.setText("\u20B9" + productItem.getPrice() + "");
        tvProdPriceMRP.setPaintFlags(Paint.STRIKE_THRU_TEXT_FLAG);
        tvRvListOffer.setText(productItem.getDiscount());
        tvAttribute.setText(productItem.getAttribute() + " per Pack");
        Glide.with(ivRvListImage).load(productItem.getImages().get(0)).into(ivRvListImage);
        ivRvListImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), ProductActivity.class);
                intent.putExtra("id", productItem.getId());
                v.getContext().startActivity(intent);
            }
        });

        btnAddList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (PreferenceHelper.readBooleanFromPreference(BottomNavigation.PREF_USER_AVAILABLE_KEY)) {

                    StringBuffer stringBuffer = new StringBuffer();
                    String string = PreferenceHelper.readStringFromPreference(
                            BottomNavigation.PREF_USER_CART_KEY);
                    if (!PreferenceHelper.readStringFromPreference(
                            BottomNavigation.PREF_USER_CART_KEY).equals("")) {
                        boolean canIAddIt = true;
                        Log.d("TAG", "onClick: " + string);
                        String[] cart = string.split(" ");
                        for (int i = 0; i < cart.length; i++) {
                            if (Integer.parseInt(cart[i]) == productItem.getId()) {
                                Toast.makeText(v.getContext(), "Item Already in cart",
                                        Toast.LENGTH_SHORT).show();
                                canIAddIt = false;
                                break;
                            }
                        }
                        if (canIAddIt) {
                            stringBuffer.append(string).append(productItem.getId()).append(" ");
                            PreferenceHelper.writeStringToPreference(BottomNavigation.PREF_USER_CART_KEY,
                                    stringBuffer.toString());
                            Toast.makeText(v.getContext(), "Item added to Cart",
                                    Toast.LENGTH_SHORT).show();
                        }
                    } else {
                        stringBuffer.append(string).append(productItem.getId()).append(" ");
                        PreferenceHelper.writeStringToPreference(BottomNavigation.PREF_USER_CART_KEY,
                                stringBuffer.toString());
                        Toast.makeText(v.getContext(), "Item added to Cart", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Intent intent = new Intent(v.getContext(), LoginRegisterActivity.class);
                    v.getContext().startActivity(intent);
                }
            }
        });
    }
}
