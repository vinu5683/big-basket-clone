package com.vk77492.bigbasketclone;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.reflect.TypeToken;
import com.vk77492.bigbasketclone.models.user_model.UserItem;
import com.vk77492.bigbasketclone.sharedpreference.PreferenceHelper;

import java.lang.reflect.Type;

public class LoginRegisterActivity extends AppCompatActivity {

    private Button btnLogin, btnRegister;
    private TextView etRName, etRMobile, etRPass, etREmail, etLMobile, etLPass, tvRegisterDialogue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_register);
        initViewsListeners();
    }

    private void initViewsListeners() {
        btnLogin = findViewById(R.id.btnLogin);
        btnRegister = findViewById(R.id.btnRegister);
        etLMobile = findViewById(R.id.etLMobile);
        etLPass = findViewById(R.id.etLPass);
        etREmail = findViewById(R.id.etREmail);
        etRPass = findViewById(R.id.etRPass);
        etRMobile = findViewById(R.id.etRMobile);
        etRName = findViewById(R.id.etRName);
        tvRegisterDialogue = findViewById(R.id.tvRegisterDialogue);

        PreferenceHelper.getSharedPreferences(this);
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!etLMobile.getText().toString().equals("") ||
                        !etLPass.getText().toString().equals("")) {
                    if ((PreferenceHelper.readStringFromPreference("email")
                            .equals(etLMobile.getText().toString()) ||
                            PreferenceHelper.readStringFromPreference("mobile")
                                    .equals(etLMobile.getText().toString())) &&
                            PreferenceHelper.readStringFromPreference("password")
                                    .equals(etLPass.getText().toString())) {
                        PreferenceHelper.writeBooleanToPreference(BottomNavigation.PREF_USER_AVAILABLE_KEY, true);
                        Toast.makeText(LoginRegisterActivity.this, "Login Success", Toast.LENGTH_SHORT).show();
                        finish();
                    }
                }
            }
        });

        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!etREmail.getText().toString().equals("") || !etRPass.getText().toString().equals("")
                        || !etRMobile.getText().toString().equals("") || !etRName.getText().toString().equals("")) {
                    PreferenceHelper.writeBooleanToPreference(BottomNavigation.PREF_USER_AVAILABLE_KEY, true);
                    PreferenceHelper.writeStringToPreference("name", etRName.getText().toString());
                    PreferenceHelper.writeStringToPreference("email", etREmail.getText().toString());
                    PreferenceHelper.writeStringToPreference("password", etRPass.getText().toString());
                    PreferenceHelper.writeStringToPreference("mobile", etRMobile.getText().toString());
                    Toast.makeText(LoginRegisterActivity.this, "Registration Success", Toast.LENGTH_SHORT).show();
                    finish();
                }
            }
        });

        tvRegisterDialogue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LinearLayout view = findViewById(R.id.loginInclude);
                view.setVisibility(View.GONE);
                LinearLayout view1 = findViewById(R.id.registerInclude);
                view1.setVisibility(View.VISIBLE);
            }
        });

    }
}