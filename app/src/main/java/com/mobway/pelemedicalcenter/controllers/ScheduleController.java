package com.mobway.pelemedicalcenter.controllers;

import android.app.Activity;

import com.mobway.pelemedicalcenter.adapters.RVAdapterPatient;
import com.mobway.pelemedicalcenter.adapters.RVAdapterSchedule;
import com.mobway.pelemedicalcenter.models.Patient;
import com.mobway.pelemedicalcenter.models.Schedule;
import com.mobway.pelemedicalcenter.services.PhysicianService;
import com.mobway.pelemedicalcenter.utils.TaskDao;

/**
 * Created by acstapassoli on 10/11/17.
 */

public class ScheduleController extends Controller {

    private RVAdapterSchedule adapterSchedule;
    private ScheduleControllerTask mScheduleControllerTask;

    public ScheduleController(Activity activity) {
        super(activity);
        mScheduleControllerTask = new ScheduleControllerTask(activity);
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
