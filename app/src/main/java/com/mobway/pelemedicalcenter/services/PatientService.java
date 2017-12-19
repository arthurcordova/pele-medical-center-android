package com.mobway.pelemedicalcenter.services;

import com.mobway.pelemedicalcenter.models.Patient;
import com.mobway.pelemedicalcenter.models.UserRequest;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

/**
 * Created by arthur.stapassoli on 05/12/2017.
 */

public interface PatientService {

    @POST("paciente/login")
    Call<Patient> login(@Body UserRequest body);

    @POST("paciente/create")
    Call<Patient> create(@Body UserRequest body);

    @GET("paciente/getdependents/{parentID}")
    Call<List<Patient>> dependents(@Path("parentID") String parentID);

}
