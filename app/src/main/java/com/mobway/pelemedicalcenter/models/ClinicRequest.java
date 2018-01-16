package com.mobway.pelemedicalcenter.models;

import com.google.gson.annotations.SerializedName;

/**
 * Created by AMcom on 16/01/2018.
 */

public class ClinicRequest {

    @SerializedName("codFilial")
    private Integer placeID;
    @SerializedName("codEspecialidade")
    private Integer specialtyID;

    public ClinicRequest(Integer placeID, Integer specialtyID) {
        this.placeID = placeID;
        this.specialtyID = specialtyID;
    }

    public Integer getPlaceID() {
        return placeID;
    }

    public void setPlaceID(Integer placeID) {
        this.placeID = placeID;
    }

    public Integer getSpecialtyID() {
        return specialtyID;
    }

    public void setSpecialtyID(Integer specialtyID) {
        this.specialtyID = specialtyID;
    }
}
