package com.mobway.pelemedicalcenter.database;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;

import com.mobway.pelemedicalcenter.dao.PatientDao;
import com.mobway.pelemedicalcenter.models.Patient;

/**
 * Created by acstapassoli on 05/10/17.
 */

@Database(entities = {Patient.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {

    public abstract PatientDao patientDao();

}
