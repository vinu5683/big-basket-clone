package com.vk77492.bigbasketclone.ui.mylist;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.RecyclerView;

import com.vk77492.bigbasketclone.R;

public class MyListFragment extends Fragment {

    private MyListViewModel myListViewModel;
private RecyclerView recyclerViewHorigemtel;
private RecyclerView recyclerViewVrictel;

    public View onCreateView(@NonNull LayoutInflater inflater,
            ViewGroup container, Bundle savedInstanceState) {
        myListViewModel =
                new ViewModelProvider(this).get(MyListViewModel.class);
        View root = inflater.inflate(R.layout.fragment_mylist, container, false);


        return root;
    }
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        recyclerViewHorigemtel = view.findViewById(R.id.recyclerViewHorigentel);
        recyclerViewHorigemtel.setHasFixedSize(true);


        recyclerViewVrictel = view.findViewById(R.id.recyclerViewVritcle);
        recyclerViewVrictel.setHasFixedSize(true);
        builRecyclerDataVritcal();
        builRecyclerDataHorigentile();
        setRecyclerDataVri();
        setRecyclerDataHroi();
    }

    private void setRecyclerDataHroi() {

    }

    private void setRecyclerDataVri() {

    }

    private void builRecyclerDataHorigentile() {

    }

    private void builRecyclerDataVritcal() {

    }
}