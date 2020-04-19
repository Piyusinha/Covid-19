package com.personal.covid_19.model;

import java.util.ArrayList;

public class india_Data {
    ArrayList<case_india> cases_time_series = new ArrayList<case_india>();
    ArrayList<case_india_states> statewise = new ArrayList<case_india_states>();

    public ArrayList<case_india> getCases_time_series() {
        return cases_time_series;
    }

    public void setCases_time_series(ArrayList<case_india> cases_time_series) {
        this.cases_time_series = cases_time_series;
    }

    public ArrayList<case_india_states> getStatewise() {
        return statewise;
    }

    public void setStatewise(ArrayList<case_india_states> statewise) {
        this.statewise = statewise;
    }
}
