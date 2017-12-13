package com.mobway.pelemedicalcenter.models;

import java.io.Serializable;

/**
 * Created by arthur.stapassoli on 13/12/2017.
 */

public class TimerOrderArrive implements Serializable {

    public Integer code;
    // 1 - manha 2 - tarde
    private Integer round;
    private String roundName;

    public TimerOrderArrive(Integer code) {
        this.code = code;
    }

    public TimerOrderArrive(Integer code, Integer round) {
        this.code = code;
        this.round = round;
    }

    public TimerOrderArrive(Integer code, Integer round, String roundName) {
        this.code = code;
        this.round = round;
        this.roundName = roundName;
    }
}
