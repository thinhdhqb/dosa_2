package com.example.dosa.ui.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.Signature;
import android.os.Bundle;
import android.os.Handler;
import android.util.Base64;
import android.util.Log;

import com.example.dosa.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class MainActivity extends AppCompatActivity {
    public static final String ACCOUNTPREFERENCES = "AccountPrefs" ;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        setContentView(R.layout.activity_main);


        mAuth = FirebaseAuth.getInstance();

        FirebaseUser currentUser = mAuth.getCurrentUser();
        if(currentUser != null) {
            startActivity(new Intent(getApplicationContext(), HomeActivity.class));
        }
        else {
            startActivity(new Intent(getApplicationContext(), LoginActivity.class));
        }

        // Check session
//        SharedPreferences preferences = getSharedPreferences(ACCOUNTPREFERENCES, MODE_PRIVATE);
//        if (preferences.contains("id"))
//            startActivity(new Intent(getApplicationContext(), HomeActivity.class));
//        else
//            startActivity(new Intent(getApplicationContext(), LoginActivity.class));



//        new Handler().postDelayed(new Runnable() {
//            @Override
//            public void run() {
//                startActivity(new Intent(getApplicationContext(), LoginActivity.class));
//            }
//        },2500);
    }
}