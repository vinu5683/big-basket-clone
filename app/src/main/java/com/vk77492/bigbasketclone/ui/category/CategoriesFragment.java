package com.vk77492.bigbasketclone.ui.category;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ExpandableListView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.vk77492.bigbasketclone.ProductsListActivity;
import com.vk77492.bigbasketclone.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class CategoriesFragment extends Fragment {

    private CategoriesViewModel categoriesViewModel;
    ExpandableListView expandableListView;
    ExpandableListAdapter expandableListAdapter;
    List<String> expandableListTitle;
    HashMap<String, List<String>> expandableListDetail;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        categoriesViewModel =
                new ViewModelProvider(this).get(CategoriesViewModel.class);
        View root = inflater.inflate(R.layout.fragment_category, container, false);
        return root;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {

        expandableListView = (ExpandableListView) view.findViewById(R.id.expandableListView);
        expandableListDetail = ExpandableListAdapter.getData();
        expandableListTitle = new ArrayList<String>(expandableListDetail.keySet());
        expandableListAdapter = new ExpandableListAdapter(getActivity(), expandableListTitle, expandableListDetail);
        expandableListView.setAdapter(expandableListAdapter);
        expandableListView.setOnGroupExpandListener(new ExpandableListView.OnGroupExpandListener() {

            @Override
            public void onGroupExpand(int groupPosition) {

            }
        });

        expandableListView.setOnGroupCollapseListener(new ExpandableListView.OnGroupCollapseListener() {

            @Override
            public void onGroupCollapse(int groupPosition) {

            }
        });

        expandableListView.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {
            @Override
            public boolean onChildClick(ExpandableListView parent, View v,
                                        int groupPosition, int childPosition, long id) {
                Log.d("TAG", "onChildClick: " + childPosition + groupPosition);
                Intent intent = new Intent(getActivity(), ProductsListActivity.class);
                if (groupPosition == 0) {
                    switch (childPosition) {
                        case 0:
                            intent.putExtra("tag1", "Fruits");
                            intent.putExtra("tag2", "Vegetables");
                            break;
                        case 1:
                            intent.putExtra("tag1", "Vegetables");
                            break;
                        case 2:
                            intent.putExtra("tag1", "Herbs");
                            break;
                        case 3:
                            intent.putExtra("tag1", "Fruits");
                            break;
                        case 4:
                            intent.putExtra("tag1", "Exotics");
                            break;
                        case 5:
                            intent.putExtra("tag1", "Cut Sprouts");
                            break;
                        case 6:
                            intent.putExtra("tag1", "Flower Bouquets");
                            break;
                    }
                    startActivity(intent);
                } else if (groupPosition == 4) {
                    switch (childPosition) {
                        case 0:
                            intent.putExtra("tag1", "Bakery");
                            intent.putExtra("tag2", "Cakes");
                            break;
                        case 2:
                            intent.putExtra("tag1", "bread");
                            break;
                        case 3:
                            intent.putExtra("tag1", "cookies");
                            break;
                        case 4:
                            intent.putExtra("tag1", "Gourmet");
                            break;
                        case 6:
                            intent.putExtra("tag1", "Bakery");
                            break;
                        case 5:
                            intent.putExtra("tag1", "Ice");
                            intent.putExtra("tag2", "Cream");
                            break;
                        case 7:
                            intent.putExtra("tag1", "Cake");
                    }
                    startActivity(intent);
                }



                return false;
            }
        });
    }
}

