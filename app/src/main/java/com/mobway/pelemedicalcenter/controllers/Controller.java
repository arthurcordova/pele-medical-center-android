package com.mobway.pelemedicalcenter.controllers;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.support.v7.app.AlertDialog;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by acstapassoli on 05/10/17.
 */

public abstract class Controller {

    Gson gsonBuilder;
    Retrofit retrofit;
    Activity activity;
    ProgressDialog dialog;

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

    public Controller() {
        gsonBuilder = new GsonBuilder()
                .setLenient()
                .create();

        retrofit = new Retrofit.Builder()
                .baseUrl("http://www2.beautyclinic.com.br/clickwebservice/servidor/pelews/")
//                .baseUrl("https://us-central1-scheduling-tracker.cloudfunctions.net")
                .addConverterFactory(GsonConverterFactory.create(gsonBuilder))
                .build();

    }

    public void showProgress() {
        dialog = ProgressDialog.show(activity, "",
                "Carregando. Por favor aguarde...", true);
    }

    public void showProgress(Context context) {
        dialog = ProgressDialog.show(context, "",
                "Carregando. Por favor aguarde...", true);
    }

    public void hideProgress() {
        if (dialog != null) {
            dialog.dismiss();
        }
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
