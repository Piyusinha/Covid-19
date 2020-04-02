package com.personal.covid_19.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

public class countrydailydata {
    @JsonIgnoreProperties
    String date;

    int confirmed;
    int deaths;
    int recovered;
    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public int getConfirmed() {
        return confirmed;
    }

    public void setConfirmed(int confirmed) {
        this.confirmed = confirmed;
    }

    public int getDeaths() {
        return deaths;
    }

    public void setDeaths(int deaths) {
        this.deaths = deaths;
    }

    public int getRecovered() {
        return recovered;
    }

    public void setRecovered(int recovered) {
        this.recovered = recovered;
    }



}
