package com.mobway.pelemedicalcenter;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.mobway.pelemedicalcenter.adapters.RVAdapterPatient;
import com.mobway.pelemedicalcenter.controllers.PatientController;
import com.mobway.pelemedicalcenter.models.Patient;

import java.util.ArrayList;
import java.util.List;

public class PatientListActivity extends AppCompatActivity {

    private RecyclerView mRecyclerView;
    private RVAdapterPatient mRvAdapterPatient;
    private PatientController mController;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_patient_list);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Paciente");

        mRvAdapterPatient = new RVAdapterPatient(new ArrayList<Patient>());

        mRecyclerView = findViewById(R.id.recycler_view_patients);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView.setAdapter(mRvAdapterPatient);

        findViewById(R.id.fab_add_patient).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent it = new Intent(getBaseContext(), PatientRecordActivity.class);
                startActivity(it);
            }
        });

    }

    @Override
    protected void onResume() {
        super.onResume();

        mController = new PatientController(this);
        mController.getPatients(mRvAdapterPatient);

    }
}
