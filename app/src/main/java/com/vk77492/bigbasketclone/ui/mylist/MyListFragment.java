package com.vk77492.bigbasketclone.ui.mylist;

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

import com.vk77492.bigbasketclone.BottomNavigation;
import com.vk77492.bigbasketclone.R;
import com.vk77492.bigbasketclone.recyclerviews.ProductListAdapter;

public class MyListFragment extends Fragment {

    private MyListViewModel myListViewModel;
    private RecyclerView recyclerView;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        myListViewModel = new ViewModelProvider(this).get(MyListViewModel.class);
        View root = inflater.inflate(R.layout.fragment_mylist, container, false);
        return root;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        recyclerView = view.findViewById(R.id.recyclerView);
        ProductListAdapter adapter = new ProductListAdapter(BottomNavigation.productItemList);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(adapter);
    }
}