package com.example.dosa.local.entity;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "av")
public class EngVieTranslation {
    @PrimaryKey
    public int id;
    public String word;
    public String html;
    public String description;
    public String pronounce;
}
