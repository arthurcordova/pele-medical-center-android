package com.mobway.pelemedicalcenter.services;

import com.mobway.pelemedicalcenter.models.RecoverResponse;
import com.mobway.pelemedicalcenter.models.Time;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Url;

/**
 * Created by acstapassoli on 10/11/17.
 */

public interface RecoverService {

    @GET("paciente/passrecover/{email}")
    Call<RecoverResponse> recoverPassword(@Path("email") String email);

}
