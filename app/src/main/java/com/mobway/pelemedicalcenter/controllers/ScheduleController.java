package com.mobway.pelemedicalcenter.controllers;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.mobway.pelemedicalcenter.MainActivity;
import com.mobway.pelemedicalcenter.PaymentActivity;
import com.mobway.pelemedicalcenter.adapters.RVAdapterSchedule;
import com.mobway.pelemedicalcenter.models.Schedule;
import com.mobway.pelemedicalcenter.models.ScheduleRequest;
import com.mobway.pelemedicalcenter.models.ScheduleResponse;
import com.mobway.pelemedicalcenter.models.Specialty;
import com.mobway.pelemedicalcenter.services.ScheduleService;
import com.mobway.pelemedicalcenter.services.SpecialtyService;
import com.mobway.pelemedicalcenter.utils.MobwayDialog;
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

    public void postSchedule(ScheduleRequest request) {
        Call<ScheduleResponse> call = mApi.postSchedule(request);
        call.enqueue(new Callback<ScheduleResponse>() {
            @Override
            public void onResponse(Call<ScheduleResponse> call, Response<ScheduleResponse> response) {
                if (response.isSuccessful()) {
                    Log.e("SUCCESS", response.body().toString());
                    ScheduleResponse reponse = response.body();

                    final AlertDialog.Builder b =
                            new AlertDialog.Builder(activity);
                    b.setTitle("Agendamento");
                    b.setMessage(reponse.getMensagem());
                    b.setPositiveButton("Fechar", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            MobwayDialog.TAB_SCHEDULE = true;
                            Intent intent = new Intent(activity, MainActivity.class);
                            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP); // this will clear all the stack
                            activity.startActivity(intent);
                            activity.finish();
                        }
                    });
//                    b.setNegativeButton("Não", null);
                    b.show();


                } else {

                }
            }

            @Override
            public void onFailure(Call<ScheduleResponse> call, Throwable t) {

            }
        });
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
