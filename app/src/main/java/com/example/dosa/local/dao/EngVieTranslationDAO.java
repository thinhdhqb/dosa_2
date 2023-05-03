package com.example.dosa.local.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Query;

import com.example.dosa.local.entity.Definition;
import com.example.dosa.local.entity.EngVieTranslation;
import com.example.dosa.local.entity.Example;

import java.util.List;
import java.util.Map;

@Dao
public interface EngVieTranslationDAO {
    @Query("SELECT * FROM av WHERE word LIKE :word")
    public LiveData<EngVieTranslation> getTranslationByWord(String word);
}
