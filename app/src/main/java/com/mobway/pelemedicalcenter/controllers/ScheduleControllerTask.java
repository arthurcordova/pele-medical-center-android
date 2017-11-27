package com.mobway.pelemedicalcenter.controllers;

import android.content.Context;
import android.database.sqlite.SQLiteConstraintException;
import android.os.AsyncTask;
import android.util.Log;

import com.mobway.pelemedicalcenter.adapters.RVAdapterSchedule;
import com.mobway.pelemedicalcenter.dao.ScheduleDao;
import com.mobway.pelemedicalcenter.database.AppDatabase;
import com.mobway.pelemedicalcenter.database.AppDatabaseBuilder;
import com.mobway.pelemedicalcenter.models.Schedule;
import com.mobway.pelemedicalcenter.utils.TaskDao;

import java.util.List;

/**
 * Created by acstapassoli on 06/10/17.
 */

public class ScheduleControllerTask extends AsyncTask<Schedule, String, List<Schedule>> implements TaskDao {

    private static final String TAG = ScheduleControllerTask.class.getSimpleName();

    private AppDatabase mAppDatabase;
    private ScheduleDao mDao;
    private int mTask;
    private RVAdapterSchedule mAdapterSchedule;

    public ScheduleControllerTask(Context context) {
        mAppDatabase = AppDatabaseBuilder.getInstance(context);
//        mDao = mAppDatabase.scheduleDao();
    }

    public ScheduleControllerTask delegateTask(int task) {
        mTask = task;
        return this;
    }

    public ScheduleControllerTask delegateAdapter(RVAdapterSchedule adapterSchedule) {
        mAdapterSchedule = adapterSchedule;
        return this;
    }

    @Override
    protected List<Schedule> doInBackground(Schedule... schedules) {
        switch (mTask) {
            case TASK_FIND_ALL:
                return mDao.find();
            case TASK_SAVE:
                try {
                    mDao.insert(schedules);
                } catch (SQLiteConstraintException e) {
                    Log.e(TAG, "Constraint exception");
                }
                return mDao.find();
            default:
                return null;
        }
    }

    @Override
    protected void onPostExecute(List<Schedule> patients) {
        if (patients != null) {
            if (patients.size() > 0) {
                if (mAdapterSchedule != null) {
                    mAdapterSchedule.setFilter(patients);
                }
            }
        }
        cancel(true);

    }
}

