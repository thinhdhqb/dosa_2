package com.example.dosa.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.dosa.R;
import com.example.dosa.data.entity.NewsArticle;
import com.example.dosa.repository.Repository;

import java.io.IOException;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class ArticleViewModel extends AndroidViewModel {
    private Repository repository;

    public ArticleViewModel(@NonNull Application application) {
        super(application);
        repository = new Repository(application);
    }


}
