package com.mobway.pelemedicalcenter.controllers;

import android.app.Activity;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;

import com.mobway.pelemedicalcenter.adapters.RVAdapterTime;
import com.mobway.pelemedicalcenter.models.ArriveOrderResponse;
import com.mobway.pelemedicalcenter.models.Time;
import com.mobway.pelemedicalcenter.services.ArriveOrderService;
import com.mobway.pelemedicalcenter.services.TimeService;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by acstapassoli on 10/11/17.
 */

public class ArriveOrderController extends Controller implements Callback<ArriveOrderResponse> {

    private ArriveOrderService mApi = null;

    public ArriveOrderController(Activity activity) {
        super(activity);
        mApi = retrofit.create(ArriveOrderService.class);
    }

    public void vacancies(String date, String codeRoom) {
        Call<ArriveOrderResponse> call = mApi.vacancies(date, codeRoom);
        call.enqueue(this);
    }

    @Override
    public void onResponse(Call<ArriveOrderResponse> call, Response<ArriveOrderResponse> response) {
        if (response.isSuccessful()) {
            Log.e("SUCCESS", response.body().toString());
            ArriveOrderResponse orderResponse = response.body();

        } else {

        }
    }

    @Override
    public void onFailure(Call<ArriveOrderResponse> call, Throwable t) {
        Log.e("FAIL", "FAIL");
    }
}
