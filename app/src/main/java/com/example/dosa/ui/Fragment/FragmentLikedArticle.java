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

import com.example.dosa.databinding.FragmentLikedWordsBinding;
import com.example.dosa.ui.Adapter.AdapterLikedWord;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;

public class FragmentLikedArticle extends Fragment {
    FragmentLikedWordsBinding binding;
    ArrayList<String> words;
    AdapterLikedWord adapter;
    String userID;
    SendData sendData;

    FirebaseFirestore db;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentLikedWordsBinding.inflate(inflater, container, false);

        userID = FirebaseAuth.getInstance().getCurrentUser().getUid();
        db = FirebaseFirestore.getInstance();

        words = new ArrayList<>();

        adapter = new AdapterLikedWord(getContext(), words, sendData);
        binding.rcvNews.setAdapter(adapter);
        binding.rcvNews.setLayoutManager(new LinearLayoutManager(getContext()));
        binding.rcvNews.setHasFixedSize(true);
        fetchLikedWords();
        return binding.getRoot();
    }


    public void fetchLikedWords() {
        db.collection("FavouriteWord")
                .whereEqualTo("userID", userID)
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                String word = document.getString("word");
                                adapter.list.add(word);
                                adapter.notifyDataSetChanged();
                            }
                        } else {
                        }
                    }
                });
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
