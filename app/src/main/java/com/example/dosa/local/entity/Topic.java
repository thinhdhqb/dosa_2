package com.example.dosa.local.entity;

import androidx.room.Entity;
import androidx.room.Index;
import androidx.room.PrimaryKey;

@Entity(indices = @Index("name"))
public class Topic {
    @PrimaryKey(autoGenerate=true)
    public int id;
    public String name;

}
