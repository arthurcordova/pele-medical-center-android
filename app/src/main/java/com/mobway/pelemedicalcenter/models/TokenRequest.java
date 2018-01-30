package com.mobway.pelemedicalcenter.models;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by acstapassoli on 30/01/18.
 */

public class TokenRequest implements Serializable{

    @SerializedName("codCliente")
    public Integer clientID;
    public String token;
    /**
     * A - Android
     */
    @SerializedName("tipoDispositivo")
    public String deviceType = "A";

    public TokenRequest(Integer clientID, String token) {
        this.clientID = clientID;
        this.token = token;
    }

    public TokenRequest() {
    }
}
