package com.mobway.pelemedicalcenter;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.mobway.pelemedicalcenter.controllers.PatientController;
import com.mobway.pelemedicalcenter.models.Patient;

import java.util.ArrayList;
import java.util.List;

public class PatientListActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_patient_list);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Paciente");

        List<Patient> list = new ArrayList<>();
        Patient p1 = new Patient();
        p1.setUuid(11);
        p1.setName("Joaquim Boni Stapassoli");
        p1.setGender("Masculino");
        list.add(p1);

        p1 = new Patient();
        p1.setUuid(22);
        p1.setName("Aline Boni Stapassoli");
        p1.setGender("Masculino");
        list.add(p1);

        Patient[] patients = list.toArray(new Patient[list.size()]);

        PatientController controller = new PatientController(this);
        controller.savePatient(patients);
//        controller.getPatients(null);
    }

}
