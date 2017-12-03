package com.mobway.pelemedicalcenter.services;

import com.mobway.pelemedicalcenter.models.Schedule;
import com.mobway.pelemedicalcenter.models.ScheduleRequest;
import com.mobway.pelemedicalcenter.models.ScheduleResponse;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

/**
 * Created by acstapassoli on 10/11/17.
 */

public interface ScheduleService {

    @POST("agenda/doagendamento")
    Call<ScheduleResponse> postSchedule(@Body ScheduleRequest body);

    @GET("agenda/getagendamentos/{id}")
    Call<List<Schedule>> getSchedules(@Path("id") String id);

}
