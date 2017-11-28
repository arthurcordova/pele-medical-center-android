package com.mobway.pelemedicalcenter.controllers;

import android.app.Activity;
import android.content.Context;

import com.mobway.pelemedicalcenter.adapters.RVAdapterSchedule;
import com.mobway.pelemedicalcenter.models.Schedule;
import com.mobway.pelemedicalcenter.utils.TaskDao;

/**
 * Created by acstapassoli on 10/11/17.
 */

public class ScheduleController {

    private RVAdapterSchedule adapterSchedule;
    private ScheduleControllerTask mScheduleControllerTask;

    public ScheduleController(Context context) {
        mScheduleControllerTask = new ScheduleControllerTask(context);
    }

    public void saveSchedule(Schedule... schedules) {
        mScheduleControllerTask.delegateTask(TaskDao.TASK_SAVE).execute(schedules);
    }

    public void getPatients(RVAdapterSchedule adapterSchedule){
        this.adapterSchedule = adapterSchedule;
        boolean isCancelled = mScheduleControllerTask.isCancelled();

        mScheduleControllerTask.delegateTask(TaskDao.TASK_FIND_ALL).delegateAdapter(adapterSchedule).execute();
    }

}
