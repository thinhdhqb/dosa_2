package com.example.dosa.ui.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import com.example.dosa.R;

public class SignUpActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);

        findViewById(R.id.txtSignIn).setOnClickListener(view -> {
            startActivity(new Intent(getApplicationContext(), LoginActivity.class));
        });
    }
}