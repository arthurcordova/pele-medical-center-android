package com.mobway.pelemedicalcenter.models;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;

import com.google.gson.annotations.SerializedName;
import com.mobway.pelemedicalcenter.R;

import java.io.Serializable;

/**
 * Created by arthur.stapassoli on 28/11/2017.
 */

public class Insurance implements Serializable {

    @SerializedName("id")
    private String uuid;
    @SerializedName("codFilial")
    private String branch;
    @SerializedName("descricao")
    private String description;
    @SerializedName("autorizacao")
    private Boolean authorization;

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public String getBranch() {
        return branch;
    }

    public void setBranch(String branch) {
        this.branch = branch;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Boolean getAuthorization() {
        return authorization;
    }

    public void setAuthorization(Boolean authorization) {
        this.authorization = authorization;
    }



}
