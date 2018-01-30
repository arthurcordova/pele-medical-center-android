package com.mobway.pelemedicalcenter.controllers;

import android.app.Activity;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;

import com.mobway.pelemedicalcenter.adapters.RVAdapterTime;
import com.mobway.pelemedicalcenter.models.Time;
import com.mobway.pelemedicalcenter.services.TimeService;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by acstapassoli on 10/11/17.
 */

public class NotificationController extends Controller implements Callback<List<Time>> {

    private TimeService mApi = null;
    private RVAdapterTime mAdapterTime;
    private ProgressBar mProgressBar;

    public NotificationController delegateAdapter(RVAdapterTime adapter) {
        mAdapterTime = adapter;
        return this;
    }

    public NotificationController delegateProgressBar(ProgressBar progressBar) {
        mProgressBar = progressBar;
        return this;
    }

    public NotificationController(Activity activity) {
        super(activity);
        mApi = retrofit.create(TimeService.class);
    }

    public void getHours(String date, String codeRoom, Integer filial) {
//        startProgress();
        showProgress();
        String url = "/clickwebservice/servidor/pelews/agenda/horarios/"+String.valueOf(filial)+"/"+date+"/"+codeRoom;
        Call<List<Time>> call = mApi.hours(url);
        call.enqueue(this);
    }

    private void startProgress() {
        if (mProgressBar != null) {
            mProgressBar.setVisibility(View.VISIBLE);
        }
    }

    private void endProgress() {
        if (mProgressBar != null) {
            mProgressBar.setVisibility(View.INVISIBLE);
        }
    }

    @Override
    public void onResponse(Call<List<Time>> call, Response<List<Time>> response) {
//        endProgress();
        hideProgress();
        if (response.isSuccessful()) {
            Log.e("SUCCESS", response.body().toString());
            List<Time> hours = response.body();

            if (mAdapterTime != null) {
                mAdapterTime.setFilter(hours);
            }

        } else {

        }
    }

    @Override
    public void onFailure(Call<List<Time>> call, Throwable t) {
        hideProgress();
        Log.e("FAIL", "FAIL");
    }
}