package com.mobway.pelemedicalcenter.models;

import com.google.gson.annotations.SerializedName;

/**
 * Created by AMcom on 10/01/2018.
 */

public class DoctorFilterRequest {

    @SerializedName("codCidade")
    private Integer cityCode;
    @SerializedName("codClinica")
    private Integer clinicCode;
    @SerializedName("codCEspecialidade")
    private Integer specialtyCode;
    @SerializedName("codConsulta")
    private Integer typeCode;
    @SerializedName("codConvenio")
    private Integer insuranceCode;

    public  DoctorFilterRequest(){}

    public DoctorFilterRequest(Integer cityCode, Integer clinicCode, Integer specialtyCode, Integer typeCode, Integer insuranceCode) {
        this.cityCode = cityCode;
        this.clinicCode = clinicCode;
        this.specialtyCode = specialtyCode;
        this.typeCode = typeCode;
        this.insuranceCode = insuranceCode;
    }

    public Integer getCityCode() {
        return cityCode;
    }

    public void setCityCode(Integer cityCode) {
        this.cityCode = cityCode;
    }

    public Integer getClinicCode() {
        return clinicCode;
    }

    public void setClinicCode(Integer clinicCode) {
        this.clinicCode = clinicCode;
    }

    public Integer getSpecialtyCode() {
        return specialtyCode;
    }

    public void setSpecialtyCode(Integer specialtyCode) {
        this.specialtyCode = specialtyCode;
    }

    public Integer getTypeCode() {
        return typeCode;
    }

    public void setTypeCode(Integer typeCode) {
        this.typeCode = typeCode;
    }

    public Integer getInsuranceCode() {
        return insuranceCode;
    }

    public void setInsuranceCode(Integer insuranceCode) {
        this.insuranceCode = insuranceCode;
    }
}
