package com.mobway.pelemedicalcenter;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.mobway.pelemedicalcenter.adapters.RVAdapterPatient;
import com.mobway.pelemedicalcenter.controllers.PatientController;
import com.mobway.pelemedicalcenter.models.Patient;
import com.mobway.pelemedicalcenter.models.Schedule;
import com.mobway.pelemedicalcenter.preferences.SessionManager;
import com.mobway.pelemedicalcenter.utils.MobwayDialog;

import java.util.ArrayList;
import java.util.List;

public class PatientListActivity extends AppCompatActivity {

    private RecyclerView mRecyclerView;
    private RVAdapterPatient mRvAdapterPatient;
    private PatientController mController;
    private Button mButtonNext;

    private Schedule mSchedule;
    private String mErrorMsg;
    private String mErrorTitle = "Oops, encontramos um erro!";
    private Patient mUserMaster;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_patient_list);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Quem será o paciente?");

        mSchedule = (Schedule) getIntent().getSerializableExtra("schedule");

        SessionManager sessionManager = new SessionManager(this);
        mUserMaster = sessionManager.getSessionUser();

        List<Patient> patients = new ArrayList<>();
//        patients.add(mUserMaster);

        mRvAdapterPatient = new RVAdapterPatient(patients);

        mRecyclerView = findViewById(R.id.recycler_view_patients);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView.setAdapter(mRvAdapterPatient);

        mButtonNext = findViewById(R.id.button_next);

        findViewById(R.id.fab_add_patient).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent it = new Intent(getBaseContext(), PatientRecordActivity.class);
                startActivity(it);
            }
        });

        mButtonNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!validateStep()) {
                    MobwayDialog.show(view.getContext(), mErrorMsg, mErrorTitle);
                    return;
                }
                mSchedule.setPatient(mRvAdapterPatient.getSelectedPatient());

                Intent it;
                if (mSchedule.getType().getDescription().equalsIgnoreCase("CONSULTA PARTICULAR")) {
                    it = new Intent(getBaseContext(), PaymentActivity.class);
                    it.putExtra("schedule", mSchedule);
                    startActivity(it);
                } else {
                    MobwayDialog.finishScheduleDialog(PatientListActivity.this, mSchedule);
                }

            }
        });

    }

    @Override
    protected void onResume() {
        mController = new PatientController(this);
        mController.getDependents(mUserMaster, mRvAdapterPatient);

        super.onResume();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
        }
        return super.onOptionsItemSelected(item);
    }

    public boolean validateStep() {
        if (mRvAdapterPatient.getSelectedPatient() == null) {
            mErrorMsg = "Antes de avançar, por favor selecione um paciente.";
            return false;
        }
        return true;
    }
}
