package com.mobway.pelemedicalcenter.services;

import com.mobway.pelemedicalcenter.models.ClinicResponse;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Created by acstapassoli on 10/11/17.
 */
public interface ClinicService {

    @GET("general/getclinics/{code}")
    Call<List<ClinicResponse>> clinics(@Path("code") Integer code);

}
