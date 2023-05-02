package com.example.dosa.ui.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;

import android.os.Bundle;
import android.util.Log;

import com.example.dosa.R;
import com.example.dosa.viewmodel.DictionaryViewModel;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        setContentView(R.layout.activity_main);

        DictionaryViewModel dictionaryViewModel = new ViewModelProvider(this).get(DictionaryViewModel.class);

        // preload dictionary data
        dictionaryViewModel.getWordsAsLiveDataStartWith("").observe(this, new Observer<List<String>>() {
            @Override
            public void onChanged(List<String> strings) {
                checkIfSignedIn();
            }
        });




    }

    private void checkIfSignedIn() {
        mAuth = FirebaseAuth.getInstance();
        FirebaseUser currentUser = mAuth.getCurrentUser();
        if(currentUser != null) {
//            Log.d("getDisplayName", "onCreate: " +currentUser.getPhoneNumber());

            startActivity(new Intent(getApplicationContext(), HomeActivity.class));
        }
        else {
            startActivity(new Intent(getApplicationContext(), LoginActivity.class));
        }
    }
}