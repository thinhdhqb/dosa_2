package com.example.dosa.data.entity;

import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

@Entity(foreignKeys = @ForeignKey(entity = Word.class, parentColumns = "id", childColumns = "wordID"))
public class IPA {
    @PrimaryKey(autoGenerate=true)
    public int id;
    public String ipa;
    public String tag;
    public int wordID;
}
