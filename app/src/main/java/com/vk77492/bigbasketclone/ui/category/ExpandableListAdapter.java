package com.vk77492.bigbasketclone.ui.category;

import android.content.Context;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.TextView;

import com.vk77492.bigbasketclone.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ExpandableListAdapter extends BaseExpandableListAdapter {

    private Context context;
    private List<String> ParentItem;
    private HashMap<String, List<String>> ChildItem;

    public ExpandableListAdapter(Context context, List<String> ParentItem,
                                 HashMap<String, List<String>> ChildItem) {
        this.context = context;
        this.ParentItem = ParentItem;
        this.ChildItem = ChildItem;
    }

    @Override
    public Object getChild(int listPosition, int expandedListPosition) {
        return this.ChildItem.get(this.ParentItem.get(listPosition))
                .get(expandedListPosition);
    }

    @Override
    public long getChildId(int listPosition, int expandedListPosition) {
        return expandedListPosition;
    }

    @Override
    public View getChildView(int listPosition, final int expandedListPosition,
                             boolean isLastChild, View convertView, ViewGroup parent) {
        final String expandedListText = (String) getChild(listPosition, expandedListPosition);
        if (convertView == null) {
            LayoutInflater layoutInflater = (LayoutInflater) this.context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = layoutInflater.inflate(R.layout.child_item, null);
        }
        TextView text1 = (TextView) convertView.findViewById(R.id.item1);
        TextView text2 = (TextView) convertView.findViewById(R.id.item2);
        text1.setText("" + expandedListPosition);
        text2.setText("" + expandedListText);
        return convertView;
    }

    @Override
    public int getChildrenCount(int listPosition) {
        return this.ChildItem.get(this.ParentItem.get(listPosition))
                .size();
    }

    @Override
    public Object getGroup(int listPosition) {
        return this.ParentItem.get(listPosition);
    }

    @Override
    public int getGroupCount() {
        return this.ParentItem.size();
    }

    @Override
    public long getGroupId(int listPosition) {
        return listPosition;
    }

    @Override
    public View getGroupView(int listPosition, boolean isExpanded,
                             View convertView, ViewGroup parent) {
        String listTitle = (String) getGroup(listPosition);
        if (convertView == null) {
            LayoutInflater layoutInflater = (LayoutInflater) this.context.
                    getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = layoutInflater.inflate(R.layout.parent_item, null);
        }
        TextView listTitleTextView = (TextView) convertView
                .findViewById(R.id.listTitle);
        listTitleTextView.setTypeface(null, Typeface.BOLD);
        listTitleTextView.setText(listTitle);
        return convertView;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public boolean isChildSelectable(int listPosition, int expandedListPosition) {
        return true;
    }


    public static HashMap<String, List<String>> getData() {
        HashMap<String, List<String>> ParentItem = new HashMap<String, List<String>>();

        List<String> FruitsVeg = new ArrayList<String>();
        FruitsVeg.add("All Fruits & Vegetables");
        FruitsVeg.add("Fresh Vegetables");
        FruitsVeg.add("Herbs & Seasonings");
        FruitsVeg.add("Fresh Fruits");
        FruitsVeg.add("Exotic Fruits & Vegetables");
        FruitsVeg.add("Cut & Sprouts");
        FruitsVeg.add("Flower Bouquets, Bunches");

        List<String> Foodgrains = new ArrayList<String>();
        Foodgrains.add("All Foodgrains, Oil, Masala");
        Foodgrains.add("Atta, Flours & Sooji");
        Foodgrains.add("Dals & Pulses");
        Foodgrains.add("Rice & Rice Products");
        Foodgrains.add("Organic Staples");
        Foodgrains.add("Salt, Sugar & Jagger's");
        Foodgrains.add("Edible Oils & Ghee");
        Foodgrains.add("Masalas & Spices");
        Foodgrains.add("Dry Fruits");

        List<String> Beverages = new ArrayList<String>();
        Beverages.add("All Beverages");
        Beverages.add("Coffee");
        Beverages.add("Energy & Soft Drinks");
        Beverages.add("Water");
        Beverages.add("Tea");
        Beverages.add("Health Drink, Supplement");
        Beverages.add("Fruit Juices & Drinks");

        List<String> Bakery = new ArrayList<String>();
        Bakery.add("All Bakery, Cakes & Dairy");
        Bakery.add("Non Dairy");
        Bakery.add("Bread & Buns");
        Bakery.add("Cookies, Rusk & Khari");
        Bakery.add("Gourmet Breads");
        Bakery.add("Ice Creams & Desserts");
        Bakery.add("Bakery Snacks");
        Bakery.add("Cakes & Pastries");

        List<String> Snacks = new ArrayList<String>();
        Snacks.add("All Snacks & Branded Foods");
        Snacks.add("Chocolates & Candies");
        Snacks.add("Noodles, Pasta, Vermicelli");
        Snacks.add("Breakfast Cereals");
        Snacks.add("Biscuits & Cookies");
        Snacks.add("Snacks & Namkeen");
        Snacks.add("Bakery Snacks");
        Snacks.add("Cakes & Pastries");

        List<String> Beauty = new ArrayList<String>();
        Beauty.add("All Beauty & Hygiene");
        Beauty.add("Makeup");
        Beauty.add("Ferminine Hygiene");
        Beauty.add("Oral Care");
        Beauty.add("Bath & Hand Wash");
        Beauty.add("Health & Medicine");
        Beauty.add("Hair Care");
        Beauty.add("Skin Care");
        Beauty.add("Fragrences & Deo's");



        ParentItem.put("Fruits & Vegetables", FruitsVeg);
        ParentItem.put("Foodgrains, Oil & Masala", Foodgrains);
        ParentItem.put("Bakery, Cakes & Dairy", Bakery);
        ParentItem.put("Beverages", Beverages);
        ParentItem.put("Snacks & Branded Foods", Snacks);
        ParentItem.put("Beverages", Beverages);
        ParentItem.put("Snacks & Branded Foods", Snacks);
        ParentItem.put("Beauty & Hygiene", Beauty);


        return ParentItem;


    }
}