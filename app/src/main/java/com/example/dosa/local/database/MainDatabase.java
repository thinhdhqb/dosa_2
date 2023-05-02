package com.example.dosa.local.database;


import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.dosa.local.dao.DefinitionDAO;
import com.example.dosa.local.dao.ExampleDAO;
import com.example.dosa.local.dao.IPADAO;
import com.example.dosa.local.dao.WordDAO;
import com.example.dosa.local.entity.Definition;
import com.example.dosa.local.entity.DefinitionTopic;
import com.example.dosa.local.entity.Example;
import com.example.dosa.local.entity.IPA;
import com.example.dosa.local.entity.Topic;
import com.example.dosa.local.entity.Word;

@Database(entities = {Word.class, Definition.class, DefinitionTopic.class, Topic.class, Example.class, IPA.class}, version = 5)
public abstract class MainDatabase extends RoomDatabase {
    public abstract DefinitionDAO definitionDAO();
    public abstract WordDAO wordDAO();
    public abstract ExampleDAO exampleDAO();

    public abstract IPADAO ipaDAO();

    private static MainDatabase INSTANCE;

    public static MainDatabase getDatabase(final Context context) {
        if (INSTANCE == null) {
            synchronized (MainDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                                    MainDatabase.class, "dosa")
                            .createFromAsset("dosa.db")
                            .allowMainThreadQueries()
                            .fallbackToDestructiveMigration()
                            .build();
                }
            }
        }
        return INSTANCE;
    }

}
