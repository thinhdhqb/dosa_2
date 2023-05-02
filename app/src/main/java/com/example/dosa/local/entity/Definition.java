package com.example.dosa.local.entity;


import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

@Entity(foreignKeys = @ForeignKey(entity = Word.class, parentColumns = "id", childColumns = "wordID"))
public class Definition {
    @PrimaryKey(autoGenerate=true)
    public int id;
    public String definition;
    public int wordID;

}
