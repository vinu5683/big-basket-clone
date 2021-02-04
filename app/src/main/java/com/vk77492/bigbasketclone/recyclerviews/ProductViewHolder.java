package com.vk77492.bigbasketclone.recyclerviews;

import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.vk77492.bigbasketclone.R;
import com.vk77492.bigbasketclone.models.product_model.ProductItem;

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
        btnAddHomeRv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }

    public void setData(ProductItem productItem) {
        tvHomeProductName.setText(productItem.getTitle() + " " + productItem.getSubCategory());
        double rating = 3.8;
        tvRatingStar.setText(rating + "");
        tvPriceRv.setText(productItem.getPrice() + "");
        tvRvHomeOffer.setText(productItem.getDiscount());
        tvCountRatings.setText("166 Ratings");
        tvAttribute.setText("1 " + productItem.getAttribute() + " per Pack");
        Glide.with(ivRvHomeImage).load(productItem.getImages().get(0)).into(ivRvHomeImage);

    }
}
