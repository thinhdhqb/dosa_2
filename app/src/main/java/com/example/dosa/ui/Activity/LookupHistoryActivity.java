package com.example.dosa.ui.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.os.Bundle;

import com.example.dosa.ui.Adapter.AdapterHistory;
import com.example.dosa.ui.Fragment.SendData;
import com.example.dosa.databinding.ActivityMain8Binding;

import java.util.ArrayList;
import java.util.List;

public class LookupHistoryActivity extends AppCompatActivity {
    ActivityMain8Binding binding;
    List<String> list;
    List<String> stringList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMain8Binding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        list = new ArrayList<>();
        stringList = new ArrayList<>();
        AdapterHistory adapterHistory = new AdapterHistory(list, getApplicationContext(), null);
        binding.rcvWordList.setAdapter(adapterHistory);
        binding.rcvWordList.setLayoutManager(new LinearLayoutManager(this));

        stringList.add("Exquisite");
        stringList.add("Innovations");
        stringList.add("Treadmill");

        AdapterHistory adapterHistory1 = new AdapterHistory(stringList, getApplicationContext(), null);
        binding.recyclerView7Yesterday.setAdapter(adapterHistory1);
        binding.recyclerView7Yesterday.setLayoutManager(new LinearLayoutManager(this));

    }
}