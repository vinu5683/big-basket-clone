package com.vk77492.bigbasketclone;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.vk77492.bigbasketclone.sharedpreference.PreferenceHelper;

public class CheckoutActivity extends AppCompatActivity {

    LinearLayout llAddressPage, llAddAddress;
    EditText etAddress1, etCity, etState, etPincode;
    TextView btnChangeAddress, tvAddress, tvContactNumber, tvPayable;
    Button btnAddAddress, btnPay;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_checkout);
        initViewsListeners();
    }

    private void initViewsListeners() {
        llAddressPage = findViewById(R.id.llAddressPage);
        llAddAddress = findViewById(R.id.llAddAddress);

        etAddress1 = findViewById(R.id.etAddress1);
        etCity = findViewById(R.id.etCity);
        etState = findViewById(R.id.etState);
        etPincode = findViewById(R.id.etPincode);

        tvAddress = findViewById(R.id.tvAddress);
        tvContactNumber = findViewById(R.id.tvContactNumber);
        tvPayable = findViewById(R.id.tvPayable);
        PreferenceHelper.getSharedPreferences(this);

        tvPayable.setText("Total Payable : " + getIntent().getStringExtra("cost"));
        btnChangeAddress = findViewById(R.id.btnChangeAddress);
        btnAddAddress = findViewById(R.id.btnAddAddress);
        btnPay = findViewById(R.id.btnPay);

        btnChangeAddress.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                llAddressPage.setVisibility(View.GONE);
                llAddAddress.setVisibility(View.VISIBLE);
            }
        });

        btnAddAddress.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                tvAddress.setText(etAddress1.getText().toString() + ", " + etCity.getText().toString()
                        + ", " + etState.getText().toString() + ", " + etPincode.getText().toString());

                llAddressPage.setVisibility(View.VISIBLE);
                llAddAddress.setVisibility(View.GONE);

            }
        });

        btnPay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(CheckoutActivity.this, OrderPacedActivity.class);
                startActivity(intent);
            }
        });

    }


}