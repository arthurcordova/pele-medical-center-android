package com.mobway.pelemedicalcenter.services;

import com.mobway.pelemedicalcenter.models.Patient;
import com.mobway.pelemedicalcenter.models.UserRequest;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

/**
 * Created by arthur.stapassoli on 05/12/2017.
 */

public interface PatientService {

    @POST("paciente/login")
    Call<Patient> login(@Body UserRequest body);

    @POST("paciente/create")
    Call<Patient> create(@Body UserRequest body);

}
