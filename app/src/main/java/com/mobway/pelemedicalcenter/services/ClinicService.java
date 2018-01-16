package com.mobway.pelemedicalcenter.services;

import com.mobway.pelemedicalcenter.models.ClinicRequest;
import com.mobway.pelemedicalcenter.models.ClinicResponse;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

/**
 * Created by acstapassoli on 10/11/17.
 */
public interface ClinicService {

    @POST("general/getclinics")
    Call<List<ClinicResponse>> clinics(@Body ClinicRequest clinicRequest);

}
