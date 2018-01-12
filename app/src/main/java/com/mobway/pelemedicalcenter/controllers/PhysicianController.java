package com.mobway.pelemedicalcenter.controllers;

import android.app.Activity;
import android.util.Log;

import com.mobway.pelemedicalcenter.adapters.RVAdapterPhysician;
import com.mobway.pelemedicalcenter.models.DoctorFilterRequest;
import com.mobway.pelemedicalcenter.models.Filter;
import com.mobway.pelemedicalcenter.models.Physician;
import com.mobway.pelemedicalcenter.models.Specialty;
import com.mobway.pelemedicalcenter.services.PhysicianService;
import com.mobway.pelemedicalcenter.utils.FilterManager;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by acstapassoli on 10/11/17.
 */

public class PhysicianController extends Controller implements Callback<List<Physician>> {

    private PhysicianService mApi = null;
    private RVAdapterPhysician mAdapterDoctor;

    public PhysicianController delegateAdapter(RVAdapterPhysician adapterDoctor) {
        mAdapterDoctor = adapterDoctor;
        return this;
    }

    public PhysicianController(Activity activity) {
        super(activity);
        mApi = retrofit.create(PhysicianService.class);
    }

    public void getPhysicians(Integer filial) {
        Call<List<Physician>> call = mApi.physicians(filial);
        call.enqueue(this);
    }

    public void getPhysicians(DoctorFilterRequest filters) {
        showProgress();
        Call<List<Physician>> call = mApi.physicians(filters);
        call.enqueue(this);
    }

    @Override
    public void onResponse(Call<List<Physician>> call, Response<List<Physician>> response) {
        hideProgress();
        if (response.isSuccessful()) {
            Log.e("SUCCESS", response.body().toString());
            List<Physician> physicians = response.body();

            if (mAdapterDoctor != null) {
                Filter filter = new FilterManager(activity).getFilters();
                List<Physician> filterdList = new ArrayList<>();

                if (filter.getSpecialties() != null) {
                    for (Specialty sp : filter.getSpecialties()) {
                        for (Physician ph : physicians) {
                            if (sp.getUuid().equals(ph.getSpecialtyID())) {
                                filterdList.add(ph);
                            }
                        }
                    }
                } else {
                    filterdList = physicians;
                }
                mAdapterDoctor.setFilter(filterdList);
            }

        } else {

        }
    }

    @Override
    public void onFailure(Call<List<Physician>> call, Throwable t) {
        hideProgress();
        Log.e("FAIL", "FAIL");
    }
}
