package com.vk77492.bigbasketclone;

import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.cardview.widget.CardView;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.NavigationUI;

import java.util.ArrayList;

public class BottomNavigation extends AppCompatActivity implements View.OnClickListener {

    private DrawerLayout drawerLayout;
    ActionBarDrawerToggle toggle;
    private CardView cvMyAccount, cvHome,cvCategory;
    private LinearLayout myAccountChild,myCategoryChild;
    BottomNavigationView navView;
    NavController navController;
    TextView mTvMyAccount,mTvMyCategory;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bottom_navigations);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        drawerLayout = findViewById(R.id.drawer_layout);
        toggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar,
                R.string.nav_open, R.string.nav_close);
        drawerLayout.addDrawerListener(toggle);
        toggle.getDrawerArrowDrawable().setColor(getColor(R.color.white));
        toggle.syncState();

        navView = findViewById(R.id.nav_view);
        navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        NavigationUI.setupWithNavController(navView, navController);
    }

    @Override
    public void onBackPressed() {
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START);
        } else
            super.onBackPressed();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        cvMyAccount = findViewById(R.id.myAccount);
        cvHome = findViewById(R.id.menu_item_home);
        cvCategory = findViewById(R.id.menu_item_category);
        myCategoryChild = findViewById(R.id.categoryChild);
        mTvMyAccount = findViewById(R.id.tvMyAccount);
        mTvMyCategory = findViewById(R.id.tvMenuCategory);
        myAccountChild = findViewById(R.id.myAccountChild);
        cvMyAccount.setOnClickListener(this);
        cvHome.setOnClickListener(this);
        cvCategory.setOnClickListener(this);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.myAccount:
                if (myAccountChild.getVisibility() == View.VISIBLE) {
                    myAccountChild.setVisibility(View.GONE);
                    mTvMyAccount.setCompoundDrawablesWithIntrinsicBounds(0, 0,
                            R.drawable.ic_baseline_chevron_right_24, 0);
                } else {
                    myAccountChild.setVisibility(View.VISIBLE);
                    mTvMyAccount.setCompoundDrawablesWithIntrinsicBounds(0, 0,
                            R.drawable.ic_baseline_keyboard_arrow_down_24, 0);
                }
                break;
            case R.id.menu_item_home: {
                navView.setSelectedItemId(R.id.navigation_home);
                NavigationUI.setupWithNavController(navView, navController);
                drawerLayout.closeDrawer(GravityCompat.START);
            }
            break;
            case R.id.menu_item_category:
                if (myCategoryChild.getVisibility() == View.VISIBLE) {
                    myCategoryChild.setVisibility(View.GONE);
                    mTvMyCategory.setCompoundDrawablesWithIntrinsicBounds(0, 0,
                            R.drawable.ic_baseline_chevron_right_24, 0);
                } else {
                    myCategoryChild.setVisibility(View.VISIBLE);
                    mTvMyCategory.setCompoundDrawablesWithIntrinsicBounds(0, 0,
                            R.drawable.ic_baseline_keyboard_arrow_down_24, 0);
                }
        }
    }
}