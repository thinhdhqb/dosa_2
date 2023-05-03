package com.example.dosa.repository;


import android.app.Application;
import android.os.AsyncTask;
import android.util.Log;

import androidx.lifecycle.LiveData;

import com.example.dosa.local.dao.DefinitionDAO;
import com.example.dosa.local.dao.EngVieTranslationDAO;
import com.example.dosa.local.dao.ExampleDAO;
import com.example.dosa.local.dao.IPADAO;
import com.example.dosa.local.dao.WordDAO;
import com.example.dosa.local.database.MainDatabase;
import com.example.dosa.local.entity.Definition;
import com.example.dosa.local.entity.EngVieTranslation;
import com.example.dosa.local.entity.Example;
import com.example.dosa.local.entity.IPA;
import com.example.dosa.local.entity.Word;

import java.util.List;
import java.util.Map;

public class Repository {
    private DefinitionDAO definitionDAO;
    private WordDAO wordDAO;
    private ExampleDAO exampleDAO;
    private IPADAO ipaDAO;
    private EngVieTranslationDAO engVieTranslationDAO;

    public Repository(Application application) {
        MainDatabase db = MainDatabase.getDatabase(application);
        definitionDAO = db.definitionDAO();
        wordDAO = db.wordDAO();
        exampleDAO = db.exampleDAO();
        ipaDAO = db.ipaDAO();
        engVieTranslationDAO = db.engVieTranslationDAO();
    }

    public LiveData<Map<Definition, Example>> getDefinitionsAndExamplesByWord(String word) {
        return definitionDAO.getDefinitionsAndExamplesByWord(word);
    }

    public LiveData<Map<Definition, Example>> getDefinitionsAndExamplesByWordID(int wordID) {
        return definitionDAO.getDefinitionsAndExamplesByWordID(wordID);
    }

    public LiveData<List<Word>> getWordsByWord(String word) {
        return wordDAO.getWordsByWord(word);
    }

    public LiveData<List<Definition>> getDefinitionsByWordID(int wordID) {
        return definitionDAO.getDefinitionsByWordID(wordID);
    }

    public LiveData<List<String>> getExampleByDefinitionID(int defID) {
        return exampleDAO.getExampleByDefinitionID(defID);
    }


    public LiveData<List<String>> getWordsAsLiveDataStartWith(String s) {
        return wordDAO.getWordsAsLiveDataStartWith(s);
    }

    public LiveData<List<IPA>> getIPAByWord(String word) {
        return ipaDAO.getIPAByWord(word);
    }

    public LiveData<EngVieTranslation> getTranslationByWord(String word) {
        return engVieTranslationDAO.getTranslationByWord(word);
    }





//    private static class MyAsyncTask extends AsyncTask
//            <Void, Void, Void> {
//        private WordDAO mAsyncTaskDao;
//        MyAsyncTask(WordDAO dao) {
//            mAsyncTaskDao = dao;
//        }
//        @Override
//        protected Void doInBackground(final Void... voids) {
//            Word[] words = mAsyncTaskDao.getFirst10WordsAsArray();
//            for (Word word : words) {
//                Log.d("MyAsyncTask", "doInBackground: " + word.word);
//            }
//            return null;
//        }
//    }
}
