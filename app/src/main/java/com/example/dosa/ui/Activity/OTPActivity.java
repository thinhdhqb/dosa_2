package com.example.dosa.ui.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import com.example.dosa.R;

public class OTPActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main4);

        findViewById(R.id.btnComfirm).setOnClickListener(view->{
            startActivity(new Intent(getApplicationContext(), ForgotPasswordActivity.class));
        });
    }
}