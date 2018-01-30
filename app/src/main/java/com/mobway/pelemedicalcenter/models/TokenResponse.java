package com.mobway.pelemedicalcenter.models;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by acstapassoli on 30/01/18.
 */

public class TokenResponse implements Serializable {

    @SerializedName("codigo")
    public Integer uuid;
    @SerializedName("mensagem")
    public String message;

    public TokenResponse(Integer uuid, String message) {
        this.uuid = uuid;
        this.message = message;
    }

    public TokenResponse() {
    }
}
