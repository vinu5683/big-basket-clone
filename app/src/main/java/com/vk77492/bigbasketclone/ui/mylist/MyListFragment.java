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

        recyclerViewHorigemtel = root.findViewById(R.id.recyclerViewHorigentel);
        recyclerViewHorigemtel.setHasFixedSize(true);


        recyclerViewVrictel = root.findViewById(R.id.recyclerViewVritcle);
        recyclerViewVrictel.setHasFixedSize(true);

        return root;
    }
}