package com.mobway.pelemedicalcenter.controllers;

import android.app.Activity;
import android.content.Intent;
import android.support.design.widget.Snackbar;
import android.util.Log;

import com.mobway.pelemedicalcenter.StartActivity;
import com.mobway.pelemedicalcenter.adapters.RVAdapterPatient;
import com.mobway.pelemedicalcenter.models.Patient;
import com.mobway.pelemedicalcenter.models.UserRequest;
import com.mobway.pelemedicalcenter.preferences.SessionManager;
import com.mobway.pelemedicalcenter.services.PatientService;
import com.mobway.pelemedicalcenter.utils.TaskDao;

import java.util.ArrayList;
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
    private UserRequest mUserRequest;
    private boolean isDependent = false;

    public PatientController(Activity activity) {
        super(activity);
        mPatientControllerTask = new PatientControllerTask(activity);
        mApi = retrofit.create(PatientService.class);
    }

    public void savePatient(Patient... patients) {
        mPatientControllerTask.delegateTask(TaskDao.TASK_SAVE).execute(patients);
    }

    public void create(UserRequest user) {
        showProgress();
        mUserRequest = user;
        isDependent =  user.parentCode != null;
        Call<Patient> call = mApi.create(user);
        call.enqueue(this);
    }

    public void login(UserRequest user) {
        showProgress();
        mUserRequest = user;
        Call<Patient> call = mApi.login(user);
        call.enqueue(this);
    }

    public void getDependents(final Patient master, final RVAdapterPatient adapterPatient){
        showProgress();
        Call<List<Patient>> call = mApi.dependents(String.valueOf(master.getUuid()));
        call.enqueue(new Callback<List<Patient>>() {
            @Override
            public void onResponse(Call<List<Patient>> call, Response<List<Patient>> response) {
                hideProgress();
                if (response.isSuccessful()) {
                    if (response.body() != null) {
                        Log.d(TAG, "RESPONSE: " + response.body().toString());
                        List<Patient> patients = new ArrayList<>();
                        patients.add(master);
                        patients.addAll(response.body());
                        if (adapterPatient != null) {
                            adapterPatient.setFilter(patients);
                        }
                    }
                }
            }

            @Override
            public void onFailure(Call<List<Patient>> call, Throwable t) {
                hideProgress();
                Log.d(TAG, "FAIL CONNECTION");
            }
        });
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
        hideProgress();
        if (response.isSuccessful()) {
            if (response.body() != null) {
                Log.d(TAG, "RESPONSE: " + response.body().toString());
                if (!isDependent) {
                    Patient patient = response.body();
                    patient.setEmail(mUserRequest.email);

                    SessionManager sm = new SessionManager(activity);
                    sm.createSessionLogin(patient);

                    Intent intent = new Intent(activity, StartActivity.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    activity.startActivity(intent);
                }
            } else {
                Snackbar.make(activity.getWindow().getDecorView(), "Usuário o senha inválido. Por favor tente novamente.", Snackbar.LENGTH_LONG).show();
            }
        }
    }

    @Override
    public void onFailure(Call<Patient> call, Throwable t) {
        hideProgress();
        Log.d(TAG, "FAIL CONNECTION");
    }
}
