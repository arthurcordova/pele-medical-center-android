package com.mobway.pelemedicalcenter.controllers;

import android.app.Activity;
import android.content.Intent;
import android.support.design.widget.Snackbar;
import android.util.Log;

import com.mobway.pelemedicalcenter.StartActivity;
import com.mobway.pelemedicalcenter.adapters.RVAdapterPatient;
import com.mobway.pelemedicalcenter.models.Patient;
import com.mobway.pelemedicalcenter.models.Physician;
import com.mobway.pelemedicalcenter.models.UserRequest;
import com.mobway.pelemedicalcenter.preferences.SessionManager;
import com.mobway.pelemedicalcenter.services.PatientService;
import com.mobway.pelemedicalcenter.services.PhysicianService;
import com.mobway.pelemedicalcenter.utils.TaskDao;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by acstapassoli on 10/11/17.
 */

public class PatientController extends Controller implements Callback<Patient> {

    private static final String TAG = PatientController.class.getName();
    private PatientService mApi = null;
    private RVAdapterPatient adapterPatient;
    private PatientControllerTask mPatientControllerTask;

    public PatientController(Activity activity) {
        super(activity);
        mPatientControllerTask = new PatientControllerTask(activity);
        mApi = retrofit.create(PatientService.class);
    }

    public void savePatient(Patient... patients) {
        mPatientControllerTask.delegateTask(TaskDao.TASK_SAVE).execute(patients);
    }

    public void create(UserRequest user) {
        Call<Patient> call = mApi.create(user);
        call.enqueue(this);
    }

    public void login(UserRequest user) {
        Call<Patient> call = mApi.login(user);
        call.enqueue(this);
    }

    /**
     * From local database
     *
     * @param adapterPatient
     */
    public void getPatients(RVAdapterPatient adapterPatient) {
        this.adapterPatient = adapterPatient;
        boolean isCancelled = mPatientControllerTask.isCancelled();
        mPatientControllerTask.delegateTask(TaskDao.TASK_FIND_ALL).delegateAdapter(adapterPatient).execute();
    }

    @Override
    public void onResponse(Call<Patient> call, Response<Patient> response) {
        if (response.isSuccessful()) {
            if (response.body() != null) {
                Log.d(TAG, "RESPONSE: " + response.body().toString());
                Patient patient = response.body();

                SessionManager sm = new SessionManager(activity);
                sm.createSessionLogin(patient);

                Intent intent = new Intent(activity, StartActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                activity.startActivity(intent);
            } else {
                Snackbar.make(activity.getWindow().getDecorView(), "Usuário o senha inválido. Por favor tente novamente.", Snackbar.LENGTH_LONG).show();
            }
        }
    }

    @Override
    public void onFailure(Call<Patient> call, Throwable t) {
        Log.d(TAG, "FAIL CONNECTION");
    }
}
