package com.example.dosa.ui.Fragment;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.dosa.data.entity.NewsArticle;
import com.example.dosa.data.entity.WordDetail;
import com.example.dosa.ui.Adapter.AdapterNews;
import com.example.dosa.R;
import com.example.dosa.ui.Activity.MultipleChoiceActivity;
import com.example.dosa.databinding.FragmentHomeBinding;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;


import org.json.JSONArray;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class FragmentHome extends Fragment {
    FragmentHomeBinding binding;
    List<NewsArticle> list;
    SendData sendData;
    ArrayList<NewsArticle> articles;
    AdapterNews adapterNews;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentHomeBinding.inflate(inflater, container, false);

        // Greeting text
        binding.txtGreeting.setText("Xin chào, " + FirebaseAuth.getInstance().getCurrentUser().getDisplayName() + "!");

        articles = new ArrayList<>();
        adapterNews = new AdapterNews(getActivity(), articles);
        binding.rcvRecommendedArticles.setAdapter(adapterNews);
        binding.rcvRecommendedArticles.setLayoutManager(new LinearLayoutManager(getContext()));
        binding.rcvRecommendedArticles.setHasFixedSize(true);

        fetchArticles();

        binding.imgNews.setOnClickListener(view -> {
            sendData.sendData("news", null);
        });

        binding.btnLuyenTap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getContext(), MultipleChoiceActivity.class));
            }
        });

        return binding.getRoot();
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

    private void fetchArticles() {
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        db.collection("Article")
                .orderBy("date", Query.Direction.DESCENDING)
                .limit(10)
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {
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
                                NewsArticle newsArticle = new NewsArticle(title, link, keywords, creator, description, content, date, null, source);
                                new DownloadImageTask(newsArticle).execute(imageURL);
                            }
                        } else {
                        }
                    }
                });
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
            adapterNews.notifyDataSetChanged();
        }
    }
}
