package com.mobway.pelemedicalcenter.services;

import com.mobway.pelemedicalcenter.models.Physician;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Created by acstapassoli on 10/11/17.
 */

public interface TimeService {

    @GET("/agenda/horarios/{company}/{date}/{physician)")
    Call<List<Physician>> physicians(@Path("company") String company, @Path("date") String date, @Path("physician") String physician);

}
