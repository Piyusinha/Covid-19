package com.personal.covid_19.utils;

import com.personal.covid_19.model.*;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface utils {
    @GET("all")
    Observable<allcases> allcases();
    @GET("countries?sort=cases")
    Observable<List<countrydata>> allcountrydata();



}
