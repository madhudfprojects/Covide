package com.dfcovid.remote;


import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class Class_RetrofitClient {


    public static Retrofit retrofit = null;



    public static Retrofit getClient(String url){
        if(retrofit == null){
            Gson gson = new GsonBuilder()
                    .setLenient()
                    .create();

            retrofit = new Retrofit.Builder()
                    .baseUrl(url)
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                   // .addConverterFactory(GsonConverterFactory.create(gson))
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();




            /*retrofit = new Retrofit.Builder()
                    .baseUrl(url)
                  // .client(client)
                    .addConverterFactory(GsonConverterFactory.create(gson))
                    .build();*/


        }
        return retrofit;
    }


}

