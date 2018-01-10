package com.mobway.pelemedicalcenter.services;

import com.mobway.pelemedicalcenter.models.Physician;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Created by acstapassoli on 10/11/17.
 */

public interface PhysicianService {

    @GET("medicos/getmedicos/{filial}")
    Call<List<Physician>> physicians(@Path("filial") Integer filial);

}
