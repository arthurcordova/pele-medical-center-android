package com.mobway.pelemedicalcenter.services;

import com.mobway.pelemedicalcenter.models.Insurance;
import com.mobway.pelemedicalcenter.models.Physician;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Created by acstapassoli on 10/11/17.
 */

public interface InsuranceService {

    @GET("insurance/getinsurance/{filial}")
    Call<List<Insurance>> insurances(@Path("filial") Integer filial);

}
