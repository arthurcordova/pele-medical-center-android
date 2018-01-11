package com.mobway.pelemedicalcenter.controllers;

import android.app.Activity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.mobway.pelemedicalcenter.adapters.RVAdapterSpecialty;
import com.mobway.pelemedicalcenter.models.Filter;
import com.mobway.pelemedicalcenter.models.Specialty;
import com.mobway.pelemedicalcenter.services.SpecialtyService;
import com.mobway.pelemedicalcenter.utils.FilterManager;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by acstapassoli on 10/11/17.
 */

public class SpecialtyController extends Controller implements Callback<List<Specialty>> {

    private SpecialtyService mApi = null;
    private RVAdapterSpecialty mAdapterSpecialty;
    private RecyclerView mRecyclerView;

    public SpecialtyController delegateAdapter(RVAdapterSpecialty adapterSpecialty) {
        mAdapterSpecialty = adapterSpecialty;
        return this;
    }

    public SpecialtyController delegateRecyclerView(RecyclerView recyclerView) {
        mRecyclerView = recyclerView;
        return this;
    }

    public SpecialtyController(Activity activity) {
        super(activity);
        mApi = retrofit.create(SpecialtyService.class);
    }

    public void getSpecialties(Integer filial) {
        Call<List<Specialty>> call = mApi.specialties(filial);
        call.enqueue(this);
    }

    @Override
    public void onResponse(Call<List<Specialty>> call, Response<List<Specialty>> response) {
        if (response.isSuccessful()) {
            Log.e("SUCCESS", response.body().toString());
            List<Specialty> specialties = response.body();

            if (mAdapterSpecialty != null) {
                Filter filter = new FilterManager(activity).getFilters();
//                for (Specialty specialty: specialties) {
//                    specialty.mergeWithSaved(filter.getSpecialties());
//                }
                mAdapterSpecialty.setFilter(specialties);
                mRecyclerView.setLayoutManager(new GridLayoutManager(activity, specialties.size()));
                mRecyclerView.setAdapter(mAdapterSpecialty);
            }

        }
    }

    @Override
    public void onFailure(Call<List<Specialty>> call, Throwable t) {

        Log.e("FAIL", "FAIL");
    }
}
