package com.example.dosa.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.viewmodel.CreationExtras;

import com.example.dosa.data.entity.WordList;
import com.example.dosa.repository.Repository;

import java.util.List;

public class WordListViewModel extends AndroidViewModel {
    private Repository repository;

    public WordListViewModel(@NonNull Application application) {
        super(application);
        repository = new Repository(application);
    }

    public LiveData<List<WordList>> getAllWordList() {
        return repository.getAllWordList();
    }

    public LiveData<List<String>> getWordsByWordListID(int id) {
        return repository.getWordsByWordListID(id);
    }
}
