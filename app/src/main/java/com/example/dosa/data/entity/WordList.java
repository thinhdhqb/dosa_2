package com.example.dosa.data.entity;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class WordList {
    @PrimaryKey(autoGenerate = true)
    public int id;
    public String title;
}
