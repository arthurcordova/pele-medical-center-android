package com.mobway.pelemedicalcenter.models;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by acstapassoli on 23/11/17.
 */

public class Filter implements Serializable {

    private String place;
    private Boolean emergency;
    private Boolean privateSchedule;
    private List<String> specialties;

    public Filter() {
    }

    public Filter(String place, Boolean emergency, Boolean privateSchedule, List<String> specialties) {
        this.place = place;
        this.emergency = emergency;
        this.privateSchedule = privateSchedule;
        this.specialties = specialties;
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

    public List<String> getSpecialties() {
        return specialties;
    }

    public void setSpecialties(List<String> specialties) {
        this.specialties = specialties;
    }

    public String convertSpecialtiesToSave(List<String> list) {
        String result = "";
        if (list != null) {
            for (String str: list) {
                result.concat(";").concat(str);
            }
        }
        return result;
    }

    public List<String> convertSpecialtiesToList(String str) {
        if (!str.equals("")){
            String[] ids = str.split(";");
            List<String> idsList = new ArrayList<>();
            for (int i = 0; i < ids.length ; i++) {
                idsList.add(ids[i]);
            }
            return idsList;
        }
        return null;
    }
}
