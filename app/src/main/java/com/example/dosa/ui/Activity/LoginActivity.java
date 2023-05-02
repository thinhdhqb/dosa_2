package com.example.dosa.ui.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import com.example.dosa.databinding.ActivityMain2Binding;


public class LoginActivity extends AppCompatActivity {

    ActivityMain2Binding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMain2Binding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());



        binding.txtForgotPassword.setOnClickListener(view->{
            startActivity(new Intent(getApplicationContext(), OTPActivity.class));
        });

        binding.btnLogin.setOnClickListener(view->{
            startActivity(new Intent(getApplicationContext(), HomeActivity.class));
        });

        binding.txtSignIn.setOnClickListener(view -> {
            startActivity(new Intent(getApplicationContext(), SignUpActivity.class));
        });


    }
}