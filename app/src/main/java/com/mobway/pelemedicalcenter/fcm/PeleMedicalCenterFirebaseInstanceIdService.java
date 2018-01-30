package com.mobway.pelemedicalcenter.fcm;

import android.content.Context;
import android.util.Log;

import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.FirebaseInstanceIdService;
import com.mobway.pelemedicalcenter.controllers.NotificationController;

/**
 * Created by acstapassoli on 08/10/17.
 */

public class PeleMedicalCenterFirebaseInstanceIdService extends FirebaseInstanceIdService {

    public static final String TAG = "FCM_TOKEN";
    private Context context;

    public PeleMedicalCenterFirebaseInstanceIdService(Context context) {
        this.context = context;
    }

    public PeleMedicalCenterFirebaseInstanceIdService(){}

    @Override
    public void onTokenRefresh() {
        String token = FirebaseInstanceId.getInstance().getToken();
        Log.d(TAG, token);

        if (context != null) {
            NotificationController notificationController = new NotificationController();
            notificationController.setToken(context, token);
        }
    }

}
