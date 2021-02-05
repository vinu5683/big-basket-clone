package com.vk77492.bigbasketclone.ui.home;

import android.content.Intent;
import android.icu.number.Scale;
import android.net.Uri;
import android.os.Bundle;
import android.os.SystemClock;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.reflect.TypeToken;
import com.synnapps.carouselview.CarouselView;
import com.synnapps.carouselview.ImageListener;
import com.vk77492.bigbasketclone.BottomNavigation;
import com.vk77492.bigbasketclone.ProductsListActivity;
import com.vk77492.bigbasketclone.R;
import com.vk77492.bigbasketclone.models.product_model.ProductItem;
import com.vk77492.bigbasketclone.models.product_model.ProductList;
import com.vk77492.bigbasketclone.recyclerviews.ProductAdapter;
import com.vk77492.bigbasketclone.sharedpreference.PreferenceHelper;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.InputStream;
import java.lang.reflect.Type;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

public class HomeFragment extends Fragment implements View.OnClickListener {

    private HomeViewModel homeViewModel;
    private CarouselView mCarouselView, mCarouselView2;
    private String[] mImages1, mImages2;
    private EditText mEtSearch;
    private RecyclerView mRvFruitsVeg;
    private ProductList productList;
    private ImageView mIvFruits;
    CardView mCvCleaning;


    private Runnable showRunnable = new Runnable() {
        @Override
        public void run() {
            SystemClock.sleep(150);
            makeItVisible();
        }
    };

    private void makeItVisible() {
        getActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                mEtSearch.setVisibility(View.VISIBLE);
            }
        });
    }


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        homeViewModel =
                new ViewModelProvider(this).get(HomeViewModel.class);
        View root = inflater.inflate(R.layout.fragment_home, container, false);
        return root;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mEtSearch = view.findViewById(R.id.etHomeSearch);
        mEtSearch.setVisibility(View.GONE);
        initViewsAndImages(view);
    }

    private void initViewsAndImages(View view) {
        Thread thread = new Thread(showRunnable);
        thread.start();
        mCarouselView = view.findViewById(R.id.carouselView);
        mCarouselView2 = view.findViewById(R.id.carouselView2);
        mRvFruitsVeg = view.findViewById(R.id.rvHomeFruitsVeg);
        setBgThread();

        mIvFruits = view.findViewById(R.id.ivFruits);
        mCvCleaning = view.findViewById(R.id.cvCleaning);
        mIvFruits.setOnClickListener(this);
        mCvCleaning.setOnClickListener(this);
        ImageView mIvTopOne = view.findViewById(R.id.ivTopOne);
        Glide.with(mIvTopOne).load("https://www.bigbasket.com/media/uploads/banner_images/T1_bigdays_EP_1130x400_1stFeb21.jpg").into(mIvTopOne);
        ImageView mIvTopTwo = view.findViewById(R.id.ivTopTwo);
        Glide.with(mIvTopTwo).load("https://www.bigbasket.com/media/uploads/banner_images/cp_t1_hardinsasta_mumbai_400_2ndfeb.jpg").into(mIvTopTwo);
        ImageView mIvTopThree = view.findViewById(R.id.ivTopThree);
        Glide.with(mIvTopThree).load("https://www.bigbasket.com/media/uploads/banner_images/TB_FreshoDays_Mumbai_1600x460.jpg").into(mIvTopThree);

        mImages1 = new String[]{"https://www.bigbasket.com/media/uploads/banner_images/NNP9989-28thjan.jpg",
                "https://www.bigbasket.com/media/uploads/banner_images/NNP9990-28thjan.jpg",
                "https://www.bigbasket.com/media/uploads/banner_images/NNP9993-28thjan.jpg",
                "https://www.bigbasket.com/media/uploads/banner_images/NNP9991-28thjan.jpg",
                "https://www.bigbasket.com/media/uploads/banner_images/NNP9992-28thjan.jpg",
                "https://www.bigbasket.com/media/uploads/banner_images/NNP9988-28thjan.jpg"};
        mCarouselView.setImageListener(new ImageListener() {
            @Override
            public void setImageForPosition(int position, ImageView imageView) {
                try {
                    imageView.setScaleType(ImageView.ScaleType.FIT_XY);
                    Glide.with(imageView).load(mImages1[position]).into(imageView);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
        mCarouselView.setPageCount(mImages1.length);

        mImages2 = new String[]{
                "https://www.bigbasket.com/media/uploads/banner_images/All_PetStore_DT_3_1130x400_25thJan.jpg",
                "https://www.bigbasket.com/media/uploads/banner_images/All_BakeryStore_DT_4_1130x400_25thJan.jpg",
                "https://www.bigbasket.com/media/uploads/banner_images/All_BabyStore_DT_1_1130x400_25thJan.jpg"};
        mCarouselView2.setImageListener(new ImageListener() {
            @Override
            public void setImageForPosition(int position, ImageView imageView) {
                try {
                    imageView.setScaleType(ImageView.ScaleType.FIT_XY);
                    Glide.with(imageView).load(mImages2[position]).into(imageView);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
        mCarouselView2.setPageCount(mImages2.length);
    }

    private void setBgThread() {
        Thread thread = new Thread(fetchProductsDataRunnable);
        thread.start();
    }

    private Runnable fetchProductsDataRunnable = new Runnable() {
        @Override
        public void run() {
            fetchProductsData();
        }
    };

    private void fetchProductsData() {
        try {
            InputStream inputStream = getActivity().getAssets().open("productslist.json");
            int data = inputStream.read();
            StringBuffer stringBuffer = new StringBuffer();
            while (data != -1) {
                char ch = (char) data;
                stringBuffer.append(ch);
                data = inputStream.read();
            }
            buildPOJOFromJSON(stringBuffer.toString());

        } catch (Exception e) {

        }
    }

    private void buildPOJOFromJSON(String buffer) {
        Gson gson = new Gson();
        Type type = new TypeToken<ProductList>() {
        }.getType();


//        jsonObject.add("names", gson.fromJson(buffer, type));
//        jsonObject.addProperty("userId", "vinod");


        productList = gson.fromJson(buffer, type);
        setRecyclerAdapter(productList);
    }

    private void setRecyclerAdapter(ProductList responseModel) {
        getActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                BottomNavigation.productItemList = responseModel.getProduct();
                ProductAdapter productAdapter = new ProductAdapter(responseModel.getProduct());
                LinearLayoutManager linearLayoutManager = new
                        LinearLayoutManager(getActivity(), RecyclerView.HORIZONTAL, false);
                mRvFruitsVeg.setLayoutManager(linearLayoutManager);
                mRvFruitsVeg.setAdapter(productAdapter);
            }
        });
    }

    @Override
    public void onClick(View v) {
        Intent intent = new Intent(getActivity(), ProductsListActivity.class);
        switch (v.getId()) {
            case R.id.ivFruits:
                intent.putExtra("tag1", "Fruits");
                intent.putExtra("tag2", "Vegetables");
                startActivity(intent);
                break;
            case R.id.cvCleaning:
                intent.putExtra("tag1", "Clean");
                intent.putExtra("tag2", "Detergent");
                startActivity(intent);
                break;
        }
    }
}