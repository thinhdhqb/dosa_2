package com.example.dosa.ui.Fragment;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.dosa.databinding.FragmentKhotuDecriptionBinding;

public class FragmentkhoTuDecription extends Fragment {
    FragmentKhotuDecriptionBinding binding;
SendData sendData;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentKhotuDecriptionBinding.inflate(inflater, container, false);

        binding.btnContinues.setOnClickListener(view->{
            sendData.sendData("khotu_continues", null);
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
