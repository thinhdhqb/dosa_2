package com.example.dosa.data.entity;

import android.graphics.Bitmap;

import java.io.Serializable;
import java.util.ArrayList;

public class NewsArticle {
    private String title;
    private String link;
    private ArrayList<String> keywords;
    private ArrayList<String> creator;
    private String description;
    private String content;
    private String date;
    private Bitmap image;
    private String source;


    public NewsArticle() {
    }

    public NewsArticle(String title, String link, ArrayList<String> keywords, ArrayList<String> creator, String description, String content, String date, Bitmap image, String source) {
        this.title = title;
        this.link = link;
        this.keywords = keywords;
        this.creator = creator;
        this.description = description;
        this.content = content;
        this.date = date;
        this.image = image;
        this.source = source;
    }

    public ArrayList<String> getCreator() {
        return creator;
    }

    public ArrayList<String> getKeywords() {
        return keywords;
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

    public Bitmap getImage() {
        return image;
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

    public void setImage(Bitmap image) {
        this.image = image;
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
