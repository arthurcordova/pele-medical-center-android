package com.mobway.pelemedicalcenter.models;

import com.google.gson.annotations.SerializedName;

/**
 * Created by acstapassoli on 08/01/18.
 */

public class ClinicResponse {

    @SerializedName("codFilial")
    private Integer id;

    @SerializedName("descricao")
    private String name;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
