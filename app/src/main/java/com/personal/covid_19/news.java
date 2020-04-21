package com.personal.covid_19;

import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import com.personal.covid_19.model.newsallcn;
import com.personal.covid_19.model.newsresponse;
import com.personal.covid_19.utils.utils;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

import static android.content.ContentValues.TAG;
import static android.content.Context.MODE_PRIVATE;


/**
 * A simple {@link Fragment} subclass.
 */
public class news extends Fragment {
    private RecyclerView recyclerView;
    private  newadapter newadapter;
    private List<newsallcn> listallnews=new ArrayList<>();
    public news() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        ViewGroup root=(ViewGroup)inflater.inflate(R.layout.fragment_news, container, false);
        ProgressBar progressBar=root.findViewById(R.id.newsprogress);

        SharedPreferences pref = getActivity().getApplicationContext().getSharedPreferences("mode", MODE_PRIVATE);

        recyclerView=root.findViewById(R.id.newslist);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        utils apiinterface=RetrofitClient.getnewsretrofit(getContext()).create(utils.class);
        if(!pref.getBoolean("india",false))
        {
        apiinterface.newsresponse().subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<newsresponse>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(newsresponse newsresponse) {
                        Log.d(TAG, "onNext: "+newsresponse.getArticles());
                        for(int i=0;i<newsresponse.getArticles().size();i++)
                        {
                            if(!(newsresponse.getArticles().get(i).getDescription()==null)) {
                                if(!(newsresponse.getArticles().get(i).getDescription().length()==0))
                                {
                                newsallcn newsallcn1 = new newsallcn(newsresponse.getArticles().get(i).getAuthor(), newsresponse.getArticles().get(i).getTitle(), newsresponse.getArticles().get(i).getDescription()
                                        , newsresponse.getArticles().get(i).getUrl(), newsresponse.getArticles().get(i).getUrlToImage(),
                                        newsresponse.getArticles().get(i).getPublishedAt(), newsresponse.getArticles().get(i).getContent());
                                listallnews.add(newsallcn1);
                            }}

                        }

                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.d(TAG, "onError: "+e.getLocalizedMessage());
                    }

                    @Override
                    public void onComplete() {
                        newadapter=new newadapter(listallnews,getContext());
                        newadapter.notifyDataSetChanged();
                        progressBar.setVisibility(View.GONE);
                        recyclerView.setAdapter(newadapter);



                    }
                });}
        else if(pref.getBoolean("india",false))
        {
            apiinterface.indiannewsresponse().subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(new Observer<newsresponse>() {
                        @Override
                        public void onSubscribe(Disposable d) {

                        }

                        @Override
                        public void onNext(newsresponse newsresponse) {
                            Log.d(TAG, "onNext: "+newsresponse.getArticles());
                            for(int i=0;i<newsresponse.getArticles().size();i++)
                            {
                                if(!(newsresponse.getArticles().get(i).getDescription()==null)) {
                                    if(!(newsresponse.getArticles().get(i).getDescription().length()==0))
                                    {
                                        newsallcn newsallcn1 = new newsallcn(newsresponse.getArticles().get(i).getAuthor(), newsresponse.getArticles().get(i).getTitle(), newsresponse.getArticles().get(i).getDescription()
                                                , newsresponse.getArticles().get(i).getUrl(), newsresponse.getArticles().get(i).getUrlToImage(),
                                                newsresponse.getArticles().get(i).getPublishedAt(), newsresponse.getArticles().get(i).getContent());
                                        listallnews.add(newsallcn1);
                                    }}

                            }

                        }

                        @Override
                        public void onError(Throwable e) {
                            Log.d(TAG, "onError: "+e.getLocalizedMessage());
                        }

                        @Override
                        public void onComplete() {
                            newadapter=new newadapter(listallnews,getContext());
                            newadapter.notifyDataSetChanged();
                            progressBar.setVisibility(View.GONE);
                            recyclerView.setAdapter(newadapter);



                        }
                    });
        }
        Log.d(TAG, "onCreateView: "+pref.getBoolean("india",false));
        return root;
    }
}
