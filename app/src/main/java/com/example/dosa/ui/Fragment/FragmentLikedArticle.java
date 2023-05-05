package com.example.dosa.ui.Fragment;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.SearchView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.dosa.data.entity.NewsArticle;
import com.example.dosa.databinding.FragmentLikedWordsBinding;
import com.example.dosa.databinding.FragmentTudienBinding;
import com.example.dosa.ui.Adapter.AdapterLikedArticle;
import com.example.dosa.ui.Adapter.AdapterLikedWord;
import com.example.dosa.ui.Adapter.AdapterNews;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.io.InputStream;
import java.util.ArrayList;

public class FragmentLikedArticle extends Fragment {
    FragmentLikedWordsBinding binding;
    ArrayList<String> articleIDs;
    ArrayList<NewsArticle> articles;

    AdapterNews adapter;
    String userID;
    SendData sendData;

    FirebaseFirestore db;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentLikedWordsBinding.inflate(inflater, container, false);

        userID = FirebaseAuth.getInstance().getCurrentUser().getUid();
        db = FirebaseFirestore.getInstance();

        articleIDs = new ArrayList<>();
        articles = new ArrayList<>();
        adapter = new AdapterNews(getContext(), articles);
        binding.rcvNews.setAdapter(adapter);
        binding.rcvNews.setLayoutManager(new LinearLayoutManager(getContext()));
        binding.rcvNews.setHasFixedSize(true);

        binding.schvNews.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                adapter.getFilter().filter(newText);
                return false;
            }
        });
        return binding.getRoot();
    }


    public void fetchArticlesID() {
        adapter.list.clear();
        db.collection("FavouriteArticle")
                .whereEqualTo("userID", userID)
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                String id = document.getString("articleID");
                                if (id != null)
                                    fetchArticles(id);
                            }
                        } else {
                        }
                    }
                });
    }

    public void fetchArticles(String id) {
        articles.clear();
        db.collection("Article")
                .document(id)
                .get()
                .addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                        DocumentSnapshot document = task.getResult();
                        String title = document.getString("title");
                        String link = document.getString("link");
                        ArrayList<String> keywords = (ArrayList<String>) document.get("keywords");
                        if (document.get("keywords") == null) {
                            keywords = new ArrayList<>();
                            keywords.add("Other");
                        }
                        ArrayList<String> creator = (ArrayList<String>) document.get("creator");
                        String description = document.getString("description");
                        String content = document.getString("content");
                        String date = document.getString("date");
                        String imageURL = document.getString("imageURL");
                        String source = document.getString("source");
                        NewsArticle newsArticle = new NewsArticle(document.getId(), title, link, keywords, creator, description, content, date, null, source);
                        new DownloadImageTask(newsArticle).execute(imageURL);
                    }
                });
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        if (context instanceof SendData) {
            sendData = (SendData) context;
        } else {
            throw new RuntimeException(context.toString());
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        sendData = null;
    }

    private class DownloadImageTask extends AsyncTask<String, Void, Bitmap> {
        NewsArticle newsArticle;

        public DownloadImageTask(NewsArticle newsArticle) {
            this.newsArticle = newsArticle;
        }

        protected Bitmap doInBackground(String... urls) {
            String urldisplay = urls[0];
            Bitmap mIcon11 = null;
            try {
                InputStream in = new java.net.URL(urldisplay).openStream();
                mIcon11 = BitmapFactory.decodeStream(in);
            } catch (Exception e) {
                e.printStackTrace();
            }
            return mIcon11;
        }

        protected void onPostExecute(Bitmap result) {
            newsArticle.setImage(result);
            articles.add(newsArticle);
            adapter.notifyDataSetChanged();
            Log.d("LIKEDASDAS", "onPostExecute: " + adapter.list.size());
        }
    }


}
