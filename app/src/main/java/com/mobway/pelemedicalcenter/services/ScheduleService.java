package com.mobway.pelemedicalcenter.services;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

/**
 * Created by acstapassoli on 10/11/17.
 */

public interface ScheduleService {

    @POST("agenda/doagendamento")
    Call<ScheduleResponse> postSchedule(@Body ScheduleRequest body);

//    @GET
//    Call<List<>> insurances(@Url String url);

}
