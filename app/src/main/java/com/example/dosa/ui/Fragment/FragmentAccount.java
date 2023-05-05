package com.example.dosa.ui.Fragment;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.dosa.ui.Activity.LoginActivity;
import com.example.dosa.ui.Activity.LookupHistoryActivity;
import com.example.dosa.ui.Activity.MainActivity;
import com.example.dosa.ui.Activity.NewPasswordActivity;

import com.example.dosa.databinding.FragmentAccountBinding;
import com.example.dosa.ui.Activity.ResetPasswordActivity;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class FragmentAccount extends Fragment {
    FirebaseAuth mAuth;
    SendData sendData;
    MainActivity mainActivity;
    FragmentAccountBinding binding;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentAccountBinding.inflate(inflater, container, false);
        mAuth = FirebaseAuth.getInstance();
        FirebaseUser user = mAuth.getCurrentUser();


        binding.layoutResetPass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (user.getProviderId().equals("password")) {
                    getContext().startActivity(new Intent(getContext(), ResetPasswordActivity.class));
                }
                else
                    Toast.makeText(mainActivity, "Tài khoản này không sử dụng mật khẩu", Toast.LENGTH_SHORT).show();
            }
        });

        binding.layoutLikedArticle.setOnClickListener(view -> {
            sendData.sendData("likedArticles", null);
        });

        binding.layoutLikedWord.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendData.sendData("likedWords", null);

            }
        });

        binding.btnLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mAuth.getInstance().signOut();
                startActivity(new Intent(getContext(), LoginActivity.class));
            }
        });
        // Display user info
        FirebaseUser currentUser = mAuth.getCurrentUser();
        binding.txtUserDisplayName.setText(currentUser.getDisplayName());
        binding.txtUserEmail.setText(currentUser.getEmail());
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
