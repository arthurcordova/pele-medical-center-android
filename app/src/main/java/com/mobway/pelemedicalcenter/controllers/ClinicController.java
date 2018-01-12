package com.mobway.pelemedicalcenter.controllers;

import android.app.Activity;
import android.content.Context;
import android.util.Log;

import com.mobway.pelemedicalcenter.adapters.RVAdapterCity;
import com.mobway.pelemedicalcenter.adapters.RVAdapterClinic;
import com.mobway.pelemedicalcenter.models.CityResponse;
import com.mobway.pelemedicalcenter.models.ClinicResponse;
import com.mobway.pelemedicalcenter.services.CityService;
import com.mobway.pelemedicalcenter.services.ClinicService;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by acstapassoli on 10/11/17.
 */

public class ClinicController extends Controller implements Callback<List<ClinicResponse>> {

    private ClinicService mApi = null;
    private RVAdapterClinic mAdapterClinic;

    public ClinicController delegateAdapter(RVAdapterClinic adapterClinic) {
        mAdapterClinic = adapterClinic;
        return this;
    }

    public ClinicController(Activity activity) {
        super(activity);
        mApi = retrofit.create(ClinicService.class);
    }

    /**
     * Retorna a lista de clinicas por cidade.
     *
     * @param code - Código da cidade
     */
    public void getClinics(Integer code, Context context) {
        showProgress(context);
        Call<List<ClinicResponse>> call = mApi.clinics(code);
        call.enqueue(this);
    }

    @Override
    public void onResponse(Call<List<ClinicResponse>> call, Response<List<ClinicResponse>> response) {
        hideProgress();
        if (response.isSuccessful()) {
            if (response.body() != null) {
                Log.e("SUCCESS", response.body().toString());
                List<ClinicResponse> clinics = response.body();
                if (mAdapterClinic != null) {
                    mAdapterClinic.setFilter(clinics);
                }
            }
        } else {

        }
    }

    @Override
    public void onFailure(Call<List<ClinicResponse>> call, Throwable t) {
        hideProgress();
        Log.e("FAIL", "FAIL");
    }
}
