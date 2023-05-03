package com.example.dosa.viewmodel;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.dosa.data.entity.Definition;
import com.example.dosa.data.entity.EngVieTranslation;
import com.example.dosa.data.entity.Example;
import com.example.dosa.data.entity.IPA;
import com.example.dosa.data.entity.Word;
import com.example.dosa.repository.Repository;

import java.util.List;
import java.util.Map;

public class DictionaryViewModel extends AndroidViewModel {
    private Repository repository;

    public DictionaryViewModel(Application application) {
        super(application);
        repository = new Repository(application);
    }

    public LiveData<Map<Definition, Example>> getDefinitionsAndExamplesByWord(String word) {
        return repository.getDefinitionsAndExamplesByWord(word);
    }

    public LiveData<List<String>> getWordsAsLiveDataStartWith(String s) {
        return repository.getWordsAsLiveDataStartWith(s);
    }

    public LiveData<Map<Definition, Example>> getDefinitionsAndExamplesByWordID(int wordID) {
        return repository.getDefinitionsAndExamplesByWordID(wordID);
    }

    public LiveData<List<Word>> getWordsByWord(String word) {
        return repository.getWordsByWord(word);
    }

    public LiveData<List<Definition>> getDefinitionsByWordID(int wordID) {
        return repository.getDefinitionsByWordID(wordID);
    }

    public LiveData<List<String>> getExampleByDefinitionID(int defID) {
        return repository.getExampleByDefinitionID(defID);
    }

    public LiveData<List<IPA>> getIPAByWord(String word) {
        return repository.getIPAByWord(word);
    }

    public LiveData<EngVieTranslation> getTranslationByWord(String word) {
        return repository.getTranslationByWord(word);
    }
}
