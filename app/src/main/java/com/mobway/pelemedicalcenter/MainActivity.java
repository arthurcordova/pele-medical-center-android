package com.mobway.pelemedicalcenter;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;

import com.mobway.pelemedicalcenter.fragments.DoctorFragment;
import com.mobway.pelemedicalcenter.fragments.NotificationsFragment;
import com.mobway.pelemedicalcenter.fragments.ProfileFragment;
import com.mobway.pelemedicalcenter.fragments.ScheduleFragment;
import com.mobway.pelemedicalcenter.utils.BottomNavUtils;

public class MainActivity extends AppCompatActivity {

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_doctor:
                    replaceFragment(new DoctorFragment());
                    return true;
                case R.id.navigation_schedule:
                    replaceFragment(new ScheduleFragment());
                    return true;
                case R.id.navigation_notifications:
                    replaceFragment(new NotificationsFragment());
                    return true;
                case R.id.navigation_my_data:
                    replaceFragment(new ProfileFragment());
                    return true;
            }
            return false;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        BottomNavigationView navigation = findViewById(R.id.navigation);
        BottomNavUtils.disableShiftMode(navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
    }

    protected void replaceFragment(Fragment fragment) {
        getSupportFragmentManager().beginTransaction().replace(R.id.frame_container, fragment).commit();
    }
}
