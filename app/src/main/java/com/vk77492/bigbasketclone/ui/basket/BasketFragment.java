package com.vk77492.bigbasketclone.ui.basket;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.vk77492.bigbasketclone.R;

import java.util.ArrayList;

public class BasketFragment extends Fragment {

    private BasketViewModel basketViewModel;
    private RecyclerView recyclerView;
    private ArrayList<BasketViewModel>basketModelClassList;
    public View onCreateView(@NonNull LayoutInflater inflater,
            ViewGroup container, Bundle savedInstanceState) {
        basketViewModel =
                new ViewModelProvider(this).get(BasketViewModel.class);
        View root = inflater.inflate(R.layout.fragment_basket, container, false);
        recyclerView=root.findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        basketModelClassList = new ArrayList<>();
        return root;
    }

}