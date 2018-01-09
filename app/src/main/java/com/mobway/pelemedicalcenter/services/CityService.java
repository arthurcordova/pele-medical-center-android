package com.mobway.pelemedicalcenter.services;

import com.mobway.pelemedicalcenter.models.CityResponse;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by acstapassoli on 10/11/17.
 */

public interface CityService {

    @GET("general/getcities")
    Call<List<CityResponse>> cities();

}
