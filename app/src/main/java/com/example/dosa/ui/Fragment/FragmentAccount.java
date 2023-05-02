package com.example.dosa.ui.Fragment;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.dosa.ui.Activity.LoginActivity;
import com.example.dosa.ui.Activity.LookupHistoryActivity;
import com.example.dosa.ui.Activity.MainActivity;
import com.example.dosa.ui.Activity.NewPasswordActivity;

import com.example.dosa.databinding.FragmentAccountBinding;
public class FragmentAccount extends Fragment {

    SendData sendData;
    MainActivity mainActivity;
    FragmentAccountBinding binding;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentAccountBinding.inflate(inflater, container, false);

        binding.imgEdit.setOnClickListener(view -> {
            sendData.sendData("account_detail", null);
        });

        binding.contralayout1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getContext().startActivity(new Intent(getContext(), NewPasswordActivity.class));
            }
        });

        binding.contranHistory.setOnClickListener(view -> {
            getContext().startActivity(new Intent(getContext(), LookupHistoryActivity.class));
        });

        binding.contranSetting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendData.sendData("setting", null);
            }
        });

        binding.btnLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SharedPreferences preferences = getContext().getSharedPreferences(MainActivity.ACCOUNTPREFERENCES, Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = preferences.edit();
                editor.clear();
                editor.commit();
                startActivity(new Intent(getContext(), LoginActivity.class));
            }
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
