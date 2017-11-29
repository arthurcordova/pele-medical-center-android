package com.mobway.pelemedicalcenter.models;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

import java.io.Serializable;

/**
 * Created by acstapassoli on 23/11/17.
 */

public class Schedule implements Serializable {

    private String uuid;
    private Patient patient;
    private Physician physician;
    private Time timeInfo;
    private Consult type;
    private String date;
    private String time;
    private String status;
    private String payment;

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public Patient getPatient() {
        return patient;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }

    public Physician getPhysician() {
        return physician;
    }

    public void setPhysician(Physician physician) {
        this.physician = physician;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getPayment() {
        return payment;
    }

    public void setPayment(String payment) {
        this.payment = payment;
    }

    public Time getTimeInfo() {
        return timeInfo;
    }

    public void setTimeInfo(Time timeInfo) {
        this.timeInfo = timeInfo;
    }

    public Consult getType() {
        return type;
    }

    public void setType(Consult type) {
        this.type = type;
    }
}
