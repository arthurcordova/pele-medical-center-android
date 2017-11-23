package com.mobway.pelemedicalcenter;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.EditText;

import com.mobway.pelemedicalcenter.controllers.PatientController;
import com.mobway.pelemedicalcenter.models.Patient;

public class PatientRecordActivity extends AppCompatActivity {

    private PatientController mController;
    private EditText mInputName;
    private EditText mInputGender;
    private EditText mInputBirth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_patient_record);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Cadastro de paciente");

        mInputName = findViewById(R.id.input_name);
        mInputGender = findViewById(R.id.input_sexo);
        mInputBirth = findViewById(R.id.input_birth);

        findViewById(R.id.button_save).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Patient patient = new Patient();
                patient.setName(mInputName.getText().toString());
                patient.setGender(mInputGender.getText().toString());
                patient.setBirth(mInputBirth.getText().toString());

                mController = new PatientController(PatientRecordActivity.this);
                mController.savePatient(patient);
                finish();
            }
        });

    }

}
