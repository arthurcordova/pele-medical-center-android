package com.mobway.pelemedicalcenter.services;

import com.mobway.pelemedicalcenter.models.Consult;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

/**
 * Created by acstapassoli on 10/11/17.
 */

public interface ConsultService {

    @GET("procedimentos/getprocedimentos/{filial}")
    Call<List<Consult>> typeConsults(@Path("filial") Integer filial);


}
