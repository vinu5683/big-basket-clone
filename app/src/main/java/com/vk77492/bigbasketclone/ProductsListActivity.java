package com.vk77492.bigbasketclone;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.vk77492.bigbasketclone.models.product_model.ProductItem;
import com.vk77492.bigbasketclone.recyclerviews.ProductListAdapter;

import java.util.ArrayList;
import java.util.List;

public class ProductsListActivity extends AppCompatActivity {

    private RecyclerView rvProductListActivity;
    private List<ProductItem> productItems;
    private List<ProductItem> resultProductItems;
    private TextView tvBackProdActivity, tvItemNotFound;
    private ImageView ivItemNotFound;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_products_list);
        initViewsAndData();
        getSortedList();
        setRecyclerViewAdapter();
    }

    private void initViewsAndData() {
        productItems = BottomNavigation.productItemList;
        rvProductListActivity = findViewById(R.id.rvProductListActivity);
        tvBackProdActivity = findViewById(R.id.tvBackProdActivity);
        tvItemNotFound = findViewById(R.id.tvItemNotFound);
        ivItemNotFound = findViewById(R.id.ivItemNotFound);
        tvBackProdActivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void getSortedList() {
        resultProductItems = new ArrayList<>();

        if (getIntent() != null && getIntent().getExtras() != null) {
            String tag1 = getIntent().getStringExtra("tag1");
            String tag2 = getIntent().getStringExtra("tag2");
            if (tag1 != null && tag2 != null) {
                tag1 = tag1.toLowerCase();
                tag2 = tag2.toLowerCase();
                for (int i = 0; i < productItems.size(); i++) {
                    if (productItems.get(i).getTitle().toLowerCase().contains(tag1) ||
                            productItems.get(i).getTitle().toLowerCase().contains(tag2) ||
                            productItems.get(i).getSubCategory().toLowerCase().contains(tag1) ||
                            productItems.get(i).getCategory().toLowerCase().contains(tag2) ||
                            productItems.get(i).getCategory().toLowerCase().contains(tag1) ||
                            productItems.get(i).getSubCategory().toLowerCase().contains(tag2))
                        resultProductItems.add(productItems.get(i));
                }
            } else if (tag1 != null) {
                tag1 = tag1.toLowerCase();
                for (int i = 0; i < productItems.size(); i++) {
                    if (productItems.get(i).getSubCategory().toLowerCase().contains(tag1) ||
                            productItems.get(i).getCategory().toLowerCase().contains(tag1) ||
                            productItems.get(i).getTitle().toLowerCase().contains(tag1))
                        resultProductItems.add(productItems.get(i));
                }
            } else
                resultProductItems = productItems;
        }
    }

    private void setRecyclerViewAdapter() {
        if (resultProductItems.size() != 0) {
            ProductListAdapter adapter = new ProductListAdapter(resultProductItems);
            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
            rvProductListActivity.setLayoutManager(linearLayoutManager);
            rvProductListActivity.setAdapter(adapter);
        } else {
            ivItemNotFound.setVisibility(View.VISIBLE);
            tvItemNotFound.setVisibility(View.VISIBLE);
        }
    }
}