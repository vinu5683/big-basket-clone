package com.vk77492.bigbasketclone.ui.basket;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.vk77492.bigbasketclone.BottomNavigation;
import com.vk77492.bigbasketclone.CheckoutActivity;
import com.vk77492.bigbasketclone.R;
import com.vk77492.bigbasketclone.RemoveItemListener;
import com.vk77492.bigbasketclone.UpdatePrice;
import com.vk77492.bigbasketclone.models.product_model.ProductItem;
import com.vk77492.bigbasketclone.recyclerviews.CartListAdapter;
import com.vk77492.bigbasketclone.recyclerviews.CartViewHolder;
import com.vk77492.bigbasketclone.recyclerviews.ProductAdapter;
import com.vk77492.bigbasketclone.sharedpreference.PreferenceHelper;

import java.util.ArrayList;
import java.util.List;

public class BasketFragment extends Fragment implements RemoveItemListener, UpdatePrice {

    private BasketViewModel basketViewModel;
    private RecyclerView cartRecyclerView, rvPopularItems;
    private TextView tvTotPrice, tvSavedPrice;
    private Button btnCheckout;
    List<ProductItem> productItems;
    List<ProductItem> allProductItems;
    LinearLayout llEmptyBasket;
    public static double totMrp = 0, totPrice = 0;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        basketViewModel =
                new ViewModelProvider(this).get(BasketViewModel.class);
        View root = inflater.inflate(R.layout.fragment_basket, container, false);
        return root;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        totMrp = 0;
        totPrice = 0;
        initViewsListeners(view);
        getProducts(view);
        setRecyclersViewData();
        this.onFinish(0, 0);
    }

    private void setRecyclersViewData() {
        //all product list recyclerView
        ProductAdapter productAdapter = new ProductAdapter(allProductItems);
        LinearLayoutManager linearLayoutManager = new
                LinearLayoutManager(getActivity(), RecyclerView.HORIZONTAL, false);
        rvPopularItems.setLayoutManager(linearLayoutManager);
        rvPopularItems.setAdapter(productAdapter);

        //Cart Items recyclerview
        CartListAdapter cartListAdapter = new CartListAdapter(productItems, this, this);
        LinearLayoutManager ll = new
                LinearLayoutManager(getActivity());
        Log.d("TAG", "setRecyclersViewData: " + productItems.size());
        cartRecyclerView.setLayoutManager(ll);
        cartRecyclerView.setAdapter(cartListAdapter);

        if (productItems.size() <= 0) {
            llEmptyBasket.setVisibility(View.VISIBLE);
        } else
            llEmptyBasket.setVisibility(View.GONE);
    }

    private void initViewsListeners(View view) {
        cartRecyclerView = view.findViewById(R.id.cartRecyclerView);
        rvPopularItems = view.findViewById(R.id.rvPopularItems);
        tvTotPrice = view.findViewById(R.id.tvTotPrice);
        tvSavedPrice = view.findViewById(R.id.tvSavedPrice);
        btnCheckout = view.findViewById(R.id.btnCheckout);
        llEmptyBasket = view.findViewById(R.id.llEmptyBasket);
        PreferenceHelper.getSharedPreferences(view.getContext());
        allProductItems = BottomNavigation.productItemList;

        btnCheckout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (totPrice != 0) {
                    Intent intent = new Intent(getActivity(), CheckoutActivity.class);
                    intent.putExtra("cost", totPrice + "");
                    startActivity(intent);
                } else
                    Toast.makeText(getContext(), "Sorry Cart Is Empty", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void getProducts(View v) {
        productItems = new ArrayList<>();
        if (PreferenceHelper.readBooleanFromPreference(BottomNavigation.PREF_USER_AVAILABLE_KEY)) {
            String string = PreferenceHelper.readStringFromPreference(BottomNavigation.PREF_USER_CART_KEY);
            if (!string.equals("")) {
                String[] cart = string.split(" ");
                for (int i = 0; i < cart.length; i++) {
                    for (int j = 0; j < allProductItems.size(); j++) {
                        if (Integer.parseInt(cart[i]) == allProductItems.get(j).getId()) {
                            productItems.add(allProductItems.get(j));
                        }
                    }
                }
            }
        } else
            Toast.makeText(v.getContext(), "Login to see the cart", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void removeItemFromCart(int id) {
        String string = PreferenceHelper.readStringFromPreference(BottomNavigation.PREF_USER_CART_KEY);
        if (!string.equals("")) {
            StringBuffer stringBuffer = new StringBuffer();
            String[] cart = string.split(" ");
            for (int i = 0; i < cart.length; i++) {
                if (Integer.parseInt(cart[i]) != id) {
                    stringBuffer.append(cart[i]).append(" ");
                }
            }
            PreferenceHelper.writeStringToPreference(BottomNavigation.PREF_USER_CART_KEY, stringBuffer.toString());
            getProducts(getView());
            setRecyclersViewData();
        }
    }


    @Override
    public void onFinish(int id, int qty) {
        totMrp = 0;
        totPrice = 0;

        for (int i = 0; i < productItems.size(); i++) {
            if (productItems.get(i).getId() == id) {
                totMrp += productItems.get(i).getPrice() * qty;
                double price = productItems.get(i).getPrice() * qty;
                price = Math.floor(price - (price * productItems.get(i).getOffer()) / 100);
                totPrice += price;
            } else {
                totMrp += productItems.get(i).getPrice();
                double price = productItems.get(i).getPrice();
                price = Math.floor(price - (price * productItems.get(i).getOffer()) / 100);
                totPrice += price;
            }
        }

        tvTotPrice.setText("Total \u20B9" + totPrice);
        double savedPrice = totMrp - totPrice;
        tvSavedPrice.setText("Saved Rs " + savedPrice);

    }
}