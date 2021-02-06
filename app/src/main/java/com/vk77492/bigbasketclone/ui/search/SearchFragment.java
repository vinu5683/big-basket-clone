package com.vk77492.bigbasketclone.ui.search;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.vk77492.bigbasketclone.BottomNavigation;
import com.vk77492.bigbasketclone.ProductsListActivity;
import com.vk77492.bigbasketclone.R;

public class SearchFragment extends Fragment {

    private SearchViewModel searchViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        searchViewModel =
                new ViewModelProvider(this).get(SearchViewModel.class);
        View root = inflater.inflate(R.layout.fragment_search, container, false);
        EditText etSearch = root.findViewById(R.id.etSearch);
        etSearch.requestFocus();

        return root;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ImageButton ibSearch = view.findViewById(R.id.ibSearch);
        EditText etSearch = view.findViewById(R.id.etSearch);
        ibSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!etSearch.getText().toString().isEmpty()) {
                    Intent intent = new Intent(getActivity(), ProductsListActivity.class);
                    intent.putExtra("tag1", etSearch.getText().toString());
                    intent.putExtra("tag2", etSearch.getText().toString());
                    startActivity(intent);
                }
            }
        });

    }
}