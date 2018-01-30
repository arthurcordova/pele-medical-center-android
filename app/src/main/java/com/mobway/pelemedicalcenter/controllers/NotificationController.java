package com.mobway.pelemedicalcenter.controllers;

import android.app.Activity;
import android.content.Context;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;

import com.mobway.pelemedicalcenter.adapters.RVAdapterNotification;
import com.mobway.pelemedicalcenter.adapters.RVAdapterTime;
import com.mobway.pelemedicalcenter.models.NoticationData;
import com.mobway.pelemedicalcenter.models.NotificationRequest;
import com.mobway.pelemedicalcenter.models.NotificationResponse;
import com.mobway.pelemedicalcenter.models.Patient;
import com.mobway.pelemedicalcenter.models.Time;
import com.mobway.pelemedicalcenter.models.TokenRequest;
import com.mobway.pelemedicalcenter.models.TokenResponse;
import com.mobway.pelemedicalcenter.models.UserRequest;
import com.mobway.pelemedicalcenter.preferences.SessionManager;
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

    /**
     * For background task
     */
    public NotificationController() {
        super();
        mApi = retrofit.create(NotificationService.class);
    }

    public void getNotifications() {
        showProgress();
        Call<List<NotificationResponse>> call = mApi.notifications(new NotificationRequest(20591, "T"));
        call.enqueue(this);
    }

    public void setToken(Context context, final String token) {
        SessionManager sessionManager = new SessionManager(context);
        Patient user = sessionManager.getSessionUser();
        if (user != null) {
            Call<TokenResponse> call = mApi.setNotificationToken(new TokenRequest(Integer.parseInt(user.getUuid()), token));
            call.enqueue(new Callback<TokenResponse>() {
                @Override
                public void onResponse(Call<TokenResponse> call, Response<TokenResponse> response) {
                    Log.d("TOKEN", "TOKEN GRAVADO RETORNOU");
                    if (response.isSuccessful()) {
                        TokenResponse tokenResponse = response.body();
                        if (tokenResponse != null) {
                            Log.d("TOKEN", "TOKEN GRAVADO COM SUCESSO --------- "+token+" --------- " + tokenResponse.message);
                        } else {
                            Log.d("TOKEN", "TOKEN GRAVADO COM ERRO --------- ");
                        }

                    } else {
                        Log.d("TOKEN", "TOKEN COM ERRO");
                    }
                }

                @Override
                public void onFailure(Call<TokenResponse> call, Throwable t) {
                    Log.d("TOKEN", "FALHA AO GRAVAR O TOKEN");
                }
            });
        } else {
            Log.d("TOKEN", "USUÁRIO NULL");
        }
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
