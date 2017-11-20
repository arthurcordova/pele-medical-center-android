package com.mobway.pelemedicalcenter;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.mobway.pelemedicalcenter.controllers.PhysicianController;
import com.mobway.pelemedicalcenter.fragments.DoctorFragment;
import com.mobway.pelemedicalcenter.fragments.FilterFragment;
import com.mobway.pelemedicalcenter.fragments.NotificationsFragment;
import com.mobway.pelemedicalcenter.fragments.ProfileFragment;
import com.mobway.pelemedicalcenter.fragments.ScheduleFragment;
import com.mobway.pelemedicalcenter.services.PhysicianService;
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
//                case R.id.navigation_notifications:
//                    replaceFragment(new NotificationsFragment());
//                    return true;
//                case R.id.navigation_my_data:
//                    replaceFragment(new ProfileFragment());
//                    return true;
            }
            return false;
        }
    };

    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        BottomNavigationView navigation = findViewById(R.id.navigation);
        BottomNavUtils.disableShiftMode(navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        mAuth = FirebaseAuth.getInstance();

        mAuth.signInWithEmailAndPassword("arthur.stapassoli@gmail.com", "123456")
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.d("FIREBASE", "signInWithEmail:success");
                            FirebaseUser user = mAuth.getCurrentUser();
                            Log.d("TOKEN", "TOKEN:"+ user.zzEI());
                            Log.d("TOKEN", "REFRESH:"+ user.zzEH());

                        } else {
                            // If sign in fails, display a message to the user.
                            Log.w("FIREBASE", "signInWithEmail:failure", task.getException());

                        }

                    }
                });

        replaceFragment(new DoctorFragment());

        findViewById(R.id.button_filter).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent it = new Intent(getBaseContext(), FilterActivity.class);
                startActivity(it);
            }
        });

    }

    @Override
    protected void onStart() {
        super.onStart();

        FirebaseUser currentUser = mAuth.getCurrentUser();
//        new PhysicianController(this).getPhysicians();


    }

    protected Fragment replaceFragment(Fragment fragment) {
        getSupportFragmentManager().beginTransaction().replace(R.id.frame_container, fragment).commit();
        return  fragment;
    }

    public String getStringTest() {
        return "MUhaha";
    }

}
