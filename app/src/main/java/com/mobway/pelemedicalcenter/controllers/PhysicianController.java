package com.mobway.pelemedicalcenter.controllers;

import android.app.Activity;
import android.util.Log;

import com.mobway.pelemedicalcenter.adapters.RVAdapterDoctor;
import com.mobway.pelemedicalcenter.models.Physician;
import com.mobway.pelemedicalcenter.services.PhysicianService;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by acstapassoli on 10/11/17.
 */

public class PhysicianController extends Controller implements Callback<List<Physician>> {

    private PhysicianService mApi = null;
    private RVAdapterDoctor mAdapterDoctor;

    public PhysicianController delegateAdapter(RVAdapterDoctor adapterDoctor){
        mAdapterDoctor = adapterDoctor;
        return this;
    }

    public PhysicianController(Activity activity) {
        super(activity);
        mApi = retrofit.create(PhysicianService.class);
    }

    public void getPhysicians(){
        Call<List<Physician>> call = mApi.physicians();
        call.enqueue(this);
    }

    @Override
    public void onResponse(Call<List<Physician>> call, Response<List<Physician>> response) {
        if (response.isSuccessful()) {
            Log.e("SUCCESS", response.body().toString());
            List<Physician> physicians = response.body();

            if (mAdapterDoctor != null){
                mAdapterDoctor.setFilter(physicians);
            }

        } else {

        }
    }

    @Override
    public void onFailure(Call<List<Physician>> call, Throwable t) {

        Log.e("FAIL", "FAIL");
    }
}
