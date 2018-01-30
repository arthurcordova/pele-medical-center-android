package com.mobway.pelemedicalcenter.models;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by acstapassoli on 30/01/18.
 */

public class NotificationResponse implements Serializable {

    @SerializedName("id")
    public Integer uuid;
    @SerializedName("titulo")
    public String title;
    @SerializedName("mensagem")
    public String message;
    @SerializedName("data")
    public String date;
    @SerializedName("status")
    public String status;
    @SerializedName("dataVizualizacao")
    public String dateView;
    @SerializedName("codCliente")
    public Integer clientID;
    @SerializedName("codFilial")
    public Integer filial;
    @SerializedName("idMsgCliente")
    public Integer messageIDClient;

    public NotificationResponse(Integer uuid, String title, String message, String date, String status, String dateView, Integer clientID, Integer filial, Integer messageIDClient) {
        this.uuid = uuid;
        this.title = title;
        this.message = message;
        this.date = date;
        this.status = status;
        this.dateView = dateView;
        this.clientID = clientID;
        this.filial = filial;
        this.messageIDClient = messageIDClient;
    }

    public NotificationResponse() {
    }
}
