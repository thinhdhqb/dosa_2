package com.example.dosa.ui.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.dosa.databinding.ActivityMain10Binding;

public class MultipleChoiceActivity extends AppCompatActivity {
    ActivityMain10Binding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMain10Binding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

    }
}