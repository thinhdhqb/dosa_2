package com.example.dosa.local.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Query;

import com.example.dosa.local.entity.Definition;
import com.example.dosa.local.entity.Example;
import com.example.dosa.local.entity.IPA;

import java.util.List;
import java.util.Map;

@Dao
public interface IPADAO {
    @Query("SELECT * FROM IPA JOIN Word ON IPA.wordID = Word.id WHERE Word.word LIKE :word")
    public LiveData<List<IPA>> getIPAByWord(String word);
}
