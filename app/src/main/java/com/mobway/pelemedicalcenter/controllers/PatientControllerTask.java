package com.mobway.pelemedicalcenter.controllers;

import android.content.Context;
import android.database.sqlite.SQLiteConstraintException;
import android.os.AsyncTask;
import android.util.Log;

import com.mobway.pelemedicalcenter.adapters.RVAdapterPatient;
import com.mobway.pelemedicalcenter.dao.PatientDao;
import com.mobway.pelemedicalcenter.database.AppDatabase;
import com.mobway.pelemedicalcenter.database.AppDatabaseBuilder;
import com.mobway.pelemedicalcenter.models.Patient;
import com.mobway.pelemedicalcenter.utils.TaskDao;

import java.util.List;

/**
 * Created by acstapassoli on 06/10/17.
 */

public class PatientControllerTask extends AsyncTask<Patient, String, List<Patient>> implements TaskDao {

    private static final String TAG = PatientControllerTask.class.getSimpleName();

    private AppDatabase mAppDatabase;
    private PatientDao mDao;
    private int mTask;
    private RVAdapterPatient mAdapterPatient;

    public PatientControllerTask(Context context) {
        mAppDatabase = AppDatabaseBuilder.getInstance(context);
        mDao = mAppDatabase.patientDao();
    }

    public PatientControllerTask delegateTask(int task) {
        mTask = task;
        return this;
    }

    public PatientControllerTask delegateAdapter(RVAdapterPatient adapterPatient) {
        mAdapterPatient = adapterPatient;
        return this;
    }

    @Override
    protected List<Patient> doInBackground(Patient... patients) {
        switch (mTask) {
            case TASK_FIND_ALL:
                return mDao.find();
            case TASK_SAVE:
                try {
                    mDao.insert(patients);
                } catch (SQLiteConstraintException e) {
                    Log.e(TAG, "Constraint exception");
                }
                return mDao.find();
            default:
                return null;
        }
    }

    @Override
    protected void onPostExecute(List<Patient> patients) {
        if (patients != null) {
            if (patients.size() > 0) {
                if (mAdapterPatient != null) {
                    mAdapterPatient.setFilter(patients);
                }
            }
        }
    }
}

