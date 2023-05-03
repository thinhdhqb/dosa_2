package com.example.dosa.data.database;


import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.dosa.data.dao.DefinitionDAO;
import com.example.dosa.data.dao.EngVieTranslationDAO;
import com.example.dosa.data.dao.ExampleDAO;
import com.example.dosa.data.dao.IPADAO;
import com.example.dosa.data.dao.WordDAO;
import com.example.dosa.data.entity.Definition;
import com.example.dosa.data.entity.DefinitionTopic;
import com.example.dosa.data.entity.EngVieTranslation;
import com.example.dosa.data.entity.Example;
import com.example.dosa.data.entity.IPA;
import com.example.dosa.data.entity.Topic;
import com.example.dosa.data.entity.Word;

@Database(entities = {Word.class, Definition.class, DefinitionTopic.class, Topic.class, Example.class, IPA.class, EngVieTranslation.class}, version = 5)
public abstract class MainDatabase extends RoomDatabase {
    public abstract DefinitionDAO definitionDAO();
    public abstract WordDAO wordDAO();
    public abstract ExampleDAO exampleDAO();

    public abstract IPADAO ipaDAO();
    public abstract EngVieTranslationDAO engVieTranslationDAO();

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
