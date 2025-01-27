package com.vk77492.bigbasketclone.recyclerviews;

import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.vk77492.bigbasketclone.BottomNavigation;
import com.vk77492.bigbasketclone.LoginRegisterActivity;
import com.vk77492.bigbasketclone.ProductActivity;
import com.vk77492.bigbasketclone.R;
import com.vk77492.bigbasketclone.models.product_model.ProductItem;
import com.vk77492.bigbasketclone.models.user_model.UserItem;
import com.vk77492.bigbasketclone.sharedpreference.PreferenceHelper;

import java.lang.reflect.Type;

public class ProductViewHolder extends RecyclerView.ViewHolder {

    private TextView tvHomeProductName, tvRatingStar, tvCountRatings,
            tvPriceRv, tvAttribute, tvRvHomeOffer;
    private Button btnAddHomeRv;
    private ImageView ivRvHomeImage;

    public ProductViewHolder(@NonNull View itemView) {
        super(itemView);
        initViews(itemView);
    }

    private void initViews(View itemView) {
        tvHomeProductName = itemView.findViewById(R.id.tvHomeProductName);
        tvRatingStar = itemView.findViewById(R.id.tvRatingStar);
        tvRvHomeOffer = itemView.findViewById(R.id.tvRvHomeOffer);
        tvPriceRv = itemView.findViewById(R.id.tvPriceRv);
        tvCountRatings = itemView.findViewById(R.id.tvCountRatings);
        tvAttribute = itemView.findViewById(R.id.tvAttribute);
        ivRvHomeImage = itemView.findViewById(R.id.ivRvHomeImage);
        btnAddHomeRv = itemView.findViewById(R.id.btnAddHomeRv);
        PreferenceHelper.getSharedPreferences(itemView.getContext());
    }

    public void setData(ProductItem productItem) {
        tvHomeProductName.setText(productItem.getTitle() + " " + productItem.getSubCategory());
        double rating = 3.8;
        tvRatingStar.setText(rating + "");
        tvPriceRv.setText("\u20B9" + productItem.getPrice());
        tvRvHomeOffer.setText(productItem.getDiscount());
        tvCountRatings.setText("166 Ratings");
        tvAttribute.setText(productItem.getAttribute() + " per Pack");
        Glide.with(ivRvHomeImage).load(productItem.getImages().get(0)).into(ivRvHomeImage);
        ivRvHomeImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), ProductActivity.class);
                intent.putExtra("id", productItem.getId());
                v.getContext().startActivity(intent);
            }
        });
        btnAddHomeRv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (PreferenceHelper.readBooleanFromPreference(BottomNavigation.PREF_USER_AVAILABLE_KEY)) {
                    Log.d("TAG", "onClick: user found");
                    StringBuffer stringBuffer = new StringBuffer();
                    String string = PreferenceHelper.readStringFromPreference(
                            BottomNavigation.PREF_USER_CART_KEY);
                    if (!PreferenceHelper.readStringFromPreference(
                            BottomNavigation.PREF_USER_CART_KEY).equals("")) {
                        boolean canIAddIt = true;
                        Log.d("TAG", "onClick: " + string);
                        String[] cart = string.split(" ");
                        for (int i = 0; i < cart.length; i++) {
                            Log.d("TAG", "cart item id " + cart[i]);
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
