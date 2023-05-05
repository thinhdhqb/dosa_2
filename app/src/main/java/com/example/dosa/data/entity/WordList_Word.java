package com.example.dosa.data.entity;

import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

@Entity(foreignKeys = @ForeignKey(entity = WordList.class, parentColumns = "id", childColumns = "wordListID"))
public class WordList_Word {
    @PrimaryKey(autoGenerate=true)
    public int id;
    public int wordListID;
    public String word;
}
