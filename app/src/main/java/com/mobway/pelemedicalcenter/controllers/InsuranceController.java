package com.mobway.pelemedicalcenter.controllers;

import android.app.Activity;
import android.util.Log;

import com.mobway.pelemedicalcenter.adapters.RVAdapterInsurance;
import com.mobway.pelemedicalcenter.models.Insurance;
import com.mobway.pelemedicalcenter.services.InsuranceService;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by acstapassoli on 10/11/17.
 */

public class InsuranceController extends Controller implements Callback<List<Insurance>> {

    private InsuranceService mApi = null;
    private RVAdapterInsurance mAdapterInsurance;

    public InsuranceController delegateAdapter(RVAdapterInsurance adapterInsurance) {
        mAdapterInsurance = adapterInsurance;
        return this;
    }

    public InsuranceController(Activity activity) {
        super(activity);
        mApi = retrofit.create(InsuranceService.class);
    }

    public void getInsurances() {
        Call<List<Insurance>> call = mApi.insurances();
        call.enqueue(this);
    }

    @Override
    public void onResponse(Call<List<Insurance>> call, Response<List<Insurance>> response) {
        if (response.isSuccessful()) {
            Log.e("SUCCESS", response.body().toString());
            List<Insurance> insurances = response.body();

            if (mAdapterInsurance != null) {
                mAdapterInsurance.setFilter(insurances);
            }
        } else {

        }
    }

    @Override
    public void onFailure(Call<List<Insurance>> call, Throwable t) {

        Log.e("FAIL", "FAIL");
    }
}