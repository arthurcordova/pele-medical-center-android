package com.mobway.pelemedicalcenter.preferences;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;

import com.mobway.pelemedicalcenter.models.Patient;

/**
 * Created by arthurcordova on 7/5/16.
 */
public final class SessionManager {

    private SharedPreferences preferences;
    private SharedPreferences.Editor editor;
    private Context context;

    private final String KEY_ID = "uuid";
    private final String KEY_NAME = "name";
    private final String KEY_EMAIL = "email";

    public SessionManager(Context context) {
        this.context = context;
        preferences = context.getSharedPreferences("user_session", context.MODE_PRIVATE);
        editor = preferences.edit();
    }

    public void createSessionLogin(Patient patient) {
        editor.putString(KEY_ID, patient.getUuid());
        editor.putString(KEY_NAME, patient.getName());
        editor.putString(KEY_EMAIL, patient.getEmail());
        editor.commit();
    }

    public void destroySessionLogin(Class redirect) {
        editor.clear();
        editor.commit();
        if (redirect != null) {
            redirectToTarget(redirect);
        }
    }

    public void checkLogin(Class targetTrue, Class targetFalse) {
        if (isLoggedIn()) {
            redirectToTarget(targetTrue);
        } else {
            redirectToTarget(targetFalse);
        }
    }

    public void redirectToTarget(Class clazz) {
        if (clazz != null) {
            Intent i = new Intent(context, clazz);
            i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
//            i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//            i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
            context.startActivity(i);
            ((AppCompatActivity) context).finish();
        }
    }

    private boolean isLoggedIn() {
        return preferences.getString(KEY_ID, null) != null;
    }

    public Patient getSessionUser() {
        if (isLoggedIn()) {
            Patient user = new Patient();
            user.setUuid(preferences.getString(KEY_ID, ""));
            user.setEmail(preferences.getString(KEY_EMAIL, null));
            user.setName(preferences.getString(KEY_NAME, null));
            return user;
        }
        return null;
    }
}
