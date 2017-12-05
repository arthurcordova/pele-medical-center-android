package com.mobway.pelemedicalcenter;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.mobway.pelemedicalcenter.models.Patient;
import com.mobway.pelemedicalcenter.preferences.SessionManager;

import java.util.Timer;
import java.util.TimerTask;

public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        final SessionManager sm = new SessionManager(this);

        new Timer().schedule(new TimerTask() {
            @Override
            public void run() {
                Patient user = sm.getSessionUser();
                Intent intent = new Intent();
                if (user != null) {
                    intent.setClass(SplashActivity.this, StartActivity.class);
                } else {
                    intent.setClass(SplashActivity.this, LoginActivity.class);
                }
                startActivity(intent);
                overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
                finish();
            }
        }, 3000);
    }

}
