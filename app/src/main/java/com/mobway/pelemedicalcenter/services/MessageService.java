package com.mobway.pelemedicalcenter.services;

import com.mobway.pelemedicalcenter.models.NotificationRequest;
import com.mobway.pelemedicalcenter.models.NotificationResponse;
import com.mobway.pelemedicalcenter.models.Time;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Url;

/**
 * Created by acstapassoli on 10/11/17.
 */

public interface MessageService {

    @POST("general/getclinics")
    Call<List<NotificationResponse>> notifications(@Body NotificationRequest request);

}
