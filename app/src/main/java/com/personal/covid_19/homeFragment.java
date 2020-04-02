package com.personal.covid_19;

import android.os.AsyncTask;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.io.IOException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import co.blankkeys.animatedlinegraphview.AnimatedLineGraphView;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.JsonArray;
import com.personal.covid_19.model.allcases;
import com.personal.covid_19.model.countrydailydata;
import com.personal.covid_19.model.countrydata;
import  com.personal.covid_19.utils.*;
import com.ramijemli.percentagechartview.PercentageChartView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;


/**
 * A simple {@link Fragment} subclass.
 */
public class homeFragment extends Fragment {
    AnimatedLineGraphView affectedGraphView,activegraphview;
    TextView affected,active,fconame,factive,sconame,sactive,tconame,tactive;
    PercentageChartView percentageChartView;
    JSONObject jsonValuecases,jsonValuedeath
            ,jsonValuerecover= null;
    List<String> casesdata = new ArrayList<>();
    List<String> deathdata = new ArrayList<>();
    List<String> recoverdata = new ArrayList<>();
    String cars[];
    List<String> topthreecon=new ArrayList<>();
    List<countrydailydata> countrydatadaily;
    ObjectMapper objectMapper = new ObjectMapper();

    private static String TAG="Check";


    public homeFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        ViewGroup root=(ViewGroup)inflater.inflate(R.layout.fragment_home, container, false);
        affectedGraphView=root.findViewById(R.id.affectedgraph);
        activegraphview=root.findViewById(R.id.activegraph);
        affected=root.findViewById(R.id.textaffected);
        active=root.findViewById(R.id.textactive);
        fconame=root.findViewById(R.id.textfcon);
        factive=root.findViewById(R.id.textfconcases);
        sconame=root.findViewById(R.id.textscon);
        sactive=root.findViewById(R.id.textsconcases);
        tconame=root.findViewById(R.id.texttcon);
        tactive=root.findViewById(R.id.texttconcases);
        percentageChartView=root.findViewById(R.id.percentage);

        utils apiinterface=RetrofitClient.getClient(getContext()).create(utils.class);
        apiinterface.allcases().subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<allcases>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(allcases allcases) {
                        Log.d("TAG", "onNext: "+allcases.getActive());
                        DecimalFormat precision = new DecimalFormat("0.0");
                        affected.setText(Float.toString(Float.parseFloat(precision.format(allcases.getCases()/1000)))+"k");
                        active.setText(Float.toString(Float.parseFloat(precision.format(allcases.getActive()/1000)))+"k");
                        percentageChartView.setProgress((allcases.getActive()/allcases.getCases())*100,true);

                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.d("TAG", "onError: "+e.getLocalizedMessage());
                    }

                    @Override
                    public void onComplete() {

                    }
                });
            new fetchdailydata().execute();
           apiinterface.allcountrydata().subscribeOn(Schedulers.io())
           .observeOn(AndroidSchedulers.mainThread())
           .subscribe(new Observer<List<countrydata>>() {
               @Override
               public void onSubscribe(Disposable d) {

               }

               @Override
               public void onNext(List<countrydata> countrydata) {
                   Log.d(TAG, "onNext: "+countrydata.get(0).getCountry());
                   fconame.setText(countrydata.get(0).getCountry());
                   factive.setText(Float.toString(countrydata.get(0).getActive()));
                   sconame.setText(countrydata.get(1).getCountry());
                   sactive.setText(Float.toString(countrydata.get(1).getActive()));
                   tconame.setText(countrydata.get(2).getCountry());
                   tactive.setText(Float.toString(countrydata.get(2).getActive()));
                   topthreecon.add(countrydata.get(0).getCountry());
                   topthreecon.add(countrydata.get(1).getCountry());
                   topthreecon.add(countrydata.get(2).getCountry());

               }

               @Override
               public void onError(Throwable e) {
                   Log.d(TAG, "onError: "+e.getLocalizedMessage());
               }

               @Override
               public void onComplete() {
                   new topcondailydata().execute();

               }
           })  ;



        // Inflate the layout for this fragment
        return root;
    }
    private class fetchdailydata extends AsyncTask<String,Void,String>
    {

        @Override
        protected String doInBackground(String... strings) {
            OkHttpClient client = new OkHttpClient();
            Request request = new Request.Builder().url("https://corona.lmao.ninja/v2/historical/all").build();
            try {

                Response response = client.newCall(request).execute();
//                Log.d("CheckR", "doInBackground: "+response.body().string());
                JSONObject array1 = new JSONObject(response.body().string());

                if (array1.length() > 0) {
                    jsonValuecases = array1.getJSONObject("cases");
                    jsonValuedeath = array1.getJSONObject("deaths");
                    jsonValuerecover=array1.getJSONObject("recovered");
                    Log.d(TAG, "doInBackground: "+jsonValuecases.toString());


                }


            } catch (IOException | JSONException e) {
                e.printStackTrace();
            }


            return null;
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            dailydatatolist(jsonValuecases,casesdata);
            dailydatatolist(jsonValuedeath,deathdata);
            dailydatatolist(jsonValuerecover,recoverdata);
            int size=casesdata.size();
            float casedata[] = new float[]{ Float.valueOf(casesdata.get(size-1)),Float.valueOf(casesdata.get(size-2)),Float.valueOf(casesdata.get(size-3)),Float.valueOf(casesdata.get(size-4)),
                    Float.valueOf(casesdata.get(size-5)),Float.valueOf(casesdata.get(size-6)),Float.valueOf(casesdata.get(size-7)),Float.valueOf(casesdata.get(size-8)),Float.valueOf(casesdata.get(size-9))
                    ,Float.valueOf(casesdata.get(size-10))};


            affectedGraphView.setData(null);// for example
            affectedGraphView.setData(casedata);
            float activecasep[]=new float[]{
                    Float.valueOf(casesdata.get(size-1))-Float.valueOf(deathdata.get(size-1))-Float.valueOf(recoverdata.get(size-1)),Float.valueOf(casesdata.get(size-2))-Float.valueOf(deathdata.get(size-2))-Float.valueOf(recoverdata.get(size-2)),Float.valueOf(casesdata.get(size-3))-Float.valueOf(deathdata.get(size-3))-Float.valueOf(recoverdata.get(size-3)),Float.valueOf(casesdata.get(size-4))-Float.valueOf(deathdata.get(size-4))-Float.valueOf(recoverdata.get(size-4)),
                    Float.valueOf(casesdata.get(size-5))-Float.valueOf(recoverdata.get(size-5))-Float.valueOf(deathdata.get(size-5)),Float.valueOf(casesdata.get(size-6))-Float.valueOf(deathdata.get(size-6))-Float.valueOf(recoverdata.get(size-6)),
                    Float.valueOf(casesdata.get(size-7))-Float.valueOf(deathdata.get(size-7))-Float.valueOf(recoverdata.get(size-7)),Float.valueOf(casesdata.get(size-8))-Float.valueOf(deathdata.get(size-8))-Float.valueOf(recoverdata.get(size-8)),
                    Float.valueOf(casesdata.get(size-9))-Float.valueOf(deathdata.get(size-9))-Float.valueOf(recoverdata.get(size-9))
                    ,Float.valueOf(casesdata.get(size-10))-Float.valueOf(deathdata.get(size-10))-Float.valueOf(recoverdata.get(size-10))};

            activegraphview.setData(null);
            activegraphview.setData(activecasep);


        }

        private void dailydatatolist(JSONObject jsonObject, List<String> dailydata) {
            try {
                // Iterate your json object keys
                Iterator iterator = jsonObject.keys();

                // Initiate the for loop
                String jsonKey=null;
                while (iterator.hasNext()) {

                    // Parsing json keys
                    jsonKey = (String) iterator.next();


                    // Parsing json values
                    String jsonValue = jsonValuecases.getString(jsonKey);
                    dailydata.add(jsonValue);


                    // Printing the log


                }


            } catch (JSONException e) {
                e.printStackTrace();
                Log.e("OOPs!!", "Please check your json or something else bad happened!!");
            }



        }


    }
    private class topcondailydata extends AsyncTask<String,Void,String>
    {
        @Override
        protected String doInBackground(String... strings) {
            OkHttpClient client = new OkHttpClient();
            Request request = new Request.Builder().url("https://pomber.github.io/covid19/timeseries.json").build();
            try {

                Response response = client.newCall(request).execute();
//                Log.d("CheckR", "doInBackground: "+response.body().string());
                JSONObject array1 = new JSONObject(response.body().string());

                if (array1.length() > 0) {

                   JSONArray check=array1.getJSONArray(topthreecon.get(0).substring(0,2));

                    countrydatadaily=objectMapper.readValue(String.valueOf(check),objectMapper.getTypeFactory().constructCollectionType(List.class, countrydailydata.class));
                    Log.d(TAG, "doInBackground: "+countrydatadaily.get(countrydatadaily.size()-1).getConfirmed());



                }


            } catch (IOException | JSONException e) {
                e.printStackTrace();
            }


            return null;
        }
    }
}


