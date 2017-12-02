package com.mobway.pelemedicalcenter.models;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.util.Log;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by acstapassoli on 23/11/17.
 */

public class Schedule implements Serializable {

    @SerializedName("codeAgenda")
    private String uuid;
    private Patient patient;
    private Physician physician;
    private Time timeInfo;
    private Consult type;
    @SerializedName("data")
    private String date;
    @SerializedName("horario")
    private String time;
    private String status;
    private String payment;

    private String codProcedimento;
    private String descProcedimento;
    private String medico;
    private String especialidade;

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
        if (time != null) {
            return time.substring(0,5);
        }
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

    public String getCodProcedimento() {
        return codProcedimento;
    }

    public void setCodProcedimento(String codProcedimento) {
        this.codProcedimento = codProcedimento;
    }

    public String getDescProcedimento() {
        return descProcedimento;
    }

    public void setDescProcedimento(String descProcedimento) {
        this.descProcedimento = descProcedimento;
    }

    public String getMedico() {
        return medico;
    }

    public void setMedico(String medico) {
        this.medico = medico;
    }

    public String getEspecialidade() {
        return especialidade;
    }

    public void setEspecialidade(String especialidade) {
        this.especialidade = especialidade;
    }

    public String getDay(){
        return convertStringToDate(0);
    }

    public String getMonthName() {
        return convertStringToDate(1);
    }

    public String getWeekName() {
        return convertStringToDate(2);
    }

    private String convertStringToDate(int fieldToReturn) {
        if (date != null) {
            SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");

            try {
                Date dt = format.parse(date);

                switch (fieldToReturn) {
                    case 0 :
                        SimpleDateFormat formatDay = new SimpleDateFormat("dd");
                        return formatDay.format(dt);

                    case 1 :
                        SimpleDateFormat formatMonth = new SimpleDateFormat("MMM");
                        return formatMonth.format(dt);

                    case 2 :
                        SimpleDateFormat formatWeek = new SimpleDateFormat("EEE");
                        return formatWeek.format(dt);
                }


            } catch (ParseException e) {
                return null;
            }
        }
        return null;
    }
}
