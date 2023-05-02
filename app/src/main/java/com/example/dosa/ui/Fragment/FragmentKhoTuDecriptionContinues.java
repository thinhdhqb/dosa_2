package com.example.dosa.ui.Fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.dosa.databinding.FragmentKhotuDecriptionContinuesBinding;

public class FragmentKhoTuDecriptionContinues extends Fragment {
    FragmentKhotuDecriptionContinuesBinding binding;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentKhotuDecriptionContinuesBinding.inflate(inflater,container,false);
        return  binding.getRoot();
    }
}
