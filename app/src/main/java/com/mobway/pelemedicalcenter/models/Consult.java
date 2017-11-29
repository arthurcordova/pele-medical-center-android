package com.mobway.pelemedicalcenter.models;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by arthur.stapassoli on 29/11/2017.
 */

public class Consult implements Serializable {

    @SerializedName("codProd")
    private String uuid;
    @SerializedName("descricao")
    private String description;

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
