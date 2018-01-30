package com.mobway.pelemedicalcenter.services;

import com.mobway.pelemedicalcenter.models.NotificationRequest;
import com.mobway.pelemedicalcenter.models.NotificationResponse;
import com.mobway.pelemedicalcenter.models.Time;
import com.mobway.pelemedicalcenter.models.TokenRequest;
import com.mobway.pelemedicalcenter.models.TokenResponse;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Url;

/**
 * Created by acstapassoli on 10/11/17.
 */

public interface NotificationService {

    @POST("messages/getmessages")
    Call<List<NotificationResponse>> notifications(@Body NotificationRequest request);

    @POST("messages/settoken")
    Call<TokenResponse> setNotificationToken(@Body TokenRequest request);

    @POST("messages/setread/{messageID}")
    Call<TokenResponse> setAsRead(@Path("messageID") Integer messageID);

    @POST("messages/delete/{messageID}")
    Call<TokenResponse> delete(@Path("messageID") Integer messageID);

}
