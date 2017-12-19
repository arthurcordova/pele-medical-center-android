package com.mobway.pelemedicalcenter;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;

import com.mobway.pelemedicalcenter.controllers.PatientController;
import com.mobway.pelemedicalcenter.models.Patient;
import com.mobway.pelemedicalcenter.models.UserRequest;
import com.mobway.pelemedicalcenter.preferences.SessionManager;

public class PatientRecordActivity extends AppCompatActivity {

    private PatientController mController;
    private EditText mInputName;
    private EditText mInputEmail;
//    private EditText mInputBirth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_patient_record);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Cadastro de dependentes");

        mInputName = findViewById(R.id.input_name);
        mInputEmail = findViewById(R.id.input_email);
//        mInputBirth = findViewById(R.id.input_birth);

        final SessionManager sm = new SessionManager(this);

        findViewById(R.id.button_save).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (checkField(mInputName) && checkField(mInputEmail)) {
                    Patient userMaster = sm.getSessionUser();
                    UserRequest patient = new UserRequest(mInputName.getText().toString(), mInputEmail.getText().toString(), null, userMaster.getUuid());

                    mController = new PatientController(PatientRecordActivity.this);
                    mController.create(patient);
                    finish();
                }
            }
        });

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
        }
        return super.onOptionsItemSelected(item);
    }

    private boolean checkField(EditText input) {
        if (input.getText().toString().equals("")) {
            input.setError("Campo obrigatório!");
            return false;
        }
        return true;
    }

}
