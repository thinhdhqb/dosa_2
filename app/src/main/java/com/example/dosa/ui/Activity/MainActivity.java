package com.example.dosa.ui.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;

import com.example.dosa.R;

public class MainActivity extends AppCompatActivity {
    public static final String ACCOUNTPREFERENCES = "AccountPrefs" ;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        setContentView(R.layout.activity_main);

        // Check session
        SharedPreferences preferences = getSharedPreferences(ACCOUNTPREFERENCES, MODE_PRIVATE);
        if (preferences.contains("id"))
            startActivity(new Intent(getApplicationContext(), HomeActivity.class));
        else
            startActivity(new Intent(getApplicationContext(), LoginActivity.class));



//        new Handler().postDelayed(new Runnable() {
//            @Override
//            public void run() {
//                startActivity(new Intent(getApplicationContext(), LoginActivity.class));
//            }
//        },2500);
    }
}