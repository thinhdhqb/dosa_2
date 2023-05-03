package com.example.dosa.data.entity;

import androidx.room.Entity;
import androidx.room.Index;
import androidx.room.PrimaryKey;

@Entity(tableName = "av", indices = @Index("word"))
public class EngVieTranslation {
    @PrimaryKey
    public int id;
    public String word;
    public String html;
    public String description;
    public String pronounce;
}
