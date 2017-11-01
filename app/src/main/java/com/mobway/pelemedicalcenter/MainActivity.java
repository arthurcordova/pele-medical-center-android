package com.mobway.pelemedicalcenter;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.widget.TextView;

import com.mobway.pelemedicalcenter.utils.BottomNavUtils;

public class MainActivity extends AppCompatActivity {

    private TextView mTextMessage;

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_doctor:
                    mTextMessage.setText(R.string.title_nav_doctors);
                    return true;
                case R.id.navigation_schedule:
                    mTextMessage.setText(R.string.title_nav_schedules);
                    return true;
                case R.id.navigation_notifications:
                    mTextMessage.setText(R.string.title_nav_notifications);
                    return true;
                case R.id.navigation_my_data:
                    mTextMessage.setText(R.string.title_nav_my_data);
                    return true;

            }
            return false;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mTextMessage = findViewById(R.id.message);
        BottomNavigationView navigation = findViewById(R.id.navigation);
        BottomNavUtils.disableShiftMode(navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
    }

}
