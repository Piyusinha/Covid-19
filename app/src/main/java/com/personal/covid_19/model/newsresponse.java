package com.personal.covid_19.model;

import java.util.ArrayList;

public class newsresponse {
    private String status;
    private float totalResults;
    ArrayList<newsallcn> articles = new ArrayList<newsallcn>();

    public ArrayList<newsallcn> getArticles() {
        return articles;
    }

    public void setArticles(ArrayList<newsallcn> articles) {
        this.articles = articles;
    }




    // Getter Methods

    public String getStatus() {
        return status;
    }

    public float getTotalResults() {
        return totalResults;
    }

    // Setter Methods

    public void setStatus( String status ) {
        this.status = status;
    }

    public void setTotalResults( float totalResults ) {
        this.totalResults = totalResults;
    }
}
