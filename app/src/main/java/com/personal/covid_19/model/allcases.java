package com.personal.covid_19.model;

public class allcases {
    private float cases;
    private float deaths;
    private float recovered;
    private float updated;
    private float active;
    private float affectedCountries;


    // Getter Methods

    public float getCases() {
        return cases;
    }

    public float getDeaths() {
        return deaths;
    }

    public float getRecovered() {
        return recovered;
    }

    public float getUpdated() {
        return updated;
    }

    public float getActive() {
        return active;
    }

    public float getAffectedCountries() {
        return affectedCountries;
    }

    // Setter Methods

    public void setCases( float cases ) {
        this.cases = cases;
    }

    public void setDeaths( float deaths ) {
        this.deaths = deaths;
    }

    public void setRecovered( float recovered ) {
        this.recovered = recovered;
    }

    public void setUpdated( float updated ) {
        this.updated = updated;
    }

    public void setActive( float active ) {
        this.active = active;
    }

    public void setAffectedCountries( float affectedCountries ) {
        this.affectedCountries = affectedCountries;
    }
}
