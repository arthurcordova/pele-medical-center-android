package com.mobway.pelemedicalcenter.controllers;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.mobway.pelemedicalcenter.LoginActivity;
import com.mobway.pelemedicalcenter.MainActivity;
import com.mobway.pelemedicalcenter.PaymentActivity;
import com.mobway.pelemedicalcenter.adapters.RVAdapterSchedule;
import com.mobway.pelemedicalcenter.models.RecoverResponse;
import com.mobway.pelemedicalcenter.models.Schedule;
import com.mobway.pelemedicalcenter.models.ScheduleRequest;
import com.mobway.pelemedicalcenter.models.ScheduleResponse;
import com.mobway.pelemedicalcenter.services.RecoverService;
import com.mobway.pelemedicalcenter.services.ScheduleService;
import com.mobway.pelemedicalcenter.utils.MobwayDialog;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by acstapassoli on 10/11/17.
 */

public class RecoverController extends Controller implements Callback<RecoverResponse> {

    private RecoverService mApi;

    public RecoverController(Activity activity) {
        super(activity);
        mApi = retrofit.create(RecoverService.class);
    }

    public void recoverPassword(String email) {
        Call<RecoverResponse> call = mApi.recoverPassword(email);
        call.enqueue(this);
    }

    @Override
    public void onResponse(Call<RecoverResponse> call, Response<RecoverResponse> response) {
        if (response.isSuccessful()) {
            Log.e("SUCCESS", response.body().toString());
            RecoverResponse responde = response.body();

            final AlertDialog.Builder b =
                    new AlertDialog.Builder(activity);
            b.setTitle("Recuperação de senha");
            b.setMessage(responde.message);
            b.setPositiveButton("Fechar", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    ((LoginActivity)activity).mButtonClose.performClick();
                }
            });
            b.setNegativeButton("", null);
            b.show();




        } else {

        }
    }

    @Override
    public void onFailure(Call<RecoverResponse> call, Throwable t) {
        Log.e("FAIL", "FAIL");
    }
}
