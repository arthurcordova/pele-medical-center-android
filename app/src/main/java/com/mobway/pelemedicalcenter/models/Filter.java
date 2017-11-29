package com.mobway.pelemedicalcenter.models;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by acstapassoli on 23/11/17.
 */

public class Filter implements Serializable {

    private String clinic;
    private String insurance;
    private String place;
    private Boolean emergency;
    private Boolean privateSchedule;
    private List<Specialty> specialties;

    public Filter() {
    }

    public Filter(String place, Boolean emergency, Boolean privateSchedule, List<Specialty> specialties) {
        this.place = place;
        this.emergency = emergency;
        this.privateSchedule = privateSchedule;
        this.specialties = specialties;
    }

    public String getClinic() {
        return clinic;
    }

    public void setClinic(String clinic) {
        this.clinic = clinic;
    }

    public String getInsurance() {
        return insurance;
    }

    public void setInsurance(String insurance) {
        this.insurance = insurance;
    }

    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
    }

    public Boolean getEmergency() {
        return emergency;
    }

    public void setEmergency(Boolean emergency) {
        this.emergency = emergency;
    }

    public Boolean getPrivateSchedule() {
        return privateSchedule;
    }

    public void setPrivateSchedule(Boolean privateSchedule) {
        this.privateSchedule = privateSchedule;
    }

    public List<Specialty> getSpecialties() {
        return specialties;
    }

    public void setSpecialties(List<Specialty> specialties) {
        this.specialties = specialties;
    }

    public String convertSpecialtiesToSave(List<Specialty> list) {
        String result = "";
        if (list != null) {
            for (Specialty specialty: list) {
                result = result + ";" +specialty.getUuid();
            }
        }
        return result;
    }

    public List<Specialty> convertSpecialtiesToList(String str) {
        if (!str.equals("")){
            String[] ids = str.split(";");
            List<Specialty> idsList = new ArrayList<>();
            for (int i = 0; i < ids.length ; i++) {
                if (!ids[i].equals("")){
                    idsList.add(new Specialty(ids[i], null));
                }
            }
            return idsList;
        }
        return null;
    }

}
