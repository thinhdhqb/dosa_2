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
import com.example.dosa.databinding.FragmentSettingBinding;

import java.util.ArrayList;
import java.util.List;

public class FragmentSetting extends Fragment {
    FragmentSettingBinding binding;
    List<String> list;
    SendData sendData;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentSettingBinding.inflate(inflater, container, false);
        list = new ArrayList<>();
        list.add("Tiếng Anh");
        list.add("Tiếng Việt \t\t Mặc định");
        list.add("Tiếng Ý");

        AdapterHistory adapterHistory = new AdapterHistory(list, getContext(), null);
        binding.recyclerSetting.setAdapter(adapterHistory);
        binding.recyclerSetting.setLayoutManager(new LinearLayoutManager(getContext()));

        binding.btnBack.setOnClickListener(view->{
            sendData.sendData("account", null);
        });

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
