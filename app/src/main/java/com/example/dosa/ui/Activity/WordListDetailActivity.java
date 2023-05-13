package com.example.dosa.ui.Activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.example.dosa.R;
import com.example.dosa.databinding.ActivityWordListDetailBinding;
import com.example.dosa.databinding.FragmentTudienBinding;
import com.example.dosa.ui.Adapter.AdapterWordInWordList;
import com.example.dosa.viewmodel.DictionaryViewModel;
import com.example.dosa.viewmodel.WordListViewModel;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.List;

public class WordListDetailActivity extends AppCompatActivity {
    ActivityWordListDetailBinding binding;
    AdapterWordInWordList adapter;
    WordListViewModel wordListViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityWordListDetailBinding.inflate(getLayoutInflater());

        setContentView(binding.getRoot());

        Intent intent = getIntent();
        String mode = intent.getStringExtra("mode");

        wordListViewModel = new ViewModelProvider(this).get(WordListViewModel.class);




        adapter = new AdapterWordInWordList();
        binding.rcvWord.setAdapter(adapter);
        binding.rcvWord.setLayoutManager(new LinearLayoutManager(this));
        if (mode.equals("favWord")) {
            fetchLikedWords();
        }
        else {
            int wordListID = intent.getIntExtra("wordListID", 1);
            fetchData(wordListID);
        }

        binding.schvWordList.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                adapter.getFilter().filter(newText);
                return false;
            }
        });
        binding.btnPractice.setClickable(false);

        binding.btnPractice.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(WordListDetailActivity.this, FlashcardActivity.class);
                intent.putStringArrayListExtra("wordList", adapter.list);
                startActivity(intent);
            }
        });

        binding.txtBackWordList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                    WordListDetailActivity.this.onBackPressed();
            }
        });

        binding.txtBackTextWordList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                WordListDetailActivity.this.onBackPressed();

            }
        });

    }

    private void fetchData(int wordListID) {
        wordListViewModel.getWordsByWordListID(wordListID).observe(WordListDetailActivity.this, new Observer<List<String>>() {
            @Override
            public void onChanged(List<String> strings) {
                adapter.list.addAll(strings);
                adapter.filteredList.addAll(strings);
                adapter.notifyDataSetChanged();
                binding.btnPractice.setEnabled(true);
                binding.btnPractice.setClickable(true);
            }
        });
    }

    private void fetchLikedWords() {
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        String userID = FirebaseAuth.getInstance().getUid();
        adapter.list.clear();
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
                                adapter.filteredList.add(word);
                                adapter.notifyDataSetChanged();
                            }
                            binding.btnPractice.setEnabled(true);
                            binding.btnPractice.setClickable(true);
                        } else {
                        }
                    }
                });
    }
}