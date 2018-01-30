package com.mobway.pelemedicalcenter.controllers;

import android.app.Activity;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;

import com.mobway.pelemedicalcenter.adapters.RVAdapterNotification;
import com.mobway.pelemedicalcenter.adapters.RVAdapterTime;
import com.mobway.pelemedicalcenter.models.NoticationData;
import com.mobway.pelemedicalcenter.models.NotificationRequest;
import com.mobway.pelemedicalcenter.models.NotificationResponse;
import com.mobway.pelemedicalcenter.models.Time;
import com.mobway.pelemedicalcenter.services.NotificationService;
import com.mobway.pelemedicalcenter.services.TimeService;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by acstapassoli on 10/11/17.
 */

public class NotificationController extends Controller implements Callback<List<NotificationResponse>> {

    private NotificationService mApi;
    private RVAdapterNotification mAdapter;
    private NoticationData mData;

    public NotificationController delegateAdapter(RVAdapterNotification adapter) {
        mAdapter = adapter;
        return this;
    }

    public NotificationController delegateDataSource(NoticationData data) {
        mData = data;
        return this;
    }

    public NotificationController(Activity activity) {
        super(activity);
        mApi = retrofit.create(NotificationService.class);
    }

    public void getNotifications() {
        showProgress();
        Call<List<NotificationResponse>> call = mApi.notifications(new NotificationRequest(20591,"T"));
        call.enqueue(this);
    }

    @Override
    public void onResponse(Call<List<NotificationResponse>> call, Response<List<NotificationResponse>> response) {
        hideProgress();
        if (response.isSuccessful()) {
            Log.e("SUCCESS", response.body().toString());
            List<NotificationResponse> notifications = response.body();

//            mData.notification.set(notifications);
//            mData.notification.notifyChange();

            if (mAdapter != null) {
                mAdapter.setFilter(notifications);
            }

        } else {

        }
    }

    @Override
    public void onFailure(Call<List<NotificationResponse>> call, Throwable t) {
        hideProgress();
        Log.e("FAIL", "FAIL");
    }
}
