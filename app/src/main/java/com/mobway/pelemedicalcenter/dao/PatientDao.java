package com.mobway.pelemedicalcenter.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import com.mobway.pelemedicalcenter.models.Patient;

import java.util.List;

/**
 * Created by acstapassoli on 05/10/17.
 */
@Dao
public interface PatientDao {

    @Query("SELECT * FROM patient")
    List<Patient> find();

    @Insert
    void insert(Patient... patients);

    @Delete
    void delete(Patient banner);

    @Query("DELETE FROM patient")
    void delete();
}
