package com.example.dosa.data.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Query;

import com.example.dosa.data.entity.IPA;

import java.util.List;

@Dao
public interface IPADAO {
    @Query("SELECT * FROM IPA JOIN Word ON IPA.wordID = Word.id WHERE Word.word LIKE :word")
    public LiveData<List<IPA>> getIPAByWord(String word);
}
