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
import com.example.dosa.R;
import com.example.dosa.databinding.FragmentTudienBinding;


import java.util.ArrayList;
import java.util.List;

public class Fragment_TuDien extends Fragment {
    FragmentTudienBinding binding;
    List<NewsArticle> list;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentTudienBinding.inflate(inflater, container, false);
        list = new ArrayList<>();
//        list.add(new NewsArticle("1","The world’s happiest countries for 2023","Văn hóa","CNC:","abc", R.drawable.img_9));
//        list.add(new NewsArticle("2","European Central Bank hikes rates despite ...","Kinh Tế","CNC:","abc",R.drawable.img_29));  list.add(new NewsArticle("1","The world’s happiest countries for 2023","Văn hóa","CNC:","abc",R.drawable.img_9));
//        list.add(new NewsArticle("3","The world’s happiest countries for 2023","Văn hóa","CNC:","abc",R.drawable.img_9));
//        list.add(new NewsArticle("4","European Central Bank hikes rates despite ...","Kinh Tế","CNC:","abc",R.drawable.img_29));  list.add(new NewsArticle("1","The world’s happiest countries for 2023","Văn hóa","CNC:","abc",R.drawable.img_9));
//        list.add(new NewsArticle("5","The world’s happiest countries for 2023","Văn hóa","CNC:","abc",R.drawable.img_9));
//        list.add(new NewsArticle("6","European Central Bank hikes rates despite ...","Kinh Tế","CNC:","abc",R.drawable.img_29));  list.add(new NewsArticle("1","The world’s happiest countries for 2023","Văn hóa","CNC:","abc",R.drawable.img_9));
//        list.add(new NewsArticle("7","The world’s happiest countries for 2023","Văn hóa","CNC:","abc",R.drawable.img_9));
//        list.add(new NewsArticle("8","European Central Bank hikes rates despite ...","Kinh Tế","CNC:","abc",R.drawable.img_29));  list.add(new NewsArticle("1","The world’s happiest countries for 2023","Văn hóa","CNC:","abc",R.drawable.img_9));
//        list.add(new NewsArticle("9","The world’s happiest countries for 2023","Văn hóa","CNC:","abc",R.drawable.img_9));
//        list.add(new NewsArticle("10","European Central Bank hikes rates despite ...","Kinh Tế","CNC:","abc",R.drawable.img_29));  list.add(new NewsArticle("1","The world’s happiest countries for 2023","Văn hóa","CNC:","abc",R.drawable.img_9));

        AdapterNews adapterNews = new AdapterNews(getContext(),list);
        binding.recyclerView.setAdapter(adapterNews);
        binding.recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        binding.recyclerView.setHasFixedSize(true);


        return binding.getRoot();
    }
}
