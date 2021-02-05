package com.vk77492.bigbasketclone;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.graphics.Paint;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.vk77492.bigbasketclone.models.product_model.ProductItem;
import com.vk77492.bigbasketclone.recyclerviews.ProductAdapter;
import com.vk77492.bigbasketclone.sharedpreference.PreferenceHelper;

import java.util.ArrayList;
import java.util.List;

public class ProductActivity extends AppCompatActivity implements View.OnClickListener {
    private RecyclerView rvProductFruitsVeg;
    private ImageView mIvMain, iv0, iv1, iv2, iv3;
    private ProductItem item;
    private Button addToBasket;
    private TextView tvTitle, tvProductPrice, tvProductMRP, tvProductDiscount, tvAbout, tvUses;
    private List<ProductItem> productItems = BottomNavigation.productItemList;
    private int prodId;
    private List<ProductItem> resultProductItems;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product);
        initViewsListeners();
        if (getIntent() != null && getIntent().getExtras() != null) {
            prodId = getIntent().getIntExtra("id", 1);
            for (int i = 0; i < productItems.size(); i++) {
                if (productItems.get(i).getId() == prodId) {
                    item = productItems.get(i);
                    setProductPageData(item);
                    getSortedList();
                    setRecyclerViewData();
                }
            }
        }
    }

    private void setRecyclerViewData() {
        ProductAdapter productAdapter = new ProductAdapter(resultProductItems);
        LinearLayoutManager linearLayoutManager = new
                LinearLayoutManager(this, RecyclerView.HORIZONTAL, false);
        rvProductFruitsVeg.setLayoutManager(linearLayoutManager);
        rvProductFruitsVeg.setAdapter(productAdapter);
    }

    private void initViewsListeners() {
        rvProductFruitsVeg = findViewById(R.id.rvProductFruitsVeg);
        addToBasket = findViewById(R.id.addToBasket);
        mIvMain = findViewById(R.id.ivMain);
        iv0 = findViewById(R.id.iv0);
        iv1 = findViewById(R.id.iv1);
        iv2 = findViewById(R.id.iv2);
        iv3 = findViewById(R.id.iv3);
        tvTitle = findViewById(R.id.tvProductTitle);
        tvProductPrice = findViewById(R.id.tvProductPrice);
        tvProductMRP = findViewById(R.id.tvProductMRP);
        tvProductDiscount = findViewById(R.id.tvProductDiscount);
        tvAbout = findViewById(R.id.tvAbout);
        tvUses = findViewById(R.id.tvUses);

        iv0.setOnClickListener(this);
        iv1.setOnClickListener(this);
        iv2.setOnClickListener(this);
        iv3.setOnClickListener(this);

        addToBasket.setOnClickListener(this);

    }

    private void setProductPageData(ProductItem productItem) {
        if (productItem.getImages().size() >= 4) {
            Glide.with(mIvMain).load(productItem.getImages().get(0)).into(mIvMain);
            Glide.with(iv0).load(productItem.getImages().get(0)).into(iv0);
            Glide.with(iv1).load(productItem.getImages().get(1)).into(iv1);
            Glide.with(iv2).load(productItem.getImages().get(2)).into(iv2);
            Glide.with(iv3).load(productItem.getImages().get(3)).into(iv3);
        } else if (productItem.getImages().size() == 3) {
            Glide.with(mIvMain).load(productItem.getImages().get(0)).into(mIvMain);
            Glide.with(iv0).load(productItem.getImages().get(0)).into(iv0);
            Glide.with(iv1).load(productItem.getImages().get(1)).into(iv1);
            Glide.with(iv2).load(productItem.getImages().get(2)).into(iv2);
            iv3.setVisibility(View.GONE);
        } else if (productItem.getImages().size() == 2) {
            Glide.with(mIvMain).load(productItem.getImages().get(0)).into(mIvMain);
            Glide.with(iv0).load(productItem.getImages().get(0)).into(iv0);
            Glide.with(iv1).load(productItem.getImages().get(1)).into(iv1);
            iv2.setVisibility(View.GONE);
            iv3.setVisibility(View.GONE);
        } else if (productItem.getImages().size() == 1) {
            Glide.with(mIvMain).load(productItem.getImages().get(0)).into(mIvMain);
            Glide.with(iv0).load(productItem.getImages().get(0)).into(iv0);
            iv1.setVisibility(View.GONE);
            iv2.setVisibility(View.GONE);
            iv3.setVisibility(View.GONE);
        } else {
            mIvMain.setVisibility(View.GONE);
            iv0.setVisibility(View.GONE);
            iv1.setVisibility(View.GONE);
            iv2.setVisibility(View.GONE);
            iv3.setVisibility(View.GONE);
        }

        tvTitle.setText(productItem.getTitle() + " - " + productItem.getSubCategory());
        tvProductPrice.setText("\u20B9" + productItem.getPrice());
        tvProductMRP.setText("\u20B9" + productItem.getPrice());
        tvProductMRP.setPaintFlags(Paint.STRIKE_THRU_TEXT_FLAG);
        tvProductDiscount.setText(productItem.getDiscount());
        tvAbout.setText(productItem.getAboutProduct());
        tvUses.setText(productItem.getStorageAndUses());
    }

    private void getSortedList() {
        resultProductItems = new ArrayList<>();
        String tag1 = item.getSuggestedItemsTag();
        tag1 = tag1.toLowerCase();
        for (int i = 0; i < productItems.size(); i++) {
            if (productItems.get(i).getSubCategory().toLowerCase().contains(tag1) ||
                    productItems.get(i).getCategory().toLowerCase().contains(tag1) ||
                    productItems.get(i).getTitle().toLowerCase().contains(tag1))
                resultProductItems.add(productItems.get(i));
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.iv0:
                Glide.with(mIvMain).load(item.getImages().get(0)).into(mIvMain);
                break;
            case R.id.iv1:
                Glide.with(mIvMain).load(item.getImages().get(1)).into(mIvMain);
                break;
            case R.id.iv2:
                Glide.with(mIvMain).load(item.getImages().get(2)).into(mIvMain);
                break;
            case R.id.iv3:
                Glide.with(mIvMain).load(item.getImages().get(3)).into(mIvMain);
                break;
            case R.id.addToBasket:
                addItemToCart();
                break;


        }
    }

    private void addItemToCart() {
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
                if (Integer.parseInt(cart[i]) == prodId) {
                    Toast.makeText(this, "Item Already in cart",
                            Toast.LENGTH_SHORT).show();
                    canIAddIt = false;
                    break;
                }
            }
            if (canIAddIt) {
                stringBuffer.append(string).append(prodId).append(" ");
                PreferenceHelper.writeStringToPreference(BottomNavigation.PREF_USER_CART_KEY,
                        stringBuffer.toString());
                Toast.makeText(this, "Item added to Cart",
                        Toast.LENGTH_SHORT).show();
            }
        } else {
            stringBuffer.append(string).append(prodId).append(" ");
            PreferenceHelper.writeStringToPreference(BottomNavigation.PREF_USER_CART_KEY,
                    stringBuffer.toString());
            Toast.makeText(this, "Item added to Cart", Toast.LENGTH_SHORT).show();
        }
    }
}