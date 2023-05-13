package com.example.dosa.data.entity;

import android.graphics.Bitmap;

import java.io.Serializable;
import java.util.ArrayList;

public class NewsArticle {

    private String id;
    private String title;
    private String link;
    private ArrayList<String> keywords;
    private ArrayList<String> creator;
    private String description;
    private String content;
    private String date;
    private String imageURL;
    private String source;


    public NewsArticle() {
    }

    public NewsArticle(String id, String title, String link, ArrayList<String> keywords, ArrayList<String> creator, String description, String content, String date, String imageURL, String source) {
        this.title = title;
        this.id = id;
        this.link = link;
        this.keywords = keywords;
        this.creator = creator;
        this.description = description;
        this.content = content;
        this.date = date;
        this.imageURL = imageURL;
        this.source = source;
    }

    public ArrayList<String> getCreator() {
        return creator;
    }

    public ArrayList<String> getKeywords() {
        return keywords;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public String getDate() {
        return date;
    }

    public String getDescription() {
        return description;
    }

    public String getImageURL() {
        return imageURL;
    }

    public String getLink() {
        return link;
    }

    public String getSource() {
        return source;
    }

    public String getTitle() {
        return title;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public void setCreator(ArrayList<String> creator) {
        this.creator = creator;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setImageURL(String imageURL) {
        this.imageURL = imageURL;
    }

    public void setKeywords(ArrayList<String> keywords) {
        this.keywords = keywords;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
