package com.mobway.pelemedicalcenter.models;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by arthur.stapassoli on 01/11/2017.
 */

public class Physician implements Serializable {

    @SerializedName("codfunc")
    private String uuid;
    @SerializedName("nome")
    private String name;
    @SerializedName("sexo")
    private String gender;
    @SerializedName("especialidade")
    private String specialty;
    @SerializedName("idEspecialidade")
    private String specialtyID;
    @SerializedName("clinica")
    private String place;
    @SerializedName("aceitaPlano")
    private Boolean acceptInsurance;

    private String avatar;
    private String crm;

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

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getSpecialty() {
        return specialty;
    }

    public void setSpecialty(String specialty) {
        this.specialty = specialty;
    }

    public String getSpecialtyID() {
        return specialtyID;
    }

    public void setSpecialtyID(String specialtyID) {
        this.specialtyID = specialtyID;
    }

    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
    }

    public Boolean getAcceptInsurance() {
        return acceptInsurance;
    }

    public void setAcceptInsurance(Boolean acceptInsurance) {
        this.acceptInsurance = acceptInsurance;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getCrm() {
        return crm;
    }

    public void setCrm(String crm) {
        this.crm = crm;
    }
}
