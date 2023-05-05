package com.example.dosa.repository;


import android.app.Application;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.dosa.R;
import com.example.dosa.data.dao.DefinitionDAO;
import com.example.dosa.data.dao.EngVieTranslationDAO;
import com.example.dosa.data.dao.ExampleDAO;
import com.example.dosa.data.dao.IPADAO;
import com.example.dosa.data.dao.WordDAO;
import com.example.dosa.data.dao.WordListDAO;
import com.example.dosa.data.database.MainDatabase;
import com.example.dosa.data.entity.Definition;
import com.example.dosa.data.entity.EngVieTranslation;
import com.example.dosa.data.entity.Example;
import com.example.dosa.data.entity.IPA;
import com.example.dosa.data.entity.NewsArticle;
import com.example.dosa.data.entity.Word;
import com.example.dosa.data.entity.WordList;
import com.google.zxing.common.StringUtils;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.jsoup.internal.StringUtil;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class Repository {
    private DefinitionDAO definitionDAO;
    private WordDAO wordDAO;
    private ExampleDAO exampleDAO;
    private IPADAO ipaDAO;
    private EngVieTranslationDAO engVieTranslationDAO;

    private WordListDAO wordListDAO;
    private String API_KEY;


    public Repository(Application application) {
        MainDatabase db = MainDatabase.getDatabase(application);
        definitionDAO = db.definitionDAO();
        wordDAO = db.wordDAO();
        exampleDAO = db.exampleDAO();
        ipaDAO = db.ipaDAO();
        engVieTranslationDAO = db.engVieTranslationDAO();
        wordListDAO = db.wordListDAO();
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

    public LiveData<List<WordList>> getAllWordList() {
        return wordListDAO.getAllWordList();
    }

    public LiveData<List<String>> getWordsByWordListID(int id) {
        return wordListDAO.getWordsByWordListID(id);
    }



}
