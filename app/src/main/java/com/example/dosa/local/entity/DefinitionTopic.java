package com.example.dosa.local.entity;


import androidx.room.Entity;
import androidx.room.ForeignKey;

@Entity(tableName = "Definition_Topic", primaryKeys = {"defID", "topicID"},
        foreignKeys = {
            @ForeignKey(entity = Definition.class, parentColumns = "id", childColumns = "defID"),
            @ForeignKey(entity = Topic.class, parentColumns = "id", childColumns = "topicID"),
        })
public class DefinitionTopic {
    public int defID;
    public int topicID;
}
