package com.personal.covid_19.model;

public class case_india {
    private String dailyconfirmed;
    private String dailydeceased;
    private String dailyrecovered;
    private String date;
    private String totalconfirmed;
    private String totaldeceased;
    private String totalrecovered;


    // Getter Methods

    public String getDailyconfirmed() {
        return dailyconfirmed;
    }

    public String getDailydeceased() {
        return dailydeceased;
    }

    public String getDailyrecovered() {
        return dailyrecovered;
    }

    public String getDate() {
        return date;
    }

    public String getTotalconfirmed() {
        return totalconfirmed;
    }

    public String getTotaldeceased() {
        return totaldeceased;
    }

    public String getTotalrecovered() {
        return totalrecovered;
    }

    // Setter Methods

    public void setDailyconfirmed( String dailyconfirmed ) {
        this.dailyconfirmed = dailyconfirmed;
    }

    public void setDailydeceased( String dailydeceased ) {
        this.dailydeceased = dailydeceased;
    }

    public void setDailyrecovered( String dailyrecovered ) {
        this.dailyrecovered = dailyrecovered;
    }

    public void setDate( String date ) {
        this.date = date;
    }

    public void setTotalconfirmed( String totalconfirmed ) {
        this.totalconfirmed = totalconfirmed;
    }

    public void setTotaldeceased( String totaldeceased ) {
        this.totaldeceased = totaldeceased;
    }

    public void setTotalrecovered( String totalrecovered ) {
        this.totalrecovered = totalrecovered;
    }
}
