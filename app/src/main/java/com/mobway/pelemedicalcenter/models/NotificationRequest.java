package com.mobway.pelemedicalcenter.models;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by acstapassoli on 30/01/18.
 */

public class NotificationRequest implements Serializable {

    @SerializedName("codCliente")
    public Integer clientID;
    public String status;

    public NotificationRequest(Integer clientID, String status) {
        this.clientID = clientID;
        this.status = status;
    }

    public NotificationRequest() {
    }
}
