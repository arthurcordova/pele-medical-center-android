package com.mobway.pelemedicalcenter.controllers;

import android.app.Activity;
import android.util.Log;

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

    private PhysicianService api = null;

    public PhysicianController(Activity activity) {
        super(activity);
        api = retrofit.create(PhysicianService.class);
    }

    public void getPhysicians(){
        Call<List<Physician>> call = api.physicians();
        call.enqueue(this);
    }

    @Override
    public void onResponse(Call<List<Physician>> call, Response<List<Physician>> response) {
        Log.e("SUCCESS", response.body().toString());
        if (response.isSuccessful()) {
            List<Physician> banners = response.body();

        } else {

        }
    }

    @Override
    public void onFailure(Call<List<Physician>> call, Throwable t) {

        Log.e("FAIL", "FAIL");
    }
}
