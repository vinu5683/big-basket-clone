package com.vk77492.bigbasketclone.ui.basket;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.vk77492.bigbasketclone.R;

import java.util.ArrayList;

public class BasketFragment extends Fragment {

    private BasketViewModel basketViewModel;
    private RecyclerView recyclerView;
    private ArrayList<BasketViewModel>basketModelClassList;
    public BasketFragment(){

    }
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
        recyclerView=view.findViewById(R.id.recyclerView);
        builRecyclerData();

        setRecyclerData();
    }

    private void setRecyclerData() {
        basketAdapter BasketAdapter = new basketAdapter(basketModelClassList);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(BasketAdapter);
    }

    private void builRecyclerData() {
        basketModelClassList = new ArrayList<>();
        basketModelClassList.add(new BasketViewModel());
        basketModelClassList.add(new BasketViewModel());
        basketModelClassList.add(new BasketViewModel());
        basketModelClassList.add(new BasketViewModel());
        basketModelClassList.add(new BasketViewModel());

    }
}