package com.personal.covid_19.model;

import java.util.ArrayList;

public class newsallcn {
    ArrayList<String> sources = new ArrayList<String>();
    private String author;
    private String title;
    private String description;
    private String url;
    private String urlToImage;

    @Override
    public String toString() {
        return "newsallcn{" +
                "author='" + author + '\'' +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", url='" + url + '\'' +
                ", urlToImage='" + urlToImage + '\'' +
                ", publishedAt='" + publishedAt + '\'' +
                ", content='" + content + '\'' +
                '}';
    }

    public newsallcn(String author, String title, String description, String url, String urlToImage, String publishedAt, String content) {
        this.author = author;
        this.title = title;
        this.description = description;
        this.url = url;
        this.urlToImage = urlToImage;
        this.publishedAt = publishedAt;
        this.content = content;
    }

    public ArrayList<String> getSources() {
        return sources;
    }

    public void setSources(ArrayList<String> sources) {
        this.sources = sources;
    }

    private String publishedAt;
    private String content;


    // Getter Methods


    public String getAuthor() {
        return author;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public String getUrl() {
        return url;
    }

    public String getUrlToImage() {
        return urlToImage;
    }

    public String getPublishedAt() {
        return publishedAt;
    }

    public String getContent() {
        return content;
    }

    // Setter Methods



    public void setAuthor( String author ) {
        this.author = author;
    }

    public void setTitle( String title ) {
        this.title = title;
    }

    public void setDescription( String description ) {
        this.description = description;
    }

    public void setUrl( String url ) {
        this.url = url;
    }

    public void setUrlToImage( String urlToImage ) {
        this.urlToImage = urlToImage;
    }

    public void setPublishedAt( String publishedAt ) {
        this.publishedAt = publishedAt;
    }

    public void setContent( String content ) {
        this.content = content;
    }
}


    // Setter Methods



