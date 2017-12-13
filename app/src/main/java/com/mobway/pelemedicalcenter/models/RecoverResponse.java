package com.mobway.pelemedicalcenter.models;

import com.google.gson.annotations.SerializedName;

/**
 * Created by arthur.stapassoli on 13/12/2017.
 */

public class RecoverResponse {

    @SerializedName("codigo")
    public Integer id;
    @SerializedName("mensagem")
    public String message;

    public RecoverResponse() {
    }

    public RecoverResponse(Integer id, String message) {
        this.id = id;
        this.message = message;
    }
}
