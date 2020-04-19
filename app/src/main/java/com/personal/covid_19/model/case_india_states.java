package com.personal.covid_19.model;

public class case_india_states {
    private String active;
    private String confirmed;
    private String deaths;
    private String deltaconfirmed;
    private String deltadeaths;
    private String deltarecovered;
    private String lastupdatedtime;
    private String recovered;
    private String state;
    private String statecode;
    private String statenotes;


    // Getter Methods

    public String getActive() {
        return active;
    }

    public String getConfirmed() {
        return confirmed;
    }

    public String getDeaths() {
        return deaths;
    }

    public String getDeltaconfirmed() {
        return deltaconfirmed;
    }

    public String getDeltadeaths() {
        return deltadeaths;
    }

    public String getDeltarecovered() {
        return deltarecovered;
    }

    public String getLastupdatedtime() {
        return lastupdatedtime;
    }

    public String getRecovered() {
        return recovered;
    }

    public String getState() {
        return state;
    }

    public String getStatecode() {
        return statecode;
    }

    public String getStatenotes() {
        return statenotes;
    }

    // Setter Methods

    public void setActive( String active ) {
        this.active = active;
    }

    public void setConfirmed( String confirmed ) {
        this.confirmed = confirmed;
    }

    public void setDeaths( String deaths ) {
        this.deaths = deaths;
    }

    public void setDeltaconfirmed( String deltaconfirmed ) {
        this.deltaconfirmed = deltaconfirmed;
    }

    public void setDeltadeaths( String deltadeaths ) {
        this.deltadeaths = deltadeaths;
    }

    public void setDeltarecovered( String deltarecovered ) {
        this.deltarecovered = deltarecovered;
    }

    public void setLastupdatedtime( String lastupdatedtime ) {
        this.lastupdatedtime = lastupdatedtime;
    }

    public void setRecovered( String recovered ) {
        this.recovered = recovered;
    }

    public void setState( String state ) {
        this.state = state;
    }

    public void setStatecode( String statecode ) {
        this.statecode = statecode;
    }

    public void setStatenotes( String statenotes ) {
        this.statenotes = statenotes;
    }
}
