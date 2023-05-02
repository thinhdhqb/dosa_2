package com.example.dosa.ui.Fragment;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.dosa.databinding.FragmentAccountDetailBinding;

public class Fragment_Account_Detail extends Fragment {

    FragmentAccountDetailBinding binding;

    SendData sendData;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentAccountDetailBinding.inflate(inflater, container, false);
        binding.imgBack.setOnClickListener(view->
        {
            sendData.sendData("account", null);
        });
        return binding.getRoot();
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        if (context instanceof SendData){
            sendData = (SendData) context;
        }else
        {
            throw  new  RuntimeException(context.toString());
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        sendData = null;
    }
}
