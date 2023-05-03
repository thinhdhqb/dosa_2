package com.example.dosa.data.dao;


import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Query;

import com.example.dosa.data.entity.Word;

import java.util.List;

@Dao
public interface WordDAO {
    @Query("SELECT * FROM Word LIMIT 10")
    public Word[] getFirst10WordsAsArray();


    @Query("SELECT DISTINCT word FROM Word WHERE word LIKE :s || '%' ORDER BY word ASC LIMIT 10")
    public String[] getWordsStartWith(String s);

    @Query("SELECT DISTINCT word FROM Word WHERE word LIKE :s || '%' ORDER BY word ASC LIMIT 10")
    public LiveData<List<String>> getWordsAsLiveDataStartWith(String s);

    @Query("SELECT * FROM Word WHERE word LIKE :word")
    public LiveData<List<Word>> getWordsByWord(String word);
}
