package com.mobway.pelemedicalcenter.models;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

/**
 * Created by acstapassoli on 25/11/17.
 */

public class Specialty implements Serializable {
    @SerializedName("id")
    private String uuid;
    @SerializedName("descricao")
    private String name;
    private boolean selected = false;

    public Specialty(String uuid, String name) {
        this.uuid = uuid;
        this.name = name;
    }

    public Specialty() {

    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isSelected() {
        return selected;
    }

    public void setSelected(boolean selected) {
        this.selected = selected;
    }

    public void mergeWithSaved(List<Specialty> list) {
        if (list != null) {
            for (Specialty specialty : list) {
                if (specialty.getUuid().equals(uuid)) {
                    selected = true;
                }
            }
        }

    }
}
