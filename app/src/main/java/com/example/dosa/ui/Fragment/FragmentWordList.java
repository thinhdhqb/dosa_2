package com.example.dosa.ui.Fragment;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.dosa.data.entity.WordList;
import com.example.dosa.databinding.FragmentKhotuBinding;
import com.example.dosa.ui.Adapter.AdapterWordList;
import com.example.dosa.viewmodel.WordListViewModel;

import java.util.ArrayList;
import java.util.List;

public class FragmentWordList extends Fragment {
    FragmentKhotuBinding binding;
    List<String> list;
    SendData sendData;
    WordListViewModel wordListViewModel;
    AdapterWordList adapterWordList;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentKhotuBinding.inflate(inflater,container,false);
        wordListViewModel = new ViewModelProvider(this).get(WordListViewModel.class);
        list = new ArrayList<>();
        adapterWordList = new AdapterWordList(getActivity());
        fetchData();
        binding.rcvWordList.setAdapter(adapterWordList);
        binding.rcvWordList.setLayoutManager(new LinearLayoutManager(getContext()));


        return binding.getRoot();
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        if (context instanceof SendData) {
            sendData = (SendData) context;
        } else {
            throw new RuntimeException(context.toString());
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        sendData = null;
    }

    public void fetchData() {
        wordListViewModel.getAllWordList().observe(getActivity(), new Observer<List<WordList>>() {
            @Override
            public void onChanged(List<WordList> wordLists) {
                for (WordList wordList: wordLists) {
                    adapterWordList.list.add(wordList);
                }
                adapterWordList.notifyDataSetChanged();
            }
        });
    }
}
