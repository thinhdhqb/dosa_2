package com.example.dosa.local.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Query;

import java.util.List;

@Dao
public interface ExampleDAO {
    @Query("SELECT example FROM Example INNER JOIN Definition ON Definition.id = Example.defID WHERE defID = :defID")
    public LiveData<List<String>> getExampleByDefinitionID(int defID);
}
