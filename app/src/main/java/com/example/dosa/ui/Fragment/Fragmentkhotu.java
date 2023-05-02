package com.example.dosa.ui.Fragment;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.dosa.ui.Adapter.AdapterHistory;
import com.example.dosa.databinding.FragmentKhotuBinding;

import java.util.ArrayList;
import java.util.List;

public class Fragmentkhotu extends Fragment {
    FragmentKhotuBinding binding;
    List<String> list;
    SendData sendData;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
      binding = FragmentKhotuBinding.inflate(inflater,container,false);
        list = new ArrayList<>();

        list.add("Optimism");
        list.add("Pessimism");
        list.add("Exquisite");
        list.add("Innovations");
        list.add("Treadmill");

        AdapterHistory adapterHistory = new AdapterHistory(list, getContext(), new SendData() {
            @Override
            public void sendData(String a, Bundle data) {
                sendData.sendData("khotu_decription", null);
            }


        });
        binding.recyclerViewYesterday.setAdapter(adapterHistory);
        binding.recyclerViewYesterday.setLayoutManager(new LinearLayoutManager(getContext()));


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
}
