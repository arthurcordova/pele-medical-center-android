package com.mobway.pelemedicalcenter.controllers;

import android.app.Activity;
import android.util.Log;

import com.mobway.pelemedicalcenter.adapters.RVAdapterCity;
import com.mobway.pelemedicalcenter.models.CityResponse;
import com.mobway.pelemedicalcenter.services.CityService;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by acstapassoli on 10/11/17.
 */

public class CityController extends Controller implements Callback<List<CityResponse>> {

    private CityService mApi = null;
    private RVAdapterCity mAdapterCity;

    public CityController delegateAdapter(RVAdapterCity adapterInsurance) {
        mAdapterCity = adapterInsurance;
        return this;
    }

    public CityController(Activity activity) {
        super(activity);
        mApi = retrofit.create(CityService.class);
    }

    public void getCities() {
        Call<List<CityResponse>> call = mApi.cities();
        call.enqueue(this);
    }

    @Override
    public void onResponse(Call<List<CityResponse>> call, Response<List<CityResponse>> response) {
        if (response.isSuccessful()) {
            if (response.body() != null) {
                Log.e("SUCCESS", response.body().toString());
                List<CityResponse> insurances = response.body();
                if (mAdapterCity != null) {
                    mAdapterCity.setFilter(insurances);
                }
            }
        } else {

        }
    }

    @Override
    public void onFailure(Call<List<CityResponse>> call, Throwable t) {

        Log.e("FAIL", "FAIL");
    }
}
