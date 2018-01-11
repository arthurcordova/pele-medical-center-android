package com.mobway.pelemedicalcenter.utils;

import android.content.Context;
import android.content.SharedPreferences;

import com.mobway.pelemedicalcenter.models.Consult;
import com.mobway.pelemedicalcenter.models.Filter;

import static android.content.Context.MODE_PRIVATE;


/**
 * Created by arthurcordova on 7/5/16.
 */
public final class FilterManager {

    private SharedPreferences preferences;
    private SharedPreferences.Editor editor;
    private Context context;

    private final String PREF_NAME = "filters_manager";

    private final String KEY_PLACE = "place";
    private final String KEY_PLACE_ID = "place_id";
    private final String KEY_CLINIC = "clinic";
    private final String KEY_CLINIC_ID = "clinic_id";
    private final String KEY_EMERGENCY = "emergency";
    private final String KEY_INSURANCE = "insurance";
    private final String KEY_INSURANCE_ID = "insurance_id";
    private final String KEY_PRIVATE = "private";
    private final String KEY_SPECIALTIES = "specialties";
    private final String KEY_SPECIALTY_ID = "specialty_id";
    private final String KEY_CONSULT_TYPE = "consult_type";
    private final String KEY_CONSULT_TYPE_ID = "consult_type_id";

    public FilterManager(Context context) {
        this.context = context;
        preferences = context.getSharedPreferences(PREF_NAME, MODE_PRIVATE);
        editor = preferences.edit();
    }

    public void save(Filter filter) {
        if (filter.getPlaceID() != null) {
            editor.putInt(KEY_PLACE_ID, filter.getPlaceID());
        }
        if (filter.getPlace() != null) {
            editor.putString(KEY_PLACE, filter.getPlace());
        }
        if (filter.getEmergency() != null) {
            editor.putBoolean(KEY_EMERGENCY, filter.getEmergency());
        }
        if (filter.getClinic() != null) {
            editor.putString(KEY_CLINIC, filter.getClinic());
        }
        if (filter.getClinicID() != null) {
            editor.putInt(KEY_CLINIC_ID, filter.getClinicID());
        }
        if (filter.getInsurance() != null) {
            editor.putString(KEY_INSURANCE, filter.getInsurance());
        }
        if (filter.getInsuranceID() != null) {
            editor.putInt(KEY_INSURANCE_ID, filter.getInsuranceID());
        }
        if (filter.getSpecialties() != null) {
            editor.putString(KEY_SPECIALTIES, filter.convertSpecialtiesToSave(filter.getSpecialties()));
        }
        if (filter.getSpecialtyID() != null) {
            editor.putInt(KEY_SPECIALTY_ID, filter.getSpecialtyID());
        }
        if (filter.getConsult() != null) {
            editor.putString(KEY_CONSULT_TYPE, filter.getConsult().getDescription());
        }
        if (filter.getConsult() != null) {
            editor.putString(KEY_CONSULT_TYPE_ID, filter.getConsult().getUuid());
        }
        editor.commit();
    }

    public void clear() {
        editor.clear();
        editor.commit();
    }

    public Filter getFilters() {
        Filter filter = new Filter();
        filter.setPlaceID(preferences.getInt(KEY_PLACE_ID, 0));
        filter.setPlace(preferences.getString(KEY_PLACE,""));
        filter.setClinic(preferences.getString(KEY_CLINIC,""));
        filter.setClinicID(preferences.getInt(KEY_CLINIC_ID, 0));
        filter.setInsurance(preferences.getString(KEY_INSURANCE,""));
        filter.setInsuranceID(preferences.getInt(KEY_INSURANCE_ID,0));
        filter.setEmergency(preferences.getBoolean(KEY_EMERGENCY, false));
        filter.setSpecialtyID(preferences.getInt(KEY_SPECIALTY_ID, 0));
//        filter.setPrivateSchedule(preferences.getBoolean(KEY_PRIVATE, false));
        filter.setSpecialties(filter.convertSpecialtiesToList(preferences.getString(KEY_SPECIALTIES, "")));
        filter.setConsult(new Consult(preferences.getString(KEY_CONSULT_TYPE_ID,""), preferences.getString(KEY_CONSULT_TYPE,"")));
        return filter;
    }
}
