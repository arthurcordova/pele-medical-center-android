package com.mobway.pelemedicalcenter.models;

import java.io.Serializable;

/**
 * Created by acstapassoli on 24/11/17.
 */

public class Time implements Serializable {

    private String hour;
    private boolean selected = false;

    public Time(String hour) {
        this.hour = hour;
    }

    public String getHour() {
        return hour;
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
