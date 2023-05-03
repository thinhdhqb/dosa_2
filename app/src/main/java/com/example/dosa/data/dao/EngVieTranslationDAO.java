package com.example.dosa.data.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Query;

import com.example.dosa.data.entity.EngVieTranslation;

@Dao
public interface EngVieTranslationDAO {
    @Query("SELECT * FROM av WHERE word LIKE :word")
    public LiveData<EngVieTranslation> getTranslationByWord(String word);
}
