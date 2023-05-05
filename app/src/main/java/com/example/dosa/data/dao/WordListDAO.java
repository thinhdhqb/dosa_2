package com.example.dosa.data.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Query;

import com.example.dosa.data.entity.IPA;
import com.example.dosa.data.entity.WordList;

import java.util.List;

@Dao
public interface WordListDAO {
    @Query("SELECT word FROM WordList_Word WHERE wordListID = :id")
    public LiveData<List<String>> getWordsByWordListID(int id);

    @Query("SELECT * FROM WordList")
    public LiveData<List<WordList>> getAllWordList();
}
