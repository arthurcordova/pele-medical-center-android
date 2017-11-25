package com.mobway.pelemedicalcenter.controllers;

import android.app.Activity;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by acstapassoli on 05/10/17.
 */

public abstract class Controller {

    Gson gsonBuilder;
    Retrofit retrofit;
    Activity activity;

    public Controller(Activity activity) {
        this.activity = activity;
        gsonBuilder = new GsonBuilder()
                .setLenient()
                .create();

        retrofit = new Retrofit.Builder()
                .baseUrl("http://www2.beautyclinic.com.br/clickwebservice/servidor/pelews/")
//                .baseUrl("https://us-central1-scheduling-tracker.cloudfunctions.net")
                .addConverterFactory(GsonConverterFactory.create(gsonBuilder))
                .build();

    }


    public void start() {
//
//        OkHttpClient client = new OkHttpClient.Builder().addInterceptor(new Interceptor() {
//            @Override
//            public Response intercept(Chain chain) throws IOException {
//                Request newRequest  = chain.request().newBuilder()
//                        .addHeader("Authorization", "Bearer " + token)
//                        .build();
//                return chain.proceed(newRequest);
//            }
//        }).build();

    }
}
