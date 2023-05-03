package com.example.dosa.data.entity;

import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

@Entity(foreignKeys = @ForeignKey(entity = Definition.class, parentColumns = "id", childColumns = "defID"))
public class Example {
    @PrimaryKey(autoGenerate=true)
    public int id;
    public int defID;
    public String example;
}
