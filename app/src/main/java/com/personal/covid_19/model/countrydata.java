package com.personal.covid_19.model;

public class countrydata {
    private String country;

    private float cases;
    private float todayCases;
    private float deaths;
    private float todayDeaths;
    private float recovered;
    private float active;
    private float critical;
    private float casesPerOneMillion;
    private float deathsPerOneMillion;
    private float updated;


    // Getter Methods

    public String getCountry() {
        return country;
    }



    public float getCases() {
        return cases;
    }

    public float getTodayCases() {
        return todayCases;
    }

    public float getDeaths() {
        return deaths;
    }

    public float getTodayDeaths() {
        return todayDeaths;
    }

    public float getRecovered() {
        return recovered;
    }

    public float getActive() {
        return active;
    }

    public float getCritical() {
        return critical;
    }

    public float getCasesPerOneMillion() {
        return casesPerOneMillion;
    }

    public float getDeathsPerOneMillion() {
        return deathsPerOneMillion;
    }

    public float getUpdated() {
        return updated;
    }

    // Setter Methods

    public void setCountry( String country ) {
        this.country = country;
    }



    public void setCases( float cases ) {
        this.cases = cases;
    }

    public void setTodayCases( float todayCases ) {
        this.todayCases = todayCases;
    }

    public void setDeaths( float deaths ) {
        this.deaths = deaths;
    }

    public void setTodayDeaths( float todayDeaths ) {
        this.todayDeaths = todayDeaths;
    }

    public void setRecovered( float recovered ) {
        this.recovered = recovered;
    }

    public void setActive( float active ) {
        this.active = active;
    }

    public void setCritical( float critical ) {
        this.critical = critical;
    }

    public void setCasesPerOneMillion( float casesPerOneMillion ) {
        this.casesPerOneMillion = casesPerOneMillion;
    }

    public void setDeathsPerOneMillion( float deathsPerOneMillion ) {
        this.deathsPerOneMillion = deathsPerOneMillion;
    }

    public void setUpdated( float updated ) {
        this.updated = updated;
    }
}


