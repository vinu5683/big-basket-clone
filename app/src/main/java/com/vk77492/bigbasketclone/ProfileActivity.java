package com.vk77492.bigbasketclone;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.vk77492.bigbasketclone.sharedpreference.PreferenceHelper;

public class ProfileActivity extends AppCompatActivity {

    private TextView tvUserName, tvUserEmail, tvUserPhone;
    private CardView cvLogOut;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        initViewsListeners();
    }

    private void initViewsListeners() {
        tvUserName = findViewById(R.id.tvUserName);
        tvUserEmail = findViewById(R.id.tvUserEmail);
        tvUserPhone = findViewById(R.id.tvUserPhone);
        cvLogOut = findViewById(R.id.cvLogOut);
        PreferenceHelper.getSharedPreferences(this);
        tvUserName.setText(PreferenceHelper.readStringFromPreference("name"));
        tvUserEmail.setText(PreferenceHelper.readStringFromPreference("email"));
        tvUserPhone.setText(PreferenceHelper.readStringFromPreference("mobile"));
        cvLogOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), BottomNavigation.class);
                PreferenceHelper.writeBooleanToPreference(BottomNavigation.PREF_USER_AVAILABLE_KEY, false);
                startActivity(intent);
                finish();
            }
        });
    }
}