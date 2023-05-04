package com.example.dosa.ui.Fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.dosa.data.entity.NewsArticle;
import com.example.dosa.ui.Adapter.AdapterNews;
import com.example.dosa.databinding.FragmentTudienBinding;


import java.util.ArrayList;
import java.util.List;

public class FragmentNews extends Fragment {
    FragmentTudienBinding binding;
    List<NewsArticle> list;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentTudienBinding.inflate(inflater, container, false);
        list = new ArrayList<>();

        AdapterNews adapterNews = new AdapterNews(getContext(),list);
        binding.rcvNews.setAdapter(adapterNews);
        binding.rcvNews.setLayoutManager(new LinearLayoutManager(getContext()));
        binding.rcvNews.setHasFixedSize(true);


        return binding.getRoot();
    }
}
