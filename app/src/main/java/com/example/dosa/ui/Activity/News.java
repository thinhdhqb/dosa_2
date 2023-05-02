package com.example.dosa.ui.Activity;

public class News {
    private String id;
    private String nameNews;
    private String type;
    private String date;
    private String decription;

    private int image;

    public News() {
    }

    public News(String id, String nameNews, String type, String date, String decription, int image) {
        this.id = id;
        this.nameNews = nameNews;
        this.type = type;
        this.date = date;
        this.decription = decription;
        this.image = image;
    }

    public void setId(String id) {
        this.id = id;
    }


    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }

    public void setNameNews(String nameNews) {
        this.nameNews = nameNews;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void setDecription(String decription) {
        this.decription = decription;
    }

    public String getId() {
        return id;
    }

    public String getNameNews() {
        return nameNews;
    }

    public String getType() {
        return type;
    }

    public String getDate() {
        return date;
    }

    public String getDecription() {
        return decription;
    }
}
