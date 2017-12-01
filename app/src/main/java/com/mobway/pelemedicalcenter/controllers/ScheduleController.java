package com.mobway.pelemedicalcenter.controllers;

import android.app.Activity;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.mobway.pelemedicalcenter.adapters.RVAdapterSchedule;
import com.mobway.pelemedicalcenter.models.Schedule;
import com.mobway.pelemedicalcenter.models.Specialty;
import com.mobway.pelemedicalcenter.services.ScheduleService;
import com.mobway.pelemedicalcenter.services.SpecialtyService;
import com.mobway.pelemedicalcenter.utils.TaskDao;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by acstapassoli on 10/11/17.
 */

public class ScheduleController extends Controller implements Callback<List<Schedule>> {

    private ScheduleService mApi;
    private RVAdapterSchedule mAdapterSchedule;
    private RecyclerView mRecyclerView;

    public ScheduleController delegateRecyclerView(RecyclerView recyclerView) {
        mRecyclerView = recyclerView;
        return this;
    }

    public ScheduleController delegateAdapter(RVAdapterSchedule adapterSchedule) {
        mAdapterSchedule = adapterSchedule;
        return this;
    }

    public ScheduleController(Activity activity) {
        super(activity);
        mApi = retrofit.create(ScheduleService.class);
    }

    public void getSchedules(String userID) {
        Call<List<Schedule>> call = mApi.getSchedules(userID);
        call.enqueue(this);
    }

    @Override
    public void onResponse(Call<List<Schedule>> call, Response<List<Schedule>> response) {
        if (response.isSuccessful()) {
            Log.e("SUCCESS", response.body().toString());
            List<Schedule> schedules = response.body();

            if (mAdapterSchedule != null) {
                mAdapterSchedule.setFilter(schedules);
            }

        } else {

        }
    }

    @Override
    public void onFailure(Call<List<Schedule>> call, Throwable t) {
        Log.e("FAIL", "FAIL");
    }
}
