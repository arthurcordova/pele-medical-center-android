package com.mobway.pelemedicalcenter.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import com.mobway.pelemedicalcenter.models.Patient;
import com.mobway.pelemedicalcenter.models.Schedule;

import java.util.List;

/**
 * Created by acstapassoli on 05/10/17.
 */
@Dao
public interface ScheduleDao {

    @Query("SELECT * FROM schedule")
    List<Schedule> find();

    @Insert
    void insert(Schedule... schedules);

    @Delete
    void delete(Schedule schedule);

    @Query("DELETE FROM schedule")
    void delete();
}
