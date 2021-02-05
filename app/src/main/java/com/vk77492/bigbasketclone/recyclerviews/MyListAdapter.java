package com.vk77492.bigbasketclone.recyclerviews;

import android.graphics.Paint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.vk77492.bigbasketclone.R;
import com.vk77492.bigbasketclone.models.product_model.ProductItem;

import java.util.List;

public class MyListAdapter extends RecyclerView.Adapter<MyListViewHolder> {

    List<ProductItem> productLists;

    public MyListAdapter(List<ProductItem> productLists) {
        this.productLists = productLists;
    }

    @NonNull
    @Override
    public MyListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.items_list_layout,
                parent, false);
        return new MyListViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyListViewHolder holder, int position) {
        ProductItem productItem = productLists.get(position);
        holder.setData(productItem);
    }

    @Override
    public int getItemCount() {
        return productLists.size();
    }
}

class MyListViewHolder extends RecyclerView.ViewHolder{
    private TextView tvProdName, tvProdPriceMRP, tvProdPrice, tvAttribute, tvRvListOffer;
    private Button btnAddList;
    private ImageView ivRvListImage;
    public MyListViewHolder(@NonNull View itemView) {
        super(itemView);
        tvProdName = itemView.findViewById(R.id.tvProdName);
        tvProdPriceMRP = itemView.findViewById(R.id.tvProdPriceMRP);
        tvRvListOffer = itemView.findViewById(R.id.tvRvListOffer);
        tvProdPrice = itemView.findViewById(R.id.tvProdPrice);
        tvAttribute = itemView.findViewById(R.id.tvProductQuantity);
        ivRvListImage = itemView.findViewById(R.id.ivRvListImage);
        btnAddList = itemView.findViewById(R.id.btnAddList);
        btnAddList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            }
        });
    }

    public void setData(ProductItem productItem) {
        tvProdName.setText(productItem.getTitle() + " " + productItem.getSubCategory());
        tvProdPrice.setText("\u20B9" + productItem.getPrice() + "");
        tvProdPriceMRP.setText("\u20B9" + productItem.getPrice() + "");
        tvProdPriceMRP.setPaintFlags(Paint.STRIKE_THRU_TEXT_FLAG);
        tvRvListOffer.setText(productItem.getDiscount());
        tvAttribute.setText(productItem.getAttribute() + " per Pack");
        Glide.with(ivRvListImage).load(productItem.getImages().get(0)).into(ivRvListImage);
    }
}
