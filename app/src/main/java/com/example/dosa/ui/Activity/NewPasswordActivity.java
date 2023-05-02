package com.example.dosa.ui.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.dosa.R;

public class NewPasswordActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main7);

        findViewById(R.id.imgBack).setOnClickListener(view -> {
            onBackPressed();
        });
    }
}