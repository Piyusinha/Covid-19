package com.personal.covid_19;

import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.io.IOException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

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
import com.personal.covid_19.model.india_Data;
import  com.personal.covid_19.utils.*;
import com.ramijemli.percentagechartview.PercentageChartView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;


/**
 * A simple {@link Fragment} subclass.
 */
public class homeFragment extends Fragment {
    AnimatedLineGraphView affectedGraphView,activegraphview,graphfcon,graphscon,graphtcon;
    TextView affected,active,fconame,factive,sconame,sactive,tconame,tactive;
    PercentageChartView percentageChartView;
    JSONObject jsonValuecases,jsonValuedeath
            ,jsonValuerecover= null;
    TextView readmore;

    List<String> casesdata = new ArrayList<>();
    List<String> deathdata = new ArrayList<>();
    List<String> recoverdata = new ArrayList<>();
    String cars[];
    List<String> topthreecon=new ArrayList<>();
    List<countrydailydata> countrydatadaily1=null;
    List<countrydailydata> countrydatadaily2=null;
    List<countrydailydata> countrydatadaily3=null;

    ObjectMapper objectMapper = new ObjectMapper();
    HashMap<Integer, String> statecases=new HashMap<>();
    LinkedHashMap<Integer, String> sortedCASES = new LinkedHashMap<>();
    List<String> top3states=new ArrayList<>();
    List<Integer> top3statecases=new ArrayList<>();




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
        graphfcon=root.findViewById(R.id.graphfirstcountry);
        graphscon=root.findViewById(R.id.graphsecondcountry);
        graphtcon=root.findViewById(R.id.graphthirdcountry);
        affected=root.findViewById(R.id.textaffected);
        active=root.findViewById(R.id.textactive);
        fconame=root.findViewById(R.id.textfcon);
        factive=root.findViewById(R.id.textfconcases);
        sconame=root.findViewById(R.id.textscon);
        sactive=root.findViewById(R.id.textsconcases);
        tconame=root.findViewById(R.id.texttcon);
        tactive=root.findViewById(R.id.texttconcases);
        percentageChartView=root.findViewById(R.id.percentage);
        readmore=root.findViewById(R.id.readmore);
        readmore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.worldometers.info/coronavirus/coronavirus-symptoms/"));
                startActivity(browserIntent);
            }
        });
        utils apiinterfaceindia=RetrofitClient.getindiaretrofit(getContext()).create(utils.class);
        apiinterfaceindia.allindiadata().subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<india_Data>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @RequiresApi(api = Build.VERSION_CODES.N)
                    @Override
                    public void onNext(india_Data india_data) {
                        Log.d("HIEE", "onNext: "+india_data.getCases_time_series().toString());
                        for(int days=1;days<india_data.getStatewise().size();days++)
                        {
                            statecases.put(Integer.valueOf(india_data.getStatewise().get(days).getActive()),india_data.getStatewise().get(days).getState());
                        }
                        statecases.entrySet()
                                .stream()
                                .sorted(HashMap.Entry.comparingByKey())
                                .forEachOrdered(x -> sortedCASES.put(x.getKey(), x.getValue()));
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.e("hiee error", "onError: "+e.getLocalizedMessage() );
                    }

                    @Override
                    public void onComplete() {
                        List<Integer> l=new ArrayList<>(sortedCASES.keySet());
                        Log.d(TAG, "onComplete: "+l.size());
                        top3states.add(sortedCASES.get(l.get(l.size()-1)));
                        top3states.add(sortedCASES.get(l.get(l.size()-2)));
                        top3states.add(sortedCASES.get(l.get(l.size()-3)));
                        top3statecases.add(l.get(l.size()-1));
                        top3statecases.add(l.get(l.size()-2));
                        top3statecases.add(l.get(l.size()-3));

                        Log.d(TAG, "onComplete: "+top3states.get(0));
                        Log.d(TAG, "onComplete: "+top3statecases.get(0));



                    }
                });
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
                   fconame.setText(countrydata.get(1).getCountry());
                   factive.setText(Float.toString(countrydata.get(1).getCases()));
                   sconame.setText(countrydata.get(2).getCountry());
                   sactive.setText(Float.toString(countrydata.get(2).getCases()));
                   tconame.setText(countrydata.get(3).getCountry());
                   tactive.setText(Float.toString(countrydata.get(3).getCases()));
                   topthreecon.add(countrydata.get(1).getCountry());
                   topthreecon.add(countrydata.get(2).getCountry());
                   topthreecon.add(countrydata.get(3).getCountry());

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
                    Float.valueOf(casesdata.get(size-1))-Float.valueOf(deathdata.get(size-1))-Float.valueOf(recoverdata.get(size-1)),Float.valueOf(casesdata.get(size-2))-Float.valueOf(deathdata.get(size-2))-Float.valueOf(recoverdata.get(size-2)),
                    Float.valueOf(casesdata.get(size-3))-Float.valueOf(deathdata.get(size-3))-Float.valueOf(recoverdata.get(size-3)),Float.valueOf(casesdata.get(size-4))-Float.valueOf(deathdata.get(size-4))-Float.valueOf(recoverdata.get(size-4)),
                    Float.valueOf(casesdata.get(size-5))-Float.valueOf(recoverdata.get(size-5))-Float.valueOf(deathdata.get(size-5)),Float.valueOf(casesdata.get(size-6))-Float.valueOf(deathdata.get(size-6))-Float.valueOf(recoverdata.get(size-6)),
                    Float.valueOf(casesdata.get(size-7))-Float.valueOf(deathdata.get(size-7))-Float.valueOf(recoverdata.get(size-7)),Float.valueOf(casesdata.get(size-8))-Float.valueOf(deathdata.get(size-8))-Float.valueOf(recoverdata.get(size-8)),
                    Float.valueOf(casesdata.get(size-9))-Float.valueOf(deathdata.get(size-9))-Float.valueOf(recoverdata.get(size-9)),Float.valueOf(casesdata.get(size-10))-Float.valueOf(deathdata.get(size-10))-Float.valueOf(recoverdata.get(size-10))};

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
                    JSONArray country1 = null;
                if(topthreecon.get(0).equals("USA")) {
                   country1 = array1.getJSONArray(topthreecon.get(0).substring(0,2));
                }
                else
                {
                    country1 = array1.getJSONArray(topthreecon.get(0));
                }

                   JSONArray country2=array1.getJSONArray(topthreecon.get(1));
                   JSONArray country3=array1.getJSONArray(topthreecon.get(2));

                    countrydatadaily1=objectMapper.readValue(String.valueOf(country1),objectMapper.getTypeFactory().constructCollectionType(List.class, countrydailydata.class));
                    countrydatadaily2=objectMapper.readValue(String.valueOf(country2),objectMapper.getTypeFactory().constructCollectionType(List.class, countrydailydata.class));
                    countrydatadaily3=objectMapper.readValue(String.valueOf(country3),objectMapper.getTypeFactory().constructCollectionType(List.class, countrydailydata.class));




                }


            } catch (IOException | JSONException e) {
                e.printStackTrace();
            }


            return null;
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);

            int size=countrydatadaily1.size();
            float country1data[]=new float[]{countrydatadaily1.get(size-1).getConfirmed(),countrydatadaily1.get(size-2).getConfirmed(),
                    countrydatadaily1.get(size-3).getConfirmed(),countrydatadaily1.get(size-4).getConfirmed(),
                    countrydatadaily1.get(size-5).getConfirmed(),countrydatadaily1.get(size-6).getConfirmed(),
                    countrydatadaily1.get(size-7).getConfirmed(),countrydatadaily1.get(size-8).getConfirmed(),
                    countrydatadaily1.get(size-9).getConfirmed(),countrydatadaily1.get(size-10).getConfirmed()};
            float country2data[]=new float[]{countrydatadaily2.get(size-1).getConfirmed(),countrydatadaily2.get(size-2).getConfirmed(),
                    countrydatadaily2.get(size-3).getConfirmed(),countrydatadaily2.get(size-4).getConfirmed(),
                    countrydatadaily2.get(size-5).getConfirmed(),countrydatadaily2.get(size-6).getConfirmed(),
                    countrydatadaily2.get(size-7).getConfirmed(),countrydatadaily2.get(size-8).getConfirmed(),
                    countrydatadaily2.get(size-9).getConfirmed(),countrydatadaily2.get(size-10).getConfirmed()};
            float country3data[]=new float[]{countrydatadaily3.get(size-1).getConfirmed(),countrydatadaily3.get(size-2).getConfirmed(),
                    countrydatadaily3.get(size-3).getConfirmed(),countrydatadaily3.get(size-4).getConfirmed(),
                    countrydatadaily3.get(size-5).getConfirmed(),countrydatadaily3.get(size-6).getConfirmed(),
                    countrydatadaily3.get(size-7).getConfirmed(),countrydatadaily3.get(size-8).getConfirmed(),
                    countrydatadaily3.get(size-9).getConfirmed(),countrydatadaily3.get(size-10).getConfirmed()};

            graphfcon.setData(null);
            graphscon.setData(null);
            graphtcon.setData(null);

            graphfcon.setData(country1data);

            graphscon.setData(country2data);
            graphtcon.setData(country3data);



        }
    }
}


