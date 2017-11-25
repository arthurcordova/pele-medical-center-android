package com.mobway.pelemedicalcenter.services;

import com.mobway.pelemedicalcenter.models.Physician;
import com.mobway.pelemedicalcenter.models.Specialty;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by acstapassoli on 10/11/17.
 */

public interface SpecialtyService {

    @GET("especialidade/getespecialidades/0")
    Call<List<Specialty>> specialties();

}
