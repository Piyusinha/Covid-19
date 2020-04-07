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
    @GET("top-headlines?language=en&q=corona&apiKey=d658411b9a6c41d4b52e9ffe93b46d11")
    Observable<newsresponse> newsresponse();



}
