package com.example.dosa.ui.Activity;

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

import java.util.List;

public class WordListDetailActivity extends AppCompatActivity {
    ActivityWordListDetailBinding binding;
    AdapterWordInWordList adapter;
    WordListViewModel wordListViewModel;
    int wordListID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityWordListDetailBinding.inflate(getLayoutInflater());

        setContentView(binding.getRoot());

        Intent intent = getIntent();
        wordListID = intent.getIntExtra("wordListID", 1);
        wordListViewModel = new ViewModelProvider(this).get(WordListViewModel.class);




        adapter = new AdapterWordInWordList();
        binding.rcvWord.setAdapter(adapter);
        binding.rcvWord.setLayoutManager(new LinearLayoutManager(this));
        fetchData();

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

    }

    private void fetchData() {
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
}