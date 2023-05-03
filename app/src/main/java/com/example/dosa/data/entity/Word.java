package com.example.dosa.data.entity;

import androidx.room.Entity;
import androidx.room.Index;
import androidx.room.PrimaryKey;

@Entity(indices = @Index("word"))
public class Word {
    @PrimaryKey(autoGenerate=true)
    public int id;
    public String word;
    public String pos;

}
