package com.mobway.pelemedicalcenter.services;

import com.mobway.pelemedicalcenter.models.ArriveOrderResponse;
import com.mobway.pelemedicalcenter.models.Time;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Url;

/**
 * Created by acstapassoli on 10/11/17.
 */

public interface ArriveOrderService {

    @GET("queue/getqueue/1/{date}/{room}")
    Call<ArriveOrderResponse> vacancies(@Path("date") String date, @Path("room") String room);

}
