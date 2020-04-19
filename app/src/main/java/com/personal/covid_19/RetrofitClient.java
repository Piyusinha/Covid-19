package com.personal.covid_19;

import android.content.Context;

import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;

import java.util.Collections;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitClient {

    private static Retrofit retrofit = null;
    private static  Retrofit newsretrofit=null;
    private static  Retrofit indiaretrofit=null;



    public static Retrofit getClient(Context context) {
        if (retrofit == null) {
            retrofit = new Retrofit.Builder()
                    .addConverterFactory(GsonConverterFactory.create())
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .baseUrl("https://corona.lmao.ninja/v2/")
                    .build();
        }
        return retrofit;
    }
    public static Retrofit getnewsretrofit(Context context)
    {
        if (newsretrofit == null) {
            newsretrofit = new Retrofit.Builder()
                    .addConverterFactory(GsonConverterFactory.create())
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .baseUrl("https://newsapi.org/v2/")
                    .build();
        }
        return newsretrofit;
    }
    public static Retrofit getindiaretrofit(Context context)
    {

        if (indiaretrofit == null) {
            indiaretrofit = new Retrofit.Builder()
                    .addConverterFactory(GsonConverterFactory.create())
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .baseUrl("https://api.covid19india.org/")
                    .build();
        }
        return indiaretrofit;
    }

}
