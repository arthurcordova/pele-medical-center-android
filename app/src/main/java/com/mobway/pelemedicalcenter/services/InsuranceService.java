package com.mobway.pelemedicalcenter.services;

import com.mobway.pelemedicalcenter.models.Insurance;
import com.mobway.pelemedicalcenter.models.Physician;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by acstapassoli on 10/11/17.
 */

public interface InsuranceService {

    @GET("insurance/getinsurance/1")
    Call<List<Insurance>> insurances();

}
