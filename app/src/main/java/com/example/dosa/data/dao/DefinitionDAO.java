package com.example.dosa.data.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Query;

import com.example.dosa.data.entity.Definition;
import com.example.dosa.data.entity.Example;

import java.util.List;
import java.util.Map;

@Dao
public interface DefinitionDAO {
    @Query("SELECT Definition.*, Example.* FROM Definition INNER JOIN Word ON Word.id = Definition.wordID JOIN Example ON Example.defID = Definition.id WHERE Word.word LIKE :word")
    public LiveData<Map<Definition, Example>> getDefinitionsAndExamplesByWord(String word);

    @Query("SELECT * FROM Definition WHERE wordID = :wordID")
//    @Query("SELECT D.*, E.* FROM Definition D LEFT JOIN (SELECT id, MIN(example), defID FROM Example GROUP BY defID) E ON D.id = E.defID WHERE wordID = :wordID")
    public LiveData<List<Definition>> getDefinitionsByWordID(int wordID);

    @Query("SELECT Definition.*, Example.* FROM Definition JOIN Example ON Example.defID = Definition.id WHERE wordID = :wordID")
//    @Query("SELECT D.*, E.* FROM Definition D LEFT JOIN (SELECT id, MIN(example), defID FROM Example GROUP BY defID) E ON D.id = E.defID WHERE wordID = :wordID")
    public LiveData<Map<Definition, Example>> getDefinitionsAndExamplesByWordID(int wordID);
}
