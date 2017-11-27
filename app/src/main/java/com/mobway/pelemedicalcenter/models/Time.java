package com.mobway.pelemedicalcenter.models;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by acstapassoli on 24/11/17.
 */

public class Time implements Serializable {

    @SerializedName("codSala")
    private String codeRoom;
    @SerializedName("horarioInicial")
    private String hour;
    @SerializedName("intervaloMinutos")
    private String interval;
    private boolean selected = false;

    public Time(String hour) {
        this.hour = hour;
    }

    public String getHour() {
        if (hour == null) {
            return null;
        }
        String form = hour.substring(0,5);
        return form;
    }

    public void setHour(String hour) {
        this.hour = hour;
    }

    public boolean isSelected() {
        return selected;
    }

    public void setSelected(boolean selected) {
        this.selected = selected;
    }
}
