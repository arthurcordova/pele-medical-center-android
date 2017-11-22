package com.mobway.pelemedicalcenter.controllers;

import android.app.Activity;
import android.util.Log;

import com.mobway.pelemedicalcenter.adapters.RVAdapterPatient;
import com.mobway.pelemedicalcenter.models.Patient;
import com.mobway.pelemedicalcenter.models.Physician;
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

public class PatientController extends Controller {

    private PhysicianService api = null;
    private RVAdapterPatient adapterPatient;
    private PatientControllerTask mPatientControllerTask;

    public PatientController(Activity activity) {
        super(activity);
        mPatientControllerTask = new PatientControllerTask(activity);
//        api = retrofit.create(PhysicianService.class);
    }

    public void savePatient(Patient... patients) {
        mPatientControllerTask.delegateTask(TaskDao.TASK_SAVE).execute(patients);
    }

    public void getPatients(RVAdapterPatient adapterPatient){
        this.adapterPatient = adapterPatient;

        mPatientControllerTask.delegateTask(TaskDao.TASK_FIND_ALL).execute();
    }


//    @Override
//    public void onResponse(Call<List<Physician>> call, Response<List<Physician>> response) {
//        Log.e("SUCCESS", response.body().toString());
//        if (response.isSuccessful()) {
//            List<Physician> banners = response.body();
//
//        } else {
//
//        }
//    }
//
//    @Override
//    public void onFailure(Call<List<Physician>> call, Throwable t) {
//
//        Log.e("FAIL", "FAIL");
//    }
}
