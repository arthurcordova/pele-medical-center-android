package com.mobway.pelemedicalcenter.controllers;

import android.app.Activity;
import android.content.Context;
import android.util.Log;

import com.mobway.pelemedicalcenter.adapters.RVAdapterConsult;
import com.mobway.pelemedicalcenter.adapters.RVAdapterInsurance;
import com.mobway.pelemedicalcenter.models.Consult;
import com.mobway.pelemedicalcenter.models.Insurance;
import com.mobway.pelemedicalcenter.services.ConsultService;
import com.mobway.pelemedicalcenter.services.InsuranceService;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by acstapassoli on 10/11/17.
 */

public class ConsultController extends Controller implements Callback<List<Consult>> {

    private ConsultService mApi = null;
    private RVAdapterConsult mAdapterConsult;

    public ConsultController delegateAdapter(RVAdapterConsult adapterConsult) {
        mAdapterConsult = adapterConsult;
        return this;
    }

    public ConsultController(Activity activity) {
        super(activity);
        mApi = retrofit.create(ConsultService.class);
    }

    public void getTypes(Integer filial, Context context) {
        showProgress(context);
        Call<List<Consult>> call = mApi.typeConsults(filial);
        call.enqueue(this);
    }

    @Override
    public void onResponse(Call<List<Consult>> call, Response<List<Consult>> response) {
        hideProgress();
        if (response.isSuccessful()) {
            Log.e("SUCCESS", response.body().toString());
            List<Consult> consultList = response.body();

            if (mAdapterConsult != null) {
                mAdapterConsult.setFilter(consultList);
            }
        } else {

        }
    }

    @Override
    public void onFailure(Call<List<Consult>> call, Throwable t) {
        hideProgress();
        Log.e("FAIL", "FAIL");
    }
}
